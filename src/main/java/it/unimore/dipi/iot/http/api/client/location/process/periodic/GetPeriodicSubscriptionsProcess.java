package it.unimore.dipi.iot.http.api.client.location.process.periodic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.CallbackReference;
import it.unimore.dipi.iot.http.api.client.location.model.Link;
import it.unimore.dipi.iot.http.api.client.location.model.NotificationSubscriptionListPeriodic;
import it.unimore.dipi.iot.http.api.client.location.model.PeriodicNotificationSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.periodic.GetPeriodicSubscriptionsResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetPeriodicSubscriptionsProcess {

    final static protected Logger logger = LoggerFactory.getLogger( GetPeriodicSubscriptionsProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetPeriodicSubscriptionsProcess(String baseUrl) {

        this.baseUrl=baseUrl;
        this.httpClient = HttpClients.custom().build();
    }
    public void getSubscriptionsList() {
        try {
            //"https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/distance"
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions/periodic");

            //Create the HTTP GET Request
            HttpGet getRequest = new HttpGet(targetUrl);

            //Add Request Header
            getRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("Testing response info...");

                Gson gson = new GsonBuilder().create();

                GetPeriodicSubscriptionsResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetPeriodicSubscriptionsResponseDescriptor.class);

                NotificationSubscriptionListPeriodic notificationSubscriptionList =
                        responseDescriptor.getNotificationSubscriptionListPeriodic();

                List<PeriodicNotificationSubscription> periodicNotificationSubscriptionList =
                        notificationSubscriptionList.getPeriodicNotificationSubscriptionList();

                if(periodicNotificationSubscriptionList!=null){
                    //print response
                    int i = 0;
                    for ( PeriodicNotificationSubscription p : periodicNotificationSubscriptionList ){
                        i=i+1;
                        System.out.println("\u001B[36m"+"Subscription periodic number " + i+"\u001B[0m");
                        CallbackReference callbackReference = p.getCallbackReference();
                        System.out.println("callbackData: " + callbackReference.getCallbackData());
                        System.out.println("notifyURL: " + callbackReference.getNotifyURL());
                        System.out.println("notificationFormat: " + callbackReference.getNotificationFormat());

                        for(String a : p.getAddress())
                            System.out.println("address: " + a);

                        System.out.println("clientCorrelator: " + p.getClientCorrelator());
                        System.out.println("duration: " + p.getDuration());
                        System.out.println("frequency: " + p.getFrequency());

                        for (Link l : p.getLink())
                            System.out.println("href: " + l.getHref() + "\nrel: " + l.getRel() );

                        System.out.println("RequestAccuracy: " + p.getRequestedAccuracy());
                        System.out.println("Requester: " + p.getRequester());
                        System.out.println("resourceURL: " + p.getResourceURL());

                    }
                }
                System.out.println("resourceURL: " + notificationSubscriptionList.getResourceURL());


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        GetPeriodicSubscriptionsProcess subscriptionsProcess = new GetPeriodicSubscriptionsProcess(baseUrl);
        subscriptionsProcess.getSubscriptionsList();
    }
}

