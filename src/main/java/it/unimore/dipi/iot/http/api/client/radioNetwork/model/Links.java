package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class Links {

    @SerializedName("self")
    @Expose
    private Self self;

    @SerializedName("subscription")
    @Expose
    private List<Subscription> subscription = null;

    public List<Subscription> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<Subscription> subscription) {
        this.subscription = subscription;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

}