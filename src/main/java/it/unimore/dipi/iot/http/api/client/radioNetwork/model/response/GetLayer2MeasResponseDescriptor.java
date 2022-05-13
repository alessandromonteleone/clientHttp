package it.unimore.dipi.iot.http.api.client.radioNetwork.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.CellInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.CellUEInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.TimeStamp;

import java.util.List;

public class GetLayer2MeasResponseDescriptor {

    @SerializedName("cellInfo")
    @Expose
    private List<CellInfo> cellInfo = null;
    @SerializedName("cellUEInfo")
    @Expose
    private List<CellUEInfo> cellUEInfo = null;
    @SerializedName("timeStamp")
    @Expose
    private TimeStamp timeStamp;

    public List<CellInfo> getCellInfo() {
        return cellInfo;
    }

    public void setCellInfo(List<CellInfo> cellInfo) {
        this.cellInfo = cellInfo;
    }

    public List<CellUEInfo> getCellUEInfo() {
        return cellUEInfo;
    }

    public void setCellUEInfo(List<CellUEInfo> cellUEInfo) {
        this.cellUEInfo = cellUEInfo;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

}
