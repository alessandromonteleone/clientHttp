package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AssociateId {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("value")
    @Expose
    private String value;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
