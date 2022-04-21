package it.unimore.dipi.iot.http.api.client.location.model.response;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class LocationInfo {

    @SerializedName("latitude")
    @Expose
    private List<Double> latitude = null;
    @SerializedName("longitude")
    @Expose
    private List<Double> longitude = null;
    @SerializedName("shape")
    @Expose
    private Integer shape;
    @SerializedName("timestamp")
    @Expose
    private TimeStamp timestamp;

    public List<Double> getLatitude() {
        return latitude;
    }

    public void setLatitude(List<Double> latitude) {
        this.latitude = latitude;
    }

    public List<Double> getLongitude() {
        return longitude;
    }

    public void setLongitude(List<Double> longitude) {
        this.longitude = longitude;
    }

    public Integer getShape() {
        return shape;
    }

    public void setShape(Integer shape) {
        this.shape = shape;
    }

    public TimeStamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TimeStamp timestamp) {
        this.timestamp = timestamp;
    }

}