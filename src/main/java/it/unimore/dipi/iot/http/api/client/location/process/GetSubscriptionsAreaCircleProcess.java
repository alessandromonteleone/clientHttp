package it.unimore.dipi.iot.http.api.client.location.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Subscription;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.GetSubscriptionsResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionList;
import it.unimore.dipi.iot.http.api.client.location.model.response.GetSubscriptionsAreaCircleResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.GetSubscriptionsAreaCircleResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSubscriptionsAreaCircleProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetSubscriptionsAreaCircleProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.baseUrl=baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }
    public void getSubscriptionsList(){
        try {
            //"https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/area/circle"
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions/area/circle");

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
                GetSubscriptionsAreaCircleResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetSubscriptionsAreaCircleResponseDescriptor.class);

                NotificationSubscriptionList notificationSubscriptionList = responseDescriptor.getNotificationSubscriptionList();
                System.out.println("resourceURL: "+ notificationSubscriptionList.getResourceURL());

                if (  notificationSubscriptionList.getCircleNotificationSubscription() != null){
                    int i = 0;
                    for(CircleNotificationSubscription circleNotificationSubscription :
                            notificationSubscriptionList.getCircleNotificationSubscription()) {
                        i=i+1;
                        System.out.println("\u001B[32m"+"Subscription Circle Number: " + i+"\u001B[0m");
                        for (String a : circleNotificationSubscription.getAddress()) {
                            System.out.println("Address: " + a);
                        }

                        System.out.println("CallbackData" + circleNotificationSubscription.getCallbackReference().getCallbackData());
                        System.out.println("Notify URLL" + circleNotificationSubscription.getCallbackReference().getNotifyURL());
                        System.out.println("checkImmediate: " + circleNotificationSubscription.getCheckImmediate());
                        System.out.println("clientCorrelator: " + circleNotificationSubscription.getClientCorrelator());
                        System.out.println("enteringLeavingCriteria: " + circleNotificationSubscription.getEnteringLeavingCriteria());
                        System.out.println("frequency: " + circleNotificationSubscription.getFrequency());
                        System.out.println("latitude: " + circleNotificationSubscription.getLatitude());
                        System.out.println("longitude: " + circleNotificationSubscription.getLongitude());
                        System.out.println("radius: " +circleNotificationSubscription.getRadius());
                        System.out.println("ResourceURL: " + circleNotificationSubscription.getResourceURL());
                        System.out.println("trackingAccuracy: " + circleNotificationSubscription.getTrackingAccuracy());

                    }
                }


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        GetSubscriptionsAreaCircleProcess subscriptionsProcess = new GetSubscriptionsAreaCircleProcess(baseUrl);
        subscriptionsProcess.getSubscriptionsList();
    }

}
