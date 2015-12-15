/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.xml.bind.annotation.*;

/**
 *
 * @author ccarrubba
 */

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "api")


@XmlType(name = "AltmetricInfoResource", propOrder = {
    
    "doi",
    "title",
    "issns",
    "journal",
    "cohorts_pub",
    "type",
    "id",
    "schema",
    "cited_by_posts_count",
    "cited_by_tweeters_count",
    "cited_by_accounts_count",
    "last_updated",
    "score",
    "history",
    "added_on",
    "published_on",
    "readers",
    "readers_count",
    "images",
    "details_url"
    
   
})
public class AltmetricInfoResource {
 
     @XmlElement(required = true)
   
    protected String doi;
    protected String title;
    protected String issns;
    protected String journal;
    protected String cohorts_pub;
    protected String type;
    protected String id;
    protected String schema;
    protected String cited_by_posts_count;
    protected String cited_by_tweeters_count;
    protected String cited_by_accounts_count;
    protected String last_updated;
    protected String score;
    protected Object history;
    protected String added_on;
    protected String published_on;
    protected Object readers;
    protected String readers_count;
    protected Object images;
    protected String details_url;

  

    
    public String getDOI() {
        return doi;
    }

    public void setDOI(String value) {
        this.doi= value;
    }
    
    
      public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title= value;
    }
    
    
      public String getIssns() {
        return issns;
    }

    public void setIssns(String value) {
        this.issns= value;
    }
    
    
      public String getJournal() {
        return journal;
    }

    public void setJournal(String value) {
        this.journal= value;
    }
    
    
      public String getCohorts_pub() {
        return cohorts_pub;
    }

    public void setCohorts_pub(String value) {
        this.cohorts_pub= value;
    }
    
    
      public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type= value;
    }
    
    
      public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id= value;
    }
    
       public String getSchema() {
        return schema;
    }

    public void setSchema(String value) {
        this.schema= value;
    }
       public String getCited_by_posts_count() {
        return cited_by_posts_count;
    }

    public void setCited_by_posts_count(String value) {
        this.cited_by_posts_count= value;
    }
       public String getCited_by_tweeters_count() {
        return cited_by_tweeters_count;
    }

    public void setCited_by_tweeters_count(String value) {
        this.cited_by_tweeters_count= value;
    }
       public String getCited_by_accounts_count() {
        return cited_by_accounts_count;
    }

    public void setCited_by_accounts_count(String value) {
        this.cited_by_accounts_count= value;
    }
       public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String value) {
        this.last_updated= value;
    }
       public String getScore() {
        return score;
    }

    public void setScore(String value) {
        this.score= value;
    }
    
    
          public Object getHistory() {
        return history;
    }

    public void setHistory(Object value) {
        this.history= value;
    }
    
          public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String value) {
        this.added_on= value;
    }
    
    
          public String getPublished_on() {
        return published_on;
    }

    public void setPublished_on(String value) {
        this.published_on= value;
    }
    
          public Object getReaders() {
        return readers;
    }

    public void setReaders(Object value) {
        this.readers= value;
    }
    
          public String getReaders_count() {
        return readers_count;
    }

    public void setReaders_count(String value) {
        this.readers_count= value;
    }
  
    public Object getImages() {
        return images;
    }

    public void setImages(Object value) {
        this.images= value;
    }
    
   public String getDetails_url() {
        return details_url;
    }

    public void setDetails_url(String value) {
        this.details_url= value;
    }
    
    
    
    
}
