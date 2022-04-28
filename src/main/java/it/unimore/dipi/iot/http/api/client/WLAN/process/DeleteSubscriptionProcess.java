package it.unimore.dipi.iot.http.api.client.WLAN.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Links;
import it.unimore.dipi.iot.http.api.client.WLAN.model.NotificationEvent;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Self;
import it.unimore.dipi.iot.http.api.client.WLAN.model.request.PutSubscriptionRequestDescriptor;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.PostSubscriptionResponseDescriptor;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

public class DeleteSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostSubscriptionProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public DeleteSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void DeleteSubscription(String subscriptionId) {

        try {

            String targetUrl = String.format("%s/subscriptions/%s", this.baseUrl, subscriptionId);
            logger.info("Target Url: {}", targetUrl);

            //Create the HTTP Delete Request
            HttpDelete deleteRequest = new HttpDelete(targetUrl);

            //Add Content Type Header
            deleteRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Add Request Header
            deleteRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the Request
            CloseableHttpResponse response = httpClient.execute(deleteRequest);


            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
                //Extract the Header

                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 = Arrays.stream(response.getHeaders("http")).findFirst();
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info(Header);
                }

                //System.out.println(response);
                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        logger.info("Starting IoT Inventory Location Create Tester ...");
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2";
        String subscriptionId = "sub-pMfWRBXydlsR-EIJ"; //si trova nell' href delle response del post subscription
        DeleteSubscriptionProcess deleteSubscriptionProcess = new DeleteSubscriptionProcess(baseUrl);
        deleteSubscriptionProcess.DeleteSubscription(subscriptionId);

    }
}
