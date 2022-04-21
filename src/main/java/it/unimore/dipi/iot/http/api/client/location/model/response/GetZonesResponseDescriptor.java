package it.unimore.dipi.iot.http.api.client.location.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetZonesResponseDescriptor {

    @SerializedName("zoneList")
    @Expose
    private ZoneList zoneList;

    public ZoneList getZoneList() {
        return zoneList;
    }

    public void setZoneList(ZoneList zoneList) {
        this.zoneList = zoneList;
    }
}
