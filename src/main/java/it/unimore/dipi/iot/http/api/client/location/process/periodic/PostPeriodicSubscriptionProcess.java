package it.unimore.dipi.iot.http.api.client.location.process.periodic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.Link;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.request.periodic.PeriodicRequestDescriptor;
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

public class PostPeriodicSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostPeriodicSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostPeriodicSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void createNewSubscription(PeriodicRequestDescriptor PeriodicRequestDescriptor) {
        try{
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
            String targetUrl = String.format("%s/subscriptions/periodic", this.baseUrl);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(PeriodicRequestDescriptor);

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
                PeriodicRequestDescriptor subscriptionPeriodicResponse =
                        gson.fromJson(bodyString, PeriodicRequestDescriptor.class);

                //testing response
                logger.info("Testing info response");
                //to do print

                System.out.println("\nperiodicNotificationSubscription:");
                System.out.println("    address:");
                for (String address : subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getAddress())
                    System.out.println("        " + address);
                System.out.println("    callbackReference: ");
                System.out.println("        callbackData: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().
                        getCallbackReference().getCallbackData());
                System.out.println("        notificationFormat: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().
                        getCallbackReference().getNotificationFormat());
                System.out.println("        notifyURL: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().
                        getCallbackReference().getNotifyURL());
                System.out.println("    clientCorrelator: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getClientCorrelator());
                System.out.println("    duration: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getDuration());
                System.out.println("    frequency: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getFrequency());
                System.out.println("    links: ");
                for (Link link : subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getLink()){
                    System.out.println("        href: " + link.getHref());
                    System.out.println("        rel: " + link.getRel());
                }
                System.out.println("    requestedAccuracy: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getRequestedAccuracy());
                System.out.println("        requester: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getRequester());
                System.out.println("    resourceURL: " + subscriptionPeriodicResponse.getPeriodicNotificationSubscription().getResourceURL());
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

        PostPeriodicSubscriptionProcess apiLocationProcess = new PostPeriodicSubscriptionProcess(baseUrl);

        PeriodicRequestDescriptor periodicRequestDescriptor = new PeriodicRequestDescriptor();

        PeriodicNotificationSubscription periodicNotificationSubscription = new PeriodicNotificationSubscription();

        List<String> address = new ArrayList<>();
        address.add("10.100.0.1");
        address.add("10.1.0.1");

        periodicNotificationSubscription.setAddress(address);

        CallbackReference callbackReference = new CallbackReference();
        callbackReference.setCallbackData("0123");
        callbackReference.setNotificationFormat("XML");
        callbackReference.setNotifyURL("http://79ee-62-211-88-203.eu.ngrok.io/location/periodic");

        periodicNotificationSubscription.setCallbackReference(callbackReference);

        periodicNotificationSubscription.setClientCorrelator("1234");
        periodicNotificationSubscription.setDuration(5);
        periodicNotificationSubscription.setFrequency(30);

        Link link = new Link();
        link.setHref("");
        link.setRel("");

        List<Link> linkList = new ArrayList<>();
        linkList.add(link);
        periodicNotificationSubscription.setLink(linkList);

        periodicNotificationSubscription.setRequestedAccuracy(10);

        //Identifies the entity that is requesting the information (e.g. "sip" URI, "tel" URI, "acr" URI)
        periodicNotificationSubscription.setRequester("sip"); //sip, tel, acr
        periodicNotificationSubscription.setResourceURL("");

        periodicRequestDescriptor.setPeriodicNotificationSubscription(periodicNotificationSubscription);

        //Create new Location
        apiLocationProcess.createNewSubscription(periodicRequestDescriptor);
    }

}
