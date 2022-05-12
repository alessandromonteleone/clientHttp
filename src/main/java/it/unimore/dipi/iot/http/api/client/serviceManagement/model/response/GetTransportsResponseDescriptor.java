package it.unimore.dipi.iot.http.api.client.serviceManagement.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.Endpoint;

public class GetTransportsResponseDescriptor {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("protocol")
    @Expose
    private String protocol;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("endpoint")
    @Expose
    private Endpoint endpoint;

    @SerializedName("security")
    @Expose
    private String security;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}
