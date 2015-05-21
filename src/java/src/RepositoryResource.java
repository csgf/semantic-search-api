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
 * @author ccarrubba
 */

@XmlRootElement(name = "repository")

public class RepositoryResource {
    
     @XmlElement(required = true)
    protected List<RepositoryInVirtuoso> repository;
     
     
     
     public List<RepositoryInVirtuoso> getResourceVirtuoso() {
        if (repository == null) {
            repository = new ArrayList<RepositoryInVirtuoso>();
        }
        return this.repository;
    }
    
    @Override
    public String toString() {
        return (this.repository != null) ? "{\"repository\": " + this.repository.toString() + "}" : "{repository:[]}";
    }
    
}
