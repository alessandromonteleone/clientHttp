package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class FilterCriteria {

    @SerializedName("appInstanceId")
    @Expose
    private String appInstanceId;
    @SerializedName("associateId")
    @Expose
    private List<AssociateId> associateId = null;
    @SerializedName("mobilityStatus")
    @Expose
    private List<Integer> mobilityStatus = null;

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

    public List<AssociateId> getAssociateId() {
        return associateId;
    }

    public void setAssociateId(List<AssociateId> associateId) {
        this.associateId = associateId;
    }

    public List<Integer> getMobilityStatus() {
        return mobilityStatus;
    }

    public void setMobilityStatus(List<Integer> mobilityStatus) {
        this.mobilityStatus = mobilityStatus;
    }

}
