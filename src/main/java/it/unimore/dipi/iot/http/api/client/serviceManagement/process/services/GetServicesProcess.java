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


public class GetServicesProcess {


    final static protected Logger logger = LoggerFactory.getLogger(GetServicesProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private Gson gson;

    public GetServicesProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }


    public void getAllServices(Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {

            ///base_URL   /services?ser_instance_id=ef05534b-6ff7-4f53-9e6f-37be536ddf9c
            String targetUrl = String.format("%s/%s", this.baseUrl, "services");

            // URI Builder with Parameters
            //Output -> "/mec_service_mgmt/v1/services?ser_instance_id=9426eeeb-1a48-4f91-95cc-595da4b414a0&ser_instance_id=ad1c45a4-d312-4d0a-8fa8-957e79020d42"
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

    public void getSerInstanceId(ArrayList<String> serInstanceId, Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {
            ///base_URL   /services?ser_instance_id=ef05534b-6ff7-4f53-9e6f-37be536ddf9c
            String targetUrl = String.format("%s/%s", this.baseUrl, "services");

            // URI Builder with Parameters
            //Output -> "/mec_service_mgmt/v1/services?ser_instance_id=9426eeeb-1a48-4f91-95cc-595da4b414a0&ser_instance_id=ad1c45a4-d312-4d0a-8fa8-957e79020d42"
            URIBuilder builder = new URIBuilder(targetUrl);
            for (String id : serInstanceId) {
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

    public void getSerName(ArrayList<String> serName, Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {
            ///base_URL   /services?ser_instance_id=ef05534b-6ff7-4f53-9e6f-37be536ddf9c
            String targetUrl = String.format("%s/%s", this.baseUrl, "services");

            // URI Builder with Parameters
            //Output -> "/mec_service_mgmt/v1/services?ser_name=...&ser_name=..."
            URIBuilder builder = new URIBuilder(targetUrl);
            for (String sn : serName ) {
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

    public void getSerCategoryId(String serCategoryId, Boolean consumedLocalOnly, Boolean isLocal, String scopeOfLocality) {

        try {
            ///base_URL   /services?ser_instance_id=ef05534b-6ff7-4f53-9e6f-37be536ddf9c
            String targetUrl = String.format("%s/%s", this.baseUrl, "services");

            // URI Builder with Parameters
            //Output -> "/mec_service_mgmt/v1/services?ser_name=...&ser_name=..."
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


    public void getServiceWithSerId(String serviceId) {

        try {
            ///base_URL   /services?ser_instance_id=ef05534b-6ff7-4f53-9e6f-37be536ddf9c
            String targetUrl = String.format("%s/%s/%s", this.baseUrl, "services",serviceId);

            // URI Builder with Parameters
            //Output -> "/mec_service_mgmt/v1/services?ser_name=...&ser_name=..."
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
                System.out.println("state" + service.getState());

                System.out.println("transportInfo:");
                System.out.println("    id: " + service.getTransportInfo().getId());
                System.out.println("    name: " + service.getTransportInfo().getName());
                System.out.println("    type: " + service.getTransportInfo().getType());
                System.out.println("    protocol" + service.getTransportInfo().getProtocol());
                System.out.println("    version: " + service.getTransportInfo().getVersion());

                System.out.println("    endpoint:\n         uris:");
                for (String uris : service.getTransportInfo().getEndpoint().getUris())
                    System.out.println("            " + uris);

                System.out.println("    security:" + service.getTransportInfo().getSecurity());
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
        logger.info("Starting GET Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";


        //"get /service" to get them or they are in response of Post .../services
        ArrayList<String> serInstanceId = new ArrayList<>() {{
            add("53647bc8-1c20-407d-a508-1dc1ebc61a99");
            add("573ecc2f-1ef1-4aa3-8b91-99644716e328");
            add("7a8c1416-cf0d-4f9d-8f32-1df9fb4c8add");
        }};

        ArrayList<String> serName = new ArrayList<>() {{
            add("mec012-1");
            add("mec028-1");
        }};

        GetServicesProcess servicesProcess = new GetServicesProcess(baseUrl);

        Boolean consumedLocalOnly = null;
        Boolean isLocal = null;
        String scopeOfLocality = null;
        String serCategoryId = "rniId"; // WAI, locationId

        ///services
        servicesProcess.getAllServices(consumedLocalOnly, isLocal, scopeOfLocality);
        servicesProcess.getSerCategoryId(serCategoryId,consumedLocalOnly,isLocal,scopeOfLocality);
        servicesProcess.getSerInstanceId(serInstanceId, consumedLocalOnly,isLocal,scopeOfLocality);
        servicesProcess.getSerName(serName, consumedLocalOnly,isLocal,scopeOfLocality);

        //services/{serviceId}
        servicesProcess.getServiceWithSerId(serInstanceId.get(0));

    }
}

