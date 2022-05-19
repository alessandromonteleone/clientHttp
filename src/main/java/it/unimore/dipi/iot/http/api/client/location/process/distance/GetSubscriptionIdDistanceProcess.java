package it.unimore.dipi.iot.http.api.client.location.process.distance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.DistanceNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.distance.GetSubscriptionIdDistanceResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.areaCircle.GetSubscriptionIdAreaCircleProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSubscriptionIdDistanceProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetSubscriptionIdDistanceProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetSubscriptionIdDistanceProcess(String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        this.baseUrl = baseUrl;
    }

    public void GetSubscriptionInfo(String subscriptionId) {
        try {
            ///location/v2/subscriptions/area/circle/1
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions/distance", subscriptionId);

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

                GetSubscriptionIdDistanceResponseDescriptor responseDescriptor = gson.fromJson
                        (bodyString, GetSubscriptionIdDistanceResponseDescriptor.class);

                DistanceNotificationSubscription distanceNotificationSubscription = responseDescriptor.
                        getDistanceNotificationSubscription();

                //print response
                for (String a : distanceNotificationSubscription.getMonitoredAddress()) {
                    System.out.println("Monitor Address: " + a);
                }
                for (String a : distanceNotificationSubscription.getReferenceAddress()) {
                    System.out.println("Reference Address: " + a);
                }

                System.out.println("CallbackData" + distanceNotificationSubscription.getCallbackReference().getCallbackData());
                System.out.println("Notify URLL" + distanceNotificationSubscription.getCallbackReference().getNotifyURL());
                System.out.println("checkImmediate: " + distanceNotificationSubscription.isCheckImmediate());
                System.out.println("clientCorrelator: " + distanceNotificationSubscription.getClientCorrelator());
                System.out.println("Criteria: " + distanceNotificationSubscription.getCriteria());
                System.out.println("distance: " + distanceNotificationSubscription.getDistance());
                System.out.println("frequency: " + distanceNotificationSubscription.getFrequency());
                System.out.println("ResourceURL: " + distanceNotificationSubscription.getResourceURL());
                System.out.println("trackingAccuracy: " + distanceNotificationSubscription.getTrackingAccuracy());


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
        GetSubscriptionIdDistanceProcess subscriptionIdProcess = new GetSubscriptionIdDistanceProcess(baseUrl);
        subscriptionIdProcess.GetSubscriptionInfo(subscriptionId);
    }

}
