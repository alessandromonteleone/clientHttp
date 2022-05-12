package it.unimore.dipi.iot.http.api.client.location.model.response.areaCircle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;

public class PostSubscriptionAreaCircleResponseDescriptor {
    @SerializedName("circleNotificationSubscription")
    @Expose
    private CircleNotificationSubscription circleNotificationSubscription;

    public CircleNotificationSubscription getCircleNotificationSubscription() {
        return circleNotificationSubscription;
    }

    public void setCircleNotificationSubscription(CircleNotificationSubscription circleNotificationSubscription) {
        this.circleNotificationSubscription = circleNotificationSubscription;
    }
}
