package it.unimore.dipi.iot.http.api.client.location.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unimore.dipi.iot.http.api.client.location.model.*;
import it.unimore.dipi.iot.http.api.client.location.model.response.*;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
                logger.info("Testing response info...");

                GetZonesResponseDescriptor getZonesResponseDescriptor = this.objectMapper.readValue
                        (bodyString, GetZonesResponseDescriptor.class);
                //test response info

                ZoneList zoneList = getZonesResponseDescriptor.getZoneList();

                System.out.println("ResourceURL: "+zoneList.getResourceURL());
                List <ZoneInfo> zoneInfoList = zoneList.getZone();
                int i=0;
                for(ZoneInfo z : zoneInfoList){
                    i=i+1;
                    System.out.println("\u001B[32m"+"Zone number "+"\u001B[0m"+i);
                    System.out.println("NumberOfAccessPoints: "+z.getNumberOfAccessPoints());
                    System.out.println("NumberOfUnserviceableAccessPoints: "+z.getNumberOfUnserviceableAccessPoints());
                    System.out.println("NumberOfUsers: "+z.getNumberOfUsers());
                    System.out.println("ResourceURL: "+z.getResourceURL());
                    System.out.println("ZoneId: "+ z.getZoneId());
                }

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

                GetZoneIdResponseDescriptor getZoneIdResponseDescriptor = this.objectMapper.readValue
                        (bodyString, GetZoneIdResponseDescriptor.class);
                logger.info("Testing response info...");
                //test response info
                ZoneInfo zoneInfo = getZoneIdResponseDescriptor.getZoneInfo();
                System.out.println("NumberOfAccessPoints: "+zoneInfo.getNumberOfAccessPoints());
                System.out.println("NumberOfUnserviceableAccessPoints: "+zoneInfo.getNumberOfUnserviceableAccessPoints());
                System.out.println("NumberOfUsers: "+zoneInfo.getNumberOfUsers());
                System.out.println("ResourceURL: "+zoneInfo.getResourceURL());
                System.out.println("ZoneId: "+ zoneInfo.getZoneId());
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

                GetZoneAccessPointListResponseDescriptor getZoneAccessPointListResponseDescriptor =
                        this.objectMapper.readValue(bodyString, GetZoneAccessPointListResponseDescriptor.class);
                logger.info("Testing response info...");

                AccessPointList accessPointList = getZoneAccessPointListResponseDescriptor.getAccessPointList();

                List<AccessPointInfo> accessPointInfoList = accessPointList.getAccessPoint();
                int i = 0;
                for(AccessPointInfo a : accessPointInfoList){
                    i=i+1;
                    System.out.println("\u001B[32m"+"AccessPoint number: "+"\u001B[0m"+i);
                    System.out.println("AccessPointId: " + a.getAccessPointId());
                    System.out.println("ConnectionType: " + a.getConnectionType());
                    LocationInfo locationInfo = a.getLocationInfo();
                    for (double lat : locationInfo.getLatitude()) {
                        System.out.println("Latitudine: " + lat);
                    }
                    for (double lon : locationInfo.getLongitude()) {
                        System.out.println("Longitudine: " + lon);
                    }
                    System.out.println("Shape: "+locationInfo.getShape());
                    System.out.println("NanoSeconds: " + locationInfo.getTimestamp().getNanoSeconds());
                    System.out.println("Seconds: " + locationInfo.getTimestamp().getSeconds());
                    System.out.println("NumberOfUsers: " + a.getNumberOfUsers());
                    System.out.println("operationStatus: " + a.getOperationStatus());
                    System.out.println("ResourceURL: " + a.getResourceURL());
                }
                System.out.println("ResourceURL: " + accessPointList.getResourceURL());
                System.out.println("ZoneId: "+ accessPointList.getZoneId());
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

                GetZoneAccessPointResponseDescriptor getZoneAccessPointResponseDescriptor = this.objectMapper.readValue
                        (bodyString, GetZoneAccessPointResponseDescriptor.class);
                //test response info
                AccessPointInfo accessPointInfo = getZoneAccessPointResponseDescriptor.getAccessPointInfo();
                logger.info("Testing response info...");

                System.out.println("AccessPointId: "+ accessPointInfo.getAccessPointId());
                System.out.println("ConnectionType "+accessPointInfo.getConnectionType());
                LocationInfo locationInfo = accessPointInfo.getLocationInfo();
                for (double lat : locationInfo.getLatitude()) {
                    System.out.println("Latitudine: " + lat);
                }
                for (double lon : locationInfo.getLongitude()) {
                    System.out.println("Longitudine: " + lon);
                }
                System.out.println("Shape: "+locationInfo.getShape());
                System.out.println("NanoSeconds" + locationInfo.getTimestamp().getNanoSeconds());
                System.out.println("Seconds" + locationInfo.getTimestamp().getSeconds());
                System.out.println("NumberOfUsers: "+accessPointInfo.getNumberOfUsers());
                System.out.println("OperationStatus: "+accessPointInfo.getOperationStatus());
                System.out.println("resourceURL: "+ accessPointInfo.getResourceURL());



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
                case "1":  // lista zone con info
                    System.out.println("--------- GET ZONES LIST ----------");
                    apiLocationProcess.getZonesList();
                    break;
                case "2": // info di una zona specificata
                    System.out.println("ZoneId (zone01, zone02, zone03, zone04 ) : ");
                    tastiera = new Scanner(System.in);
                    zoneId = tastiera.nextLine();
                    System.out.printf("--------- GET %s INFO ----------\n", zoneId);
                    apiLocationProcess.getZone(zoneId);
                    break;
                case "3": // lista con info degli accessPoint in una zona specificata
                    System.out.println("ZoneId (zone01, zone02, zone03, zone04 ): ");
                    tastiera = new Scanner(System.in);
                    zoneId = tastiera.nextLine();
                    System.out.printf("--------- GET %s INFO ----------\n", zoneId);
                    apiLocationProcess.getZoneAccessPointList(zoneId);
                    break;
                case "4": // info di un accessPoint specificato in una zona specificata
                    System.out.println("ZoneId (zone01, zone02, zone03, zone04 ): ");
                    tastiera = new Scanner(System.in);  //es: zone01
                    zoneId= tastiera.nextLine();
                    System.out.println("Access point id: ");
                    tastiera = new Scanner(System.in);  //es: wifi-ap-2
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


