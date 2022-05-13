package it.unimore.dipi.iot.http.api.client.radioNetwork.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.Plmn;

import java.util.List;

public class GetPlmnInfoResponseDescriptor {

    @SerializedName("appInstanceId")
    @Expose
    private String appInstanceId;

    @SerializedName("plmn")
    @Expose
    private List<Plmn> plmnList;

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

    public List<Plmn> getPlmnList() {
        return plmnList;
    }

    public void setPlmnList(List<Plmn> plmnList) {
        this.plmnList = plmnList;
    }
}
