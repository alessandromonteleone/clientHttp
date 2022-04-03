package it.unimore.dipi.iot.http.api.client.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
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
import java.util.List;
import java.util.Scanner;

public class GetZonesProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetZonesProcess.class);

    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    private String baseUrl;

    public GetZonesProcess(String baseUrl){
        this.baseUrl = baseUrl; //https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void getZonesList(){
        try{
            String targetUrl = String.format("%s/%s", this.baseUrl, "queries/zones");

            //Create the HTTP GET Request
            HttpGet getZonesInformationRequest = new HttpGet(targetUrl);

            //Add Request Header
            getZonesInformationRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getZonesInformationRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                System.out.println("---------GET ZONES LIST ----------");
                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);
            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getZone(String zoneId){

        try{

            String targetUrl = String.format("%s/%s/%s", this.baseUrl,"queries/zones", zoneId);

            //Create the HTTP GET Request
            HttpGet getLocationListRequest = new HttpGet(targetUrl);

            //Add Request Header
            getLocationListRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLocationListRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getZoneAccessPointList(String zoneId){

        try{

            String targetUrl = String.format("%s/%s/%s/%s", this.baseUrl,"queries/zones", zoneId,"accessPoints");

            //Create the HTTP GET Request
            HttpGet getLocationListRequest = new HttpGet(targetUrl);

            //Add Request Header
            getLocationListRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLocationListRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);


            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getZoneAccessPoint(String zoneId, String accessPointId){

        try{

            String targetUrl = String.format("%s/%s/%s/%s/%s", this.baseUrl,"queries/zones", zoneId,"accessPoints",accessPointId);

            //Create the HTTP GET Request
            HttpGet getLocationListRequest = new HttpGet(targetUrl);

            //Add Request Header
            getLocationListRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLocationListRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d", response != null ? response.getStatusLine().getStatusCode() : -1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        logger.info("Starting IoT Inventory GET Tester ...");
        String zoneId=null;
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/location/v2";
        String s = "1";
        while (! s.equals("0")) {

            System.out.println("""
                    0)EXIT
                    1) GET ZONES LIST
                    2) GET ZONE INFO
                    3) GET ZONE ACCESS POINTS LIST
                    4) GET ZONE ACCESS POINT\040
                    enter a number:\s""");

            Scanner tastiera = new Scanner(System.in);
            s = tastiera.nextLine();

            GetZonesProcess apiLocationProcess = new GetZonesProcess(baseUrl);
            switch (s) {
                case "1":
                    System.out.println("--------- GET ZONES LIST ----------");
                    apiLocationProcess.getZonesList();
                    break;
                case "2":
                    System.out.println("ZoneId (zone01, zone02, zone03, zone04 ) : ");
                    tastiera = new Scanner(System.in);
                    zoneId = tastiera.nextLine();
                    System.out.printf("--------- GET %s INFO ----------\n", zoneId);
                    apiLocationProcess.getZone(zoneId);
                    break;
                case "3":
                    System.out.println("ZoneId (zone01, zone02, zone03, zone04 ): ");
                    tastiera = new Scanner(System.in);
                    zoneId = tastiera.nextLine();
                    System.out.printf("--------- GET %s INFO ----------\n", zoneId);
                    apiLocationProcess.getZoneAccessPointList(zoneId);
                    break;
                case "4":
                    System.out.println("ZoneId (zone01, zone02, zone03, zone04 ): ");
                    tastiera = new Scanner(System.in);
                    zoneId= tastiera.nextLine();
                    System.out.println("Access point id: ");
                    tastiera = new Scanner(System.in);
                    String accessPointId = tastiera.nextLine();
                    System.out.printf("--------- GET %s ACCESS POINT %s INFO ----------\n", zoneId,accessPointId);
                    apiLocationProcess.getZoneAccessPoint(zoneId,accessPointId);
                    break;
                case "0":
                    System.out.println("exit");
            }
        }
    }

}


