package it.unimore.dipi.iot.http.api.client.applicationSupport.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//{
//  "subscriptionType": "AppTerminationNotificationSubscription",
//  "callbackReference": "https://232b-79-32-252-29.eu.ngrok.io/location-area-circle",
//  "appInstanceId": "2b3e30cb-4113-4ef6-9388-ef99247f5a34"
//}
public class PostApplicationSubscriptionRequestDescriptor {

    @SerializedName("subscriptionType")
    @Expose
    private String subscriptionType;

    @SerializedName("callbackReference")
    @Expose
    private String callbackReference;

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

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }
}
