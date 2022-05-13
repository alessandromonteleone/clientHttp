package it.unimore.dipi.iot.http.api.client.radioNetwork.process.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.AssociateId;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.CellUserInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.ErabInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.UeInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.response.GetRabInfoResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.serviceManagement.process.GetTransportsProcess;
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

public class GetRabInfoProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetRabInfoProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetRabInfoProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        ObjectMapper objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }


    public void getAllRabInfo() {

        try {

            ///base_URL   /queries/rab_info
            String targetUrl = String.format("%s/%s/%s", baseUrl,"queries","rab_info");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getRabInfoRequest = new HttpGet(builder.build());

            //Add Request Header
            getRabInfoRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getRabInfoRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("test response...");

                //Deserialize Json String and Log obtained ResponseDescriptor
                Gson gson = new GsonBuilder().create();

                GetRabInfoResponseDescriptor responseDescriptor = gson.fromJson(bodyString, GetRabInfoResponseDescriptor.class);
                printResponseRabInfo(responseDescriptor);

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getQueryRabInfo(String appInsId, ArrayList<String> cellIdList, ArrayList<String> ueAddressList, String erabId, String qci ) {

        try {

            ///base_URL   /queries/rab_info
            String targetUrl = String.format("%s/%s/%s", baseUrl, "queries", "rab_info");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            if ( appInsId != null)
                builder.addParameter("app_ins_id", appInsId);

            if ( cellIdList != null) {
                for (String cellId : cellIdList )
                builder.addParameter("cell_id", cellId);
            }

            if ( ueAddressList != null) {
                for (String ueAddress : ueAddressList)
                builder.addParameter("ue_ipv4_address", ueAddress);
            }

            if ( erabId != null)
            builder.addParameter("erab_id", erabId);

            if ( qci != null)
            builder.addParameter("qci", qci);


            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getRabInfoRequest = new HttpGet(builder.build());

            //Add Request Header
            getRabInfoRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getRabInfoRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("test response...");

                //Deserialize Json String and Log obtained ResponseDescriptor
                Gson gson = new GsonBuilder().create();
                GetRabInfoResponseDescriptor responseDescriptor = gson.fromJson(bodyString, GetRabInfoResponseDescriptor.class);

                printResponseRabInfo(responseDescriptor);

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printResponseRabInfo(GetRabInfoResponseDescriptor responseDescriptor){

        System.out.println("appInstanceId: " + responseDescriptor.getAppInstanceId());

        int i = 0;

        if (responseDescriptor.getCellUserInfo() != null) {
            int sizeList = responseDescriptor.getCellUserInfo().size();
            for (CellUserInfo cellUserInfo : responseDescriptor.getCellUserInfo()) {
                i = i + 1;
                System.out.println("\u001B[32m" + "cellUserInfo " + i + " / " + sizeList + "\u001B[0m");

                System.out.println("    ecgi: ");
                System.out.println("        cellId: " + cellUserInfo.getEcgi().getCellId());
                System.out.println("        plmn: ");
                System.out.println("            mcc: " + cellUserInfo.getEcgi().getPlmn().getMcc());
                System.out.println("            mnc: " + cellUserInfo.getEcgi().getPlmn().getMnc());

                System.out.println("    ueInfo: ");
                for (UeInfo ueInfo : cellUserInfo.getUeInfo()) {
                    System.out.println("        associatedId:");
                    for (AssociateId associateId : ueInfo.getAssociateId()) {
                        System.out.println("            type: " + associateId.getType());
                        System.out.println("            value: " + associateId.getValue());
                    }

                    System.out.println("        erabInfo:");
                    for (ErabInfo erabInfo : ueInfo.getErabInfo()) {
                        System.out.println("            erabId: " + erabInfo.getErabId());
                        System.out.println("            erabQosParameters:");
                        System.out.println("                qci: " + erabInfo.getErabQosParameters().getQci());
                    }
                }

                System.out.println("\nrequestId: " + responseDescriptor.getRequestId());
                System.out.println("timeStamp: ");
                System.out.println("    nanoSeconds: " + responseDescriptor.getTimeStamp().getNanoSeconds());
                System.out.println("    seconds: " + responseDescriptor.getTimeStamp().getSeconds());
            }
        }
}



    public static void main(String[] args) {

        String baseURL = "https://try-mec.etsi.org/sbx1hio0m7/mep1/rni/v2";

        GetRabInfoProcess rabInfoProcess = new GetRabInfoProcess(baseURL);



        String appInsId = "298b2c0c-7efa-45d3-8b47-8ab3c009b845";

        ArrayList<String> cellIdList = new ArrayList<>();
        cellIdList.add("6060606");

        ArrayList<String> ueAddressList = new ArrayList<>();
        ueAddressList.add("10.100.0.2");

        String erabId ="820";
        String qci = "80";

        //GET /queries/rab_info
        rabInfoProcess.getAllRabInfo();

        //GET /queries/rab_info         queries
        rabInfoProcess.getQueryRabInfo(appInsId,cellIdList,null,null,null);


    }
}
