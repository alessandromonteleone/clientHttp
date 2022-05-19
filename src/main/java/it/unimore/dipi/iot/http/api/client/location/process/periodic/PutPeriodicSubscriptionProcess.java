package it.unimore.dipi.iot.http.api.client.location.process.periodic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.process.PostSubscriptionProcess;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.Link;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.periodic.PeriodicRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.periodic.GetPeriodicSubscriptionIdResponseDescriptor;
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

public class PutPeriodicSubscriptionProcess {
    final static protected Logger logger = LoggerFactory.getLogger(PostSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PutPeriodicSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, PeriodicRequestDescriptor requestDescriptor) {

        try {

            String targetUrl = String.format("%s/subscriptions/periodic/%s", this.baseUrl, subscriptionId);
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

                GetPeriodicSubscriptionIdResponseDescriptor periodicResponse = gson.
                        fromJson(bodyString, GetPeriodicSubscriptionIdResponseDescriptor.class);

                PeriodicNotificationSubscription periodicNotificationSubscription =
                        periodicResponse.getPeriodicNotificationSubscription();

                CallbackReference callbackReference = periodicNotificationSubscription.getCallbackReference();
                System.out.println("callbackData: " + callbackReference.getCallbackData());
                System.out.println("notifyURL: " + callbackReference.getNotifyURL());
                System.out.println("notificationFormat: " + callbackReference.getNotificationFormat());

                for(String a : periodicNotificationSubscription.getAddress())
                    System.out.println("address: " + a);

                System.out.println("clientCorrelator: " + periodicNotificationSubscription.getClientCorrelator());
                System.out.println("duration: " + periodicNotificationSubscription.getDuration());
                System.out.println("frequency: " + periodicNotificationSubscription.getFrequency());

                for (Link l : periodicNotificationSubscription.getLink())
                    System.out.println("href: " + l.getHref() + "\nrel: " + l.getRel() );

                System.out.println("RequestAccuracy: " + periodicNotificationSubscription.getRequestedAccuracy());
                System.out.println("Requester: " + periodicNotificationSubscription.getRequester());
                System.out.println("resourceURL: " + periodicNotificationSubscription.getResourceURL());



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
        String subscriptionId = "1";

        //process
        PutPeriodicSubscriptionProcess subscriptionProcess = new PutPeriodicSubscriptionProcess(baseUrl);

        //request Descriptor
        PeriodicRequestDescriptor periodicRequestDescriptor = new PeriodicRequestDescriptor();
        PeriodicNotificationSubscription periodicNotificationSubscription = new PeriodicNotificationSubscription();

        List<String> address = new ArrayList<>();
        address.add("10.100.0.1");
        address.add("10.1.0.1");
        periodicNotificationSubscription.setAddress(address);

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("1234");
        callbackReference.setNotifyURL("http://79ee-62-211-88-203.eu.ngrok.io/location/periodic");
        callbackReference.setNotificationFormat("XML");

        periodicNotificationSubscription.setCallbackReference(callbackReference);


        periodicNotificationSubscription.setClientCorrelator("0123");
        periodicNotificationSubscription.setDuration(0); //0 is default
        periodicNotificationSubscription.setFrequency(60);

        Link link = new Link();
        link.setRel("");
        link.setHref("");
        List<Link> linkList = new ArrayList<>();
        linkList.add(link);

        periodicNotificationSubscription.setLink(linkList);

        periodicNotificationSubscription.setRequestedAccuracy(0);
        periodicNotificationSubscription.setRequester("a");

        // set the correct subscriptionID in link ( ../periodic/subscriptionID )
        periodicNotificationSubscription.setResourceURL
                ("https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/periodic/2");

        periodicRequestDescriptor.setPeriodicNotificationSubscription(periodicNotificationSubscription);

        subscriptionProcess.UpdateSubscription(subscriptionId,periodicRequestDescriptor);
    }
}
