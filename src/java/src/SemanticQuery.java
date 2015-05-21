/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openrdf.model.Value;
import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

/**
 *
 * @author grid
 */
public class SemanticQuery{

   // public static String graph = "<http://RepositoryOntology_v2>";
    public static String graph = "<http://CHAIN-Reds_Test>";
    public static RepositoryConnection virtuosoConnection;
    
    
    
    
    public RepositoryConnection ConnectionToVirtuoso() throws RepositoryException {
        String endpointURL = "http://virtuoso.ct.infn.it:8890/sparql";
        // String endpointURL ="http://virtuoso.ct.infn.it:8896/chain-reds-kb/sparql";


        Repository myRepository = new HTTPRepository(endpointURL, "");

        myRepository.initialize();
        RepositoryConnection virtuosoConnection = myRepository.getConnection();

        System.out.println("Connessione a Virtuoso");
        
        return virtuosoConnection;
    }

    static public int testEndPoint() throws MalformedURLException {
        System.out.println("test endpoint=> http://virtuoso.ct.infn.it:8890/sparql");
        URL url = new URL("http://virtuoso.ct.infn.it:8890/sparql");
        HttpURLConnection http = null;
        int statusCode = 0;
        try {
            http = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CATCH1");
            statusCode = -1;
        }

        try {
            statusCode = http.getResponseCode();
        } catch (IOException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CATCH2");
            statusCode = -1;
        }
        System.out.println("STATUS CHAIN URL --->" + statusCode);

        return statusCode;
    }
    
    public ArrayList queryVirtuosoResource(String search_word, int numberRecords,int offset) throws MalformedURLException {
        ArrayList arrayVirtuosoResourceDupl = new ArrayList();
        ArrayList arrayVirtuosoResource = new ArrayList();

        int statusCode = testEndPoint();

        if (statusCode != -1) {
            try {
                virtuosoConnection = ConnectionToVirtuoso();

                String bif_word = " \"' " + search_word + " '\"";
                
               String word = "' " + search_word.toUpperCase() + " '";
               
               
               System.out.println("richiesta ricerca per la parola: "+word);
               
               
                // RepositoryConnection con = ConnectionToVirtuoso();

                //System.out.println("N page "+numPage);
               // int page = Integer.parseInt(numPage);



                //System.out.println("page "+page +" "+(page-1));

               // int numOffset = (page - 1) * numberRecords;
                //int numberFinal = numberRecords * page;


                String queryString = ""
                        + "SELECT distinct(?s)  FROM"  + graph + " WHERE {"
                        + "?s dc:title ?title."
                        + "?title bif:contains " + bif_word + "."
                        //+ " FILTER (CONTAINS ( UCASE(str(?title)), " + word + " ) )."
                        +" ?s <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#isResourceOf> ?rep." 
                        +" ?rep <http://www.semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#rank> ?rank. "
                        + "}ORDER BY ASC(?rank) limit " + numberRecords+" offset "+offset;
                //+ "FILTER regex(str(?desc), " + word + ", 'i')."

                System.out.println("QUERYING VIRTUOSO REPOSITORY......" + queryString);

                TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);



                TupleQueryResult result = tupleQuery.evaluate();


                //System.out.println("VIRTUOSO query has next? " + result.hasNext() + "   " + queryString);

                if (result.hasNext()) {
                    while (result.hasNext()) {


                        BindingSet bindingSet = result.next();
                        String resource = bindingSet.getValue("s").stringValue();
                        
                        arrayVirtuosoResourceDupl.add(resource);
                    }

                }

                arrayVirtuosoResource = getListNotDuplicate(arrayVirtuosoResourceDupl);
                // String [] pp= new String[3];
                //  new ArrayList(Arrays.asList(pp)); 
                //Collections.addAll(arrayVirtuosoResource, pp); 
                //con.close();
            } catch (QueryEvaluationException ex) {
                Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);

                arrayVirtuosoResource.add("Exception");

                System.out.println("CHAIN QueryEvaluationException");

            } catch (MalformedQueryException ex) {
                Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
                arrayVirtuosoResource.add("Exception");
                System.out.println(" CHAIN MalformedQueryException");

            } catch (RepositoryException ex) {
                Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
                arrayVirtuosoResource.add("Exception");
                System.out.println(" CHAIN RepositoryException");
            } finally {
                try {
                    virtuosoConnection.close();
                } catch (RepositoryException ex) {
                    Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return arrayVirtuosoResource;
    }

    
    


    public static  ArrayList getListNotDuplicate(ArrayList listOriginal) {
        
        ArrayList listNuova=new ArrayList();
        
        if (listOriginal.size() > 1) {
            int k=1;
            int j,i=0;
            boolean duplicato;
            listNuova.add(listOriginal.get(0));
            
            for (i = 1; i < listOriginal.size(); i++) {
                
                duplicato=false;
                
                for (j=0;j<i;j++){
                    
                    if (listOriginal.get(i).equals( listOriginal.get(j)) ) 
                    {
                        //System.out.println("duplicato: "+listOriginal.get(i));
                        duplicato=true;
                    }
                       
                }
                if(!duplicato)
                {
                    //System.out.println();
                    listNuova.add(listOriginal.get(i));
                   // listAuthor[k]=listAuthor[i];
                    //k++;
                }
                
                 
            }
            
//            newArrayAuthors=new String[listNuova.size()];
//            for (int z=0;z<listNuova.size();z++)
//            {
//                newArrayAuthors[z]=listNuova.get(z).toString();
//            }

            return listNuova;
        } else {
            return listOriginal;
        }
    }



    public String[] getInfoResource(String idResource) throws MalformedQueryException, RepositoryException, UnsupportedEncodingException {

        String elem[] = new String[14];
       
       
        
       // RepositoryConnection conn = null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        //String virtuosoURL = "http://192.168.39.138:8080/openrdf-sesame";
        try {

            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "RepositoryOntology");
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
//            HTTPRepository endpoint = getHttpRepository();
//            endpoint.initialize();
//
//            conn = endpoint.getConnection();
            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "SELECT  * FROM " + graph + "  WHERE {\n"
                    + "<" + idResource + "> myOnto:datestamp ?date.\n"
                    + "} ";
//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "SELECT  *  WHERE {\n"
//                    + "<" + idResource + "> myOnto:datestamp ?date.\n"
//                    + "}";
            
            //System.out.println("INFO RESOURCE "+queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            //tupleQuery.setMaxQueryTime(20);
            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();


                Value dateStamp = bindingSet.getValue("date");
                //Value author = bindingSet.getValue("author");

                


                //RESOURCE
                elem[0] = idResource;
                //TITLE
                elem[1] = "";
                ArrayList listTitles = getTitlesResource(idResource);
                for (int i = 0; i < listTitles.size(); i++) {
                    String title = (String) listTitles.get(i);
                    if (elem[1].equals("")) {
                        elem[1] = title;
                    } else {
                        elem[1] = elem[1] + "##" + title;
                    }
                }
                //AUTHORS
                elem[2] = "";
                ArrayList listAuthors = getAuthorsResource(idResource);
                for (int i = 0; i < listAuthors.size(); i++) {
                    String auth = (String) listAuthors.get(i);
                    auth = auth.replace(" ", "");
                    if (elem[2].equals("")) {
                        elem[2] = auth;
                    } else {
                        elem[2] = elem[2] + " ; " + auth;
                    }
                }
                //DATESTAMP
                elem[3] = dateStamp.stringValue();
                //DESCRIPTION
                elem[4] = "";
                ArrayList listDescriptions = getDescriptionsResource(idResource);
                for (int i = 0; i < listDescriptions.size(); i++) {
                    String desc = (String) listDescriptions.get(i);
                    if (elem[4].equals("")) {
                        elem[4] = desc;
                    } else {
                        elem[4] = elem[4] + "##" + desc;
                    }
                }
                //PUBLISHER
                elem[5] = "";
                ArrayList listPublishers = getPublishersResource(idResource);
                for (int i = 0; i < listPublishers.size(); i++) {
                    String publisher = (String) listPublishers.get(i);
                    if (elem[5].equals("")) {
                        elem[5] = publisher;
                    } else {
                        elem[5] = elem[5] + "##" + publisher;
                    }
                }
                //IDENTIFIER
                elem[6] = "";
                ArrayList listIdentifiers = getIdentifiersResource(idResource);
                for (int i = 0; i < listIdentifiers.size(); i++) {
                    String id = (String) listIdentifiers.get(i);
                    if (elem[6].equals("")) {
                        elem[6] = id;
                    } else {
                        elem[6] = elem[6] + "##" + id;
                    }
                }
                //System.out.println("DIM ID "+listIdentifiers.size());
                //SOURCE
                elem[7] = "";
                ArrayList listSources = getSourcesResource(idResource);
                for (int i = 0; i < listSources.size(); i++) {
                    String source = (String) listSources.get(i);
                    if (elem[7].equals("")) {
                        elem[7] = source;
                    } else {
                        elem[7] = elem[7] + "##" + source;
                    }
                }
                //System.out.println("DIM SOURCE  "+listSources.size());
                //SUBJECT
                elem[8] = "";
                ArrayList listSubjects = getSubjectsResource(idResource);
                for (int i = 0; i < listSubjects.size(); i++) {
                    String subject = (String) listSubjects.get(i);
                    if (elem[8].equals("")) {
                        elem[8] = subject;
                    } else {
                        elem[8] = elem[8] + "##" + subject;
                    }
                }
                //System.out.println("DIM SUBJECT  "+listSubjects.size());
                //LANGUAGE
                elem[9] = "";
                ArrayList listLanguage = getLanguagesResource(idResource);
                for (int i = 0; i < listLanguage.size(); i++) {
                    String language = (String) listLanguage.get(i);
                    if (elem[9].equals("")) {
                        elem[9] = language;
                    } else {
                        elem[9] = elem[9] + "##" + language;
                    }
                }
                //System.out.println("DIM LANGUAGE  "+listLanguage.size());
                //DATE
                elem[10] = "";
                ArrayList listDate = getDataResource(idResource);
                for (int i = 0; i < listDate.size(); i++) {
                    String data = (String) listDate.get(i);
                    if (elem[10].equals("")) {
                        elem[10] = data;
                    } else {
                        elem[10] = elem[10] + "##" + data;
                    }
                }
                //System.out.println("DIM DATE  "+listDate.size());
                //CONTRIBUTOR
                elem[11] = "";
                ArrayList listContributor = getContributorResource(idResource);
                for (int i = 0; i < listContributor.size(); i++) {
                    String contributor = (String) listContributor.get(i);
                    if (elem[11].equals("")) {
                        elem[11] = contributor;
                    } else {
                        elem[11] = elem[11] + "##" + contributor;
                    }
                }
                //System.out.println("DIM CONTRIBUTOR  "+listContributor.size());
                //COVERAGE
                elem[12] = "";
                ArrayList listCoverage = getCoverageSource(idResource);
                for (int i = 0; i < listCoverage.size(); i++) {
                    String coverage = (String) listCoverage.get(i);
                    if (elem[12].equals("")) {
                        elem[12] = coverage;
                    } else {
                        elem[12] = elem[12] + "##" + coverage;
                    }
                }
                //  System.out.println("DIM COVERAGE  "+listCoverage.size());
                //RIGHTS
                elem[13] = "";
                ArrayList listRight = getRightResource(idResource);
                for (int i = 0; i < listRight.size(); i++) {
                    String right = (String) listRight.get(i);
                    if (elem[13].equals("")) {
                        elem[13] = right;
                    } else {
                        elem[13] = elem[13] + "##" + right;
                    }
                }

               // System.out.println("DIM RIGHTS  "+listRight.size());



//                //INFO REPOSITORY
//                String[] infoRep = getInfoRepositoryFromResource(idResource, conn);
//                //nome
//                elem[7] = infoRep[0];
//                //numRecords
//                elem[8] = infoRep[1];
//                //url
//                elem[9] = infoRep[2];
//                //latitude
//                elem[10] = infoRep[3];
//                //longi
//                elem[11] = infoRep[4];
//
//                //RELATION
//                elem[12] = "";
//                ArrayList listRelations = getRelationsDataSet(idResource, conn);
//                for (int i = 0; i < listRelations.size(); i++) {
//                    String relation = (String) listRelations.get(i);
//                    if (elem[12].equals("")) {
//                        elem[12] = relation;
//                    } else {
//                        elem[12] = elem[12] + "##" + relation;
//                    }
//                }


            }


        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elem;
    }

    public ArrayList getTitlesResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listTitles = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
//                    + "SELECT * FROM " + graph + "   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:title ?title.\n"
//                    + "}";
           
            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
                    + "SELECT * FROM " + graph + "   WHERE {\n"
                    + "<" + sourcePaper + "> dc:title ?title.\n"
                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();

                Value title = bindingSet.getValue("title");

               String title1=title.stringValue().replaceAll("[\r\n]+", " ");
               String title2=replaceApici(title1);
                String titleFinale=new String(title2.getBytes("iso-8859-1"), "utf-8");
                //System.out.println("TITLE: " + title.stringValue());
                //System.out.format("%s",title);
                listTitles.add(titleFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        
       


        return listTitles;
    }
    
    
    public String replaceApici(String myString)
    {
        char [] chars=new char[]{'"','\''};
        
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < myString.length(); j++) {
                // input = input.replace(" ", "");
                if (myString.charAt(j) == chars[i]) {

                    //   System.out.println("contiene");

                    myString = myString.replace(myString.charAt(j), ' ');

                }
//                else
//                  System.out.println("NON contiene");  
            }
        }
        //  System.out.println("INPUT FINALE: " + input);
        return myString;
        
    }

    public ArrayList getDescriptionsResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listDescriptions = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
//                    + "SELECT * FROM " + graph + "  WHERE {\n"
//                    + "<" + sourcePaper + "> dc:description ?desc.\n"
//                    + "}";
            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
                    + "SELECT * FROM " + graph + "   WHERE {\n"
                    + "<" + sourcePaper + "> dc:description ?desc.\n"
                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value desc = bindingSet.getValue("desc");
                String desc1=desc.stringValue().replaceAll("[\r\n]+", " ");
                String desc2=replaceApici(desc1);
                String descFinale=new String(desc2.getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listDescriptions.add(descFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listDescriptions;
    }

    public static String controlsChars(String input) {


        char[] notValidChars = new char[]{'{', '}', '|', '\\', '^', '~', '[', ']', ','};

        for (int i = 0; i < notValidChars.length; i++) {
            for (int j = 0; j < input.length(); j++) {
                // input = input.replace(" ", "");
                if (input.charAt(j) == notValidChars[i]) {

                    //   System.out.println("contiene");

                    input = input.replace(input.charAt(j), '_');

                }
//                else
//                  System.out.println("NON contiene");  
            }
        }
        //  System.out.println("INPUT FINALE: " + input);
        return input;
    }
    
    public ArrayList getArraywithoutNumbers(ArrayList myList)
    {
        char[] notValidChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8','9'};

        for (int i = 0; i < notValidChars.length; i++) {
            for (int j = 0; j < myList.size(); j++) {
                String control=myList.get(j).toString();
                if (control.charAt(0) == notValidChars[i]) {

                    //   System.out.println("contiene");
                    myList.remove(j);
                    

                }
//                else
//                  System.out.println("NON contiene");  
            }
        }
        //  System.out.println("INPUT FINALE: " + input);
        return myList;
    }

    public ArrayList getSourcesResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listSources = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();
//
//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
//                    + "SELECT * FROM " + graph + "  WHERE {\n"
//                    + "<" + sourcePaper + "> dc:source ?source.\n"
//                    + "}";
            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
                    + "SELECT *  FROM " + graph + " WHERE {\n"
                    + "<" + sourcePaper + "> dc:source ?source.\n"
                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value source = bindingSet.getValue("source");
                String source1=source.stringValue().replaceAll("[\r\n]+", " ");
                String source2=replaceApici(source1);
                String sourceFinale=new String(source2.getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listSources.add(sourceFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listSources;
    }

    public ArrayList getAuthorsResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listAuthors = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
//                    + "SELECT * FROM " + graph + "  WHERE {\n"
//                    + "<" + sourcePaper + "> myOnto:hasAuthor ?author.\n"
//                    + "?author foaf:name ?name.\n"
//                    + "}";
            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT distinct ?name FROM " + graph + "     WHERE {\n"
                    + "<" + sourcePaper + "> myOnto:hasAuthor ?author.\n"
                    + "?author foaf:name ?name.\n"
                    + "}";

            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            String myName="";
            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value name = bindingSet.getValue("name");
                if(graph.equals("<http://CHAIN-Reds_Test>"))
                    myName=name.stringValue().replace(" ", "");
                else
                    myName=name.stringValue();
                String finalName=myName.replaceAll("[\r\n]+", " ");
                String finalName2=replaceApici(finalName);
                String nameFinale=new String(finalName2.getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listAuthors.add(nameFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }


        return listAuthors;
    }

    public ArrayList getIdentifiersResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listIdentifiers = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "   WHERE {\n"
                    + "<" + sourcePaper + "> dc:identifier ?id.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:identifier ?id.\n"
//                    + "}";

            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value identifier = bindingSet.getValue("id");

                String outIdentifiers=new String(identifier.stringValue().getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listIdentifiers.add(outIdentifiers);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listIdentifiers;
    }

    public ArrayList getPublishersResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listPublishers = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:publisher ?pub.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:publisher ?pub.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value publisher = bindingSet.getValue("pub");
                String publi1=publisher.stringValue().replaceAll("[\r\n]+", " ");
                String publi2=replaceApici(publi1);
                String publiFinale=new String(publi2.getBytes("iso-8859-1"), "utf-8");

                // System.out.println("PUBLISHER: " +publisher.stringValue() );
                listPublishers.add(publiFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listPublishers;
    }

    public ArrayList getSubjectsResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listSubject = new ArrayList();
   
        try {
            
            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:subject ?subject.\n"
                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value subject = bindingSet.getValue("subject");
                String sub1=subject.stringValue().replaceAll("[\r\n]+", " ");
                String sub2=replaceApici(sub1);
                String subFinale=new String(sub2.getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listSubject.add(subFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }


      //  System.out.println("DIM SUBJECT: "+listSubject.size());
        return listSubject;
    }

    public ArrayList getLanguagesResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listLanguage = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:language ?language.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:language ?language.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value language = bindingSet.getValue("language");

                String languageFinale=new String(language.stringValue().getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listLanguage.add(languageFinale);
                

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listLanguage;
    }

    public ArrayList getDataResource(String sourcePaper) {
        ArrayList listDate = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:date ?date.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:date ?date.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value date = bindingSet.getValue("date");



                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listDate.add(date.stringValue());

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listDate;
    }

    public ArrayList getContributorResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listContributor = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:contributor ?contributor.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:contributor ?contributor.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value contributor = bindingSet.getValue("contributor");
                String contributorFinale=new String(contributor.stringValue().getBytes("iso-8859-1"), "utf-8");


                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listContributor.add(contributorFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listContributor;
    }

    public ArrayList getCoverageSource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listCoverage = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:coverage ?coverage.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:coverage ?coverage.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value coverage = bindingSet.getValue("coverage");
                String coverageFinale=new String(coverage.stringValue().getBytes("iso-8859-1"), "utf-8");


                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listCoverage.add(coverageFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listCoverage;
    }

    public ArrayList getRightResource(String sourcePaper) throws UnsupportedEncodingException {
        ArrayList listRight = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc:rights ?rights.\n"
                    + "}";
//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourcePaper + "> dc:rights ?rights.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value rights = bindingSet.getValue("rights");
                String rightsFinale=new String(rights.stringValue().getBytes("iso-8859-1"), "utf-8");


                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listRight.add(rightsFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listRight;
    }

    public ArrayList getRelationsDataSet(String sourceDataSet) throws UnsupportedEncodingException {
        ArrayList listRelations = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourceDataSet + "> dc:relation ?relation.\n"
                    + "}";

//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourceDataSet + "> dc:relation ?relation.\n"
//                    + "}";
            //System.out.println("QUERY RELATION"+queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value relation = bindingSet.getValue("relation");
                String relationFinale=new String(relation.stringValue().getBytes("iso-8859-1"), "utf-8");


                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listRelations.add(relationFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listRelations;
    }

    public ArrayList getFormatDataSet(String sourceDataSet) throws UnsupportedEncodingException {
        ArrayList listFormats = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourceDataSet + "> dc:format ?format.\n"
                    + "}";

//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourceDataSet + "> dc:format ?format.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value format = bindingSet.getValue("format");
                String formatFinale=new String(format.stringValue().getBytes("iso-8859-1"), "utf-8");


                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listFormats.add(formatFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listFormats;
    }

    public ArrayList getTypeDataSet(String sourceDataSet) throws UnsupportedEncodingException {
        ArrayList listType = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourceDataSet + "> dc:type ?type.\n"
                    + "}";

//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourceDataSet + "> dc:type ?type.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value type = bindingSet.getValue("type");
                String typeFinale=new String(type.stringValue().getBytes("iso-8859-1"), "utf-8");


                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                listType.add(typeFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listType;
    }

    public ArrayList getIdentifierDataSet(String sourceDataSet) {
        ArrayList listIdentifier = new ArrayList();
        //RepositoryConnection conn=null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
            //virtuosoEndpoint.initialize();

            //conn = virtuosoEndpoint.getConnection();

            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + sourceDataSet + "> dc:identifier ?id.\n"
                    + "}";

//            String queryString = "prefix dc: <http://purl.org/dc/elements/1.1/>"
//                    + "SELECT *   WHERE {\n"
//                    + "<" + sourceDataSet + "> dc:identifier ?id.\n"
//                    + "}";
            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value id = bindingSet.getValue("id");



                //

                //System.out.println("ID_DATASET: " + id.stringValue());
                listIdentifier.add(id.stringValue());

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return listIdentifier;
    }

//    public String[] getInfoRepository(String source) {
//        String[] infoRep = new String[10];
//        RepositoryConnection conn = null;
//        //String virtuosoURL = "http://virtuoso:8890/sparql";
//        String virtuosoURL = "http://192.168.39.138:8080/openrdf-sesame";
//        try {
//            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
//            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "RepositoryOntology");
//            //virtuosoEndpoint.initialize();
//
//            //conn = virtuosoEndpoint.getConnection();
//            HTTPRepository endpoint = getHttpRepository();
//            endpoint.initialize();
//
//            conn = endpoint.getConnection();
//
//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
//                    + "SELECT * FROM " + graph + "  WHERE {\n"
//                    + "<" + source + "> myOnto:isResourceOf ?Rep.\n"
//                    + "?Rep myOnto:numberOfRecordsRepository ?numRec.\n"
//                    + "?Rep myOnto:nameRepository ?name.\n"
//                    + "?Rep myOnto:longitudeRepository ?longi.\n"
//                    + "?Rep myOnto:latitudeRepository ?lati.\n"
//                    + "?Rep myOnto:countryCodeRepository ?ccode.\n"
//                    + "?Rep myOnto:addressRepository ?address.\n"
//                    + "?Rep myOnto:urlRepository ?url.\n"
//                    + "?Rep myOnto:addressOAIPMHRepository ?oai.\n"
//                    + "?Rep myOnto:isRepositoryOf ?Org.\n"
//                    + "?Org myOnto:nameOrganization ?nameOrg.\n"
//                    + "}";
////            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
////                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
////                    + "SELECT *  WHERE {\n"
////                    + "<" + source + "> myOnto:isResourceOf ?Rep.\n"
////                    + "?Rep myOnto:numberOfRecordsRepository ?numRec.\n"
////                    + "?Rep myOnto:nameRepository ?name.\n"
////                    + "?Rep myOnto:longitudeRepository ?longi.\n"
////                    + "?Rep myOnto:latitudeRepository ?lati.\n"
////                    + "?Rep myOnto:countryCodeRepository ?ccode.\n"
////                    + "?Rep myOnto:addressRepository ?address.\n"
////                    + "?Rep myOnto:urlRepository ?url.\n"
////                    + "?Rep myOnto:addressOAIPMHRepository ?oai.\n"
////                    + "?Rep myOnto:isRepositoryOf ?Org.\n"
////                    + "?Org myOnto:nameOrganization ?nameOrg.\n"
////                    + "}";
//
//
//            //System.out.println(queryString);
//            TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
//
//            TupleQueryResult result = tupleQuery.evaluate();
//            while (result.hasNext()) {
//                BindingSet bindingSet = result.next();
//                Value idRep = bindingSet.getValue("Rep");
//                Value name = bindingSet.getValue("name");
//                Value numRec = bindingSet.getValue("numRec");
//                Value url = bindingSet.getValue("url");
//                Value longi = bindingSet.getValue("longi");
//                Value lati = bindingSet.getValue("lati");
//                Value ccode = bindingSet.getValue("ccode");
//                Value address = bindingSet.getValue("address");
//                Value oai = bindingSet.getValue("oai");
//                Value nameOrg = bindingSet.getValue("nameOrg");
//
//                infoRep[0] = idRep.stringValue();
//                infoRep[1] = name.stringValue();
//                infoRep[2] = numRec.stringValue();
//                infoRep[3] = url.stringValue();
//                infoRep[4] = longi.stringValue();
//                infoRep[5] = lati.stringValue();
//                infoRep[6] = ccode.stringValue();
//                infoRep[7] = address.stringValue();
//                infoRep[8] = oai.stringValue();
//                infoRep[9] = nameOrg.stringValue();
//
//
//            }
//
//        } catch (RepositoryException e) {
//            e.printStackTrace();
//        } catch (MalformedQueryException ex) {
//            Logger.getLogger(SemanticQueryMoreInfo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (QueryEvaluationException ex) {
//            Logger.getLogger(SemanticQueryMoreInfo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//
//        return infoRep;
//    }

    public String[] getDataSetFromResource(String source) throws UnsupportedEncodingException {
        String[] dataSet = new String[5];
       // RepositoryConnection conn = null;
        //String virtuosoURL = "http://virtuoso:8890/sparql";
        // String virtuosoURL = "http://192.168.39.138:8080/openrdf-sesame";
        try {
            //HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "");
//            HTTPRepository virtuosoEndpoint = new HTTPRepository(virtuosoURL, "RepositoryOntology");
//            virtuosoEndpoint.initialize();
//
//            conn = virtuosoEndpoint.getConnection();
            //HTTPRepository endpoint = getHttpRepository();
            //endpoint.initialize();

            //conn = endpoint.getConnection();
            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                    + "SELECT * FROM " + graph + "  WHERE {\n"
                    + "<" + source + "> myOnto:hasDataSet ?DataSet.\n"
                    + "}";
//            String queryString = "prefix myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
//                    + "prefix dc: <http://purl.org/dc/elements/1.1/>\n"
//                    + "SELECT *  WHERE {\n"
//                    + "<" + source + "> myOnto:hasDataSet ?DataSet.\n"
//                    + "}";


            //System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            //System.out.println("la query dataset ha result? "+result.hasNext());
            
            if(result.hasNext()){
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value idDataSet = bindingSet.getValue("DataSet");


                dataSet[0] = idDataSet.stringValue();
                //IDENTIFIER
                dataSet[1] = "";
                ArrayList listIdentifiers = getIdentifierDataSet(idDataSet.stringValue());
                //System.out.println("NUM IDENTIFIER: " + listIdentifiers.size());
                for (int i = 0; i < listIdentifiers.size(); i++) {
                    String id = (String) listIdentifiers.get(i);
                    if (dataSet[1].equals("")) {
                        dataSet[1] = id;
                    } else {
                        dataSet[1] = dataSet[1] + "##" + id;
                    }
                }

                //TYPE
                dataSet[2] = "";
                ArrayList listType = getTypeDataSet(idDataSet.stringValue());
                for (int i = 0; i < listType.size(); i++) {
                    String type = (String) listType.get(i);
                    if (dataSet[2].equals("")) {
                        dataSet[2] = type;
                    } else {
                        dataSet[2] = dataSet[2] + "##" + type;
                    }
                }
                //FORMAT
                dataSet[3] = "";
                ArrayList listFormat = getFormatDataSet(idDataSet.stringValue());
                for (int i = 0; i < listFormat.size(); i++) {
                    String format = (String) listFormat.get(i);
                    if (dataSet[3].equals("")) {
                        dataSet[3] = format;
                    } else {
                        dataSet[3] = dataSet[3] + "##" + format;
                    }
                }
                //RELATION
                dataSet[4] = "";
                ArrayList listRelation = getRelationsDataSet(idDataSet.stringValue());
                for (int i = 0; i < listRelation.size(); i++) {
                    String relation = (String) listRelation.get(i);
                    if (dataSet[4].equals("")) {
                        dataSet[4] = relation;
                    } else {
                        dataSet[4] = dataSet[4] + "##" + relation;
                    }
                }


            }
        }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        


        return dataSet;
    }
    
    
      public   ArrayList getRepository(String resource) throws RepositoryException, MalformedQueryException, UnsupportedEncodingException {
       
          ArrayList arrayRepositoryInfo = new ArrayList();


        try {

            //RepositoryConnection con = ConnectionToVirtuoso();

            String queryStringRepository = "PREFIX myOnto:<http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#>"
                    + " SELECT * FROM "+graph+"\n"
                    +" WHERE {\n"    
                    +" <"+resource+"> <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#isResourceOf> ?rep.\n"
                     +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#acronimRepository> ?acronimRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#nameRepository> ?nameRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#longitudeRepository> ?longRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#latitudeRepository> ?latRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#countryCodeRepository> ?countryCodeRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#urlRepository> ?urlRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#addressOAIPMHRepository> ?OAIPMHRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#addressRepository> ?addressRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#acronimRepository> ?acroRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#domainRepository> ?domainRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#projectRepository> ?projectRep.\n"
                    +" ?rep <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#isRepositoryOf> ?org.\n"
                    +" ?org <http://semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#nameOrganization> ?nameOrg.\n"
                    +" ?rep <http://www.semanticweb.org/ontologies/2013/2/7/RepositoryOntology.owl#rank> ?rank. "
                    
                    +"}";


            TupleQuery tupleQuery_repository = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryStringRepository);



            TupleQueryResult result_repository = tupleQuery_repository.evaluate();


            //System.out.println("REPOSITORY QUERY HAS NEXT?" + result_repository.hasNext() + "   " + queryStringRepository);
            
            
            String nameRep = "";
            String countryCodeRep = "";
            String longitudeRep = "";
            String latitudeRep = "";
            String urlRep = "";
            String addressOAIPMHRep = "";
            String addressRep = "";
            String acronimRep = "";
            String domainRep = "";
            String projectRep = "";
            String nameOrganization="";
            String rankRep="";


            if (result_repository.hasNext()) {

                while (result_repository.hasNext()) {
                    BindingSet bindingSet_repository = result_repository.next();
                    
                    
                    nameRep = bindingSet_repository.getValue("nameRep").stringValue();
                    String outNameRep=new String(nameRep.getBytes("iso-8859-1"), "utf-8");
                    longitudeRep = bindingSet_repository.getValue("longRep").stringValue();
                    latitudeRep = bindingSet_repository.getValue("latRep").stringValue();
                    countryCodeRep = bindingSet_repository.getValue("countryCodeRep").stringValue();
                    urlRep = bindingSet_repository.getValue("urlRep").stringValue();
                    addressOAIPMHRep = bindingSet_repository.getValue("OAIPMHRep").stringValue();
                    addressRep = bindingSet_repository.getValue("addressRep").stringValue();
                    String outAddressRep=new String(addressRep.getBytes("iso-8859-1"), "utf-8");
                    acronimRep = bindingSet_repository.getValue("acroRep").stringValue();

                    domainRep = bindingSet_repository.getValue("domainRep").stringValue();
                    String outDomainRep=new String(domainRep.getBytes("iso-8859-1"), "utf-8");
                    projectRep = bindingSet_repository.getValue("projectRep").stringValue();
                    String outProjectRep=new String(projectRep.getBytes("iso-8859-1"), "utf-8");
                    nameOrganization = bindingSet_repository.getValue("nameOrg").stringValue();
                    String outNameOrganization=new String(nameOrganization.getBytes("iso-8859-1"), "utf-8");
                    rankRep = bindingSet_repository.getValue("rank").stringValue();
                    String outRankRep=new String(rankRep.getBytes("iso-8859-1"), "utf-8");

                    arrayRepositoryInfo.add(0, outNameRep);
                    arrayRepositoryInfo.add(1, countryCodeRep);
                    arrayRepositoryInfo.add(2, longitudeRep);
                    arrayRepositoryInfo.add(3, latitudeRep);
                    arrayRepositoryInfo.add(4, urlRep);
                    arrayRepositoryInfo.add(5, addressOAIPMHRep);
                    arrayRepositoryInfo.add(6, outAddressRep);
                    arrayRepositoryInfo.add(7, acronimRep);
                    arrayRepositoryInfo.add(8, outDomainRep);
                    arrayRepositoryInfo.add(9, outProjectRep);
                    arrayRepositoryInfo.add(10, outNameOrganization);
                    arrayRepositoryInfo.add(11, outRankRep);
                    
                    
                    
                    
                }
            }

 
            //System.out.println("DIM DI REP "+arrayRepositoryInfo.size());

        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayRepositoryInfo;
    }
      
 public ArrayList getValueDCTerms(String sourcePaper,String dcterms) throws UnsupportedEncodingException
 {
     ArrayList list = new ArrayList();
        
        try {
            
            String queryString = "prefix dc-terms: <http://purl.org/dc/terms/>"
                    + "SELECT distinct (?el) FROM " + graph + "  WHERE {\n"
                    + "<" + sourcePaper + "> dc-terms:"+dcterms+" ?el.\n"
                    + "}";
           // System.out.println(queryString);
            TupleQuery tupleQuery = virtuosoConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            TupleQueryResult result = tupleQuery.evaluate();
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                Value subject = bindingSet.getValue("el");
                String el1=subject.stringValue().replaceAll("[\r\n]+", " ");
                String el2=replaceApici(el1);
                String elFinale=new String(el2.getBytes("iso-8859-1"), "utf-8");

                // System.out.println("name: " + name.stringValue() + "fname: " + fname.stringValue());
                list.add(elFinale);

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryEvaluationException ex) {
            Logger.getLogger(SemanticQuery.class.getName()).log(Level.SEVERE, null, ex);
        }



        return list;
 }
      
      
  
  
      
      
}

