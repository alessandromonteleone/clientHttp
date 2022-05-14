package it.unimore.dipi.iot.http.api.client.location.process.zonalTraffic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.ZonalTrafficSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.zonalTraffic.ZonalTrafficSubscriptionDescriptor;
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

public class PostZonalTrafficSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostZonalTrafficSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostZonalTrafficSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void CreateZonalTrafficSubscription(ZonalTrafficSubscriptionDescriptor requestDescriptor) {
        try{
            //baseURL/subscriptions/zonalTraffic
            String targetUrl = String.format("%s/%s/%s",baseUrl,"subscriptions","zonalTraffic");

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(requestDescriptor);

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
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 =  Arrays.stream(response.getHeaders("http")).findFirst() ;
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info("Response Header: {}", Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Header: {}", Header);
                logger.info("testing response...");

                Gson gson = new GsonBuilder().create();

                ZonalTrafficSubscriptionDescriptor responseDescriptor = gson.fromJson(bodyString, ZonalTrafficSubscriptionDescriptor.class);

                System.out.println("\nzonalTrafficSubscription:");
                System.out.println("    callbackReference:");
                System.out.println("        notifyURL: " + responseDescriptor.getZonalTrafficSubscription().getCallbackReference().getNotifyURL());
                System.out.println("    clientCorrelator: " + responseDescriptor.getZonalTrafficSubscription().getClientCorrelator());
                System.out.println("    resourceURL: " + responseDescriptor.getZonalTrafficSubscription().getResourceURL());
                System.out.println("    userEventCriteria: ");
                for (String criteria : responseDescriptor.getZonalTrafficSubscription().getUserEventCriteria())
                    System.out.println("        " +  criteria);
                System.out.println("    zoneId: " + responseDescriptor.getZonalTrafficSubscription().getZoneId() + "\n");

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

        logger.info("Starting Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";

        PostZonalTrafficSubscriptionProcess subscriptionProcess = new PostZonalTrafficSubscriptionProcess(baseUrl);

        ZonalTrafficSubscription zonalTrafficSubscription = new ZonalTrafficSubscription();

        zonalTrafficSubscription.setClientCorrelator("0123");

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setNotifyURL("http://my.callback.com/location-zonal-traffic/some-id");
        zonalTrafficSubscription.setCallbackReference(callbackReference);

        zonalTrafficSubscription.setZoneId("zone01");

        List<String> userEventCriteriaList = new ArrayList<>();
        userEventCriteriaList.add("Entering");

        zonalTrafficSubscription.setUserEventCriteria(userEventCriteriaList);

        ZonalTrafficSubscriptionDescriptor requestDescriptor = new ZonalTrafficSubscriptionDescriptor();

        requestDescriptor.setZonalTrafficSubscription(zonalTrafficSubscription);

        //POST .../subscriptions/zonalTraffic
        subscriptionProcess.CreateZonalTrafficSubscription(requestDescriptor);


    }

}
