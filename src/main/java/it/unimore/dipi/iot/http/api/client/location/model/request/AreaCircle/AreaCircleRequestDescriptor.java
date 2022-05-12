package it.unimore.dipi.iot.http.api.client.location.model.request.AreaCircle;

import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;

public class AreaCircleRequestDescriptor {

    private CircleNotificationSubscription circleNotificationSubscription;

    public CircleNotificationSubscription getCircleNotificationSubscription() {
        return circleNotificationSubscription;
    }
    public void setCircleNotificationSubscription(CircleNotificationSubscription circleNotificationSubscription) {
        this.circleNotificationSubscription = circleNotificationSubscription;
    }
}

