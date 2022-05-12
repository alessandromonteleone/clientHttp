package it.unimore.dipi.iot.http.api.client.applicationSupport.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.Links;

import java.util.List;

public class GetApplicationsSubscriptionsResponseDescriptor {

    @SerializedName("_links")
    @Expose
    private Links link;

    public Links getLink() {
        return link;
    }

    public void setLink(Links link) {
        this.link = link;
    }
}
