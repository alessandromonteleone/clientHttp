package it.unimore.dipi.iot.http.api.client.applicationSupport.process.timing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.TimeStamp;
import it.unimore.dipi.iot.http.api.client.applicationSupport.model.response.GetTimingCapsResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetTimingCapsProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetTimingCapsProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetTimingCapsProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void getTimeStamp() {

        try {

            //BaseUrl: https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1
            String targetUrl = String.format("%s/%s", this.baseUrl, "timing/timing_caps");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getTimingRequest = new HttpGet(builder.build());

            //Add Request Header
            getTimingRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getTimingRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                //Deserialize Json String and obtained gson
                Gson gson = new GsonBuilder().create();

                GetTimingCapsResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetTimingCapsResponseDescriptor.class);

                TimeStamp timeStamp = responseDescriptor.getTimeStamp();

                logger.info("Testing Response...");

                System.out.println("seconds: " + timeStamp.getSeconds());

                System.out.println("nanoSeconds: " + timeStamp.getNanoSeconds());


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main ( String[] args ){

        String baseURL = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1";

        GetTimingCapsProcess timingCapsProcess = new GetTimingCapsProcess(baseURL);

        timingCapsProcess.getTimeStamp();

    }
}
