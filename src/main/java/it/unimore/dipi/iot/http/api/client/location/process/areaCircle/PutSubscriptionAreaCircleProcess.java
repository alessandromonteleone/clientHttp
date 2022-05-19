package it.unimore.dipi.iot.http.api.client.location.process.areaCircle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.NotificationEvent;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Self;
import it.unimore.dipi.iot.http.api.client.WLAN.model.request.PutSubscriptionRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.PostSubscriptionResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.WLAN.process.PostSubscriptionProcess;
import it.unimore.dipi.iot.http.api.client.WLAN.process.PutSubscriptionProcess;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.AreaCircle.AreaCircleRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.areaCircle.PostSubscriptionAreaCircleResponseDescriptor;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
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

public class PutSubscriptionAreaCircleProcess {
    final static protected Logger logger = LoggerFactory.getLogger(PutSubscriptionAreaCircleProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PutSubscriptionAreaCircleProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, AreaCircleRequestDescriptor requestDescriptor){

        try{

            String targetUrl = String.format("%s/subscriptions/area/circle/%s", this.baseUrl,subscriptionId);
            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(requestDescriptor);

            //Create the HTTP Put Request
            HttpPut createRequest = new HttpPut(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the Request
            CloseableHttpResponse response = httpClient.execute(createRequest);


            //La sandbox Risponde con 200 "ok"
            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 =  Arrays.stream(response.getHeaders("http")).findFirst() ;
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info(Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);

                Gson gson = new GsonBuilder().create();

                PostSubscriptionAreaCircleResponseDescriptor responseDescriptor = gson.fromJson(
                        bodyString, PostSubscriptionAreaCircleResponseDescriptor.class);

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

        logger.info("Starting Location Tester ...");
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        String subscriptionId = "1";

        //process
        PutSubscriptionAreaCircleProcess subscriptionProcess = new PutSubscriptionAreaCircleProcess(baseUrl);

        //request Descriptor
        AreaCircleRequestDescriptor requestDescriptor = new AreaCircleRequestDescriptor();

        CircleNotificationSubscription circleNotificationSubscription = new CircleNotificationSubscription();

        circleNotificationSubscription.setClientCorrelator("0123");

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("1234");
        callbackReference.setNotifyURL("http://my.callback.com/location-area-circle/some-id");

        circleNotificationSubscription.setCallbackReference(callbackReference);

        List<String> address = new ArrayList<>();
        address.add("10.100.0.1");

        circleNotificationSubscription.setAddress(address);

        circleNotificationSubscription.setCheckImmediate(true);

        circleNotificationSubscription.setEnteringLeavingCriteria("Entering");

        circleNotificationSubscription.setFrequency(1);

        circleNotificationSubscription.setLatitude(43.748993);

        circleNotificationSubscription.setLongitude(7.437573);

        circleNotificationSubscription.setRadius(200);

        circleNotificationSubscription.setResourceURL
                ("http://[hostIP]/sbox-xyz123/location/v2/subscriptions/area/circle/subscription/1");

        circleNotificationSubscription.setTrackingAccuracy(10);

        requestDescriptor.setCircleNotificationSubscription(circleNotificationSubscription);

        subscriptionProcess.UpdateSubscription(subscriptionId, requestDescriptor);
    }
}
