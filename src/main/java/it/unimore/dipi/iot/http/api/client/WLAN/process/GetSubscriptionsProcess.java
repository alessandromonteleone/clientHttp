package it.unimore.dipi.iot.http.api.client.WLAN.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Subscription;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.GetSubscriptionsResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.GetDistanceProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSubscriptionsProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetSubscriptionsProcess(String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = baseUrl;
    }

    public void getSubscriptionsList(){
        try {
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2/subscriptions
            String targetUrl = String.format("%s/%s", this.baseUrl, "subscriptions");

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
                GetSubscriptionsResponseDescriptor subscriptionsResponseDescriptor =
                        gson.fromJson(bodyString,GetSubscriptionsResponseDescriptor.class);

                System.out.println("href: " + subscriptionsResponseDescriptor.get_links().getSelf().getHref());
                int i = 0;
                for (Subscription s : subscriptionsResponseDescriptor.getSubscriptionsList()){
                    i = i + 1;
                    System.out.println("\u001B[32m"+"Subscription number" + i + "\u001B[0m");
                    System.out.println("href: " + s.getHref());
                    System.out.println("subscriptionType: " + s.getSubscriptionType());
                }

    } else {
            logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
        }
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2";
        GetSubscriptionsProcess subscriptionsProcess = new GetSubscriptionsProcess(baseUrl);
        subscriptionsProcess.getSubscriptionsList();

    }
}
