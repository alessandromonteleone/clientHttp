package it.unimore.dipi.iot.http.api.client.applicationMobility.process.services;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

public class DeleteAppMobilityServicesProcess {

    final static protected Logger logger = LoggerFactory.getLogger(DeleteAppMobilityServicesProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public DeleteAppMobilityServicesProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void DeleteAppMobilityServices(String appMobilityServiceId) {

        try {

            //BaseUrl/
            String targetUrl = String.format("%s/app_mobility_services/%s", this.baseUrl, appMobilityServiceId);
            logger.info("Target Url: {}", targetUrl);

            //Create the HTTP Delete Request
            HttpDelete deleteRequest = new HttpDelete(targetUrl);

            //Add Content Type Header
            deleteRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Add Request Header
            deleteRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the Request
            CloseableHttpResponse response = httpClient.execute(deleteRequest);


            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
                //Extract the Header
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 = Arrays.stream(response.getHeaders("http")).findFirst();
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);

            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        logger.info("Starting Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/amsi/v1";

        //find it in Post ResponseBody
        String appMobilityServiceId = "aHhCkDzrvVC2RG-N";

        //process
        DeleteAppMobilityServicesProcess deleteAppMobilityServicesProcess = new DeleteAppMobilityServicesProcess(baseUrl);

        //DELETE .../app_mobility_services/{appMobilityServiceId}
        deleteAppMobilityServicesProcess.DeleteAppMobilityServices(appMobilityServiceId);

    }
}