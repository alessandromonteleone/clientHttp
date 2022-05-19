package it.unimore.dipi.iot.http.api.client.applicationMobility.process.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.AppMobilityServicesDescriptor;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.DeviceInformation;
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

public class GetAppMobilityServicesProcess {


    final static protected Logger logger = LoggerFactory.getLogger(GetAppMobilityServicesProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private Gson gson;

    public GetAppMobilityServicesProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }


    public void getAllServices() {

        try {

            ///base_URL   /app_mobility_services
            String targetUrl = String.format("%s/%s", this.baseUrl, "app_mobility_services");

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
                gson = new GsonBuilder().create();

                //print response
                AppMobilityServicesDescriptor[] responseDescriptorList =
                        gson.fromJson(bodyString, AppMobilityServicesDescriptor[].class);

                int i = 0;
                int sizeList = responseDescriptorList.length;


                for (AppMobilityServicesDescriptor appMobilityServicesDescriptor : responseDescriptorList) {
                    i = i + 1;
                    System.out.println("\n\u001B[32m" + "AppMobilityService " + i + " / " + sizeList + "\u001B[0m");
                    System.out.println("\nappMobilityServiceId: " + appMobilityServicesDescriptor.getAppMobilityServiceId());
                    System.out.println("deviceInformation: ");

                    if (appMobilityServicesDescriptor.getDeviceInformation() != null) {
                        for (DeviceInformation deviceInformation : appMobilityServicesDescriptor.getDeviceInformation()) {
                            System.out.println("    \nassociatedId:");
                            System.out.println("        type: " + deviceInformation.getAssociateId().getType());
                            System.out.println("        value:  " + deviceInformation.getAssociateId().getValue());
                            System.out.println("    appMobilityServiceLevel: " + deviceInformation.getAppMobilityServiceLevel());
                        }
                    }

                    System.out.println("serviceConsumerId: ");
                    System.out.println("    appInstanceId: " + appMobilityServicesDescriptor.getServiceConsumerId().getAppInstanceId());
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

    public void getServiceQueries (String filter, String all_fields, String fields, String exclude_fields, String exclude_default) {

        try {
            ///base_URL   //app_mobility_services
            String targetUrl = String.format("%s/%s", this.baseUrl, "app_mobility_services");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            if (filter != null)
                builder.addParameter("filter", filter);

            if (all_fields != null)
                builder.addParameter("all_fields", all_fields);

            if (fields != null)
                builder.addParameter("fields", fields);

            if (exclude_fields != null)
                builder.addParameter("exclude_fields", exclude_fields);

            if (exclude_default != null)
                builder.addParameter("exclude_default", exclude_default);


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
                gson = new GsonBuilder().create();

                //print response
                AppMobilityServicesDescriptor[] responseDescriptorList =
                        gson.fromJson(bodyString, AppMobilityServicesDescriptor[].class);

                int i = 0;
                int sizeList = responseDescriptorList.length;

                for (AppMobilityServicesDescriptor appMobilityServicesDescriptor : responseDescriptorList) {
                    i = i +1;
                    System.out.println("\n\u001B[32m" + "AppMobilityService " + i + " / " + sizeList + "\u001B[0m");
                    System.out.println("\nappMobilityServiceId: " + appMobilityServicesDescriptor.getAppMobilityServiceId());
                    System.out.println("deviceInformation: ");

                    if (appMobilityServicesDescriptor.getDeviceInformation() != null) {
                        for (DeviceInformation deviceInformation : appMobilityServicesDescriptor.getDeviceInformation()) {
                            System.out.println("    \nassociatedId:");
                            System.out.println("        type: " + deviceInformation.getAssociateId().getType());
                            System.out.println("        value:  " + deviceInformation.getAssociateId().getValue());
                            System.out.println("    appMobilityServiceLevel: " + deviceInformation.getAppMobilityServiceLevel());
                        }
                    }

                    System.out.println("serviceConsumerId: ");
                    System.out.println("    appInstanceId: " + appMobilityServicesDescriptor.getServiceConsumerId().getAppInstanceId());
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


    public void getServicesWithAppMobilitySerId(String appMobilityServiceId) {

        try {

            ///base_URL   /app_mobility_services
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "app_mobility_services", appMobilityServiceId );

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
                gson = new GsonBuilder().create();

                //print response
                AppMobilityServicesDescriptor responseDescriptor =
                        gson.fromJson(bodyString, AppMobilityServicesDescriptor.class);

                    System.out.println("\nappMobilityServiceId: " + responseDescriptor.getAppMobilityServiceId());
                    System.out.println("deviceInformation: ");

                    if (responseDescriptor.getDeviceInformation() != null) {
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

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/amsi/v1";

        GetAppMobilityServicesProcess appMobilityServicesProcess = new GetAppMobilityServicesProcess(baseUrl);

        String filter = null;

        String all_fields = null;

        String fields = null;

        String exclude_fields = null;

        String exclude_default = null;

        String appMobilityServiceId = "2sL0Wx54dTlDmTSY";

        // GET .../app_mobility_services
        appMobilityServicesProcess.getAllServices();

        //GET .../app_mobility_services     queries
        appMobilityServicesProcess.getServiceQueries(filter,all_fields,fields,exclude_fields,exclude_default);

        //GET .../app_mobility_services/{appMobilityServiceId}
        appMobilityServicesProcess.getServicesWithAppMobilitySerId(appMobilityServiceId);

    }
}