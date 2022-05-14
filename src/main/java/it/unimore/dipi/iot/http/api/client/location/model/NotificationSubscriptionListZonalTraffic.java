package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NotificationSubscriptionListZonalTraffic {

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("zonalTrafficSubscription")
    @Expose
    private List<ZonalTrafficSubscription> zonalTrafficSubscriptionList = null;

    @SerializedName("zoneId")
    @Expose
    private String zoneId;

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public List<ZonalTrafficSubscription> getZonalTrafficSubscriptionList() {
        return zonalTrafficSubscriptionList;
    }

    public void setZonalTrafficSubscriptionList(List<ZonalTrafficSubscription> zonalTrafficSubscriptionList) {
        this.zonalTrafficSubscriptionList = zonalTrafficSubscriptionList;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
