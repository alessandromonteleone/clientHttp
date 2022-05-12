package it.unimore.dipi.iot.http.api.client.location.model.request.usersTracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.UserTrackingSubscription;

public class UserTrackingRequestDescriptor {
    @SerializedName("userTrackingSubscription")
    @Expose
    private UserTrackingSubscription userTrackingSubscription;

    public UserTrackingSubscription getUserTrackingSubscription() {
        return userTrackingSubscription;
    }

    public void setUserTrackingSubscription(UserTrackingSubscription userTrackingSubscription) {
        this.userTrackingSubscription = userTrackingSubscription;
    }
}
