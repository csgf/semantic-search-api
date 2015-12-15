/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author grid
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "simpleResources"
})
@XmlRootElement(name = "api")


public class GetListSimpleResourcesVirtuoso {
    
      @XmlElement(required = true)
     
      protected List<SimpleResourceVirtuoso> simpleResources;
     
     
     
     
    
     public List<SimpleResourceVirtuoso> getSimpleResources() {
        if (simpleResources == null) {
            simpleResources = new ArrayList<SimpleResourceVirtuoso>();
           
        }
        return this.simpleResources;
    }
     
    
    @Override
    public String toString() {
        return (this.simpleResources != null) ? "{\"simpleResources\": " + this.simpleResources.toString() + "}" : "{simpleResources:[]}";
    }
    
    
    
}
