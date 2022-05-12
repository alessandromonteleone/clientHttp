package it.unimore.dipi.iot.http.api.client.serviceManagement.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.response.GetTransportsResponseDescriptor;
import it.unimore.dipi.iot.http.api.client.serviceManagement.process.services.GetServicesProcess;
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

public class GetTransportsProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetTransportsProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetTransportsProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        ObjectMapper objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }


    public void getTransports() {

        try {

            ///base_URL   /transports
            String targetUrl = String.format("%s/%s", baseUrl,"transports");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            logger.info("URI: {}", builder);

            //Create the HTTP GET Request
            HttpGet getServiceRequest = new HttpGet(builder.build());

            //Add Request Header
            getServiceRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(getServiceRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Raw Response Body: {}", bodyString);

                logger.info("test response...");

                //Deserialize Json String and Log obtained ResponseDescriptor
                Gson gson = new GsonBuilder().create();

                GetTransportsResponseDescriptor[] responseDescriptorList = gson.fromJson(bodyString, GetTransportsResponseDescriptor[].class);

                int i = 0;

                int size = responseDescriptorList.length;
                for(GetTransportsResponseDescriptor transportsDescriptor : responseDescriptorList){

                    i = i + 1;

                    System.out.println("\u001B[32m" + "transports " + i + " / " + size + "\u001B[0m");
                    System.out.println("id: " + transportsDescriptor.getId());
                    System.out.println("name: " + transportsDescriptor.getName());
                    System.out.println("type: " + transportsDescriptor.getType());
                    System.out.println("protocol: " + transportsDescriptor.getProtocol());
                    System.out.println("version: " + transportsDescriptor.getVersion());
                    System.out.println("endpoint: ");
                    System.out.println("    uris: ");
                    for(String uris : transportsDescriptor.getEndpoint().getUris())
                        System.out.println("        " + uris);
                    System.out.println("secutity: " + transportsDescriptor.getSecurity());

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

        String baseURL = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";

        GetTransportsProcess transportsProcess = new GetTransportsProcess(baseURL);

        //GET /transports
        transportsProcess.getTransports();

    }
}
