package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CallbackReference {

    private String callbackData;
    private String notifyURL;

    @SerializedName("notificationFormat")
    @Expose
    private String notificationFormat;

    public String getNotificationFormat() {
        return notificationFormat;
    }

    public void setNotificationFormat(String notificationFormat) {
        this.notificationFormat = notificationFormat;
    }

    public String getCallbackData() {
        return callbackData;
    }
    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
    public String getNotifyURL() {
        return notifyURL;
    }
    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }


    }
