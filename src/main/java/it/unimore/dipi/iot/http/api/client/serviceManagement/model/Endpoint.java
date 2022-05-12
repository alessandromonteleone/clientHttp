package it.unimore.dipi.iot.http.api.client.serviceManagement.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Endpoint {

    @SerializedName("uris")
    @Expose
    private List<String> uris = null;

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

}