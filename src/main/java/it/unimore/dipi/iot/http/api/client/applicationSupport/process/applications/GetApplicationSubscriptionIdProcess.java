package it.unimore.dipi.iot.http.api.client.applicationSupport.process.applications;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.response.GetApplicationSubscriptionIdResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetApplicationSubscriptionIdProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetApplicationSubscriptionIdProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetApplicationSubscriptionIdProcess(String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        this.baseUrl = baseUrl;
    }

    public void GetSubscriptionInfo(String appInstanceId,String subscriptionId  ) {
        try {
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1
            String targetUrl = String.format("%s/%s/%s/%s/%s", this.baseUrl,"applications",appInstanceId,
                    "subscriptions",subscriptionId);

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

                GetApplicationSubscriptionIdResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetApplicationSubscriptionIdResponseDescriptor.class);

                System.out.println("subscriptionType: " + responseDescriptor.getSubscriptionType());
                System.out.println("callbackReference: " + responseDescriptor.getCallbackReference());
                System.out.println("href: " + responseDescriptor.getLinks().getSelf().getHref());
                System.out.println("appInstanceId: " + responseDescriptor.getAppInstanceId());



            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1";
        String appInstanceid  = "9ca85893-2f48-4073-9f54-ab5e82327272";
        String subscriptionId = "sub-hV_xRHPGgTNrM2ep";

        GetApplicationSubscriptionIdProcess subscriptionIdProcess = new GetApplicationSubscriptionIdProcess(baseUrl);
        subscriptionIdProcess.GetSubscriptionInfo(appInstanceid,subscriptionId);

    }
}

