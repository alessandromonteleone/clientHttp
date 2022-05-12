package it.unimore.dipi.iot.http.api.client.location.process.userTracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.Link;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.UserTrackingSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.periodic.GetPeriodicSubscriptionIdResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.userTracking.GetUserTrackingSubscriptionIdResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.periodic.GetPeriodicSubscriptionIdProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUsersTrackingSubscriptionIdProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetUsersTrackingSubscriptionIdProcess.class);
    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetUsersTrackingSubscriptionIdProcess(String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = baseUrl;
    }

    public void GetSubscriptionInfo(String subscriptionId) {
        try {
            ///location/v2/subscriptions/area/circle/1
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions/userTracking", subscriptionId);

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

                GetUserTrackingSubscriptionIdResponseDescriptor UserTrackingResponse = gson.
                        fromJson(bodyString, GetUserTrackingSubscriptionIdResponseDescriptor.class);

                UserTrackingSubscription userTrackingSubscription = UserTrackingResponse.getUserTrackingSubscription();

                System.out.println("Address: " + userTrackingSubscription.getAddress());

                CallbackReference callbackReference = userTrackingSubscription.getCallbackReference();

                System.out.println("notifyURL: " + callbackReference.getNotifyURL());

                System.out.println("clientCorrelator: " + userTrackingSubscription.getClientCorrelator());

                System.out.println("resourceURL: " + userTrackingSubscription.getResourceURL());

                for (String event : userTrackingSubscription.getUserEventCriteria())
                    System.out.println("userEventCriteria: " + event);


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
        String subscriptionId = "3";
        GetUsersTrackingSubscriptionIdProcess subscriptionIdProcess = new GetUsersTrackingSubscriptionIdProcess(baseUrl);
        subscriptionIdProcess.GetSubscriptionInfo(subscriptionId);
    }
}
