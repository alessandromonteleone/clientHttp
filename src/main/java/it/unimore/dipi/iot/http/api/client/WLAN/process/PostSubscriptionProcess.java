package it.unimore.dipi.iot.http.api.client.WLAN;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.PostSubscriptionResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.GetDistanceProcess;
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

import java.util.Arrays;

public class PostSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public PostSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void CreateNewSubscription(PostSubscriptionResponseDescriptor postSubscriptionResponseDescriptor){

        try{

            String targetUrl = String.format("%s/subscriptions", this.baseUrl);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(postSubscriptionResponseDescriptor);

            //Create the HTTP Post Request
            HttpPost createLocationRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createLocationRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createLocationRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createLocationRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header
                String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();
                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);
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

        logger.info("Starting IoT Inventory Location Create Tester ...");
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";


    }

}
