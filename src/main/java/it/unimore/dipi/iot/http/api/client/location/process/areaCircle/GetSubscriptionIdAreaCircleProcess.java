package it.unimore.dipi.iot.http.api.client.location.process.areaCircle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.areaCircle.GetSubscriptionIdAreaCircleResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSubscriptionIdAreaCircleProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetSubscriptionIdAreaCircleProcess.class);
    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetSubscriptionIdAreaCircleProcess(String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = baseUrl;
    }

    public void GetSubscriptionInfo(String subscriptionId) {
        try {
            ///location/v2/subscriptions/area/circle/1
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions/area/circle", subscriptionId);

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

                GetSubscriptionIdAreaCircleResponseDescriptor responseDescriptor = gson.fromJson
                        (bodyString, GetSubscriptionIdAreaCircleResponseDescriptor.class);

                CircleNotificationSubscription circleNotificationSubscription = responseDescriptor.getCircleNotificationSubscription();

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


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        String subscriptionId = "1";
        GetSubscriptionIdAreaCircleProcess subscriptionIdProcess = new GetSubscriptionIdAreaCircleProcess(baseUrl);
        subscriptionIdProcess.GetSubscriptionInfo(subscriptionId);
    }
}
