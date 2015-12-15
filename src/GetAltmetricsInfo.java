/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author ccarrubba
 */

@Path("infoAltmetric")
public class GetAltmetricsInfo {
    
    
    
    
    
    @GET
    @Produces("application/json")
    public AltmetricInfoResource getInfoAlt(@QueryParam("doi") String doi) throws MalformedURLException, ParseException, IOException{
        
        
        System.out.println("Start to Get Info Altmetric with doi ="+doi);
        AltmetricInfoResource infoAltmetric=new AltmetricInfoResource();
        //System.out.println("PATH-->"+pathGSCholar);
     
       
        ArrayList arrayAltmetric= Altmetric.QueryApi(doi);
               
       
       
       
       infoAltmetric.setTitle(arrayAltmetric.get(0).toString());
       
       infoAltmetric.setDOI(arrayAltmetric.get(1).toString());
       
       infoAltmetric.setIssns(arrayAltmetric.get(2).toString());
               
       infoAltmetric.setJournal(arrayAltmetric.get(3).toString());        
       
       infoAltmetric.setCohorts_pub(arrayAltmetric.get(4).toString());
               
       infoAltmetric.setType(arrayAltmetric.get(5).toString());
               
       infoAltmetric.setID(arrayAltmetric.get(6).toString());
               
       infoAltmetric.setSchema(arrayAltmetric.get(7).toString());
               
       infoAltmetric.setCited_by_posts_count(arrayAltmetric.get(8).toString());
               
       infoAltmetric.setCited_by_tweeters_count(arrayAltmetric.get(9).toString());
               
       infoAltmetric.setCited_by_accounts_count(arrayAltmetric.get(10).toString());
               
       infoAltmetric.setLast_updated(arrayAltmetric.get(11).toString());
               
       infoAltmetric.setScore(arrayAltmetric.get(12).toString()); 
               
       infoAltmetric.setHistory(arrayAltmetric.get(13));
               
       infoAltmetric.setAdded_on(arrayAltmetric.get(15).toString());     
      
       infoAltmetric.setPublished_on(arrayAltmetric.get(16).toString());          
      
       infoAltmetric.setReaders(arrayAltmetric.get(17));
               
       infoAltmetric.setReaders_count(arrayAltmetric.get(18).toString());   
       
       infoAltmetric.setImages(arrayAltmetric.get(19));   
       
       infoAltmetric.setDetails_url(arrayAltmetric.get(20).toString());   
       
     

                
        
        return infoAltmetric;
    }
    
}
