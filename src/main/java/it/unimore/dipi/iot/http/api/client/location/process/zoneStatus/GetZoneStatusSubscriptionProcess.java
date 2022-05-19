package it.unimore.dipi.iot.http.api.client.location.process.zoneStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.location.model.ZoneStatusSubscription;
import it.unimore.dipi.iot.http.api.client.location.model.response.zoneStatus.GetZoneStatusSubscriptionIdResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.response.zoneStatus.GetZoneStatusSubscriptionResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.zonalTraffic.GetZonalTrafficSubscriptionProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetZoneStatusSubscriptionProcess {
    final static protected Logger logger = LoggerFactory.getLogger( GetZoneStatusSubscriptionProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetZoneStatusSubscriptionProcess(String baseUrl) {
        this.baseUrl=baseUrl;
        this.httpClient = HttpClients.custom().build();
    }
    public void getSubscriptionsList() {
        try {
            //baseUrl/      subscriptions/zoneStatus
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions/zoneStatus");

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

                GetZoneStatusSubscriptionResponseDescriptor responseDescriptor = gson.fromJson(bodyString, GetZoneStatusSubscriptionResponseDescriptor.class);

                System.out.println("\nnotificationSubscriptionList: ");
                System.out.println("    resourceURL: " + responseDescriptor.getNotificationSubscriptionList().getResourceURL());
                System.out.println("zoneStatusSubscription: \n");
                int i = 0;
                int sizeList = responseDescriptor.getNotificationSubscriptionList().getZoneStatusSubscription().size();

                for (ZoneStatusSubscription zoneStatusSubscription : responseDescriptor.getNotificationSubscriptionList().getZoneStatusSubscription()) {
                    i = i + 1;
                    System.out.println("\u001B[32m" + "zoneStatusSubscription " + i + " / " + sizeList + "\u001B[0m");
                    System.out.println("    callbackReference: ");
                    System.out.println("        notifyURL: " + zoneStatusSubscription.getCallbackReference().getNotifyURL());
                    System.out.println("   clientCorrelator: " + zoneStatusSubscription.getClientCorrelator());
                    System.out.println("    numberOfUsersZoneThreshold: " + zoneStatusSubscription.getNumberOfUsersZoneThreshold());
                    System.out.println("    operationStatus: ");
                    for (String operationStatus : zoneStatusSubscription.getOperationStatusList())
                        System.out.println("        " + operationStatus);
                    System.out.println("    resourceURL:" + zoneStatusSubscription.getResourceURL());
                    System.out.println("    zoneId: " + zoneStatusSubscription.getZoneId() + "\n");
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
            //baseUrl/      subscriptions/zoneStatus/{subscriptionId}
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions/zoneStatus",subscriptionId);

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

                GetZoneStatusSubscriptionIdResponseDescriptor responseDescriptor = gson.fromJson(bodyString, GetZoneStatusSubscriptionIdResponseDescriptor.class);


                System.out.println("\nzoneStatusSubscription: ");
                System.out.println("    callbackReference: ");
                System.out.println("        notifyURL: " + responseDescriptor.getZoneStatusSubscription().getCallbackReference().getNotifyURL());
                System.out.println("   clientCorrelator: " + responseDescriptor.getZoneStatusSubscription().getClientCorrelator());
                System.out.println("    numberOfUsersZoneThreshold: " + responseDescriptor.getZoneStatusSubscription().getNumberOfUsersZoneThreshold());
                System.out.println("    operationStatus: ");
                for (String operationStatus : responseDescriptor.getZoneStatusSubscription().getOperationStatusList())
                    System.out.println("        " + operationStatus);
                System.out.println("    resourceURL:" + responseDescriptor.getZoneStatusSubscription().getResourceURL());
                System.out.println("    zoneId: " + responseDescriptor.getZoneStatusSubscription().getZoneId() + "\n");

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

        GetZoneStatusSubscriptionProcess zoneStatusSubscriptionProcess= new GetZoneStatusSubscriptionProcess(baseUrl);

        //GET ...//subscriptions/zoneStatus
        zoneStatusSubscriptionProcess.getSubscriptionsList();

        //GET ...//subscriptions/zoneStatus/{subscriptionId}
        zoneStatusSubscriptionProcess.getSubscriptionWithId(subscriptionId);
    }
}
