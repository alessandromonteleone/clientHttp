package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ServiceConsumerId {

    @SerializedName("appInstanceId")
    @Expose
    private String appInstanceId;
    @SerializedName("mepId")
    @Expose
    private String mepId;

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

    public String getMepId() {
        return mepId;
    }

    public void setMepId(String mepId) {
        this.mepId = mepId;
    }

}