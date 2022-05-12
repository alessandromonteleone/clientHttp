package it.unimore.dipi.iot.http.api.client.serviceManagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscriptions {

    @SerializedName("href")
    @Expose
    private String href;

    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
