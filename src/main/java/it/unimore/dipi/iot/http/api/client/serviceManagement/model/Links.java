package it.unimore.dipi.iot.http.api.client.serviceManagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;

    @SerializedName("subscriptions")
    @Expose
    private List<Subscriptions> subscriptionsList;

    public List<Subscriptions> getSubscriptionsList() {
        return subscriptionsList;
    }

    public void setSubscriptionsList(List<Subscriptions> subscriptionsList) {
        this.subscriptionsList = subscriptionsList;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }
}
