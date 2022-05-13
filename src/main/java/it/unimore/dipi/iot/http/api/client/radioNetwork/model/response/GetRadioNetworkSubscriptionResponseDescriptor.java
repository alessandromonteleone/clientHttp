package it.unimore.dipi.iot.http.api.client.radioNetwork.model.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.Links;

@Generated("jsonschema2pojo")
    public class GetRadioNetworkSubscriptionResponseDescriptor {

        @SerializedName("_links")
        @Expose
        private Links links;

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }
    }

