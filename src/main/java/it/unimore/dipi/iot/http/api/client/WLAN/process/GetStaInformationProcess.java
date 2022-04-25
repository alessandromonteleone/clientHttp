package it.unimore.dipi.iot.http.api.client.WLAN;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.GetStaInformationResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.location.GetDistanceProcess;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetStaInformationProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetDistanceProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetStaInformationProcess(String baseUrl) {
        this.baseUrl=baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void getStaInfo() {
        try {
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2/queries/ap/ap_information
            String targetUrl = String.format("%s/%s", this.baseUrl, "queries/sta/sta_information");

            //Create the HTTP GET Request
            HttpGet getZonesInformationRequest = new HttpGet(targetUrl);

            //Add Request Header
            getZonesInformationRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getZonesInformationRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);
                logger.info("Testing response info...");

                //list json
                GetStaInformationResponseDescriptor[] getStaInformationResponseDescriptor =
                        this.objectMapper.readValue(bodyString, GetStaInformationResponseDescriptor[].class);

                int i = 0;
                for (GetStaInformationResponseDescriptor g : getStaInformationResponseDescriptor){
                    i=i+1;
                    System.out.println("\u001B[32m"+"Ap number: " + i +"\u001B[0m");

                    System.out.println("bssid: " + g.getApAssociated().getBssid());
                    System.out.println("rssi: " + g.getRssi().getRssi());
                    System.out.println("macId: " + g.getStaId().getMacId());
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

        GetStaInformationProcess staInformationProcess = new GetStaInformationProcess(baseUrl);
        staInformationProcess.getStaInfo();
    }
}
