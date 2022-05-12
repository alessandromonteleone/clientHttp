package it.unimore.dipi.iot.http.api.client.location.model.request.distance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.DistanceNotificationSubscription;

public class DistanceRequestDescriptor {

    @SerializedName("distanceNotificationSubscription")
    @Expose
    private DistanceNotificationSubscription distanceNotificationSubscription;

    public DistanceNotificationSubscription getDistanceNotificationSubscription() {
        return distanceNotificationSubscription;
    }

    public void setDistanceNotificationSubscription(DistanceNotificationSubscription distanceNotificationSubscription) {
        this.distanceNotificationSubscription = distanceNotificationSubscription;
    }
}
