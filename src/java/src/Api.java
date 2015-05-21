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
    "resources"
})
@XmlRootElement(name = "api")


public class Api {
    
      @XmlElement(required = true)
     protected List<ResourceVirtuoso> resources;
     
     
     
     public List<ResourceVirtuoso> getResourceVirtuoso() {
        if (resources == null) {
            resources = new ArrayList<ResourceVirtuoso>();
           
        }
        return this.resources;
    }
    
    @Override
    public String toString() {
        return (this.resources != null) ? "{\"resources\": " + this.resources.toString() + "}" : "{resources:[]}";
    }
    
    
    
}
