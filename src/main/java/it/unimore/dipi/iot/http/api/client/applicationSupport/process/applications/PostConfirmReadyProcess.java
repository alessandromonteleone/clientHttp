package it.unimore.dipi.iot.http.api.client.applicationSupport.process.applications;

import com.fasterxml.jackson.databind.ObjectMapper;


import it.unimore.dipi.iot.http.api.client.applicationSupport.model.request.PostConfirmReadyRequestDescriptor;
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
import java.util.Arrays;
import java.util.Optional;

public class PostConfirmReadyProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostConfirmReadyProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostConfirmReadyProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void confirmReady(PostConfirmReadyRequestDescriptor confirmReadyRequestDescriptor, String appInstanceId) {
        try{
            //baseURL: https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1
            //applications/edc8c240-75a9-4169-ab3a-60993ee7d90a/confirm_ready
            String targetUrl = String.format("%s/applications/%s/confirm_ready", this.baseUrl, appInstanceId);

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(confirmReadyRequestDescriptor);

            //Create the HTTP Post Request
            HttpPost createRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT){

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

        logger.info("Starting Tester ...");

        //mep1 or mep2
        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_app_support/v1";
        String appIstanceId = "0890de00-762d-42b7-bdbb-b5a256ab9261";

        PostConfirmReadyProcess confirmReadyProcess = new PostConfirmReadyProcess(baseUrl);

        PostConfirmReadyRequestDescriptor requestDescriptor = new PostConfirmReadyRequestDescriptor();
        requestDescriptor.setIndication("READY");

        confirmReadyProcess.confirmReady(requestDescriptor,appIstanceId );

    }

}
