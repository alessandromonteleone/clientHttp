package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CellUserInfo {

    @SerializedName("ecgi")
    @Expose
    private Ecgi ecgi;
    @SerializedName("ueInfo")
    @Expose
    private List<UeInfo> ueInfo = null;

    public Ecgi getEcgi() {
        return ecgi;
    }

    public void setEcgi(Ecgi ecgi) {
        this.ecgi = ecgi;
    }

    public List<UeInfo> getUeInfo() {
        return ueInfo;
    }

    public void setUeInfo(List<UeInfo> ueInfo) {
        this.ueInfo = ueInfo;
    }

}