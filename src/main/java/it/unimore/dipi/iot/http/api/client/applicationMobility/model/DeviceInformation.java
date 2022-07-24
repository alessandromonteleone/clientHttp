package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class DeviceInformation {

    @SerializedName("associateId")
    @Expose
    private AssociateId associateId;
    @SerializedName("appMobilityServiceLevel")
    @Expose
    private String appMobilityServiceLevel;
    @SerializedName("contextTransferState")
    @Expose
    private String contextTransferState;

    public AssociateId getAssociateId() {
        return associateId;
    }

    public void setAssociateId(AssociateId associateId) {
        this.associateId = associateId;
    }

    public String getAppMobilityServiceLevel() {
        return appMobilityServiceLevel;
    }

    public void setAppMobilityServiceLevel(String appMobilityServiceLevel) {
        this.appMobilityServiceLevel = appMobilityServiceLevel;
    }

    public String getContextTransferState() {
        return contextTransferState;
    }

    public void setContextTransferState(String contextTransferState) {
        this.contextTransferState = contextTransferState;
    }

}
