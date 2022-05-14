package it.unimore.dipi.iot.http.api.client.location.model.response.zonalTraffic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.ZonalTrafficSubscription;

public class GetZonalTrafficSubscriptionIdResponseDescriptor {

    @SerializedName("zonalTrafficSubscription")
    @Expose
    private ZonalTrafficSubscription zonalTrafficSubscription;

    public ZonalTrafficSubscription getZonalTrafficSubscription() {
        return zonalTrafficSubscription;
    }

    public void setZonalTrafficSubscription(ZonalTrafficSubscription zonalTrafficSubscription) {
        this.zonalTrafficSubscription = zonalTrafficSubscription;
    }
}
