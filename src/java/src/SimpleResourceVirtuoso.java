/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author grid
 */



import java.util.ArrayList;
import java.util.Formatter;
import javax.xml.bind.annotation.*;

/**
 *
 * @author grid
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "api")
@XmlType(name = "simpleResources", propOrder = {
    "titles",
    "authors",
    "idResource"
    
    })

  

public class SimpleResourceVirtuoso {
     @XmlElement(required = true)
    protected ArrayList titles;
    protected ArrayList authors;
    protected String idResource;

    
     public ArrayList getTitles() {
        return titles;
    }

    public void setTitles(ArrayList value) {
        this.titles = value;
    }

    public ArrayList getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList value) {
        this.authors = value;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String value) {
        this.idResource = value;
    }

    
    
    
    
}
