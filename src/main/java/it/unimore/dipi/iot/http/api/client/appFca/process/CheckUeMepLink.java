package it.unimore.dipi.iot.http.api.client.appFca.process;

import it.unimore.dipi.iot.http.api.client.location.model.UserList;
import it.unimore.dipi.iot.http.api.client.location.process.queries.GetUsersProcess;

import java.util.ArrayList;
import java.util.Objects;

public class CheckUeMepLink {

    String baseUrlMep1 = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
    String baseUrlMep2 = "https://try-mec.etsi.org/sbx1hio0m7/mep2/location/v2";

    public CheckUeMepLink() {
    }

    public String getMepId (String carIp){
        ArrayList<String> address = new ArrayList<>();
        address.add(carIp);

        GetUsersProcess apiLocationProcess = new GetUsersProcess(baseUrlMep1);
        UserList userList = apiLocationProcess.GetLocationLookup(new ArrayList<>(), new ArrayList<>(),address);


        if (! Objects.isNull(userList.getUser()) )
            return "mep1";
        else {
            apiLocationProcess = new GetUsersProcess(baseUrlMep2);
            userList = apiLocationProcess.GetLocationLookup(new ArrayList<>(), new ArrayList<>(),address);
            if (!Objects.isNull(userList.getUser()))
                return "mep2";
        }
        System.out.println("erroreeee");
        return null;
    }

    public static void main(String[] args) {
        CheckUeMepLink checkUeMepLink = new CheckUeMepLink();

        System.out.println(checkUeMepLink.getMepId("10.100.0.1"));
    }


}
