package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Ecgi {

    @SerializedName("cellId")
    @Expose
    private String cellId;
    @SerializedName("plmn")
    @Expose
    private Plmn plmn;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public Plmn getPlmn() {
        return plmn;
    }

    public void setPlmn(Plmn plmn) {
        this.plmn = plmn;
    }

}