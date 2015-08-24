/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author grid
 */
@Path("simpleResources")
public class GetSimpleResourceVirtuoso {
    
    
    
     SemanticQuery query = new SemanticQuery();
     private GetListSimpleResourcesVirtuoso listSimpleResources;
    //String pathGSCholar=this.getClass().getClassLoader().getResource("/scholar.py").getPath();
    
    
    @GET
    @Produces("application/json")
    public GetListSimpleResourcesVirtuoso  getListSimpleResources(@QueryParam("keyword") String keyword, @QueryParam("limit") int numResource,@QueryParam("offset") int numOffset){
        
        ArrayList listResource = new ArrayList();
        System.out.println("Start to Get INfO SIMPLE RESOURCES with title ="+keyword);
        
       // System.out.println("PATH-->"+pathGSCholar);
      //  CitationsGScholarResource infoGS=new CitationsGScholarResource();
        listResource = query.queryVirtuosoResourceNew(keyword, numResource,numOffset);
        
        listSimpleResources = new GetListSimpleResourcesVirtuoso();
        
        for (int i = 0; i < listResource.size(); i++) {
            try {
                SimpleResourceVirtuoso sResourceVirtuoso = new SimpleResourceVirtuoso();
                
                String idResource = listResource.get(i).toString();

                ArrayList titles = query.getTitlesResource(idResource);
                sResourceVirtuoso.setTitles(titles);

                ArrayList authors = query.getAuthorsResource(idResource);
                sResourceVirtuoso.setAuthors(authors);
                
                sResourceVirtuoso.setIdResource(idResource);
                
                //String idResource=listResource.get(i).toString();
                
                //getSimpleResourceVirtuoso();
                
                
                listSimpleResources.getSimpleResources().add(sResourceVirtuoso);
                
                
                
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SimpleResourceVirtuoso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
       // ResourceVirtuoso resourceVirtuoso = new ResourceVirtuoso();
        
        
       // String[] allInfo=query.executeCommand(title, pathGSCholar);
//        infoGS.setURL(allInfo[0]);
//        infoGS.setversions(allInfo[1]);
//        infoGS.setversionsList(allInfo[2]);
//        infoGS.setcitations(allInfo[3]);
//        infoGS.setcitationsList(allInfo[4]);
//        infoGS.setyear(allInfo[5]);
                
        
        return listSimpleResources;
    }
    
    
     
    
}
