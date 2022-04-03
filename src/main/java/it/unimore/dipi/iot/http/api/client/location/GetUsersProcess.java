package it.unimore.dipi.iot.http.api.client.location;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.net.http.HttpClient;
import java.util.ArrayList;

public class GetUsersProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetUsersProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    // UE Location Lookup of a specific UE or group of UEs
    public void GetLocationLookup(ArrayList<String> zoneId, ArrayList<String> accessPointId, ArrayList<String> address) {


        try {

            //"https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2/queries/users?zoneId=zone03"

            String targetUrl = String.format("%s/%s", this.baseUrl, "queries/users");

            // URI Builder with Parameters
            //Esempio Output -> "https://try-mec.etsi.org/sbx1hio0m7/mep1//location/v2/queries/users?zoneId=zone03&address=10.1.0.1
            URIBuilder builder = new URIBuilder(targetUrl);

            for (String s : zoneId) {
                builder.addParameter("zoneId", s);
            }
            for (String s : accessPointId) {
                builder.addParameter("accessPointId", s);
            }
            for (String s : address) {
                builder.addParameter("address", s);
            }

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

                //Deserialize Json String and Log obtained locations
                //Define Custom List Type

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

        GetUsersProcess apiLocationProcess = new GetUsersProcess(baseUrl);

        ArrayList<String> zoneId = new ArrayList<>() {{
            add("zone03");
        }};

        ArrayList<String> accessPointId = new ArrayList<>() {{
            add("4g-macro-cell-6");
            //add("wifi-ap-6");
        }};

        ArrayList<String> address = new ArrayList<>() {{
            //add("10.100.0.1");
            //add("10.1.0.1");
        }};

        apiLocationProcess.GetLocationLookup(zoneId, accessPointId, address);
    }
}
