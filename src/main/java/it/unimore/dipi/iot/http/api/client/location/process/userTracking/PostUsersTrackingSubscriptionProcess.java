package it.unimore.dipi.iot.http.api.client.location.process.userTracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.Link;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.UserTrackingSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.periodic.PeriodicRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.request.usersTracking.UserTrackingRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.periodic.PostPeriodicSubscriptionProcess;
import org.apache.http.Header;
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

public class PostUsersTrackingSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostUsersTrackingSubscriptionProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;
    private Gson gson;
    public PostUsersTrackingSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void createNewSubscription(UserTrackingRequestDescriptor userTrackingRequestDescriptor) {
        try{
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
            String targetUrl = String.format("%s/subscriptions/userTracking", this.baseUrl);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(userTrackingRequestDescriptor);

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
                UserTrackingRequestDescriptor userTrackingResponseDescriptor =
                        gson.fromJson(bodyString, UserTrackingRequestDescriptor.class);

                //testing response
                logger.info("Testing info response");
                UserTrackingSubscription userTrackingSubscription = userTrackingResponseDescriptor.getUserTrackingSubscription();
                System.out.println("address: " + userTrackingSubscription.getAddress());
                CallbackReference callbackReference = userTrackingSubscription.getCallbackReference();
                System.out.println("notifyURL: "+ callbackReference.getNotifyURL());
                System.out.println("clientCorrelator: " + userTrackingSubscription.getClientCorrelator());
                System.out.println("resourceURL: " +userTrackingSubscription.getResourceURL());
                for(String event : userTrackingSubscription.getUserEventCriteria() )
                    System.out.println("userEventCriteria: " + event);

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

        PostUsersTrackingSubscriptionProcess apiLocationProcess = new PostUsersTrackingSubscriptionProcess(baseUrl);

        UserTrackingRequestDescriptor requestDescriptor = new UserTrackingRequestDescriptor();

        UserTrackingSubscription userTrackingSubscription = new UserTrackingSubscription();
        userTrackingSubscription.setClientCorrelator("0123");

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setNotifyURL("http://my.callback.com/location-user-tracking/some-id");

        userTrackingSubscription.setCallbackReference(callbackReference);

        userTrackingSubscription.setAddress("10.100.0.1");

        List <String> userEventCriteria = new ArrayList<>();
        userEventCriteria.add("Entering");

        userTrackingSubscription.setUserEventCriteria(userEventCriteria);

        requestDescriptor.setUserTrackingSubscription(userTrackingSubscription);

        apiLocationProcess.createNewSubscription(requestDescriptor);

    }
}
