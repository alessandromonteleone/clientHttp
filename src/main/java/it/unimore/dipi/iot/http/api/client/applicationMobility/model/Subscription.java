package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Subscription {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("subscriptionType")
    @Expose
    private Integer subscriptionType;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(Integer subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

}