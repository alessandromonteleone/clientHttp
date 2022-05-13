package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ErabQosParameters {

    @SerializedName("qci")
    @Expose
    private Integer qci;

    public Integer getQci() {
        return qci;
    }

    public void setQci(Integer qci) {
        this.qci = qci;
    }

}