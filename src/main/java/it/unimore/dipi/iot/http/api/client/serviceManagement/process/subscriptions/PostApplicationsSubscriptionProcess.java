package it.unimore.dipi.iot.http.api.client.serviceManagement.process.subscriptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.FilteringCriteria;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.ApplicationsSubscriptionDescriptor;
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

    public void createNewSubscription(String appInstanceId, ApplicationsSubscriptionDescriptor requestDescriptor) {
        try{

            //BASE_URL/applications/{appInstanceId}/subscriptions
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

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
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
                logger.info("Raw Response Body: {}", bodyString);

                //create response gson
                Gson gson = new GsonBuilder().create();

                ApplicationsSubscriptionDescriptor responseDescriptor = gson.fromJson(bodyString, ApplicationsSubscriptionDescriptor.class);

                logger.info("testing response...\n");
                //System.out.println( + responseDescriptor.get);

                System.out.println("subscriptionType: " + responseDescriptor.getSubscriptionType());
                System.out.println("callbackReference: " +responseDescriptor.getCallbackReference());
                System.out.println("_links: ");
                System.out.println("    self:");
                System.out.println("        href: " + responseDescriptor.getLinks().getSelf().getHref());
                System.out.println("filteringCriteria: ");

                System.out.println("    serNames: ");
                for (String serNames : responseDescriptor.getFilteringCriteria().getSerNames())
                System.out.println("        " + serNames);

                System.out.println("    states: ");
                for (String states : responseDescriptor.getFilteringCriteria().getStates())
                    System.out.println("        " + states);

                System.out.println("    isLocal: " + responseDescriptor.getFilteringCriteria().getIsLocal());

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

        logger.info("Starting  ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";

        String appInstanceId = "e486e1c1-2c28-498c-b216-6e624b031c55";

        PostApplicationsSubscriptionProcess subscriptionProcess = new PostApplicationsSubscriptionProcess(baseUrl);

        ApplicationsSubscriptionDescriptor subscriptionDescriptor = new ApplicationsSubscriptionDescriptor();

        //This type represents a subscription to the notifications from the MEC platform regarding the availability of a MEC service or a list of MEC services
        //Shall be set to SerAvailabilityNotificationSubscription
        subscriptionDescriptor.setSubscriptionType("SerAvailabilityNotificationSubscription");

        subscriptionDescriptor.setCallbackReference("http://52a1-62-211-88-203.eu.ngrok.io/support_management/v1/services/subscriptions/001");

        FilteringCriteria filteringCriteria = new FilteringCriteria();

        List<String> serNames = new ArrayList<>();
        serNames.add( "myRnis");

        List<String> states = new ArrayList<>();
        states.add( "ACTIVE");
        states.add("INACTIVE");

        filteringCriteria.setSerNames(serNames);
        filteringCriteria.setStates(states);
        filteringCriteria.setIsLocal(true);

        subscriptionDescriptor.setFilteringCriteria(filteringCriteria);

        subscriptionProcess.createNewSubscription(appInstanceId, subscriptionDescriptor );

    }

}
