package it.unimore.dipi.iot.http.api.client.appFca.model;



public class ChangeMepNotificationDescriptor {

    //nuovo mep
    private String mep;

    private String clientIp;

    private Timestamp timestamp;



    public String getMep() {
        return mep;
    }

    public void setMep(String mep) {
        this.mep = mep;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
