package it.unimore.dipi.iot.http.api.client.WLAN.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.GetSubscriptionIdResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSubscriptionIdProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetSubscriptionIdProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetSubscriptionIdProcess( String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        this.baseUrl = baseUrl;
    }

    public void GetSubscriptionInfo(String subscriptionId){
        try {
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2/subscriptions
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions",subscriptionId);

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

                GetSubscriptionIdResponseDescriptor subscriptionResponse = gson.fromJson(bodyString,
                        GetSubscriptionIdResponseDescriptor.class);

                System.out.println("href: " + subscriptionResponse.get_links().getSelf().getHref());
                System.out.println("bssid: " + subscriptionResponse.getApId().getBssid());
                System.out.println("callbackReference: " + subscriptionResponse.getCallbackReference());
                System.out.println("threshold: " + subscriptionResponse.getNotificationEvent().getThreshold());
                System.out.println("trigger: " + subscriptionResponse.getNotificationEvent().getTrigger());
                System.out.println("subscriptionType: "  + subscriptionResponse.getSubscriptionType());
    }else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2";
        String subscriptionId = "sub-FTu-ySks_U130v-1";
        GetSubscriptionIdProcess subscriptionIdProcess  = new GetSubscriptionIdProcess(baseUrl);
        subscriptionIdProcess.GetSubscriptionInfo(subscriptionId);

    }

}
