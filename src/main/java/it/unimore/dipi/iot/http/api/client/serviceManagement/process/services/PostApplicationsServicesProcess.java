package it.unimore.dipi.iot.http.api.client.serviceManagement.process.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.Endpoint;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.SerCategory;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.TransportInfo;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.ServicesDescriptor;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PostApplicationsServicesProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostApplicationsServicesProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostApplicationsServicesProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void createNewSubscription(String appInstanceId, ServicesDescriptor requestDescriptor) {
        try{

            //BASE_URL/applications/appInstanceId/services
            String targetUrl = String.format("%s/applications/%s/services", this.baseUrl,appInstanceId);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(requestDescriptor);

            //Create the HTTP Post Request
            HttpPost createRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header

                //1) java.util.NoSuchElementException: No value present
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                //2)
                //soluzione 2
                String Header = "";
                Optional<org.apache.http.Header> opt1 =  Arrays.stream(response.getHeaders("http")).findFirst() ;
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info("Response Header: {}", Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);

                //create response gson
                Gson gson = new GsonBuilder().create();

                ServicesDescriptor responseDescriptor =
                        gson.fromJson(bodyString, ServicesDescriptor.class);

                //testing response

                System.out.println("serInstanceId: " + responseDescriptor.getSerInstanceId());
                System.out.println("serName: " + responseDescriptor.getSerName());

                System.out.println("serCategory:");
                System.out.println("    href: " + responseDescriptor.getSerCategory().getHref());
                System.out.println("    id: " + responseDescriptor.getSerCategory().getId());
                System.out.println("    name: " + responseDescriptor.getSerCategory().getName());
                System.out.println("    version: " + responseDescriptor.getSerCategory().getVersion());

                System.out.println("version: " + responseDescriptor.getVersion());
                System.out.println("state: " + responseDescriptor.getState());

                System.out.println("transportInfo:");
                System.out.println("    id: " + responseDescriptor.getTransportInfo().getId());
                System.out.println("    name: " + responseDescriptor.getTransportInfo().getName());
                System.out.println("    type: " + responseDescriptor.getTransportInfo().getType());
                System.out.println("    protocol: " + responseDescriptor.getTransportInfo().getProtocol());
                System.out.println("    version: " + responseDescriptor.getTransportInfo().getVersion());

                System.out.println("    endpoint:\n         uris:");
                for (String uris : responseDescriptor.getTransportInfo().getEndpoint().getUris())
                    System.out.println("            " + uris);

                System.out.println("    security: " + responseDescriptor.getTransportInfo().getSecurity());
                System.out.println("serializer: " + responseDescriptor.getSerializer());
                System.out.println("scopeOfLocality: " + responseDescriptor.getScopeOfLocality());
                System.out.println("consumedLocalOnly: " + responseDescriptor.getConsumedLocalOnly());
                System.out.println("isLocal: " + responseDescriptor.getIsLocal());

            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        logger.info("Starting IoT Inventory Location Create Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";

        String appInstanceId = "a6735c89-9041-4fac-a9d5-d28c7b81cc7d";

        PostApplicationsServicesProcess servicesProcess = new PostApplicationsServicesProcess(baseUrl);

        ServicesDescriptor requestDescriptor = new ServicesDescriptor();

        requestDescriptor.setSerName("myRnis");
        SerCategory serCategory = new SerCategory();

        serCategory.setHref("catItem1");
        serCategory.setId("id12345");
        serCategory.setName("RNI");
        serCategory.setVersion("v2");
        requestDescriptor.setSerCategory(serCategory);

        requestDescriptor.setVersion("2.2.1");
        requestDescriptor.setState("ACTIVE");

        TransportInfo transportInfo = new TransportInfo();
        transportInfo.setId("TransId12345");
        transportInfo.setName("REST");
        transportInfo.setDescription("REST API");
        transportInfo.setType("REST_HTTP");
        transportInfo.setProtocol("HTTP");
        transportInfo.setVersion("2.0");

        Endpoint endpoint = new Endpoint();
        List<String> uris = new ArrayList<>();
        uris.add("https://my.callback.com/sbx1hio0m7/mep1/rni/v2/");
        endpoint.setUris(uris);
        transportInfo.setEndpoint(endpoint);

        requestDescriptor.setTransportInfo(transportInfo);

        requestDescriptor.setSerializer("JSON");
        requestDescriptor.setScopeOfLocality("MEC_SYSTEM");
        requestDescriptor.setConsumedLocalOnly(false);
        requestDescriptor.setIsLocal(false);

        // POST /applications/{appInstanceId}/services
        servicesProcess.createNewSubscription(appInstanceId, requestDescriptor);


    }

}

