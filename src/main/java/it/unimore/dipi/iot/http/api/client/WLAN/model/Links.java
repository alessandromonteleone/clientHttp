package it.unimore.dipi.iot.http.api.client.WLAN.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Self;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Links {
    @SerializedName("self")
    @Expose
    private Self self;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }
}
