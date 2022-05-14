package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AppMobilitySubscriptionDescriptor {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("callbackReference")
    @Expose
    private String callbackReference;
    @SerializedName("filterCriteria")
    @Expose
    private FilterCriteria filterCriteria;
    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getCallbackReference() {
        return callbackReference;
    }

    public void setCallbackReference(String callbackReference) {
        this.callbackReference = callbackReference;
    }

    public FilterCriteria getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(FilterCriteria filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

}