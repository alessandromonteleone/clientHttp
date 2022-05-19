package it.unimore.dipi.iot.http.api.client.location.process.zoneStatus;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.ZoneStatusSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.zoneStatus.ZoneStatusSubscriptionDescriptor;
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

    public class PostZoneStatusSubscriptionProcess {

        final static protected Logger logger = LoggerFactory.getLogger(PostZoneStatusSubscriptionProcess.class);

        private final CloseableHttpClient httpClient;
        private final String baseUrl;

        public PostZoneStatusSubscriptionProcess(String baseUrl) {
            this.baseUrl = baseUrl;
            this.httpClient = HttpClients.custom().build();
        }

        public void CreateZoneStatusSubscription(ZoneStatusSubscriptionDescriptor requestDescriptor) {
            try {
                //baseURL/subscriptions/zoneStatus
                String targetUrl = String.format("%s/%s/%s", baseUrl, "subscriptions", "zoneStatus");

                logger.info("Target Url: {}", targetUrl);

                Gson gson = new GsonBuilder().create();
                String jsonBody = gson.toJson(requestDescriptor);

                //Create the HTTP Post Request
                HttpPost createRequest = new HttpPost(targetUrl);

                //Add Content Type Header
                createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

                //Set Payload
                createRequest.setEntity(new StringEntity(jsonBody));

                //Execute the GetRequest
                CloseableHttpResponse response = httpClient.execute(createRequest);

                if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                    //Obtain response body as a String
                    String bodyString = EntityUtils.toString(response.getEntity());

                    //Extract the Header
                    //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                    String Header = "";
                    Optional<org.apache.http.Header> opt1 = Arrays.stream(response.getHeaders("http")).findFirst();
                    if (opt1.isPresent()) {
                        Header = opt1.get().getValue();
                        logger.info("Response Header: {}", Header);
                    }

                    logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                    logger.info("Response Header: {}", Header);

                    logger.info("testing response...");



                    ZoneStatusSubscriptionDescriptor zoneStatusSubscription = gson.fromJson(bodyString, ZoneStatusSubscriptionDescriptor.class);

                    System.out.println("\nzoneStatusSubscription: ");
                    System.out.println("    callbackReference: ");
                    System.out.println("        notifyURL: " + zoneStatusSubscription.getZoneStatusSubscription().getCallbackReference().getNotifyURL());
                    System.out.println("   clientCorrelator: " + zoneStatusSubscription.getZoneStatusSubscription().getClientCorrelator());
                    System.out.println("    numberOfUsersZoneThreshold: " + zoneStatusSubscription.getZoneStatusSubscription().getNumberOfUsersZoneThreshold());
                    System.out.println("    operationStatus: ");
                    for (String operationStatus : zoneStatusSubscription.getZoneStatusSubscription().getOperationStatusList())
                        System.out.println("        " + operationStatus);
                    System.out.println("    resourceURL:" + zoneStatusSubscription.getZoneStatusSubscription().getResourceURL());
                    System.out.println("    zoneId: " + zoneStatusSubscription.getZoneStatusSubscription().getZoneId() + "\n");

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

            logger.info("Starting  Create Tester ...");

            String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
            PostZoneStatusSubscriptionProcess zoneStatusSubscriptionProcess = new PostZoneStatusSubscriptionProcess(baseUrl);

            ZoneStatusSubscription zoneStatusSubscription = new ZoneStatusSubscription();

            CallbackReference callbackReference = new CallbackReference();
            callbackReference.setNotifyURL("http://79ee-62-211-88-203.eu.ngrok.io/location/zoneStatus/0123");
            zoneStatusSubscription.setCallbackReference(callbackReference);

            zoneStatusSubscription.setClientCorrelator("0123");

            zoneStatusSubscription.setNumberOfUsersZoneThreshold(1);

            List<String> operationStatusList = new ArrayList<>();
            operationStatusList.add("Serviceable");
            zoneStatusSubscription.setOperationStatusList(operationStatusList);

            zoneStatusSubscription.setZoneId("zone01");

            ZoneStatusSubscriptionDescriptor zoneStatusSubscriptionDescriptor = new ZoneStatusSubscriptionDescriptor();
            zoneStatusSubscriptionDescriptor.setZoneStatusSubscription(zoneStatusSubscription);

            //POST .../subscriptions/zoneStatus
            zoneStatusSubscriptionProcess.CreateZoneStatusSubscription(zoneStatusSubscriptionDescriptor);


        }
    }
