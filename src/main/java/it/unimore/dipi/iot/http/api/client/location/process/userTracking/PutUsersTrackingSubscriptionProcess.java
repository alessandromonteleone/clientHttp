package it.unimore.dipi.iot.http.api.client.location.process.userTracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.UserTrackingSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.usersTracking.UserTrackingRequestDescriptor;

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

public class PutUsersTrackingSubscriptionProcess {
    final static protected Logger logger = LoggerFactory.getLogger(PutUsersTrackingSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PutUsersTrackingSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, UserTrackingRequestDescriptor requestDescriptor) {

        try {

            String targetUrl = String.format("%s/subscriptions/userTracking/%s", this.baseUrl, subscriptionId);
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

                UserTrackingRequestDescriptor userTrackingResponseDescriptor =
                        gson.fromJson(bodyString,UserTrackingRequestDescriptor.class);

                UserTrackingSubscription userTrackingSubscription =
                        userTrackingResponseDescriptor.getUserTrackingSubscription();

                System.out.println("Address: " + userTrackingSubscription.getAddress());

                CallbackReference callbackReference = userTrackingSubscription.getCallbackReference();

                System.out.println("notifyURL: " + callbackReference.getNotifyURL());

                System.out.println("clientCorrelator: " + userTrackingSubscription.getClientCorrelator());

                System.out.println("resourceURL: " + userTrackingSubscription.getResourceURL());

                for (String event : userTrackingSubscription.getUserEventCriteria())
                    System.out.println("userEventCriteria: " + event);


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
        String subscriptionId = "4";

        //process
        PutUsersTrackingSubscriptionProcess subscriptionProcess = new PutUsersTrackingSubscriptionProcess(baseUrl);

        //request Descriptor
        UserTrackingRequestDescriptor userTrackingRequestDescriptor = new UserTrackingRequestDescriptor();

        UserTrackingSubscription userTrackingSubscription = new UserTrackingSubscription();

        userTrackingSubscription.setClientCorrelator("0123");

        //attenzione id finale
        userTrackingSubscription.setResourceURL("http://[hostIP]/sbox-xyz123/location/v2/subscriptions/userTracking/4");

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setNotifyURL("http://my.callback.com/location-user-tracking/some-id");

        userTrackingSubscription.setCallbackReference(callbackReference);

        userTrackingSubscription.setAddress("10.100.0.1");

        List<String> userEventCriteriaList = new ArrayList<>();
        userEventCriteriaList.add("Entering");

        userTrackingSubscription.setUserEventCriteria(userEventCriteriaList);

        userTrackingRequestDescriptor.setUserTrackingSubscription(userTrackingSubscription);

        subscriptionProcess.UpdateSubscription(subscriptionId, userTrackingRequestDescriptor);

    }
}
