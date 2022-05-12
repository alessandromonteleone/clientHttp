package it.unimore.dipi.iot.http.api.client.applicationSupport.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@Generated("jsonschema2pojo")
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