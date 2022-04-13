package it.unimore.dipi.iot.http.api.client.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.location.model.AreaCircle.AreaCircleDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.AreaCircle.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.AreaCircle.CircleNotificationSubscription;
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

public class CreateSubscriptionsAreaCircleProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public CreateSubscriptionsAreaCircleProcess(String baseUrl) {
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
                String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();
                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);
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
       //{
        //  "circleNotificationSubscription": {
        //    "clientCorrelator": "0123",
        //    "callbackReference": {
        //      "callbackData": "1234",
        //      "notifyURL": "http://my.callback.com/location-area-circle/some-id"
        //    },
        //    "address": [
        //      "10.100.0.4"
        //    ],
        //    "checkImmediate": true,
        //    "enteringLeavingCriteria": "Entering",
        //    "frequency": 1,
        //    "latitude": 43.748993,
        //    "longitude": 7.437573,
        //    "radius": 200,
        //    "trackingAccuracy": 10
        //  }
        //}
        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("1234");
        callbackReference.setNotifyURL("http://my.callback.com/location-area-circle/some-id");

        CircleNotificationSubscription circleNotificationSubscription = new CircleNotificationSubscription();
        circleNotificationSubscription.setClientCorrelator("1234");
        circleNotificationSubscription.setCallbackReference(callbackReference);
        List<String> address = new ArrayList<>();
        address.add("10.100.0.4");
        circleNotificationSubscription.setAddress(address);
        circleNotificationSubscription.setCheckImmediate(true);
        circleNotificationSubscription.setEnteringLeavingCriteria("Entering");
        circleNotificationSubscription.setFrequency(1);
        circleNotificationSubscription.setLatitude(7.437573);
        circleNotificationSubscription.setLongitude(43.748993);
        circleNotificationSubscription.setRadius(200);
        circleNotificationSubscription.setTrackingAccuracy(10);

        AreaCircleDescriptor areaCircleDescriptor = new AreaCircleDescriptor();
        areaCircleDescriptor.setCircleNotificationSubscription(circleNotificationSubscription);

        CreateSubscriptionsAreaCircleProcess apiLocationProcess = new CreateSubscriptionsAreaCircleProcess(baseUrl);

        //Create new Location
        apiLocationProcess.createNewSubscription(areaCircleDescriptor);
    }

}



