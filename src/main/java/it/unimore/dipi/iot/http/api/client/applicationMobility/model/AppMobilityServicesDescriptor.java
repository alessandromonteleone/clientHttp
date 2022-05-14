package it.unimore.dipi.iot.http.api.client.applicationMobility.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AppMobilityServicesDescriptor {

    @SerializedName("appMobilityServiceId")
    @Expose
    private String appMobilityServiceId;
    @SerializedName("deviceInformation")
    @Expose
    private List<DeviceInformation> deviceInformation = null;
    @SerializedName("expiryTime")
    @Expose
    private Integer expiryTime;
    @SerializedName("serviceConsumerId")
    @Expose
    private ServiceConsumerId serviceConsumerId;

    public String getAppMobilityServiceId() {
        return appMobilityServiceId;
    }

    public void setAppMobilityServiceId(String appMobilityServiceId) {
        this.appMobilityServiceId = appMobilityServiceId;
    }

    public List<DeviceInformation> getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(List<DeviceInformation> deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public Integer getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Integer expiryTime) {
        this.expiryTime = expiryTime;
    }

    public ServiceConsumerId getServiceConsumerId() {
        return serviceConsumerId;
    }

    public void setServiceConsumerId(ServiceConsumerId serviceConsumerId) {
        this.serviceConsumerId = serviceConsumerId;
    }

}
