/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

/**
 * REST Web Service
 *
 * @author grid
 */
@Path("resources")
public class ApiVirtuoso {

     public static RepositoryConnection virtuosoConnection;
    public static String graph = "http://CHAIN-Reds_Test";
    private Api myResource;
    //private RepositoryResource myRepository;
    
    SemanticQuery query = new SemanticQuery();

    /**
     * Creates a new instance of ApiVirtuoso
     */
    public ApiVirtuoso() {
    }

    /**
     * Retrieves representation of an instance of src.ApiVirtuoso
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces("application/json")
    public Api getListResourceVirtuoso(@QueryParam("keyword") String keyword, @QueryParam("limit") int numResource,@QueryParam("offset") int numOffset)  {
        ArrayList listResource = new ArrayList();
        
       
         System.out.println("Start to Get List OF Resource Virtuoso with keyword ="+keyword+" limit="+numResource+" offset="+numOffset);


        try {
            //TODO return proper representation object


            listResource = query.queryVirtuosoResource(keyword, numResource,numOffset);

            myResource = new Api();
            //myRepository=new RepositoryResource();
            for (int i = 0; i < listResource.size(); i++) {
                
             
                
                RepositoryInVirtuoso repo=new RepositoryInVirtuoso();
                
                
                DatasetResource resourceDataset=new DatasetResource();
                
                
                ResourceVirtuoso resourceVirtuoso = new ResourceVirtuoso();
                String idResource = listResource.get(i).toString();

                ArrayList titles = query.getTitlesResource(idResource);
                resourceVirtuoso.setTitles(titles);

                ArrayList authors = query.getAuthorsResource(idResource);
                resourceVirtuoso.setAuthors(authors);

                ArrayList identifiers = query.getIdentifiersResource(idResource);
                resourceVirtuoso.setIdentifiers(identifiers);

                ArrayList descr = query.getDescriptionsResource(idResource);
                resourceVirtuoso.setDescriptions(descr);

                ArrayList publishers = query.getPublishersResource(idResource);
                resourceVirtuoso.setPublishers(publishers);

                ArrayList subjects = query.getSubjectsResource(idResource);
                resourceVirtuoso.setSubjects(subjects);

                ArrayList contributors = query.getContributorResource(idResource);
                resourceVirtuoso.setContributors(contributors);

                ArrayList sources = query.getSourcesResource(idResource);
                resourceVirtuoso.setSources(sources);

                ArrayList coverages = query.getCoverageSource(idResource);
                resourceVirtuoso.setCoverages(coverages);

                ArrayList languages = query.getLanguagesResource(idResource);
                resourceVirtuoso.setLanguages(languages);

                ArrayList abstracts = query.getValueDCTerms(idResource, "abstract");
                resourceVirtuoso.setAbstracts(abstracts);

                ArrayList accessRights = query.getValueDCTerms(idResource, "accessRights");
                resourceVirtuoso.setAccessRights(accessRights);

                ArrayList accuralMethods = query.getValueDCTerms(idResource, "accuralMethod");
                resourceVirtuoso.setAccuralMethods(accuralMethods);

                ArrayList accuralPeriodicity = query.getValueDCTerms(idResource, "accuralPeriodicity");
                resourceVirtuoso.setAccuralPeriodicity(accuralPeriodicity);

                ArrayList accuralPolicy = query.getValueDCTerms(idResource, "accuralPolicy");
                resourceVirtuoso.setAccuralPolicy(accuralPolicy);

                ArrayList alternative = query.getValueDCTerms(idResource, "alternative");
                resourceVirtuoso.setAlternative(alternative);


                ArrayList audience = query.getValueDCTerms(idResource, "audience");
                resourceVirtuoso.setAudience(audience);

                ArrayList available = query.getValueDCTerms(idResource, "available");
                resourceVirtuoso.setAvailable(available);

                ArrayList bibliographicCitation = query.getValueDCTerms(idResource, "bibliographicCitation");
                resourceVirtuoso.setBibliographicCitation(bibliographicCitation);

                ArrayList conformsTo = query.getValueDCTerms(idResource, "conformsTo");
                resourceVirtuoso.setConformsTo(conformsTo);

                ArrayList created = query.getValueDCTerms(idResource, "created");
                resourceVirtuoso.setCreated(created);

                ArrayList dateAccepted = query.getValueDCTerms(idResource, "dateAccepted");
                resourceVirtuoso.setDateAccepted(dateAccepted);

                ArrayList dateCopyrighted = query.getValueDCTerms(idResource, "dateCopyrighted");
                resourceVirtuoso.setDateCopyrighted(dateCopyrighted);

                ArrayList dateSubmitted = query.getValueDCTerms(idResource, "dateSubmitted");
                resourceVirtuoso.setDateSubmitted(dateSubmitted);

                ArrayList educationLevel = query.getValueDCTerms(idResource, "educationLevel");
                resourceVirtuoso.setEducationLevel(educationLevel);

                ArrayList extent = query.getValueDCTerms(idResource, "extent");
                resourceVirtuoso.setExtent(extent);

                ArrayList hasFormat = query.getValueDCTerms(idResource, "hasFormat");
                resourceVirtuoso.setHasFormat(hasFormat);
                
                ArrayList hasPart = query.getValueDCTerms(idResource, "hasPart");
                resourceVirtuoso.setHasPart(hasPart);

                ArrayList hasVersion = query.getValueDCTerms(idResource, "hasVersion");
                resourceVirtuoso.setHasVersion(hasVersion);

                ArrayList instructionalMethod = query.getValueDCTerms(idResource, "instructionalMethod");
                resourceVirtuoso.setInstructionalMethod(instructionalMethod);

                ArrayList isFormatOf = query.getValueDCTerms(idResource, "isFormatOf");
                resourceVirtuoso.setIsFormatOf(isFormatOf);

                ArrayList isPartOf = query.getValueDCTerms(idResource, "isPartOf");
                resourceVirtuoso.setIsPartOf(isPartOf);

                ArrayList isReferencedBy = query.getValueDCTerms(idResource, "isReferencedBy");
                resourceVirtuoso.setIsReferencedBy(isReferencedBy);

                ArrayList isReplacedBy = query.getValueDCTerms(idResource, "isReplacedBy");
                resourceVirtuoso.setIsReplacedBy(isReplacedBy);

                ArrayList isRequiredBy = query.getValueDCTerms(idResource, "isRequiredBy");
                resourceVirtuoso.setIsRequiredBy(isRequiredBy);

                ArrayList issued = query.getValueDCTerms(idResource, "issued");
                resourceVirtuoso.setIssued(issued);

                ArrayList isVersionOf = query.getValueDCTerms(idResource, "isVersionOf");
                resourceVirtuoso.setIsVersionOf(isVersionOf);

                ArrayList license = query.getValueDCTerms(idResource, "license");
                resourceVirtuoso.setLicense(license);
                
                ArrayList mediator = query.getValueDCTerms(idResource, "mediator");
                resourceVirtuoso.setMediator(mediator);
                
                ArrayList medium = query.getValueDCTerms(idResource, "medium");
                resourceVirtuoso.setMedium(medium);
                
                ArrayList modified = query.getValueDCTerms(idResource, "modified");
                resourceVirtuoso.setModified(modified);
                
                ArrayList provenance = query.getValueDCTerms(idResource, "provenance");
                resourceVirtuoso.setProvenance(provenance);
                
                ArrayList references = query.getValueDCTerms(idResource, "references");
                resourceVirtuoso.setReferences(references);
                
                ArrayList replaces = query.getValueDCTerms(idResource, "replaces");
                resourceVirtuoso.setReplaces(replaces);
                
                ArrayList requires = query.getValueDCTerms(idResource, "requires");
                resourceVirtuoso.setRequires(requires);
                
                ArrayList rightsHolder = query.getValueDCTerms(idResource, "rightsHolder");
                resourceVirtuoso.setRightsHolder(rightsHolder);
                
                ArrayList spatial = query.getValueDCTerms(idResource, "spatial");
                resourceVirtuoso.setSpatial(spatial);
                
                ArrayList tableOfContents = query.getValueDCTerms(idResource, "tableOfContents");
                resourceVirtuoso.setTableOfContents(tableOfContents);
                
                ArrayList temporal = query.getValueDCTerms(idResource, "temporal");
                resourceVirtuoso.setTemporal(temporal);
                
                ArrayList valid = query.getValueDCTerms(idResource, "valid");
                resourceVirtuoso.setValid(valid);
                
                ArrayList repository = query.getRepository(idResource);
                
                String nameRepo = repository.get(0).toString();
                repo.setNameRepo(nameRepo);
                
                String countryCodeRepo = repository.get(1).toString();
                repo.setCountryCodeRepo(countryCodeRepo);
                
                String longitudeRepo = repository.get(2).toString();
                repo.setLongitudeRepo(longitudeRepo);
                
                String latitudeRepo = repository.get(3).toString();
                repo.setLatitudeRepo(latitudeRepo);
                
                String urlRepo = repository.get(4).toString();
                repo.setUrlRepo(urlRepo);
                
                String addressOAIPMHRepo = repository.get(5).toString();
                repo.setAddressOAIPMHRepo(addressOAIPMHRepo);
                
                String addressRepo = repository.get(6).toString();
                repo.setAddressRepo(addressRepo);
                
                String acronimRepo = repository.get(7).toString();
                repo.setAcronimRepo(acronimRepo);
                
                String domainRepo = repository.get(8).toString();
                repo.setDomainRepo(domainRepo);
                
                String projectRepo = repository.get(9).toString();
                repo.setProjectRepo(projectRepo);
                
                String organizationRepo = repository.get(10).toString();
                repo.setOrganizationRepo(organizationRepo);
                
                String rankRepo = repository.get(11).toString();
                repo.setRankRepo(rankRepo);
                
                
                String[] data = query.getDataSetFromResource(idResource);
               
                ArrayList id_Dataset=new ArrayList();
                ArrayList type_Dataset=new ArrayList();
                ArrayList format_Dataset=new ArrayList();
                ArrayList relation_Dataset=new ArrayList();
                
                
              
                
                String idDataset = data[1];
                
                
               
                if(idDataset.contains("##")){
                    String[] split_id=idDataset.split("##");
                    for(int j=0; j<split_id.length;j++ )
                        id_Dataset.add(split_id[j]);
                }
                else
                    id_Dataset.add(idDataset);
                
                resourceDataset.setIdDataset(id_Dataset);
                
                String typeDataset = data[2];
                if(typeDataset.contains("##")){
                    String[] split_type=typeDataset.split("##");
                    for(int j=0; j<split_type.length;j++ )
                        type_Dataset.add(split_type[j]);
                }
                else
                  type_Dataset.add(typeDataset);  
                resourceDataset.setTypeDataset(type_Dataset);
                
                String formatDataset = data[3];
                 if(formatDataset.contains("##")){
                    String[] split_format=formatDataset.split("##");
                    for(int j=0; j<split_format.length;j++ )
                        format_Dataset.add(split_format[j]);
                }
                else
                  format_Dataset.add(formatDataset);   
                resourceDataset.setFormatDataset(format_Dataset);
                
                String relationDataset = data[4];
                 if(relationDataset.contains("##")){
                    String[] split_relation=relationDataset.split("##");
                    for(int j=0; j<split_relation.length;j++ )
                        relation_Dataset.add(split_relation[j]);
                }
                 else
                relation_Dataset.add(relationDataset);
                resourceDataset.setRelationDataset(relation_Dataset);
                
                
                resourceVirtuoso.setDataset(resourceDataset);
                
                
                
                resourceVirtuoso.setRepository(repo);
                
                myResource.getResourceVirtuoso().add(resourceVirtuoso);
               
                
                
                
               
               
            }



        } catch (RepositoryException ex) {
            Logger.getLogger(ApiVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedQueryException ex) {
            Logger.getLogger(ApiVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ApiVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ApiVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("chiamata api terminata");
        return myResource;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
