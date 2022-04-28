package it.unimore.dipi.iot.http.api.client.WLAN.model.response;

import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApLocation;
import it.unimore.dipi.iot.http.api.client.WLAN.model.BssLoad;

public class GetApInformationResponseDescriptor {

    public ApId apId;
    public ApLocation apLocation;
    public BssLoad bssLoad;

    public ApId getApId() {
        return apId;
    }

    public void setApId(ApId apId) {
        this.apId = apId;
    }

    public ApLocation getApLocation() {
        return apLocation;
    }

    public void setApLocation(ApLocation apLocation) {
        this.apLocation = apLocation;
    }

    public BssLoad getBssLoad() {
        return bssLoad;
    }

    public void setBssLoad(BssLoad bssLoad) {
        this.bssLoad = bssLoad;
    }
}
