package it.unimore.dipi.iot.http.api.client.WLAN.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationEvent {

    @SerializedName("threshold")
    @Expose
    private int threshold;
    @SerializedName("trigger")
    @Expose
    private int trigger;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getTrigger() {
        return trigger;
    }

    public void setTrigger(int trigger) {
        this.trigger = trigger;
    }
}
