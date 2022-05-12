package it.unimore.dipi.iot.http.api.client.applicationSupport.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostConfirmTerminationRequestDescriptor {
    @SerializedName("operationAction")
    @Expose
    private String operationAction;

    public String getOperationAction() {
        return operationAction;
    }

    public void setOperationAction(String operationAction) {
        this.operationAction = operationAction;
    }
}
