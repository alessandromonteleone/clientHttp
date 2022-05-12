package it.unimore.dipi.iot.http.api.client.location.process.distance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.*;
import it.unimore.dipi.iot.http.api.client.location.model.response.distance.GetSubscriptionsDistanceResponseDescriptor;
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

public class GetSubscriptionsDistanceProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetSubscriptionsDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetSubscriptionsDistanceProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.baseUrl=baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }
    public void getSubscriptionsList() {
        try {
            //"https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/distance"
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions/distance");

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

                GetSubscriptionsDistanceResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetSubscriptionsDistanceResponseDescriptor.class);

                NotificationSubscriptionListDistance notificationSubscriptionList =
                        responseDescriptor.getNotificationSubscriptionListDistance();

                List<DistanceNotificationSubscription> distanceNotificationSubscriptionList =
                        notificationSubscriptionList.getDistanceNotificationSubscriptionList();

                if(distanceNotificationSubscriptionList!=null){
                //print response
                    int i = 0;
                    for (DistanceNotificationSubscription d : distanceNotificationSubscriptionList){
                        i=i+1;
                        System.out.println("\u001B[36m"+"Subscription Distance number " + i+"\u001B[0m");
                        CallbackReference callbackReference = d.getCallbackReference();
                        System.out.println("callbackData: " + callbackReference.getCallbackData());
                        System.out.println("notifyURL: " + callbackReference.getNotifyURL());
                        System.out.println("checkImmediate: " + d.isCheckImmediate());
                        System.out.println("clientCorrelator: " + d.getClientCorrelator());
                        System.out.println("criteria: " + d.getCriteria());
                        System.out.println("frequency: " + d.getFrequency());
                        for (String m : d.getMonitoredAddress())
                            System.out.println("MonitorAddress: " + m);

                        for (String r : d.getReferenceAddress())
                            System.out.println("ReferenceAddress" + r);

                        System.out.println("resourceURL: " + d.getResourceURL());
                        System.out.println("trackingAccuracy: " + d.getTrackingAccuracy());
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
        GetSubscriptionsDistanceProcess subscriptionsProcess = new GetSubscriptionsDistanceProcess(baseUrl);
        subscriptionsProcess.getSubscriptionsList();
    }
}

