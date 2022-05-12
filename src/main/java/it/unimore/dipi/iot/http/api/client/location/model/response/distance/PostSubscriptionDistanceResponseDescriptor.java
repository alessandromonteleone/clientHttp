package it.unimore.dipi.iot.http.api.client.location.model.response.distance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.DistanceNotificationSubscription;

public class PostSubscriptionDistanceResponseDescriptor {
    @SerializedName("distanceNotificationSubscription")
    @Expose
    DistanceNotificationSubscription distanceNotificationSubscription;

    public DistanceNotificationSubscription getDistanceNotificationSubscription() {
        return distanceNotificationSubscription;
    }

    public void setDistanceNotificationSubscription(DistanceNotificationSubscription distanceNotificationSubscription) {
        this.distanceNotificationSubscription = distanceNotificationSubscription;
    }
}
