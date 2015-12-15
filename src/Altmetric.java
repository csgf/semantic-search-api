/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.*;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ccarrubba
 */
public class Altmetric {
    
    public static String doi_selected=new String();
    
    
    public static  String Almetric(String doi) throws FileNotFoundException, IOException{
    
        
        String response="";
        
        HttpClient client = new HttpClient();
        HttpMethod method = null;
       // String responseXML = "";

        if(doi!=""){

        method = callAPI_Almetric(doi);

        client.executeMethod(method);
       

     
       if (method.getStatusCode() == HttpStatus.SC_OK) {

        method.getResponseBody();


        response = convertStreamToString(method.getResponseBodyAsStream());

        //System.out.println("Pagina di risposta" + response);
       }

        method.releaseConnection();
        }
        
        return response;
    }
    
    public static String getLinkAlmetricFromDOI(String doi){
        String url_details_almetric="";
        try {
           
            String responseJson=Almetric(doi);
                
                if(!responseJson.equals("Not Found") && !responseJson.equals("")){
                    
                   

                    
                Object obj = JSONValue.parse(responseJson);


                JSONObject objectJ = (JSONObject) obj;

                url_details_almetric = (String) objectJ.get("details_url");
                //System.out.println("details_url=>" + url_details_almetric);
                
                doi_selected= (String) objectJ.get("doi");
                //System.out.println("doi selected=>" +doi_selected );
                }
                else{
                url_details_almetric="no link";
        
                  //System.out.println("NO almetric=>"+url_details_almetric);
                }
            
            
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Altmetric.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Altmetric.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return url_details_almetric;
    }
    
    
    public static ArrayList QueryApi(String doi) throws MalformedURLException, ParseException, IOException {
     
        
        ArrayList info_almetric= new ArrayList();
        
        String responseJson=Almetric(doi);
        
        
      //  System.out.println("Sono qua e il doi =>"+doi);
        
        if(!responseJson.equals("Not Found") && !responseJson.equals("")){
            
           System.out.println("responseJson Altmetrics=>>>"+responseJson);

            
        Object obj = JSONValue.parse(responseJson);


        JSONObject objectJ = (JSONObject) obj;

        String title = (String) objectJ.get("title");
        //System.out.println("titolo=>" + title);

        info_almetric.add(title);
        
        
        String _doi = (String) objectJ.get("doi");
        //System.out.println("doi=>" + _doi +"è uguale a= "+doi_selected);
        info_almetric.add(_doi);
        
        
        JSONArray issns = (JSONArray) objectJ.get("issns");
        //System.out.println("issns=>" + issns.size());
        info_almetric.add(issns);
        
        String journal = (String) objectJ.get("journal");
        //System.out.println("journal=>" + journal);
        info_almetric.add(journal);
        
        JSONObject cohortsOBJ = (JSONObject) objectJ.get("cohorts");
        //System.out.println("cohorts=>" + cohortsOBJ);
        String cohorts_pub = (String) cohortsOBJ.get("pub").toString();
        //System.out.println("cohorts_pub=>" + cohorts_pub);
        info_almetric.add(cohorts_pub);

        
        String type = (String) objectJ.get("type");
        //System.out.println("type=>" + type);
        info_almetric.add(type);
        
        String altmetric_id = (String) objectJ.get("altmetric_id").toString();
        //System.out.println("altmetric_id=>" + altmetric_id);
        info_almetric.add(altmetric_id);
        
        String schema = (String) objectJ.get("schema");
        //System.out.println("schema=>" + schema);

        String is_oa = (String) objectJ.get("is_oa").toString();
        //System.out.println("is_oa=>" + is_oa);
        info_almetric.add(schema);
        
        String cited_by_posts_count = (String) objectJ.get("cited_by_posts_count").toString();
        //System.out.println("cited_by_posts_count=>" + cited_by_posts_count);
        info_almetric.add(cited_by_posts_count);
        
        

        String cited_by_tweeters_count = (String) objectJ.get("cited_by_tweeters_count").toString();
        //System.out.println("cited_by_tweeters_count=>" + cited_by_tweeters_count);
        info_almetric.add(cited_by_tweeters_count);
        
        String cited_by_accounts_count = (String) objectJ.get("cited_by_accounts_count").toString();
        //System.out.println("cited_by_accounts_count=>" + cited_by_accounts_count);
        info_almetric.add(cited_by_accounts_count);
        
        String last_updated = (String) objectJ.get("last_updated").toString();
        //System.out.println("last_updated=>" + last_updated);
        info_almetric.add(last_updated);
        
        String score = (String) objectJ.get("score").toString();
       // System.out.println("score=>" + score);
        info_almetric.add(score);
        
        
         JSONObject historyOBJ = (JSONObject) objectJ.get("history");
        //String history = (String) objectJ.get("history").toString();
       // System.out.println("history=>" + history);
        info_almetric.add(historyOBJ);
        
        String url = (String) objectJ.get("url").toString();
        //System.out.println("url=>" + url);
        info_almetric.add(url);
        
        
        String added_on = (String) objectJ.get("added_on").toString();
        //System.out.println("added_on=>" + added_on);
        info_almetric.add(added_on);
        
        String published_on = (String) objectJ.get("published_on").toString();
       // System.out.println("published_on=>" + published_on);
        info_almetric.add(published_on);
        
        
        
         JSONObject readersOBJ = (JSONObject) objectJ.get("readers");
        //String readers = (String) objectJ.get("readers").toString();
        //System.out.println("readers=>" + readers);
        info_almetric.add(readersOBJ);
        
        String readers_count = (String) objectJ.get("readers_count").toString();
        //System.out.println("readers_count=>" + readers_count);
        info_almetric.add(readers_count);
               
        
        JSONObject imagesOBJ = (JSONObject) objectJ.get("images");
        
       // String images = (String) objectJ.get("images").toString();
       // System.out.println("images=>" + images);
        info_almetric.add(imagesOBJ);
        
        
        
         String details_url = (String) objectJ.get("details_url").toString();
        //System.out.println("details_url=>" + details_url);
        info_almetric.add(details_url);
        
        


       }
       return info_almetric;
            


    }

    public static HttpMethod callAPI_Almetric(String doi) throws FileNotFoundException {
        HttpMethod method = null;
        String key = "81a0d79eeb1a8cf19f340d1cbc4";

        // String tit="A Low Walk Double Threshold Discriminator for Gas Tracking Devices";

        // String myTi = new String(doi.getBytes("iso-8859-1"), "utf-8");
        // String titolo_url=myTi.replace(" ", "%20"); 
        //  String encoded_titolo = java.net.URLEncoder.encode(myTi, "UTF-8");


        //java.net.URLDecoder.decode(titolo, "UTF-8");
        // String apiCarla = "beeb2c34b9bd6f5d68d0ef07f7f8415d130fea37";
        //   String apiGiusi = "8ba23a9f1908e13354b9d32f261a3e54a2310b9b";
        //String titolo_url = "Analyzing%20epsilon'/epsilon%20in%20the%201/N_c%20Expansion";//

        method = new GetMethod("http://api.altmetric.com/v1/doi/" + doi + "?key=81a0d79eeb1a8cf19f340d1cbc48be00");

        System.out.println("http://api.altmetric.com/v1/doi/" + doi + "?key=81a0d79eeb1a8cf19f340d1cbc4");






        return method;
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {

            Writer writer = new StringWriter();



            char[] buffer = new char[1024];

            try {

                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                int n;

                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);

                }

            } finally {

                is.close();

            }

            return writer.toString();

        } else {

            return "";

        }

    }

    
}
