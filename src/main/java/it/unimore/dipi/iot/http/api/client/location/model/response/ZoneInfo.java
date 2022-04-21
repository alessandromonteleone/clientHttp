package it.unimore.dipi.iot.http.api.client.location.model.response;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ZoneInfo {

    @SerializedName("numberOfAccessPoints")
    @Expose
    private int numberOfAccessPoints;

    @SerializedName("numberOfAccessPoints")
    @Expose
    private int numberOfUnserviceableAccessPoints;

    @SerializedName("numberOfUsers")
    @Expose
    private int numberOfUsers;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("zoneId")
    @Expose
    private String zoneId;

    public int getNumberOfAccessPoints() {
        return numberOfAccessPoints;
    }

    public void setNumberOfAccessPoints(int numberOfAccessPoints) {
        this.numberOfAccessPoints = numberOfAccessPoints;
    }

    public int getNumberOfUnserviceableAccessPoints() {
        return numberOfUnserviceableAccessPoints;
    }

    public void setNumberOfUnserviceableAccessPoints(int numberOfUnserviceableAccessPoints) {
        this.numberOfUnserviceableAccessPoints = numberOfUnserviceableAccessPoints;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
