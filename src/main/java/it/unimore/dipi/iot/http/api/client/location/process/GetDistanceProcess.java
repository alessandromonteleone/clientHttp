package it.unimore.dipi.iot.http.api.client.location.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.location.model.response.GetDistanceResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.model.TerminalDistance;
import it.unimore.dipi.iot.http.api.client.location.model.TimeStamp;
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
import java.util.Scanner;

public class GetDistanceProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetDistanceProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }
    //1     distance between 2 UEs with address parameters
    public void getUeDistance( ArrayList<String> address) {

        try {

            //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
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

                //Deserialize Json String and obtained getDistanceResponseDescriptor
                GetDistanceResponseDescriptor getDistanceResponseDescriptor = this.objectMapper.readValue(bodyString,
                        GetDistanceResponseDescriptor.class);
                TerminalDistance terminalDistance = getDistanceResponseDescriptor.getTerminalDistance();

                // test response info ...
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


    public static void main(String[] args) {
            logger.info("Starting IoT Inventory GET Tester ...");
            String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";

            ArrayList<String> address = new ArrayList<>(){{
                add("10.1.0.1");
                add("10.100.0.1");
                add("10.10.0.1");
            }};
            double latitude = 45.156417;
            double longitude = 10.791375;

            GetDistanceProcess apiLocationProcess = new GetDistanceProcess(baseUrl);

        System.out.println("""
                    1) GET DISTANCE BETWEEN TWO UEs (UE Distance Lookup between terminals\s)
                    2) GET DISTANCE BETWEEN AN UE AND A POSITION (UE Distance Lookup between a terminal and a location\s)\040
                    enter a number:\s""");

        Scanner tastiera = new Scanner(System.in);
        String s = tastiera.nextLine();

            if (s.equals("1")) {
                System.out.printf("\u001B[35m" + "--------- GET UE %s - %s DISTANCE -------- \n" + "\u001B[0m", address.get(0), address.get(1));
                apiLocationProcess.getUeDistance(address);

            }
            if (s.equals("2")) {
                System.out.printf("\u001B[34m" + "--------- GET UE %s DISTANCE  FROM POSITION lat:%s long:%s  -------- \n" + "\u001B[0m",
                        address.get(1), latitude, longitude);
                apiLocationProcess.getUeGeoPositionDistance(address.get(1), latitude, longitude);
            }
        }
}
