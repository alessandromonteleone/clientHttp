package it.unimore.dipi.iot.http.api.client.applicationSupport.process.applications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.request.PostApplicationSubscriptionRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.response.PostApplicationSubscriptionResponseDescriptor;
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

public class PostApplicationsSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostApplicationsSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostApplicationsSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void createNewSubscription(String appInstanceId, PostApplicationSubscriptionRequestDescriptor requestDescriptor) {
        try{

            ////applications/2b3e30cb-4113-4ef6-9388-ef99247f5a34/subscriptions
            String targetUrl = String.format("%s/applications/%s/subscriptions", this.baseUrl,appInstanceId);

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
                Gson gson = new GsonBuilder().create();

                PostApplicationSubscriptionResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, PostApplicationSubscriptionResponseDescriptor.class);

                //testing response
                logger.info("Testing info response");
                System.out.println("CallBackReference: " + responseDescriptor.getCallbackReference());
                System.out.println("SubscriptionType: " + responseDescriptor.getSubscriptionType());
                System.out.println("href: " + responseDescriptor.getLinks().getSelf().getHref());
                System.out.println("AppInstanceId: " + responseDescriptor.getAppInstanceId());

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

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1";

        String appInstanceId = "9ca85893-2f48-4073-9f54-ab5e82327272";

        PostApplicationSubscriptionRequestDescriptor requestDescriptor = new PostApplicationSubscriptionRequestDescriptor();

        requestDescriptor.setSubscriptionType("AppTerminationNotificationSubscription");
        requestDescriptor.setCallbackReference("http://bc42-79-32-252-29.eu.ngrok.io/application_support/v1/applications/subscriptions/0001");
        requestDescriptor.setAppInstanceId(appInstanceId);

        PostApplicationsSubscriptionProcess subscriptionProcess = new PostApplicationsSubscriptionProcess(baseUrl);
        subscriptionProcess.createNewSubscription(appInstanceId, requestDescriptor);


    }

}
