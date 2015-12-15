/*
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
 * @author ccarrubba
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datasetResource", propOrder = {
    
    "identifierDataset",
    "typeDataset",
    "formatDataset",
    "relationDataset"
   
})
public class DatasetResource {

    @XmlElement(required = true)
   
    protected ArrayList identifierDataset;
    protected ArrayList typeDataset;
    protected ArrayList formatDataset;
    protected ArrayList relationDataset;

  

    
    public ArrayList getIdDataset() {
        return identifierDataset;
    }

    public void setIdDataset(ArrayList value) {
        this.identifierDataset = value;
    }

    public ArrayList getTypeDataset() {
        return typeDataset;
    }

    public void setTypeDataset(ArrayList value) {
        this.typeDataset = value;
    }

    public ArrayList getFormatDataset() {
        return formatDataset;
    }

    public void setFormatDataset(ArrayList value) {
        this.formatDataset = value;
    }

    public ArrayList setRelationDataset() {
        return relationDataset;
    }

    public void setRelationDataset(ArrayList value) {
        this.relationDataset = value;
    }

   
}