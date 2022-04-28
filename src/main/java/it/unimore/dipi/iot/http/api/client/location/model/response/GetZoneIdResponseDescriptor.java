package it.unimore.dipi.iot.http.api.client.location.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.ZoneInfo;


public class GetZoneIdResponseDescriptor {
    @SerializedName("zoneInfo")
    @Expose
    private ZoneInfo zoneInfo;

    public ZoneInfo getZoneInfo() {
        return zoneInfo;
    }

    public void setZoneInfo(ZoneInfo zoneInfo) {
        this.zoneInfo = zoneInfo;
    }
}
