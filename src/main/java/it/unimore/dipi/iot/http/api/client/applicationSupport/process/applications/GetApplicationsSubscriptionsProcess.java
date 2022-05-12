package it.unimore.dipi.iot.http.api.client.applicationSupport.process.applications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.Links;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.Self;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.Subscriptions;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.response.GetApplicationsSubscriptionsResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetApplicationsSubscriptionsProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetApplicationsSubscriptionsProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetApplicationsSubscriptionsProcess(String baseUrl) {
        this.httpClient = HttpClients.custom().build();
        ObjectMapper objectMapper = new ObjectMapper();
        this.baseUrl = baseUrl;
    }

    public void GetSubscriptionsInfo(String appInstanceId) {
        try {
            //applications/2b3e30cb-4113-4ef6-9388-ef99247f5a34/subscriptions
            String targetUrl = String.format("%s/applications/%s/subscriptions", this.baseUrl, appInstanceId);

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

                GetApplicationsSubscriptionsResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetApplicationsSubscriptionsResponseDescriptor.class);

                System.out.println("href: " + responseDescriptor.getLink().getSelf().getHref()+"\n");
                for(Subscriptions s : responseDescriptor.getLink().getSubscriptionsList())
                    System.out.println("href: " + s.getHref()+"\nsubscriptionType: " + s.getSubscriptionType()+"\n");


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1";
        String appInstanceId = "b8a203be-ac81-45a6-8d88-fdb1f8f5393b";

        GetApplicationsSubscriptionsProcess subscriptionsProcess = new GetApplicationsSubscriptionsProcess(baseUrl);
        subscriptionsProcess.GetSubscriptionsInfo(appInstanceId);
    }

}
