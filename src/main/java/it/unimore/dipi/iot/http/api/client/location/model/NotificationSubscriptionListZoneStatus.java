package it.unimore.dipi.iot.http.api.client.location.model;


import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class NotificationSubscriptionListZoneStatus {

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;
    @SerializedName("zoneStatusSubscription")
    @Expose
    private List<ZoneStatusSubscription> zoneStatusSubscription = null;

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public List<ZoneStatusSubscription> getZoneStatusSubscription() {
        return zoneStatusSubscription;
    }

    public void setZoneStatusSubscription(List<ZoneStatusSubscription> zoneStatusSubscription) {
        this.zoneStatusSubscription = zoneStatusSubscription;
    }

}
