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
    private Integer appMobilityServiceLevel;
    @SerializedName("contextTransferState")
    @Expose
    private Integer contextTransferState;

    public AssociateId getAssociateId() {
        return associateId;
    }

    public void setAssociateId(AssociateId associateId) {
        this.associateId = associateId;
    }

    public Integer getAppMobilityServiceLevel() {
        return appMobilityServiceLevel;
    }

    public void setAppMobilityServiceLevel(Integer appMobilityServiceLevel) {
        this.appMobilityServiceLevel = appMobilityServiceLevel;
    }

    public Integer getContextTransferState() {
        return contextTransferState;
    }

    public void setContextTransferState(Integer contextTransferState) {
        this.contextTransferState = contextTransferState;
    }

}
