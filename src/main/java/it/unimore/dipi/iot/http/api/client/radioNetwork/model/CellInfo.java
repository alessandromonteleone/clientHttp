package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CellInfo {

    @SerializedName("ecgi")
    @Expose
    private Ecgi ecgi;
    @SerializedName("number_of_active_ue_dl_nongbr_cell")
    @Expose
    private Integer numberOfActiveUeDlNongbrCell;
    @SerializedName("number_of_active_ue_ul_nongbr_cell")
    @Expose
    private Integer numberOfActiveUeUlNongbrCell;

    public Ecgi getEcgi() {
        return ecgi;
    }

    public void setEcgi(Ecgi ecgi) {
        this.ecgi = ecgi;
    }

    public Integer getNumberOfActiveUeDlNongbrCell() {
        return numberOfActiveUeDlNongbrCell;
    }

    public void setNumberOfActiveUeDlNongbrCell(Integer numberOfActiveUeDlNongbrCell) {
        this.numberOfActiveUeDlNongbrCell = numberOfActiveUeDlNongbrCell;
    }

    public Integer getNumberOfActiveUeUlNongbrCell() {
        return numberOfActiveUeUlNongbrCell;
    }

    public void setNumberOfActiveUeUlNongbrCell(Integer numberOfActiveUeUlNongbrCell) {
        this.numberOfActiveUeUlNongbrCell = numberOfActiveUeUlNongbrCell;
    }

}