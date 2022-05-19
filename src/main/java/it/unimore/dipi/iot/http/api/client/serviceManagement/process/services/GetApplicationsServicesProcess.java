package it.unimore.dipi.iot.http.api.client.serviceManagement.process.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.ServicesDescriptor;
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

public class GetApplicationsServicesProcess {
    final static protected Logger logger = LoggerFactory.getLogger(GetApplicationsServicesProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private Gson gson;

    public GetApplicationsServicesProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }


    public void getAllServices(String appInstanceId,Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {

            ///base_URL   /
            String targetUrl = String.format("%s/%s/%s/%s", this.baseUrl, "applications", appInstanceId, "services");

            // URI Builder with Parameters
            //Output -> "/
            URIBuilder builder = new URIBuilder(targetUrl);

            if (consumedLocalOnly != null)
                builder.addParameter("consumed_local_only", String.format("%s",consumedLocalOnly));

            if (isLocal != null)
                builder.addParameter("is_local", String.format("%s",isLocal));

            if (scopeOfLocality != null)
                builder.addParameter("scope_of_locality", String.format("%s",isLocal));

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

                ServicesDescriptor[] responseDescriptorList =
                        gson.fromJson(bodyString, ServicesDescriptor[].class);

                printResponseDescriptor(responseDescriptorList);



            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getSerInstanceId( String appInstanceId, ArrayList<String> SerInstanceId, Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {
            ///base_URL   /
            String targetUrl = String.format("%s/%s/%s/%s", this.baseUrl, "applications", appInstanceId, "services");

            // URI Builder with Parameters
            //Output -> "/
            URIBuilder builder = new URIBuilder(targetUrl);
            for (String id : SerInstanceId) {
                builder.addParameter("ser_instance_id", id);
            }

            if (consumedLocalOnly != null)
                builder.addParameter("consumed_local_only", String.format("%s",consumedLocalOnly));

            if (isLocal != null)
                builder.addParameter("is_local", String.format("%s",isLocal));

            if (scopeOfLocality != null)
                builder.addParameter("scope_of_locality", String.format("%s",isLocal));

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

                ServicesDescriptor[] responseDescriptorList =
                        gson.fromJson(bodyString, ServicesDescriptor[].class);

                printResponseDescriptor(responseDescriptorList);



            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSerName(String appInstanceId, ArrayList<String> SerName, Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {
            ///base_URL   /
            String targetUrl = String.format("%s/%s/%s/%s", this.baseUrl, "applications", appInstanceId, "services");

            // URI Builder with Parameters
            //Output -> "/"
            URIBuilder builder = new URIBuilder(targetUrl);
            for (String sn : SerName ) {
                builder.addParameter("ser_name", sn);
            }

            if (consumedLocalOnly != null)
                builder.addParameter("consumed_local_only", String.format("%s",consumedLocalOnly));

            if (isLocal != null)
                builder.addParameter("is_local", String.format("%s",isLocal));

            if (scopeOfLocality != null)
                builder.addParameter("scope_of_locality", String.format("%s",isLocal));

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

                ServicesDescriptor[] responseDescriptorList =
                        gson.fromJson(bodyString, ServicesDescriptor[].class);

                printResponseDescriptor(responseDescriptorList);


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSerCategoryId(String appInstanceId, String serCategoryId, Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {

            String targetUrl = String.format("%s/%s/%s/%s", this.baseUrl, "applications", appInstanceId, "services");
            // URI Builder with Parameters

            URIBuilder builder = new URIBuilder(targetUrl);

            builder.addParameter("ser_category_id", serCategoryId);

            if (consumedLocalOnly != null)
                builder.addParameter("consumed_local_only", String.format("%s",consumedLocalOnly));

            if (isLocal != null)
                builder.addParameter("is_local", String.format("%s",isLocal));

            if (scopeOfLocality != null)
                builder.addParameter("scope_of_locality", String.format("%s",isLocal));

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

                ServicesDescriptor[] responseDescriptorList =
                        gson.fromJson(bodyString, ServicesDescriptor[].class);

                printResponseDescriptor(responseDescriptorList);


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getServiceWithSerId(String appInstanceId, String serviceId) {

        try {

            String targetUrl = String.format("%s/%s/%s/%s/%s", this.baseUrl, "applications", appInstanceId, "services",serviceId);

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
                gson = new GsonBuilder().create();

                ServicesDescriptor responseDescriptor =
                        gson.fromJson(bodyString, ServicesDescriptor.class);


                System.out.println("serInstanceId: " + responseDescriptor.getSerInstanceId());
                System.out.println("serName: " + responseDescriptor.getSerName());

                System.out.println("serCategory:");
                System.out.println("    href: " + responseDescriptor.getSerCategory().getHref());
                System.out.println("    id: " + responseDescriptor.getSerCategory().getId());
                System.out.println("    name: " + responseDescriptor.getSerCategory().getName());
                System.out.println("    version: " + responseDescriptor.getSerCategory().getVersion());

                System.out.println("version: " + responseDescriptor.getVersion());
                System.out.println("state" + responseDescriptor.getState());

                System.out.println("transportInfo:");
                System.out.println("    id: " + responseDescriptor.getTransportInfo().getId());
                System.out.println("    name: " + responseDescriptor.getTransportInfo().getName());
                System.out.println("    type: " + responseDescriptor.getTransportInfo().getType());
                System.out.println("    protocol" + responseDescriptor.getTransportInfo().getProtocol());
                System.out.println("    version: " + responseDescriptor.getTransportInfo().getVersion());

                System.out.println("    endpoint:\n         uris:");
                for (String uris : responseDescriptor.getTransportInfo().getEndpoint().getUris())
                    System.out.println("            " + uris);

                System.out.println("    security:" + responseDescriptor.getTransportInfo().getSecurity());
                System.out.println("serializer: " + responseDescriptor.getSerializer());
                System.out.println("scopeOfLocality: " + responseDescriptor.getScopeOfLocality());
                System.out.println("consumedLocalOnly: " + responseDescriptor.getConsumedLocalOnly());
                System.out.println("isLocal: " + responseDescriptor.getIsLocal());




            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void printResponseDescriptor(ServicesDescriptor[] servicesResponseDescriptorList){

        if(servicesResponseDescriptorList != null) {

            int i = 0;

            int size = servicesResponseDescriptorList.length;

            for (ServicesDescriptor service : servicesResponseDescriptorList) {


                i = i+1;
                System.out.println("\u001B[32m" + "Service Number " + i +" / " + size + "\u001B[0m");

                System.out.println("serInstanceId: " + service.getSerInstanceId());
                System.out.println("serName: " + service.getSerName());

                System.out.println("serCategory:");
                System.out.println("    href: " + service.getSerCategory().getHref());
                System.out.println("    id: " + service.getSerCategory().getId());
                System.out.println("    name: " + service.getSerCategory().getName());
                System.out.println("    version: " + service.getSerCategory().getVersion());

                System.out.println("version: " + service.getVersion());
                System.out.println("state: " + service.getState());

                System.out.println("transportInfo:");
                System.out.println("    id: " + service.getTransportInfo().getId());
                System.out.println("    name: " + service.getTransportInfo().getName());
                System.out.println("    type: " + service.getTransportInfo().getType());
                System.out.println("    protocol: " + service.getTransportInfo().getProtocol());
                System.out.println("    version: " + service.getTransportInfo().getVersion());

                System.out.println("    endpoint:\n         uris:");
                for (String uris : service.getTransportInfo().getEndpoint().getUris())
                    System.out.println("            " + uris);

                System.out.println("    security: " + service.getTransportInfo().getSecurity());
                System.out.println("serializer: " + service.getSerializer());
                System.out.println("scopeOfLocality: " + service.getScopeOfLocality());
                System.out.println("consumedLocalOnly: " + service.getConsumedLocalOnly());
                System.out.println("isLocal: " + service.getIsLocal());
            }
        }else {
            System.out.println("List is empty");
        }
    }

    public static void main(String[] args) {
        logger.info("Starting Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";

        ArrayList<String> serInstanceId = new ArrayList<>() {{
            add("55a2dc2e-149a-4c24-9b5b-c77942c3a1f8");
            add("d9242430-4f9f-49c7-aca4-76e26dbbe99b");
            //add("5243c784-0f40-4487-854e-949cb1a6722d");
        }};

        ArrayList<String> serName = new ArrayList<>() {{
            add("mec013-1");
            //add("mec013-1");
        }};

        GetApplicationsServicesProcess servicesProcess = new GetApplicationsServicesProcess(baseUrl);

        Boolean consumedLocalOnly = null;
        Boolean isLocal = null;
        String scopeOfLocality = null;
        String serCategoryId = "locationId";
        String appInstanceId = "d1dd85e4-edb4-4726-b7d1-bb079ded1d32";


        ///applications/{appInstanceId}/services
        servicesProcess.getAllServices(appInstanceId, consumedLocalOnly, isLocal, scopeOfLocality);
        servicesProcess.getSerInstanceId(appInstanceId, serInstanceId, consumedLocalOnly, isLocal, scopeOfLocality);
        servicesProcess.getSerName(appInstanceId, serName, consumedLocalOnly, isLocal, scopeOfLocality);
        servicesProcess.getSerCategoryId(appInstanceId, serCategoryId, consumedLocalOnly, isLocal, scopeOfLocality);

        ///applications/{appInstanceId}/services/{serviceId}
        servicesProcess.getServiceWithSerId(appInstanceId, serInstanceId.get(0) );

    }
}
