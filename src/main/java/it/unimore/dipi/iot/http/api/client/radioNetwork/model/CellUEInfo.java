package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CellUEInfo {

    @SerializedName("associateId")
    @Expose
    private AssociateId associateId;
    @SerializedName("dl_nongbr_delay_ue")
    @Expose
    private Integer dlNongbrDelayUe;
    @SerializedName("ecgi")
    @Expose
    private Ecgi ecgi;
    @SerializedName("ul_nongbr_delay_ue")
    @Expose
    private Integer ulNongbrDelayUe;

    public AssociateId getAssociateId() {
        return associateId;
    }

    public void setAssociateId(AssociateId associateId) {
        this.associateId = associateId;
    }

    public Integer getDlNongbrDelayUe() {
        return dlNongbrDelayUe;
    }

    public void setDlNongbrDelayUe(Integer dlNongbrDelayUe) {
        this.dlNongbrDelayUe = dlNongbrDelayUe;
    }

    public Ecgi getEcgi() {
        return ecgi;
    }

    public void setEcgi(Ecgi ecgi) {
        this.ecgi = ecgi;
    }

    public Integer getUlNongbrDelayUe() {
        return ulNongbrDelayUe;
    }

    public void setUlNongbrDelayUe(Integer ulNongbrDelayUe) {
        this.ulNongbrDelayUe = ulNongbrDelayUe;
    }

}
