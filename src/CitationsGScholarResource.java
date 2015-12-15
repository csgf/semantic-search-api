/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author grid
 */


import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author ccarrubba
 */

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "api")


@XmlType(name = "GScholarResource", propOrder = {
    
    "URL",
    "versions",
    "versionsList",
    "citations",
    "citationsList",
    "year"
   
})
public class CitationsGScholarResource {

    @XmlElement(required = true)
   
    protected String URL;
    protected String versions;
    protected String versionsList;
    protected String citations;
    protected String citationsList;
    protected String year;

  

    
    public String getURL() {
        return URL;
    }

    public void setURL(String value) {
        this.URL= value;
    }

    public String getversions() {
        return versions;
    }

    public void setversions(String value) {
        this.versions = value;
    }

    public String getversionsList() {
        return versionsList;
    }

    public void setversionsList(String value) {
        this.versionsList = value;
    }

    public String getcitations() {
        return citations;
    }

    public void setcitations(String value) {
        this.citations = value;
    }
    public String getcitationsList() {
        return citationsList;
    }

    public void setcitationsList(String value) {
        this.citationsList = value;
    }
    public String getyear() {
        return year;
    }

    public void setyear(String value) {
        this.year = value;
    }

   
}