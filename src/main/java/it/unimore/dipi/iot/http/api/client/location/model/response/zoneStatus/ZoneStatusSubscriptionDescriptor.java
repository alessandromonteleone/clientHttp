package it.unimore.dipi.iot.http.api.client.location.model.response.zoneStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.ZoneStatusSubscription;

public class ZoneStatusSubscriptionDescriptor {

    @SerializedName("zoneStatusSubscription")
    @Expose
    private ZoneStatusSubscription zoneStatusSubscription;

    public ZoneStatusSubscription getZoneStatusSubscription() {
        return zoneStatusSubscription;
    }

    public void setZoneStatusSubscription(ZoneStatusSubscription zoneStatusSubscription) {
        this.zoneStatusSubscription = zoneStatusSubscription;
    }
}
