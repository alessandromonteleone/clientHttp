package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ErabInfo {

    @SerializedName("erabId")
    @Expose
    private Integer erabId;
    @SerializedName("erabQosParameters")
    @Expose
    private ErabQosParameters erabQosParameters;

    public Integer getErabId() {
        return erabId;
    }

    public void setErabId(Integer erabId) {
        this.erabId = erabId;
    }

    public ErabQosParameters getErabQosParameters() {
        return erabQosParameters;
    }

    public void setErabQosParameters(ErabQosParameters erabQosParameters) {
        this.erabQosParameters = erabQosParameters;
    }

}
