package it.unimore.dipi.iot.http.api.client.location.process.distance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.DistanceNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.distance.DistanceRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.distance.PostSubscriptionDistanceResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PostSubscriptionDistanceProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostSubscriptionDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;
    private Gson gson;
    public PostSubscriptionDistanceProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void createNewSubscription(DistanceRequestDescriptor distanceRequestDescriptor) {
        try{
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
            String targetUrl = String.format("%s/subscriptions/distance", this.baseUrl);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(distanceRequestDescriptor);

            //Create the HTTP Post Request
            HttpPost createRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header

                //1) java.util.NoSuchElementException: No value present
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                //2)
                //soluzione 2
                String Header = "";
                Optional<org.apache.http.Header> opt1 =  Arrays.stream(response.getHeaders("http")).findFirst() ;
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info("Response Header: {}", Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);

                //create response gson
                gson = new GsonBuilder().create();
                PostSubscriptionDistanceResponseDescriptor subscriptionDistanceResponse =
                        gson.fromJson(bodyString, PostSubscriptionDistanceResponseDescriptor.class);

                //testing response
                logger.info("Testing info response");
                DistanceNotificationSubscription notificationSubscription =
                        subscriptionDistanceResponse.getDistanceNotificationSubscription();
                CallbackReference callbackReference = notificationSubscription.getCallbackReference();

                System.out.println("callbackData: " + callbackReference.getCallbackData());
                System.out.println("notifyURL: " + callbackReference.getNotifyURL());
                System.out.println("checkImmediate: " + notificationSubscription.isCheckImmediate());
                System.out.println("clientCorrelator: " + notificationSubscription.getClientCorrelator());
                System.out.println("criteria: " + notificationSubscription.getCriteria());
                System.out.println("Distance: "+ notificationSubscription.getDistance());
                System.out.println("Frequency: "+ notificationSubscription.getFrequency());
                for ( String a:notificationSubscription.getMonitoredAddress())
                    System.out.println("monitoredAddress: " + a);
                for ( String a:notificationSubscription.getReferenceAddress())
                    System.out.println("ReferencedAddress: " + a);
                System.out.println("ResourceURL: "+notificationSubscription.getResourceURL());
                System.out.println("trackingAccuracy: " + notificationSubscription.getTrackingAccuracy());

            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        logger.info("Starting IoT Inventory Location Create Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";

        DistanceNotificationSubscription distanceNotificationSubscription = new DistanceNotificationSubscription();
        distanceNotificationSubscription.setClientCorrelator("0123");

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("1234");
        callbackReference.setNotifyURL("http://my.callback.com/location-area-circle/some-id");

        distanceNotificationSubscription.setCallbackReference(callbackReference);

        List<String> monitoredAddress = new ArrayList<>();
        monitoredAddress.add("10.100.0.1");
        monitoredAddress.add("10.100.0.2");
        monitoredAddress.add("10.100.0.3");
        monitoredAddress.add("10.100.0.4");

        distanceNotificationSubscription.setMonitoredAddress(monitoredAddress);

        distanceNotificationSubscription.setCheckImmediate(true);

        distanceNotificationSubscription.setCriteria("AllWithinDistance");

        distanceNotificationSubscription.setDistance(100);

        distanceNotificationSubscription.setFrequency(10);

        List<String> referenceAddress = new ArrayList<>();
        referenceAddress.add("10.100.0.1");

        distanceNotificationSubscription.setReferenceAddress(referenceAddress);

        distanceNotificationSubscription.setTrackingAccuracy(10);

        DistanceRequestDescriptor distanceRequestDescriptor = new DistanceRequestDescriptor();
        distanceRequestDescriptor.setDistanceNotificationSubscription(distanceNotificationSubscription);

        PostSubscriptionDistanceProcess apiLocationProcess = new PostSubscriptionDistanceProcess(baseUrl);

        //Create new Location
        apiLocationProcess.createNewSubscription(distanceRequestDescriptor);
    }

}
