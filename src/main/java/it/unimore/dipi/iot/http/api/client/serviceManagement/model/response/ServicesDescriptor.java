package it.unimore.dipi.iot.http.api.client.serviceManagement.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.SerCategory;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.TransportInfo;

import javax.annotation.Generated;


@Generated("jsonschema2pojo")
public class ServicesResponseDescriptor {

    @SerializedName("serInstanceId")
    @Expose
    private String serInstanceId;
    @SerializedName("serName")
    @Expose
    private String serName;
    @SerializedName("serCategory")
    @Expose
    private SerCategory serCategory;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("transportInfo")
    @Expose
    private TransportInfo transportInfo;
    @SerializedName("serializer")
    @Expose
    private String serializer;
    @SerializedName("scopeOfLocality")
    @Expose
    private String scopeOfLocality;
    @SerializedName("consumedLocalOnly")
    @Expose
    private Boolean consumedLocalOnly;
    @SerializedName("isLocal")
    @Expose
    private Boolean isLocal;

    public String getSerInstanceId() {
        return serInstanceId;
    }

    public void setSerInstanceId(String serInstanceId) {
        this.serInstanceId = serInstanceId;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public SerCategory getSerCategory() {
        return serCategory;
    }

    public void setSerCategory(SerCategory serCategory) {
        this.serCategory = serCategory;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TransportInfo getTransportInfo() {
        return transportInfo;
    }

    public void setTransportInfo(TransportInfo transportInfo) {
        this.transportInfo = transportInfo;
    }

    public String getSerializer() {
        return serializer;
    }

    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }

    public String getScopeOfLocality() {
        return scopeOfLocality;
    }

    public void setScopeOfLocality(String scopeOfLocality) {
        this.scopeOfLocality = scopeOfLocality;
    }

    public Boolean getConsumedLocalOnly() {
        return consumedLocalOnly;
    }

    public void setConsumedLocalOnly(Boolean consumedLocalOnly) {
        this.consumedLocalOnly = consumedLocalOnly;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }


}
