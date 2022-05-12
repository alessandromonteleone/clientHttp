package it.unimore.dipi.iot.http.api.client.WLAN.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Subscription;

import java.util.List;

public class GetSubscriptionsResponseDescriptor {

    @SerializedName("_links")
    @Expose
    private Links links;

    @SerializedName("subscription")
    @Expose
    private List<Subscription> subscriptionsList;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Subscription> getSubscriptionsList() {
        return subscriptionsList;
    }

    public void setSubscriptionsList(List<Subscription> subscriptionsList) {
        this.subscriptionsList = subscriptionsList;
    }
}
