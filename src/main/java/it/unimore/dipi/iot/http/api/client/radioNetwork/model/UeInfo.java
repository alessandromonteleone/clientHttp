package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UeInfo {

    @SerializedName("associateId")
    @Expose
    private List<AssociateId> associateId = null;
    @SerializedName("erabInfo")
    @Expose
    private List<ErabInfo> erabInfo = null;

    public List<AssociateId> getAssociateId() {
        return associateId;
    }

    public void setAssociateId(List<AssociateId> associateId) {
        this.associateId = associateId;
    }

    public List<ErabInfo> getErabInfo() {
        return erabInfo;
    }

    public void setErabInfo(List<ErabInfo> erabInfo) {
        this.erabInfo = erabInfo;
    }

}