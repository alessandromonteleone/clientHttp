package it.unimore.dipi.iot.http.api.client.WLAN.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.NotificationEvent;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Self;
import it.unimore.dipi.iot.http.api.client.WLAN.model.request.PutSubscriptionRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.PostSubscriptionResponseDescriptor;
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

import java.util.Arrays;
import java.util.Optional;

public class PutSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PutSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PutSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, PutSubscriptionRequestDescriptor requestDescriptor){

        try{

            String targetUrl = String.format("%s/subscriptions/%s", this.baseUrl,subscriptionId);
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


            //La sandbox Risponde con 200 "ok" e non 204 "no content"
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

                PostSubscriptionResponseDescriptor subscriptionResponseDescriptor = gson.fromJson(
                        bodyString, PostSubscriptionResponseDescriptor.class);

                System.out.println("Href: " + subscriptionResponseDescriptor.getLinks().getSelf().getHref());
                System.out.println("Bssid: " + subscriptionResponseDescriptor.getApId().getBssid());
                System.out.println("callbackReference: " + subscriptionResponseDescriptor.getCallbackReference());
                System.out.println("threshold: " + subscriptionResponseDescriptor.getNotificationEvent().getThreshold());
                System.out.println("trigger: " + subscriptionResponseDescriptor.getNotificationEvent().getTrigger());
                System.out.println("subscriptionType: " + subscriptionResponseDescriptor.getSubscriptionType());


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
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2";
        String subscriptionId = "sub-FTu-ySks_U130v-1";

        //process
        PutSubscriptionProcess subscriptionProcess = new PutSubscriptionProcess(baseUrl);

        //request Descriptor
        PutSubscriptionRequestDescriptor requestDescriptor = new PutSubscriptionRequestDescriptor();

        requestDescriptor.setSubscriptionType("AssocStaSubscription");
        requestDescriptor.setCallbackReference("http://bc42-79-32-252-29.eu.ngrok.io/wai/v2/notifications/001");

        Self self = new Self();
        self.setHref("http://meAppServer.example.com/wai/v2/subscriptions/sub-EzYzskzGM8GxyvEF");

        Links links = new Links();
        links.setSelf(self);

        requestDescriptor.set_links(links);

        ApId apId = new ApId();
        apId.setBssid("005C0A0A0A0A");
        requestDescriptor.setApId(apId);

        NotificationEvent notificationEvent= new NotificationEvent();
        notificationEvent.setThreshold(1);
        notificationEvent.setTrigger(2);
        requestDescriptor.setNotificationEvent(notificationEvent);

        subscriptionProcess.UpdateSubscription(subscriptionId, requestDescriptor);
    }
}
