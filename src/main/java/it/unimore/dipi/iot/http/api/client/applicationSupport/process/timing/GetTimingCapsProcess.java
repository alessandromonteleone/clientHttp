package it.unimore.dipi.iot.http.api.client.applicationSupport.process.timing;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.location.model.TerminalDistance;
import it.unimore.dipi.iot.http.api.client.location.model.TimeStamp;
import it.unimore.dipi.iot.http.api.client.location.model.response.GetDistanceResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.process.GetDistanceProcess;
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

import java.util.ArrayList;

public class TimingCapsProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public TimingCapsProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }
    public void getTimeStamp( ArrayList<String> address) {

        try {

            //BaseUrl: https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1
            String targetUrl = String.format("%s/%s", this.baseUrl, "queries/distance");

            // URI Builder with Parameters
            //Output -> "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/queries/distance?address=10.1.0.1&address=10.100.0.1
            URIBuilder builder = new URIBuilder(targetUrl);
            builder.addParameter("address", address.get(0))
                    .addParameter("address", address.get(1));

            logger.info("URI: {}", builder.toString());

            //Create the HTTP GET Request
            HttpGet getLocationListRequest = new HttpGet(builder.build());

            //Add Request Header
            getLocationListRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLocationListRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                //Deserialize Json String and Log obtained getDistanceResponseDescriptor
                GetDistanceResponseDescriptor getDistanceResponseDescriptor = this.objectMapper.readValue(bodyString, GetDistanceResponseDescriptor.class);
                TerminalDistance terminalDistance = getDistanceResponseDescriptor.getTerminalDistance();

                //test response info
                logger.info("Testing response info...");
                System.out.print("Distance: "+ terminalDistance.getDistance() + " meters\n" );
                TimeStamp timeStamp = terminalDistance.getTimestamp();
                System.out.println("Nanoseconds: "+ timeStamp.getNanoSeconds());
                System.out.println("Seconds: "+ timeStamp.getSeconds());

                //{
                //  "terminalDistance": {
                //    "distance": 310979,
                //    "timestamp": {
                //      "nanoSeconds": 0,
                //      "seconds": 1650483557
                //    }
                //  }
                //}

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2     distance between UE and geoPosition
    public void getUeGeoPositionDistance( String address, double latitude, double longitude) {

        try {

            //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
            String targetUrl = String.format("%s/%s", this.baseUrl, "queries/distance");

            // URI Builder with Parameters
            //Output -> "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/queries/distance?address=10.1.0.1&address=10.100.0.1
            URIBuilder builder = new URIBuilder(targetUrl);
            builder.addParameter("address", address)
                    .addParameter("latitude", String.valueOf(latitude))
                    .addParameter("longitude", String.valueOf(longitude));

            logger.info("URI: {}", builder.toString());

            //Create the HTTP GET Request
            HttpGet getLocationListRequest = new HttpGet(builder.build());

            //Add Request Header
            getLocationListRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLocationListRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);


}
