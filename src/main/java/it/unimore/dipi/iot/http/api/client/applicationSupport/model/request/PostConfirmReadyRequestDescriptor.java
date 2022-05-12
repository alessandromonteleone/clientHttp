package it.unimore.dipi.iot.http.api.client.applicationSupport.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostConfirmReadyRequestDescriptor {

    @SerializedName("indication")
    @Expose
    private String indication;

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }
}
