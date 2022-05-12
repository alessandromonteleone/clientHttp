package it.unimore.dipi.iot.http.api.client.serviceManagement.process.services;

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

public class DeleteApplicationsServicesProcess {

    final static protected Logger logger = LoggerFactory.getLogger(DeleteApplicationsServicesProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public DeleteApplicationsServicesProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void DeleteService(String appInstanceId, String serviceId) {

        try {
            //BASE_URL/applications/{appInstanceId}/services/{serviceId}

            String targetUrl = String.format("%s/%s/%s/%s/%s", this.baseUrl,"applications",appInstanceId,
                    "services",serviceId);
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
                logger.info("Response Header: {}", Header);

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

        logger.info("Starting ...");
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";
        String appInstanceId = "a6735c89-9041-4fac-a9d5-d28c7b81cc7d";
        //find it: "GET .../services" o  "GET .../applications/{appInstanceID}/services"
        //get it: "POST .../applications/{appInstanceID}/services
        String serviceId = "4fae3465-92e6-40d9-a7da-07dc2e518559";

        DeleteApplicationsServicesProcess servicesProcess = new DeleteApplicationsServicesProcess(baseUrl);

        //DELETE /mec_service_mgmt/v1/applications/{appInstanceID}/services/{serviceId}
        servicesProcess.DeleteService(appInstanceId,serviceId);
    }
}


