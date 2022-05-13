package it.unimore.dipi.iot.http.api.client.radioNetwork.process.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.Plmn;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.response.GetPlmnInfoResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.response.GetRabInfoResponseDescriptor;
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

public class GetPlmnInfoProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetPlmnInfoProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetPlmnInfoProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        ObjectMapper objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }


    public void getPlmnInfo(ArrayList<String> appInstanceIdList) {

        try {

            ///base_URL   /queries/plmn_info?app_ins_id=...
            String targetUrl = String.format("%s/%s/%s", baseUrl, "queries", "plmn_info");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            for (String appInstanceId : appInstanceIdList)
                builder.addParameter("app_ins_id", appInstanceId);

            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getPlmnInfoRequest = new HttpGet(builder.build());

            //Add Request Header
            getPlmnInfoRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getPlmnInfoRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("test response...");

                //Deserialize Json String and Log obtained ResponseDescriptor
                Gson gson = new GsonBuilder().create();

                GetPlmnInfoResponseDescriptor[] plmnInfoResponseDescriptorList = gson.fromJson(bodyString, GetPlmnInfoResponseDescriptor[].class);

                int i = 0;
                int sizeList = plmnInfoResponseDescriptorList.length;

                for (GetPlmnInfoResponseDescriptor responseDescriptor : plmnInfoResponseDescriptorList) {
                    i = i + 1;
                    System.out.println("\u001B[32m" + "Instance " + i + " / " + sizeList + "\u001B[0m");
                    System.out.println("appInstanceId: " + responseDescriptor.getAppInstanceId());
                    System.out.println("plmn:");
                    for (Plmn plmn : responseDescriptor.getPlmnList()) {
                        System.out.println("    mcc: " + plmn.getMcc());
                        System.out.println("    mnc: " + plmn.getMnc() + "\n");
                    }
                }

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
        String baseURL = "https://try-mec.etsi.org/sbx1hio0m7/mep1/rni/v2";
        ArrayList<String> appInstanceIdList = new ArrayList<>();
        appInstanceIdList.add("298b2c0c-7efa-45d3-8b47-8ab3c009b845");
        appInstanceIdList.add("b8ae165a-a1e3-4d6c-86d9-52c59ad314b6");
        appInstanceIdList.add("b8a203be-ac81-45a6-8d88-fdb1f8f5393b");

        GetPlmnInfoProcess plmnInfoProcess = new GetPlmnInfoProcess(baseURL);

        //GET /queries/plmn_info    queries
        plmnInfoProcess.getPlmnInfo(appInstanceIdList);

    }
}
