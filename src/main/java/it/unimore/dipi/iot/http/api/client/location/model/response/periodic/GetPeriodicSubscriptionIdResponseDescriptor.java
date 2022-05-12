package it.unimore.dipi.iot.http.api.client.location.model.response.periodic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.periodic.PeriodicRequestDescriptor;

public class GetPeriodicSubscriptionIdResponseDescriptor {
    @SerializedName("periodicNotificationSubscription")
    @Expose
    private PeriodicNotificationSubscription periodicNotificationSubscription;

    public PeriodicNotificationSubscription getPeriodicNotificationSubscription() {
        return periodicNotificationSubscription;
    }

    public void setPeriodicNotificationSubscription(PeriodicNotificationSubscription periodicNotificationSubscription) {
        this.periodicNotificationSubscription = periodicNotificationSubscription;
    }
}
