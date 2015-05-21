/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.util.ArrayList;
import java.util.Formatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author grid
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resources", propOrder = {
    "titles",
    "authors",
    "identifiers",
    "descriptions",
    "publishers",
    "subjects",
    "contributors",
    "sources",
    "coverages",
    "languages",
    "abstract",
    "accessRigths",
    "accuralMethods",
    "accuralPeriodicity",
    "accuralPolicy",
    "alternative",
    "audience",
    "available",
    "bibliographicCitation",
    "conformsTo",
    "created",
    "dateAccepted",
    "dateCopyrighted",
    "dateSubmitted",
    "educationLevel",
    "extent",
    "hasFormat",
    "hasPart",
    "hasVersion",
    "instructionalMethod",
    "isFormatOf",
    "isPartOf",
    "isReferencedBy",
    "isReplacedBy",
    "isRequiredBy",
    "issued",
    "isVersionOf",
    "license",
    "mediator",
    "medium",
    "modified",
    "provenance",
    "references",
    "replaces",
    "requires",
    "rightsHolder",
    "spatial",
    "tableOfContents",
    "temporal",
    "valid",
    "repository",
    "dataset"
    
})
public class ResourceVirtuoso {

    @XmlElement(required = true)
    protected ArrayList titles;
    protected ArrayList authors;
    protected ArrayList identifiers;
    protected ArrayList descriptions;
    protected ArrayList publishers;
    protected ArrayList subjects;
    protected ArrayList contributors;
    protected ArrayList sources;
    protected ArrayList coverages;
    protected ArrayList languages;
    protected ArrayList abstract_;
    protected ArrayList accessRigths;
    protected ArrayList accuralMethods;
    protected ArrayList accuralPeriodicity;
    protected ArrayList accuralPolicy;
    protected ArrayList alternative;
    protected ArrayList audience;
    protected ArrayList available;
    protected ArrayList bibliographicCitation;
    protected ArrayList conformsTo;
    protected ArrayList created;
    protected ArrayList dateAccepted;
    protected ArrayList dateCopyrighted;
    protected ArrayList dateSubmitted;
    protected ArrayList educationLevel;
    protected ArrayList extent;
    protected ArrayList hasFormat;
    protected ArrayList hasPart;
    protected ArrayList hasVersion;
    protected ArrayList instructionalMethod;
    protected ArrayList isFormatOf;
    protected ArrayList isPartOf;
    protected ArrayList isReferencedBy;
    protected ArrayList isReplacedBy;
    protected ArrayList isRequiredBy;
    protected ArrayList issued;
    protected ArrayList isVersionOf;
    protected ArrayList license;
    protected ArrayList mediator;
    protected ArrayList medium;
    protected ArrayList modified;
    protected ArrayList provenance;
    protected ArrayList references;
    protected ArrayList replaces;
    protected ArrayList requires;
    protected ArrayList rightsHolder;
    protected ArrayList spatial;
    protected ArrayList tableOfContents;
    protected ArrayList temporal;
    protected ArrayList valid;
    protected RepositoryInVirtuoso repository;
    protected DatasetResource dataset;
    
    

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

    public ArrayList getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList value) {
        this.identifiers = value;
    }

    public ArrayList getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ArrayList value) {
        this.descriptions = value;
    }

    public ArrayList getPublishers() {
        return publishers;
    }

    public void setPublishers(ArrayList value) {
        this.publishers = value;
    }

    public ArrayList getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList value) {
        this.subjects = value;
    }

    public ArrayList getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList value) {
        this.contributors = value;
    }

    public ArrayList getSources() {
        return sources;
    }

    public void setSources(ArrayList value) {
        this.sources = value;
    }

    public ArrayList getCoverages() {
        return coverages;
    }

    public void setCoverages(ArrayList value) {
        this.coverages = value;
    }

    public ArrayList getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList value) {
        this.languages = value;
    }

    public ArrayList getAbstracts() {
        return abstract_;
    }

    public void setAbstracts(ArrayList value) {
        this.abstract_ = value;
    }

    public ArrayList getAccessRights() {
        return accessRigths;
    }

    public void setAccessRights(ArrayList value) {
        this.accessRigths = value;
    }

    public ArrayList getAccuralMethods() {
        return accuralMethods;
    }

    public void setAccuralMethods(ArrayList value) {
        this.accuralMethods = value;
    }

    public ArrayList getAccuralPeriodicity() {
        return accuralPeriodicity;
    }

    public void setAccuralPeriodicity(ArrayList value) {
        this.accuralPeriodicity = value;
    }

    public ArrayList getAccuralPolicy() {
        return accuralPolicy;
    }

    public void setAccuralPolicy(ArrayList value) {
        this.accuralPolicy = value;
    }

    public ArrayList getAlternative() {
        return alternative;
    }

    public void setAlternative(ArrayList value) {
        this.alternative = value;
    }

    public ArrayList getAudience() {
        return audience;
    }

    public void setAudience(ArrayList value) {
        this.audience = value;
    }

    public ArrayList getAvailable() {
        return available;
    }

    public void setAvailable(ArrayList value) {
        this.available = value;
    }

    public ArrayList getBibliographicCitation() {
        return bibliographicCitation;
    }

    public void setBibliographicCitation(ArrayList value) {
        this.bibliographicCitation = value;
    }

    public ArrayList getConformsTo() {
        return conformsTo;
    }

    public void setConformsTo(ArrayList value) {
        this.conformsTo = value;
    }

    public ArrayList getCreated() {
        return created;
    }

    public void setCreated(ArrayList value) {
        this.created = value;
    }

    public ArrayList getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(ArrayList value) {
        this.dateAccepted = value;
    }

    public ArrayList getDateCopyrighted() {
        return dateCopyrighted;
    }

    public void setDateCopyrighted(ArrayList value) {
        this.dateCopyrighted = value;
    }

    public ArrayList getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(ArrayList value) {
        this.dateSubmitted = value;
    }

    public ArrayList getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(ArrayList value) {
        this.educationLevel = value;
    }

    public ArrayList getExtent() {
        return extent;
    }

    public void setExtent(ArrayList value) {
        this.extent = value;
    }

    public ArrayList getHasFormat() {
        return hasFormat;
    }

    public void setHasFormat(ArrayList value) {
        this.hasFormat = value;
    }

    public ArrayList getHasPart() {
        return hasPart;
    }

    public void setHasPart(ArrayList value) {
        this.hasPart = value;
    }

    public ArrayList getHasVersion() {
        return hasVersion;
    }

    public void setHasVersion(ArrayList value) {
        this.hasVersion = value;
    }

    public ArrayList getInstructionalMethod() {
        return instructionalMethod;
    }

    public void setInstructionalMethod(ArrayList value) {
        this.instructionalMethod = value;
    }

    public ArrayList getIsFormatOf() {
        return isFormatOf;
    }

    public void setIsFormatOf(ArrayList value) {
        this.isFormatOf = value;
    }

    public ArrayList getIsPartOf() {
        return isPartOf;
    }

    public void setIsPartOf(ArrayList value) {
        this.isPartOf = value;
    }

    public ArrayList getIsReferencedBy() {
        return isReferencedBy;
    }

    public void setIsReferencedBy(ArrayList value) {
        this.isReferencedBy = value;
    }

    public ArrayList getIsRepalcedBy() {
        return isReplacedBy;
    }

    public void setIsReplacedBy(ArrayList value) {
        this.isReplacedBy = value;
    }

    public ArrayList getIsRequiredBy() {
        return isRequiredBy;
    }

    public void setIsRequiredBy(ArrayList value) {
        this.isRequiredBy = value;
    }

    public ArrayList getIssued() {
        return issued;
    }

    public void setIssued(ArrayList value) {
        this.issued = value;
    }

    public ArrayList getIsVersionOf() {
        return isVersionOf;
    }

    public void setIsVersionOf(ArrayList value) {
        this.isVersionOf = value;
    }

    public ArrayList getLicense() {
        return license;
    }

    public void setLicense(ArrayList value) {
        this.license = value;
    }

    public ArrayList getMediator() {
        return mediator;
    }

    public void setMediator(ArrayList value) {
        this.mediator = value;
    }

    public ArrayList getMedium() {
        return medium;
    }

    public void setMedium(ArrayList value) {
        this.medium = value;
    }

    public ArrayList getModified() {
        return modified;
    }

    public void setModified(ArrayList value) {
        this.modified = value;
    }

    public ArrayList getProvenance() {
        return provenance;
    }

    public void setProvenance(ArrayList value) {
        this.provenance = value;
    }

    public ArrayList getReferences() {
        return references;
    }

    public void setReferences(ArrayList value) {
        this.references = value;
    }

    public ArrayList getReplaces() {
        return replaces;
    }

    public void setReplaces(ArrayList value) {
        this.replaces = value;
    }
    
    public ArrayList getRequires() {
        return requires;
    }

    public void setRequires(ArrayList value) {
        this.requires = value;
    }
    
    public ArrayList getRightsHolder() {
        return rightsHolder;
    }

    public void setRightsHolder(ArrayList value) {
        this.rightsHolder = value;
    }
    
   public ArrayList getSpatial() {
        return spatial;
    }

    public void setSpatial(ArrayList value) {
        this.spatial = value;
    }
    
     public ArrayList getTableOfContents() {
        return tableOfContents;
    }

    public void setTableOfContents(ArrayList value) {
        this.tableOfContents = value;
    }
    
    
    public ArrayList getTemporal() {
        return temporal;
    }

    public void setTemporal(ArrayList value) {
        this.temporal = value;
    }
    
     public ArrayList getValid() {
        return valid;
    }

    public void setValid(ArrayList value) {
        this.valid = value;
    }
    

     public DatasetResource getDataset() {
        return dataset;
    }

    public void setDataset(DatasetResource value) {
        this.dataset = value;
    }
    
   public RepositoryInVirtuoso getRepository() {
        return repository;
    }

    public void setRepository(RepositoryInVirtuoso value) {
        this.repository = value;
    }
    
//    public String getNameRepo() {
//        return nameRepo;
//    }
//
//    public void setRepository(String value) {
//        this.nameRepo = value;
//    }

    @Override
    public String toString() {
        return (new Formatter()).format(
                "{\"titles\":[" + titles + "],\"authors\":[" + authors + "],\"identifiers\":[" + identifiers + "],\"descriptions\":[" + descriptions + "],\"publishers\":[" + publishers + "],"
                + "\"subjects\":[" + subjects + "],\"contributors\":[" + contributors + "],\"sources\":[" + sources + "],\"coverages\":[" + coverages + "],\"languages\":[" + languages + "],"
                + "\"abstract\":[" + abstract_ + "],\"accessRigths\":[" + accessRigths + "]}",
                this.titles, this.authors, this.identifiers, this.descriptions, this.publishers, this.subjects,
                this.contributors, this.sources, this.coverages, this.languages, this.abstract_, this.accessRigths).toString();
    }
}
