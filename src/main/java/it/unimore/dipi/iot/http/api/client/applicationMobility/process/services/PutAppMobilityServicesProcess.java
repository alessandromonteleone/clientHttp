package it.unimore.dipi.iot.http.api.client.applicationMobility.process.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.AppMobilityServicesDescriptor;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.AssociateId;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.DeviceInformation;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.ServiceConsumerId;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
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

public class PutAppMobilityServicesProcess  {

    final static protected Logger logger = LoggerFactory.getLogger(PutAppMobilityServicesProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public PutAppMobilityServicesProcess (String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String appMobilityServiceId, AppMobilityServicesDescriptor requestDescriptor) {

        try {

            String targetUrl = String.format("%s/%s/%s", this.baseUrl,"app_mobility_services", appMobilityServiceId);
            logger.info("Target Url: {}", targetUrl);


            Gson gson = new GsonBuilder().create();
            String jsonBody = gson.toJson(requestDescriptor);

            //Create the HTTP Put Request
            HttpPut createRequest = new HttpPut(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the Request
            CloseableHttpResponse response = httpClient.execute(createRequest);


            // 200 "ok"
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 = Arrays.stream(response.getHeaders("http")).findFirst();
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info(Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Location Header: {}", Header);
                logger.info("Raw Response Body: {}", bodyString);
                logger.info("testing response...");

                //build response with gson
                AppMobilityServicesDescriptor responseDescriptor = gson.fromJson(bodyString, AppMobilityServicesDescriptor.class);

                //print response
                System.out.println("\nappMobilityServiceId: " + responseDescriptor.getAppMobilityServiceId());
                System.out.println("deviceInformation: " );

                if ( requestDescriptor.getDeviceInformation() != null) {
                    for (DeviceInformation deviceInformation : responseDescriptor.getDeviceInformation()) {
                        System.out.println("    \nassociatedId:");
                        System.out.println("        type: " + deviceInformation.getAssociateId().getType());
                        System.out.println("        value:  " + deviceInformation.getAssociateId().getValue());
                        System.out.println("    appMobilityServiceLevel: " + deviceInformation.getAppMobilityServiceLevel());
                    }
                }

                System.out.println("serviceConsumerId: ");
                System.out.println("    appInstanceId: " + responseDescriptor.getServiceConsumerId().getAppInstanceId());



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

        logger.info("Starting Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/amsi/v1";
        String appMobilityServiceId = "2sL0Wx54dTlDmTSY";
        String appInstanceId = "68a2d065-5a6d-40f3-8695-f5d497b6e51f";
        PutAppMobilityServicesProcess appMobilityServicesProcess = new PutAppMobilityServicesProcess(baseUrl);


        // create request
        AppMobilityServicesDescriptor requestDescriptor = new AppMobilityServicesDescriptor();

        requestDescriptor.setAppMobilityServiceId(appMobilityServiceId);

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setAppMobilityServiceLevel("[\"APP_MOBILITY_NOT_ALLOWED\"]");

        AssociateId associateId = new AssociateId();
        associateId.setType("[\"UE_IPv4_ADDRESS\"]");
        associateId.setValue("10.100.0.1");

        deviceInformation.setAssociateId(associateId);

        deviceInformation.setContextTransferState("[\"NOT_TRANSFERRED\"]");

        List<DeviceInformation> deviceInformationList = new ArrayList<>();
        deviceInformationList.add(deviceInformation);

        ServiceConsumerId serviceConsumerId = new ServiceConsumerId();
        serviceConsumerId.setAppInstanceId(appInstanceId);

        requestDescriptor.setDeviceInformation(deviceInformationList);

        requestDescriptor.setServiceConsumerId(serviceConsumerId);


        //PUT.../app_mobility_services/{appMobilityServiceId }
        appMobilityServicesProcess.UpdateSubscription(appMobilityServiceId,requestDescriptor);

    }

}
