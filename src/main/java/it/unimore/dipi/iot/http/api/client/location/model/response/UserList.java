package it.unimore.dipi.iot.http.api.client.location.model.response;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UserList {

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;
    @SerializedName("user")
    @Expose
    private List<User> user = null;

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

}