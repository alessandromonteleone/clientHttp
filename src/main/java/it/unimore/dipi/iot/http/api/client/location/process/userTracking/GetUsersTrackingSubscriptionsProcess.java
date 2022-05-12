package it.unimore.dipi.iot.http.api.client.location.process.userTracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.*;
import it.unimore.dipi.iot.http.api.client.location.model.response.periodic.GetPeriodicSubscriptionsResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.userTracking.GetUserTrackingSubscriptionResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.periodic.GetPeriodicSubscriptionsProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetUsersTrackingSubscriptionsProcess {

    final static protected Logger logger = LoggerFactory.getLogger( GetUsersTrackingSubscriptionsProcess.class);
    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetUsersTrackingSubscriptionsProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.baseUrl=baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }
    public void getSubscriptionsList() {
        try {
            //"https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/userTracking
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions/userTracking");

            //Create the HTTP GET Request
            HttpGet getRequest = new HttpGet(targetUrl);

            //Add Request Header
            getRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("Testing response info...");

                Gson gson = new GsonBuilder().create();

                GetUserTrackingSubscriptionResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetUserTrackingSubscriptionResponseDescriptor.class);

                NotificationSubscriptionListUserTracking notificationSubscriptionList =
                        responseDescriptor.getNotificationSubscriptionListUserTracking();

                List<UserTrackingSubscription> userTrackingSubscriptionList =
                        notificationSubscriptionList.getUserTrackingSubscriptionList();

                if( userTrackingSubscriptionList != null ){
                    //print response
                    int i = 0;
                    for ( UserTrackingSubscription u : userTrackingSubscriptionList ){
                        i=i+1;
                        System.out.println("\u001B[36m"+"Subscription UserTracking number " + i+"\u001B[0m");
                        System.out.println("Address: " + u.getAddress());

                        CallbackReference callbackReference = u.getCallbackReference();
                        System.out.println("notifyURL: " + callbackReference.getNotifyURL());

                        System.out.println("clientCorrelator: " + u.getClientCorrelator());

                        System.out.println("resourceURL: " + u.getResourceURL());

                        for (String event : u.getUserEventCriteria())
                            System.out.println("userEventCriteria: " + event);

                    }
                }

                System.out.println("resourceURL: " + notificationSubscriptionList.getResourceURL());


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";

        GetUsersTrackingSubscriptionsProcess subscriptionsProcess = new GetUsersTrackingSubscriptionsProcess(baseUrl);

        subscriptionsProcess.getSubscriptionsList();
    }
}

