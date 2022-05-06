package it.unimore.dipi.iot.http.api.client.location.model.request.periodic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;

public class PostPeriodicRequestDescriptor {
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
