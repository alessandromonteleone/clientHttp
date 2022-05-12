package it.unimore.dipi.iot.http.api.client.applicationSupport.model.response; ;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.Links;

@Generated("jsonschema2pojo")
public class PostApplicationSubscriptionResponseDescriptor {

    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;
    @SerializedName("callbackReference")
    @Expose
    private String callbackReference;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("appInstanceId")
    @Expose
    private String appInstanceId;

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

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

}