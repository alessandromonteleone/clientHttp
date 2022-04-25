package it.unimore.dipi.iot.http.api.client.WLAN;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.WLAN.model.response.GetApInformationResponseDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetApInformationProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetApInformationProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetApInformationProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void getApInfo(){

        try{
            //https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2/queries/ap/ap_information
            String targetUrl = String.format("%s/%s", this.baseUrl, "queries/ap/ap_information");

            //Create the HTTP GET Request
            HttpGet getZonesInformationRequest = new HttpGet(targetUrl);

            //Add Request Header
            getZonesInformationRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getZonesInformationRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);
                logger.info("Testing response info...");

                GetApInformationResponseDescriptor[] getApInformationResponseDescriptor = this.objectMapper.readValue
                    (bodyString, GetApInformationResponseDescriptor[].class);

                int i=0;
                for (GetApInformationResponseDescriptor g : getApInformationResponseDescriptor){
                    i=i+1;
                    System.out.println("\u001B[32m"+"Ap number: "+i+"\u001B[0m");
                    System.out.println("bssid: " + g.getApId().getBssid());
                    System.out.println("datum: " + g.getApLocation().getGeolocation().getDatum());
                    System.out.println("lat: " + g.getApLocation().getGeolocation().getLat());
                    System.out.println("latUncertainty: " + g.getApLocation().getGeolocation().getLatUncertainty());
                    System.out.println("long: " + g.getApLocation().getGeolocation().getLong());
                    System.out.println("longUncertainty: " + g.getApLocation().getGeolocation().getLongUncertainty());
                    System.out.println("availAdmCap: "+g.getBssLoad().getAvailAdmCap());
                    System.out.println("channelUtilization: "+g.getBssLoad().getChannelUtilization());
                    System.out.println("staCount: "+g.getBssLoad().getStaCount()+"\n");


                }
            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        // https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/wai/v2";

        GetApInformationProcess apInformationProcess = new GetApInformationProcess(baseUrl);
        apInformationProcess.getApInfo();

    }

}
