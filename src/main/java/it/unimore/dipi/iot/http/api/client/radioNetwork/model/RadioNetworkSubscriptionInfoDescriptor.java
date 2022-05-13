package it.unimore.dipi.iot.http.api.client.radioNetwork.model;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")

public class RadioNetworkSubscriptionInfoDescriptor {

        @SerializedName("_links")
        @Expose
        private Links links;
        @SerializedName("callbackReference")
        @Expose
        private String callbackReference;
        @SerializedName("filterCriteriaAssocHo")
        @Expose
        private FilterCriteriaAssocHo filterCriteriaAssocHo;
        @SerializedName("subscriptionType")
        @Expose
        private String subscriptionType;

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public String getCallbackReference() {
            return callbackReference;
        }

        public void setCallbackReference(String callbackReference) {
            this.callbackReference = callbackReference;
        }

        public FilterCriteriaAssocHo getFilterCriteriaAssocHo() {
            return filterCriteriaAssocHo;
        }

        public void setFilterCriteriaAssocHo(FilterCriteriaAssocHo filterCriteriaAssocHo) {
            this.filterCriteriaAssocHo = filterCriteriaAssocHo;
        }

        public String getSubscriptionType() {
            return subscriptionType;
        }

        public void setSubscriptionType(String subscriptionType) {
            this.subscriptionType = subscriptionType;
        }

}
