package it.unimore.dipi.iot.http.api.client.location.process.distance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.process.PostSubscriptionProcess;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.DistanceNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.AreaCircle.AreaCircleRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.request.distance.DistanceRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.areaCircle.PostSubscriptionAreaCircleResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.distance.PostSubscriptionDistanceResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.areaCircle.PutSubscriptionAreaCircleProcess;
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

public class PutSubscriptionDistanceProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostSubscriptionProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public PutSubscriptionDistanceProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, DistanceRequestDescriptor requestDescriptor) {

        try {

            String targetUrl = String.format("%s/subscriptions/distance/%s", this.baseUrl, subscriptionId);
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
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 = Arrays.stream(response.getHeaders("http")).findFirst();
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info(Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);

                Gson gson = new GsonBuilder().create();

                PostSubscriptionDistanceResponseDescriptor responseDescriptor = gson.fromJson(
                        bodyString, PostSubscriptionDistanceResponseDescriptor.class);

                DistanceNotificationSubscription distanceNotificationSubscription = responseDescriptor.getDistanceNotificationSubscription();

                for (String a : distanceNotificationSubscription.getMonitoredAddress())
                    System.out.println("MonitoredAddress: " + a);

                for (String a : distanceNotificationSubscription.getReferenceAddress())
                    System.out.println("ReferenceAddress: " + a);


                System.out.println("CallbackData" + distanceNotificationSubscription.getCallbackReference().getCallbackData());
                System.out.println("Notify URLL" + distanceNotificationSubscription.getCallbackReference().getNotifyURL());
                System.out.println("checkImmediate: " + distanceNotificationSubscription.isCheckImmediate());
                System.out.println("clientCorrelator: " + distanceNotificationSubscription.getClientCorrelator());
                System.out.println("Criteria: " + distanceNotificationSubscription.getCriteria());
                System.out.println("frequency: " + distanceNotificationSubscription.getFrequency());
                System.out.println("distance: " + distanceNotificationSubscription.getDistance());
                System.out.println("frequency: " + distanceNotificationSubscription.getFrequency());
                System.out.println("ResourceURL: " + distanceNotificationSubscription.getResourceURL());
                System.out.println("trackingAccuracy: " + distanceNotificationSubscription.getTrackingAccuracy());

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        logger.info("Starting IoT Inventory Location Create Tester ...");
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        String subscriptionId = "6";

        //process
        PutSubscriptionDistanceProcess subscriptionDistanceProcess = new PutSubscriptionDistanceProcess(baseUrl);

        //request Descriptor
        DistanceRequestDescriptor requestDescriptor = new DistanceRequestDescriptor();

        DistanceNotificationSubscription distanceNotificationSubscription = new DistanceNotificationSubscription();

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("1234");
        callbackReference.setNotifyURL("http://my.callback.com/location-distance/some-id");

        distanceNotificationSubscription.setCallbackReference(callbackReference);

        List<String> monitoredAddress = new ArrayList<>();
        monitoredAddress.add("10.100.0.1");
        monitoredAddress.add("10.1.0.1");

        List<String> referencedAddress = new ArrayList<>();
        referencedAddress.add("10.100.0.1");

        distanceNotificationSubscription.setMonitoredAddress(monitoredAddress);
        distanceNotificationSubscription.setReferenceAddress(referencedAddress);

        distanceNotificationSubscription.setCheckImmediate(true);
        distanceNotificationSubscription.setClientCorrelator("0123");
        distanceNotificationSubscription.setCriteria("AllWithinDistance");
        distanceNotificationSubscription.setDistance(100);
        distanceNotificationSubscription.setFrequency(10);

        //attenzione a far coincidere il carattere finale con l'id della richiesta
        distanceNotificationSubscription.setResourceURL
                ("https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/distance/6");

        distanceNotificationSubscription.setTrackingAccuracy(10);

        requestDescriptor.setDistanceNotificationSubscription(distanceNotificationSubscription);

        subscriptionDistanceProcess.UpdateSubscription(subscriptionId, requestDescriptor);
    }
}

