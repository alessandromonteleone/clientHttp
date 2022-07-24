package it.unimore.dipi.iot.http.api.client.appFca.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.appFca.model.ChangeMepNotificationDescriptor;
import it.unimore.dipi.iot.http.api.client.appFca.model.Timestamp;
import it.unimore.dipi.iot.http.api.client.location.model.User;
import it.unimore.dipi.iot.http.api.client.location.model.UserList;
import it.unimore.dipi.iot.http.api.client.location.process.queries.GetUsersProcess;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.ServicesDescriptor;
import it.unimore.dipi.iot.http.api.client.serviceManagement.process.services.GetServicesProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ClientAppFca {

    static public final String carIp="10.100.0.1";
    static public String mecServiceManagementLink = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";
    static public String mecLocationLink = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";

    static public String mecServiceManagementLink2 = "https://try-mec.etsi.org/sbx1hio0m7/mep2/mec_service_mgmt/v1";
    static public String mecLocationLink2 = "https://try-mec.etsi.org/sbx1hio0m7/mep2/location/v2";

    static public String serName = "AppFca";
    static public String serId = "AppFcaId2022";
    static CloseableHttpClient httpClient = HttpClients.custom().build();
    static URIBuilder builder;
    static HttpGet getRequest;
    static HttpPost postRequest;
    static CloseableHttpResponse response;
    static String bodyString;

    static public String serMngLink;
    static public String locationLink;


    public static void main(String[] args) throws URISyntaxException, IOException {

        // 1) ottengo lista servizi

        CheckUeMepLink checkUeMepLink = new CheckUeMepLink();
        String oldMecUe = null;
        Scanner scanner = new Scanner(System.in);
        int state = 1;
        List<String> linkAppFca = null;





        while (state != 0){
            System.out.println("""
                     0 EXIT
                     1 ALL PARKING INFO
                     2 INFO OF A PARKING
                     3 DISTANCE PARKING
                     4 DATA HISTORY
                    """);

            state = scanner.nextInt();
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //cerco a quale mep è collegato l' Ue
            System.out.println("--------------------///////////////////////////////////////////////////////////////////---------------");
            System.out.println("\u001B[32m"+"1-Cerco il mep al quale è collegato lo user"+ "\u001B[0m");

            String mepUe = checkUeMepLink.getMepId(carIp);

            //verifico se effettuare la notifica per il trasferimento di stato
            if (!Objects.equals(mepUe, oldMecUe) && oldMecUe !=null){
                System.out.println("------------------TRASFERIMENTO DI STATO-----------------");

                ChangeMepNotificationDescriptor changeMepNotificationDescriptor = new ChangeMepNotificationDescriptor();
                changeMepNotificationDescriptor.setMep(mepUe);
                changeMepNotificationDescriptor.setClientIp(carIp);
                Timestamp timestamp = new Timestamp();
                timestamp.setNanoseconds(System.currentTimeMillis()*1000.0);
                timestamp.setSeconds(System.currentTimeMillis()/1000.0);
                changeMepNotificationDescriptor.setTimestamp(timestamp);

                assert linkAppFca != null;
                builder = new URIBuilder(String.format("%s/%s",linkAppFca.get(0),"changeMepNotification"));
                ObjectMapper objectMapper = new ObjectMapper();

                String jsonBody = objectMapper.writeValueAsString(changeMepNotificationDescriptor);

                postRequest = new HttpPost(builder.build());
                postRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                postRequest.addHeader(HttpHeaders.USER_AGENT, "ClientAppFca");
                //set payload
                postRequest.setEntity(new StringEntity(jsonBody));
                //esecuzione richiesta
                CloseableHttpResponse response = httpClient.execute(postRequest);
                bodyString = EntityUtils.toString(response.getEntity());
                System.out.println("Post change mep state response: " + bodyString);

            }

            oldMecUe = mepUe;



            System.out.println("\u001B[32m"+ "\nCollegato al " + mepUe + "\u001B[0m");
            if (mepUe == null) {
                System.out.println("Error Mep Ue not found !!");
                break;
            }
            if (mepUe.equals("mep1")){
                serMngLink = mecServiceManagementLink;
                locationLink = mecLocationLink;
            }
            else if (mepUe.equals("mep2")){
                serMngLink = mecServiceManagementLink2;
                locationLink = mecLocationLink2;
            }
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

             // Cerco il mio servizio Appfca nel mep al quale è collegato l'Ue
            System.out.println("\u001B[32m"+ "\n2-Cerco nella lista dei servizi del mep selezionato il mio servizio appFca" + mepUe + "\u001B[0m");
            GetServicesProcess listServiceProcess = new GetServicesProcess(serMngLink);
            Boolean consumedLocalOnly = null;
            Boolean isLocal = null;
            String scopeOfLocality = null;
            ServicesDescriptor[] servicesDescriptors = listServiceProcess.getAllServices(consumedLocalOnly, isLocal, scopeOfLocality);

            //List<String> linkAppFca = null;
            for (ServicesDescriptor s : servicesDescriptors){
                if (Objects.equals(s.getSerName(), serName) && Objects.equals(s.getSerCategory().getId(), serId)){
                    linkAppFca = s.getTransportInfo().getEndpoint().getUris();
                }
            }
            if (linkAppFca != null) {

                System.out.println("\u001B[32m"+"3-Effettuo richiesta al mio servizio tramite il link" +
                        " ottenuto dalla lista dei servizi\n" + linkAppFca + "\u001B[0m");

                switch (state) {
                    case 1 -> {
                        builder = new URIBuilder(linkAppFca.get(0));
                        builder.addParameter("clientIp", carIp);
                        getRequest = new HttpGet(builder.build());
                        getRequest.addHeader(HttpHeaders.USER_AGENT, "ClientAppFca");

                        response = httpClient.execute(getRequest);
                        bodyString = EntityUtils.toString(response.getEntity());
                        System.out.println("Parking in Monaco: \n" + bodyString);
                    }
                    case 2 -> {
                        System.out.println("parkingId: ");
                        int n = scanner.nextInt();
                        System.out.println(n);
                        String targetURL = String.valueOf(String.format("%s/%s", linkAppFca.get(0), n));
                        builder = new URIBuilder(targetURL);
                        System.out.println(targetURL);
                        builder.addParameter("clientIp", carIp);
                        getRequest = new HttpGet(builder.build());
                        getRequest.addHeader(HttpHeaders.USER_AGENT, "ClientAppFca");
                        CloseableHttpResponse response = httpClient.execute(getRequest);
                        bodyString = EntityUtils.toString(response.getEntity());
                        System.out.println("Parking info:\n" + bodyString);
                    }
                    case 3 -> {
                        ArrayList<String> address = new ArrayList<>() {
                            {
                                add(carIp);
                            }
                        };
                        GetUsersProcess usersProcess = new GetUsersProcess(locationLink);
                        UserList userList = usersProcess.GetLocationLookup(new ArrayList<>(), new ArrayList<>(), address);
                        if (userList != null) {
                            for (User user : userList.getUser()) {
                                if (Objects.equals(user.getAddress(), carIp)) {
                                    builder = new URIBuilder(String.format("%s/distance", linkAppFca.get(0)));
                                    builder.addParameter("latitude", String.valueOf(user.getLocationInfo().getLatitude().get(0)));
                                    builder.addParameter("longitude", String.valueOf(user.getLocationInfo().getLongitude().get(0)));
                                    builder.addParameter("clientIp", carIp);
                                    getRequest = new HttpGet(builder.build());
                                    getRequest.addHeader(HttpHeaders.USER_AGENT, "ClientAppFca");
                                    response = httpClient.execute(getRequest);
                                    bodyString = EntityUtils.toString(response.getEntity());
                                    System.out.println("Distance in meters:\n" + bodyString);
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Car Address Not Found");
                        }
                    }
                    case 4 ->{

                        String targetURL = String.valueOf(String.format("%s/%s", linkAppFca.get(0),"userProfile"));
                        builder = new URIBuilder(targetURL);
                        System.out.println(targetURL);
                        builder.addParameter("clientIp", carIp);
                        getRequest = new HttpGet(builder.build());
                        getRequest.addHeader(HttpHeaders.USER_AGENT, "ClientAppFca");
                        CloseableHttpResponse response = httpClient.execute(getRequest);
                        bodyString = EntityUtils.toString(response.getEntity());
                        System.out.println("Data history:\n" + bodyString);

                    }
                    default -> state = 0;
                }
            }

        }
    }
}

