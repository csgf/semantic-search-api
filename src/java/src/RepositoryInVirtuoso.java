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
@XmlType(name = "repositoryInVirtuoso", propOrder = {
    "name",
    "countryCode",
    "longitude",
    "latitude",
    "url",
    "addressOAIPMH",
    "address",
    "acronim",
    "domain",
    "project",
    "organization",
    "rank"
})
public class RepositoryInVirtuoso {

    @XmlElement(required = true)
    protected String name;
    protected String countryCode;
    protected String longitude;
    protected String latitude;
    protected String url;
    protected String addressOAIPMH;
    protected String address;
    protected String acronim;
    protected String domain;
    protected String project;
    protected String organization;
    protected String rank;

    public String getNameRepo() {
        return name;
    }

    public void setNameRepo(String value) {
        this.name = value;
    }

    public String getCountryCodeRepo() {
        return countryCode;
    }

    public void setCountryCodeRepo(String value) {
        this.countryCode = value;
    }

    public String getLongitudeRepo() {
        return longitude;
    }

    public void setLongitudeRepo(String value) {
        this.longitude = value;
    }

    public String getLatitudeRepo() {
        return latitude;
    }

    public void setLatitudeRepo(String value) {
        this.latitude = value;
    }

    public String getUrlRepo() {
        return url;
    }

    public void setUrlRepo(String value) {
        this.url = value;
    }

    public String getAddressOAIPMHRepo() {
        return addressOAIPMH;
    }

    public void setAddressOAIPMHRepo(String value) {
        this.addressOAIPMH = value;
    }

    public String getAddressRepo() {
        return address;
    }

    public void setAddressRepo(String value) {
        this.address = value;
    }

    public String getAcronimRepo() {
        return acronim;
    }

    public void setAcronimRepo(String value) {
        this.acronim = value;
    }

    public String getDomainRepo() {
        return domain;
    }

    public void setDomainRepo(String value) {
        this.domain = value;
    }

    public String getProjectRepo() {
        return project;
    }

    public void setProjectRepo(String value) {
        this.project = value;
    }

    public String getOrganizationRepo() {
        return organization;
    }

    public void setOrganizationRepo(String value) {
        this.organization = value;
    }
    
    public String getRankRepo() {
        return rank;
    }

    public void setRankRepo(String value) {
        this.rank = value;
    }
}