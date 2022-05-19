package it.unimore.dipi.iot.http.api.client.serviceManagement.process.subscriptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.ApplicationsSubscriptionDescriptor;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.Links;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.Subscriptions;
import it.unimore.dipi.iot.http.api.client.serviceManagement.model.response.GetApplicationsSubscriptionsResponseDescriptor;
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

public class GetApplicationsSubscriptionsProcess {


    final static protected Logger logger = LoggerFactory.getLogger(GetApplicationsSubscriptionsProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private Gson gson;

    public GetApplicationsSubscriptionsProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }


    public void getSubscriptions(String appInstanceId ) {

        try {

            ///base_URL   /applications/{appInstanceId}/subscriptions
            String targetUrl = String.format("%s/%s/%s/%s", this.baseUrl, "applications", appInstanceId, "subscriptions");

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

                GetApplicationsSubscriptionsResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetApplicationsSubscriptionsResponseDescriptor.class);

                Links links = responseDescriptor.getLinks();
                System.out.println("_links: ");
                System.out.println("    self: ");
                System.out.println("        href: " + links.getSelf().getHref());
                System.out.println("    subscriptions: ");
                int i = 0;
                int sizeList = links.getSubscriptionsList().size();
                for (Subscriptions sub : links.getSubscriptionsList()) {
                    i = i + 1;
                    System.out.println("\u001B[32m" + "subscription " + i + " / " + sizeList + "\u001B[0m");
                    System.out.println("        href: " + sub.getHref());
                    System.out.println("        subscriptionType: " + sub.getSubscriptionType());
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


        public void getSubscriptionWithSubId(String appInstanceId, String subscriptionId ) {

            try {

                ///base_URL   /applications/{appInstanceId}/subscriptions
                String targetUrl = String.format("%s/%s/%s/%s/%s", this.baseUrl, "applications",appInstanceId,"subscriptions",subscriptionId);

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

                    ApplicationsSubscriptionDescriptor responseDescriptor =
                            gson.fromJson(bodyString, ApplicationsSubscriptionDescriptor.class);

                    System.out.println("subscriptionType: " + responseDescriptor.getSubscriptionType());
                    System.out.println("callbackReference: " + responseDescriptor.getCallbackReference());

                    Links links = responseDescriptor.getLinks();
                    System.out.println("_links: ");
                    System.out.println("    self: ");
                    System.out.println("        href: " + links.getSelf().getHref());
                    System.out.println("filteringCriteria: ");
                    System.out.println("    serNames: ");

                    for(String serNames : responseDescriptor.getFilteringCriteria().getSerNames())
                        System.out.println("        " + serNames);

                    for(String states : responseDescriptor.getFilteringCriteria().getSerNames())
                        System.out.println("        " + states);

                    System.out.println("    isLocal" + responseDescriptor.getFilteringCriteria().getIsLocal());


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

        logger.info("Starting GET Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/mec_service_mgmt/v1";

        String appInstanceId = "e486e1c1-2c28-498c-b216-6e624b031c55";

        String subscriptionId = "sub-aSjDldk3SWQKlZfr";

        GetApplicationsSubscriptionsProcess subscriptionsProcess = new GetApplicationsSubscriptionsProcess(baseUrl);

        //GET .../applications/{appInstanceId}/subscriptions
        subscriptionsProcess.getSubscriptions(appInstanceId);

        //GET .../applications/{appInstanceId}/subscriptions/{subscriptionId}
        subscriptionsProcess.getSubscriptionWithSubId(appInstanceId, subscriptionId);

    }
}
