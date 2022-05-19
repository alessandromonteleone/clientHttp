package it.unimore.dipi.iot.http.api.client.location.process.zonalTraffic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.ZonalTrafficSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.zonalTraffic.ZonalTrafficSubscriptionDescriptor;
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

public class PutZonalTrafficSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PutZonalTrafficSubscriptionProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public PutZonalTrafficSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, ZonalTrafficSubscriptionDescriptor requestDescriptor) {

        try {

            String targetUrl = String.format("%s/subscriptions/%s/%s", this.baseUrl,"zonalTraffic", subscriptionId);
            logger.info("Target Url: {}", targetUrl);

            Gson gson = new GsonBuilder().create();
            String jsonBody = gson.toJson(requestDescriptor);

            //Create the HTTP Put Request
            HttpPut createRequest = new HttpPut(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the Request
            CloseableHttpResponse response = httpClient.execute(createRequest);


            // 200 "ok"
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



                ZonalTrafficSubscriptionDescriptor responseDescriptor = gson.fromJson(bodyString, ZonalTrafficSubscriptionDescriptor.class);

                logger.info("testing Response...");

                System.out.println("\nzonalTrafficSubscription:");
                System.out.println("    callbackReference:");
                System.out.println("        notifyURL: " + responseDescriptor.getZonalTrafficSubscription().getCallbackReference().getNotifyURL());
                System.out.println("    clientCorrelator: " + responseDescriptor.getZonalTrafficSubscription().getClientCorrelator());
                System.out.println("    resourceURL: " + responseDescriptor.getZonalTrafficSubscription().getResourceURL());
                System.out.println("    userEventCriteria: ");
                for (String criteria : responseDescriptor.getZonalTrafficSubscription().getUserEventCriteria())
                    System.out.println("        " +  criteria);
                System.out.println("    zoneId: " + responseDescriptor.getZonalTrafficSubscription().getZoneId() + "\n");



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
        String subscriptionId = "2";

        //process
        PutZonalTrafficSubscriptionProcess putZonalTrafficSubscriptionProcess =
                new PutZonalTrafficSubscriptionProcess(baseUrl);

        //request Descriptor
        ZonalTrafficSubscription zonalTrafficSubscription = new ZonalTrafficSubscription();

        zonalTrafficSubscription.setClientCorrelator("0123");

        // http://[hostIP]/sbox-xyz123/location/v2/subscriptions/zonalTraffic/{subscriptionId}
        zonalTrafficSubscription.setResourceURL(String.format("http://[hostIP]/sbox-xyz123/location/v2/subscriptions/zonalTraffic/%s",subscriptionId));

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setNotifyURL("http://79ee-62-211-88-203.eu.ngrok.io/location/zonalTraffic/002");
        zonalTrafficSubscription.setCallbackReference(callbackReference);

        zonalTrafficSubscription.setZoneId("zone02");

        List<String> userEventCriteriaList = new ArrayList<>();
        userEventCriteriaList.add("Entering");

        zonalTrafficSubscription.setUserEventCriteria(userEventCriteriaList);

        ZonalTrafficSubscriptionDescriptor requestDescriptor = new ZonalTrafficSubscriptionDescriptor();

        requestDescriptor.setZonalTrafficSubscription(zonalTrafficSubscription);

        //PUT .../subscriptions/zonalTraffic/{subscriptionId}
        putZonalTrafficSubscriptionProcess.UpdateSubscription(subscriptionId,requestDescriptor);

    }
}
