package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class FilterCriteriaAssocHo {

    @SerializedName("associateId")
    @Expose
    private List<AssociateId> associateId = null;
    @SerializedName("ecgi")
    @Expose
    private List<Ecgi> ecgi = null;
    @SerializedName("hoStatus")
    @Expose
    private List<Integer> hoStatus = null;

    public List<AssociateId> getAssociateId() {
        return associateId;
    }

    public void setAssociateId(List<AssociateId> associateId) {
        this.associateId = associateId;
    }

    public List<Ecgi> getEcgi() {
        return ecgi;
    }

    public void setEcgi(List<Ecgi> ecgi) {
        this.ecgi = ecgi;
    }

    public List<Integer> getHoStatus() {
        return hoStatus;
    }

    public void setHoStatus(List<Integer> hoStatus) {
        this.hoStatus = hoStatus;
    }

}