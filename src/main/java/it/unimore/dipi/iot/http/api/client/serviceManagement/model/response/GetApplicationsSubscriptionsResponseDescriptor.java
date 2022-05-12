package it.unimore.dipi.iot.http.api.client.serviceManagement.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.Links;

public class GetApplicationsSubscriptionsResponseDescriptor {

    @SerializedName("_links")
    @Expose

    private Links links;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }



}
