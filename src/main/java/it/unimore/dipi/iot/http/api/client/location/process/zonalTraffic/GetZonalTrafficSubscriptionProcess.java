package it.unimore.dipi.iot.http.api.client.location.process.zonalTraffic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.ZonalTrafficSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.zonalTraffic.GetZonalTrafficSubscriptionIdResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.zonalTraffic.GetZonalTrafficSubscriptionResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetZonalTrafficSubscriptionProcess {
    final static protected Logger logger = LoggerFactory.getLogger( GetZonalTrafficSubscriptionProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetZonalTrafficSubscriptionProcess(String baseUrl) {
        this.baseUrl=baseUrl;
        this.httpClient = HttpClients.custom().build();
    }
    public void getSubscriptionsList() {
        try {
            //"https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/subscriptions/zonalTraffic
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions/zonalTraffic");

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

                GetZonalTrafficSubscriptionResponseDescriptor responseDescriptor = gson.fromJson(bodyString, GetZonalTrafficSubscriptionResponseDescriptor.class);


                System.out.println("\nnotificationSubscriptionList:");
                System.out.println("    resourceURL: " + responseDescriptor.getNotificationSubscriptionListZonalTraffic().getResourceURL() );
                System.out.println("    zonalTrafficSubscription:\n");

                int i = 0;
                int sizeList = responseDescriptor.getNotificationSubscriptionListZonalTraffic().getZonalTrafficSubscriptionList().size();

                for (ZonalTrafficSubscription zonalTrafficSubscription :
                        responseDescriptor.getNotificationSubscriptionListZonalTraffic().getZonalTrafficSubscriptionList()) {
                    i = i + 1;

                    System.out.println("\u001B[32m" + "subscription " + i + " / " + sizeList + "\u001B[0m");

                    System.out.println("        callbackReference: ");
                    System.out.println("            notifyURL: " + zonalTrafficSubscription.getCallbackReference().getNotifyURL());
                    System.out.println("        clientCorrelator: " + zonalTrafficSubscription.getClientCorrelator());
                    System.out.println("        resourceURL" + zonalTrafficSubscription.getResourceURL());
                    System.out.println("        userEventCriteria: ");
                    for (String criteria : zonalTrafficSubscription.getUserEventCriteria())
                        System.out.println("            " + criteria);
                    System.out.println("        zoneId: " + zonalTrafficSubscription.getZoneId() + "\n");

                }
            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getSubscriptionWithId(String subscriptionId) {
        try {
            //"baseURL  /subscriptions/zonalTraffic/{subscriptionId}
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions/zonalTraffic", subscriptionId);

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

                GetZonalTrafficSubscriptionIdResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetZonalTrafficSubscriptionIdResponseDescriptor.class);

                ZonalTrafficSubscription zonalTrafficSubscription = responseDescriptor.getZonalTrafficSubscription();

                System.out.println("\nzonalTrafficSubscription:");
                System.out.println("        callbackReference: ");
                System.out.println("            notifyURL: " + zonalTrafficSubscription.getCallbackReference().getNotifyURL());
                System.out.println("        clientCorrelator: " + zonalTrafficSubscription.getClientCorrelator());
                System.out.println("        resourceURL" + zonalTrafficSubscription.getResourceURL());
                System.out.println("        userEventCriteria: ");
                for (String criteria : zonalTrafficSubscription.getUserEventCriteria())
                    System.out.println("            " + criteria);
                System.out.println("        zoneId: " + zonalTrafficSubscription.getZoneId() + "\n");

            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        String subscriptionId = "1";

        GetZonalTrafficSubscriptionProcess zonalTrafficSubscriptionProcess = new GetZonalTrafficSubscriptionProcess(baseUrl);

        //GET ...//subscriptions/zonalTraffic
        zonalTrafficSubscriptionProcess.getSubscriptionsList();

        //GET ...//subscriptions/zonalTraffic/{subscriptionId}
        zonalTrafficSubscriptionProcess.getSubscriptionWithId(subscriptionId);
    }
}

