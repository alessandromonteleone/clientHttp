package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class GetAppMobilitySubscriptionResponseDescriptor {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("subscription")
    @Expose
    private List<Subscription> subscription = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Subscription> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<Subscription> subscription) {
        this.subscription = subscription;
    }

}