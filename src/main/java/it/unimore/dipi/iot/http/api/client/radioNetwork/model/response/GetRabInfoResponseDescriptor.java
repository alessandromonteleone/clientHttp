package it.unimore.dipi.iot.http.api.client.radioNetwork.model.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.CellUserInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.TimeStamp;

@Generated("jsonschema2pojo")
public class GetRabInfoResponseDescriptor {

    @SerializedName("appInstanceId")
    @Expose
    private String appInstanceId;
    @SerializedName("cellUserInfo")
    @Expose
    private List<CellUserInfo> cellUserInfo = null;
    @SerializedName("requestId")
    @Expose
    private String requestId;
    @SerializedName("timeStamp")
    @Expose
    private TimeStamp timeStamp;

    public String getAppInstanceId() {
        return appInstanceId;
    }

    public void setAppInstanceId(String appInstanceId) {
        this.appInstanceId = appInstanceId;
    }

    public List<CellUserInfo> getCellUserInfo() {
        return cellUserInfo;
    }

    public void setCellUserInfo(List<CellUserInfo> cellUserInfo) {
        this.cellUserInfo = cellUserInfo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

}