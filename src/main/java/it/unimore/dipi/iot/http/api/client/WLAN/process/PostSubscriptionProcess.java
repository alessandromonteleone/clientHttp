package it.unimore.dipi.iot.http.api.client.WLAN.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.NotificationEvent;
import it.unimore.dipi.iot.http.api.client.WLAN.model.request.PostSubscriptionRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.PostSubscriptionResponseDescriptor;
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

import java.util.Arrays;
import java.util.Optional;

public class PostSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void CreateNewSubscription(PostSubscriptionRequestDescriptor postSubscriptionRequestDescriptor){

        try{

            String targetUrl = String.format("%s/subscriptions", this.baseUrl);
            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(postSubscriptionRequestDescriptor);

            //Create the HTTP Post Request
            HttpPost createRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the Request
            CloseableHttpResponse response = httpClient.execute(createRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header

                //soluzione 1 --> riceve un no value error
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                //soluzione 2
                String Header = "";
                Optional<org.apache.http.Header> opt1 =  Arrays.stream(response.getHeaders("http")).findFirst() ;
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info(Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);

                Gson gson = new GsonBuilder().create();
                PostSubscriptionResponseDescriptor postSubscriptionResponseDescriptor =
                        gson.fromJson(bodyString,PostSubscriptionResponseDescriptor.class );
                System.out.println("Href: " + postSubscriptionResponseDescriptor.getLinks().getSelf().getHref());
                System.out.println("Bssid: " + postSubscriptionResponseDescriptor.getApId().getBssid());
                System.out.println("callbackReference: " + postSubscriptionResponseDescriptor.getCallbackReference());
                System.out.println("threshold: " + postSubscriptionResponseDescriptor.getNotificationEvent().getThreshold());
                System.out.println("trigger: " + postSubscriptionResponseDescriptor.getNotificationEvent().getTrigger());
                System.out.println("subscriptionType: " + postSubscriptionResponseDescriptor.getSubscriptionType());
                // con questa non va perchè non riconosce il trattino il _links
                // PostSubscriptionResponseDescriptor postSubscriptionResponseDescriptor =
                // this.objectMapper.readValue(bodyString, PostSubscriptionResponseDescriptor.class);

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

        logger.info("Starting  Tester ...");
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2";
        PostSubscriptionProcess subscriptionProcess = new PostSubscriptionProcess(baseUrl);

        PostSubscriptionRequestDescriptor requestDescriptor = new PostSubscriptionRequestDescriptor();
        requestDescriptor.setSubscriptionType("AssocStaSubscription");
        requestDescriptor.setCallbackReference("http://bc42-79-32-252-29.eu.ngrok.io/wai/v2/notifications/001");
        ApId apId = new ApId();
        apId.setBssid("005C0A0A0A0A");
        requestDescriptor.setApId(apId);
        NotificationEvent notificationEvent= new NotificationEvent();
        notificationEvent.setThreshold(1);
        notificationEvent.setTrigger(1);
        requestDescriptor.setNotificationEvent(notificationEvent);

        subscriptionProcess.CreateNewSubscription(requestDescriptor);
    }

}
