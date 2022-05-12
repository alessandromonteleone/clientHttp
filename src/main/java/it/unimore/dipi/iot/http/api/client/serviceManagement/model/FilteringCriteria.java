package it.unimore.dipi.iot.http.api.client.serviceManagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilteringCriteria {

    @SerializedName("serNames")
    @Expose
    private List<String> serNames = null;
    @SerializedName("states")
    @Expose
    private List<String> states = null;
    @SerializedName("isLocal")
    @Expose
    private Boolean isLocal;

    public List<String> getSerNames() {
        return serNames;
    }

    public void setSerNames(List<String> serNames) {
        this.serNames = serNames;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

}
