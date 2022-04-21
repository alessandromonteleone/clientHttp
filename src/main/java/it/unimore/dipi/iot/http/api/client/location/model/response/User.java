package it.unimore.dipi.iot.http.api.client.location.model.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class User {

    @SerializedName("accessPointId")
    @Expose
    private String accessPointId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("locationInfo")
    @Expose
    private LocationInfo locationInfo;
    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;
    @SerializedName("timestamp")
    @Expose
    private TimeStamp timestamp;
    @SerializedName("zoneId")
    @Expose
    private String zoneId;

    public String getAccessPointId() {
        return accessPointId;
    }

    public void setAccessPointId(String accessPointId) {
        this.accessPointId = accessPointId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public TimeStamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TimeStamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}