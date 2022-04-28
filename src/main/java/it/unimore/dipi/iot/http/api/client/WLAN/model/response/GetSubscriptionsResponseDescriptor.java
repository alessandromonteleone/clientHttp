package it.unimore.dipi.iot.http.api.client.WLAN.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Subscription;

import java.util.List;

public class GetSubscriptionsResponseDescriptor {

    @SerializedName("_links")
    @Expose
    private Links _links;

    @SerializedName("subscription")
    @Expose
    private List<Subscription> subscriptionsList;

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public List<Subscription> getSubscriptionsList() {
        return subscriptionsList;
    }

    public void setSubscriptionsList(List<Subscription> subscriptionsList) {
        this.subscriptionsList = subscriptionsList;
    }
}
