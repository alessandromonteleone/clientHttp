package it.unimore.dipi.iot.http.api.client.applicationSupport.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.TimeStamp;

public class GetTimingCapsResponseDescriptor {
    @SerializedName("timeStamp")
    @Expose
    private TimeStamp timeStamp;

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
