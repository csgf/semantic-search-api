/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.*;

/**
 *
 * @author grid
 */

@Path("infoGS")
public class GetCitationsGScholarResource {
    
    SemanticQuery query = new SemanticQuery();
    String pathGSCholar=this.getClass().getClassLoader().getResource("/scholar.py").getPath();
    
    
    @GET
    @Produces("application/json")
    public CitationsGScholarResource getInfoGS(@QueryParam("title") String title){
        
        
        System.out.println("Start to Get INfo GSCHOLAR with title ="+title);
        
        System.out.println("PATH-->"+pathGSCholar);
        CitationsGScholarResource infoGS=new CitationsGScholarResource();
        
        String[] allInfo=query.executeCommand(title, pathGSCholar);
        infoGS.setURL(allInfo[0]);
        infoGS.setversions(allInfo[1]);
        infoGS.setversionsList(allInfo[2]);
        infoGS.setcitations(allInfo[3]);
        infoGS.setcitationsList(allInfo[4]);
        infoGS.setyear(allInfo[5]);
                
        
        return infoGS;
    }
}






    
