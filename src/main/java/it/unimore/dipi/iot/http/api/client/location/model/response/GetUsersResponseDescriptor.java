package it.unimore.dipi.iot.http.api.client.location.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.UserList;

public class GetUsersResponseDescriptor {
    @SerializedName("userList")
    @Expose
    private UserList userList;


    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }
}

