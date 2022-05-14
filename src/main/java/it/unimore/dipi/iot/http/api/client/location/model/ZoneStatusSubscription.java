package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZoneStatusSubscription {

    @SerializedName("clientCorrelator")
    @Expose
    private String clientCorrelator;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("callbackReference")
    @Expose
    private CallbackReference callbackReference;

    @SerializedName("zoneId")
    @Expose
    private String zoneId;

    @SerializedName("numberOfUsersZoneThreshold")
    @Expose
    private int numberOfUsersZoneThreshold;

    @SerializedName("operationStatus")
    @Expose
    private List<String> operationStatusList = null;

    public String getClientCorrelator() {
        return clientCorrelator;
    }

    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public CallbackReference getCallbackReference() {
        return callbackReference;
    }

    public void setCallbackReference(CallbackReference callbackReference) {
        this.callbackReference = callbackReference;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public int getNumberOfUsersZoneThreshold() {
        return numberOfUsersZoneThreshold;
    }

    public void setNumberOfUsersZoneThreshold(int numberOfUsersZoneThreshold) {
        this.numberOfUsersZoneThreshold = numberOfUsersZoneThreshold;
    }

    public List<String> getOperationStatusList() {
        return operationStatusList;
    }

    public void setOperationStatusList(List<String> operationStatusList) {
        this.operationStatusList = operationStatusList;
    }
}
