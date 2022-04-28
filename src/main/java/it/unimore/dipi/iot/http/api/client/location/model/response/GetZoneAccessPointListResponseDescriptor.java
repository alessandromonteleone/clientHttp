package it.unimore.dipi.iot.http.api.client.location.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.AccessPointList;

public class GetZoneAccessPointListResponseDescriptor {
    @SerializedName("accessPointList")
    @Expose
    private AccessPointList accessPointList;

    public AccessPointList getAccessPointList() {
        return accessPointList;
    }

    public void setAccessPointList(AccessPointList accessPointList) {
        this.accessPointList = accessPointList;
    }
}
