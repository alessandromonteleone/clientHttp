package it.unimore.dipi.iot.http.api.client.location.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.request.AreaCircle.AreaCircleDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.CircleNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.PostSubscriptionAreaCircleResponseDescriptor;
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

public class PostSubscriptionsAreaCircleProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;
    private Gson gson;
    public PostSubscriptionsAreaCircleProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void createNewSubscription(AreaCircleDescriptor areaCircleDescriptor) {
        try{
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
            String targetUrl = String.format("%s/subscriptions/area/circle", this.baseUrl);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(areaCircleDescriptor);

            //Create the HTTP Post Request
            HttpPost createLocationRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createLocationRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createLocationRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createLocationRequest);

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

                logger.info("Testing info response");

                gson = new GsonBuilder().create();
                PostSubscriptionAreaCircleResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString,PostSubscriptionAreaCircleResponseDescriptor.class);

                for (String a : responseDescriptor.getCircleNotificationSubscription().getAddress()){
                    System.out.println("address: " + a);
                }
                System.out.println("callbackData: " + responseDescriptor.getCircleNotificationSubscription().
                        getCallbackReference().getCallbackData());
                System.out.println("notifyURL: " + responseDescriptor.getCircleNotificationSubscription().
                        getCallbackReference().getNotifyURL());
                System.out.println("checkImmediate: " + responseDescriptor.getCircleNotificationSubscription().getCheckImmediate());
                System.out.println("clientCorrelator: " + responseDescriptor.getCircleNotificationSubscription().getClientCorrelator());
                System.out.println("enteringLeavingCriteria: " + responseDescriptor.getCircleNotificationSubscription().getEnteringLeavingCriteria());
                System.out.println("frequency: " + responseDescriptor.getCircleNotificationSubscription().getFrequency());
                System.out.println("latitude: " + responseDescriptor.getCircleNotificationSubscription().getLatitude());
                System.out.println("longitude: " + responseDescriptor.getCircleNotificationSubscription().getLongitude());
                System.out.println("radius: " + responseDescriptor.getCircleNotificationSubscription().getRadius());
                System.out.println("ResourceURL: " + responseDescriptor.getCircleNotificationSubscription().getResourceURL());
                System.out.println("trackingAccuracy: " + responseDescriptor.getCircleNotificationSubscription().getTrackingAccuracy());

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

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("1234");
        callbackReference.setNotifyURL("http://my.callback.com/location-area-circle/some-id");

        CircleNotificationSubscription circleNotificationSubscription = new CircleNotificationSubscription();
        circleNotificationSubscription.setClientCorrelator("1234");
        circleNotificationSubscription.setCallbackReference(callbackReference);

        List<String> address = new ArrayList<>();
        address.add("10.100.0.1");
        address.add("10.100.0.2");
        address.add("10.100.0.3");
        address.add("10.100.0.4");

        circleNotificationSubscription.setAddress(address);
        circleNotificationSubscription.setCheckImmediate(true);
        circleNotificationSubscription.setEnteringLeavingCriteria("Entering"); //Entering Leaving
        circleNotificationSubscription.setFrequency(1);
        circleNotificationSubscription.setLatitude(7.242999); //7.242999
        circleNotificationSubscription.setLongitude(43.433359);
        circleNotificationSubscription.setRadius(300);
        circleNotificationSubscription.setTrackingAccuracy(10);

        AreaCircleDescriptor areaCircleDescriptor = new AreaCircleDescriptor();
        areaCircleDescriptor.setCircleNotificationSubscription(circleNotificationSubscription);

        PostSubscriptionsAreaCircleProcess apiLocationProcess = new PostSubscriptionsAreaCircleProcess(baseUrl);

        //Create new Location
        apiLocationProcess.createNewSubscription(areaCircleDescriptor);
    }

}



