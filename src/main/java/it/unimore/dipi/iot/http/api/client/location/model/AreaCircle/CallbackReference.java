package it.unimore.dipi.iot.http.api.client.location.model.AreaCircle;

public class CallbackReference {
    private String callbackData;
    private String notifyURL;

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