package it.unimore.dipi.iot.http.api.client.radioNetwork.process.queries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.CellInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.CellUEInfo;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.response.GetLayer2MeasResponseDescriptor;
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
import java.util.List;

public class GetLayer2MeasProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetLayer2MeasProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetLayer2MeasProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void getAllLayer2MeasInfo() {

        try {

            ///base_URL  /queries/layer2_meas
            String targetUrl = String.format("%s/%s/%s", baseUrl,"queries","layer2_meas");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getLayer2MeasRequest = new HttpGet(builder.build());

            //Add Request Header
            getLayer2MeasRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLayer2MeasRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("test response...");

                //Deserialize Json String and Log obtained ResponseDescriptor
                Gson gson = new GsonBuilder().create();

                GetLayer2MeasResponseDescriptor layer2MeasResponseDescriptor =
                        gson.fromJson(bodyString,GetLayer2MeasResponseDescriptor.class);

                printLayer2MeasInfo(layer2MeasResponseDescriptor);

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getQueryLayer2MeasInfo(String appInsId, ArrayList<String> cellIdList, ArrayList<String> ueAddressList ) {

        try {

            ///base_URL   /queries/layer2_meas
            String targetUrl = String.format("%s/%s/%s", baseUrl, "queries", "layer2_meas");

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


            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getLayer2MeasRequest = new HttpGet(builder.build());

            //Add Request Header
            getLayer2MeasRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getLayer2MeasRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("test response...");

                //Deserialize Json String and Log obtained ResponseDescriptor
                Gson gson = new GsonBuilder().create();

                GetLayer2MeasResponseDescriptor layer2MeasResponseDescriptor =
                        gson.fromJson(bodyString,GetLayer2MeasResponseDescriptor.class);

                printLayer2MeasInfo(layer2MeasResponseDescriptor);

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printLayer2MeasInfo(GetLayer2MeasResponseDescriptor responseDescriptor){

        List<CellInfo> cellInfoList = responseDescriptor.getCellInfo();
        int sizeCellInfoList = cellInfoList.size();
        int i = 0;
        List<CellUEInfo> cellUEInfoList= responseDescriptor.getCellUEInfo();
        int sizeCellUeInfo = cellUEInfoList.size();
        int j = 0;

        System.out.println("cellInfo: ");
        for (CellInfo cellInfo : cellInfoList){
            i = i + 1;
            System.out.println("\u001B[32m" + "cell " + i + " / " + sizeCellInfoList + "\u001B[0m");
            System.out.println("    ecgi:");
            System.out.println("        cellId" + cellInfo.getEcgi().getCellId());
            System.out.println("        plmn");
            System.out.println("            mcc" + cellInfo.getEcgi().getPlmn().getMcc());
            System.out.println("            mnc" + cellInfo.getEcgi().getPlmn().getMnc());
            System.out.println("    number_of_active_ue_dl_nongbr_cell: " + cellInfo.getNumberOfActiveUeDlNongbrCell());
            System.out.println("    number_of_active_ue_ul_nongbr_cell: " + cellInfo.getNumberOfActiveUeUlNongbrCell());
        }

        System.out.println("cellUeInfo: ");
        for (CellUEInfo cellUEInfo : cellUEInfoList) {
            j = j + 1;
            System.out.println("\u001B[32m" + "cellUe " + j + " / " + sizeCellUeInfo + "\u001B[0m");
            System.out.println("    associatedId:");
            System.out.println("            type: " + cellUEInfo.getAssociateId().getType());
            System.out.println("            value:  " + cellUEInfo.getAssociateId().getValue());
            System.out.println("    dl_nongbr_delay_ue: " + cellUEInfo.getDlNongbrDelayUe());
            System.out.println("    ecgi:");
            System.out.println("        cellId: " + cellUEInfo.getEcgi().getCellId());
            System.out.println("        plmn:");
            System.out.println("            mcc" + cellUEInfo.getEcgi().getPlmn().getMcc());
            System.out.println("            mnc" + cellUEInfo.getEcgi().getPlmn().getMnc());
            System.out.println("    ul_nongbr_delay_ue: " + cellUEInfo.getUlNongbrDelayUe());
        }

        System.out.println("timeStamp: ");
        System.out.println("    nanoSeconds: " + responseDescriptor.getTimeStamp().getNanoSeconds());
        System.out.println("    seconds: " + responseDescriptor.getTimeStamp().getSeconds() + "\n");

    }



    public static void main(String[] args) {

        String baseURL = "https://try-mec.etsi.org/sbx1hio0m7/mep1/rni/v2";

        GetLayer2MeasProcess layer2MeasProcess = new GetLayer2MeasProcess(baseURL);

        String appInsId = "298b2c0c-7efa-45d3-8b47-8ab3c009b845";

        ArrayList<String> cellIdList = new ArrayList<>();
        cellIdList.add("6060606");

        ArrayList<String> ueAddressList = new ArrayList<>();
        ueAddressList.add("10.100.0.1");

        //GET queries/layer2_meas
        layer2MeasProcess.getAllLayer2MeasInfo();

        //GET queries/layer2_meas
        layer2MeasProcess.getQueryLayer2MeasInfo(appInsId,cellIdList,null);


    }
}
