package it.unimore.dipi.iot.http.api.client.serviceManagement.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.FilteringCriteria;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.Links;

public class PostApplicationsSubscriptionDescriptor {

    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;
    @SerializedName("callbackReference")
    @Expose
    private String callbackReference;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("filteringCriteria")
    @Expose
    private FilteringCriteria filteringCriteria;

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getCallbackReference() {
        return callbackReference;
    }

    public void setCallbackReference(String callbackReference) {
        this.callbackReference = callbackReference;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public FilteringCriteria getFilteringCriteria() {
        return filteringCriteria;
    }

    public void setFilteringCriteria(FilteringCriteria filteringCriteria) {
        this.filteringCriteria = filteringCriteria;
    }

}
