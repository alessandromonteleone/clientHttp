
package it.unimore.dipi.iot.http.api.client.applicationMobility.process.subscriptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.*;
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

    public class GetAppMobilitySubscriptionProcess {


        final static protected Logger logger = LoggerFactory.getLogger(GetAppMobilitySubscriptionProcess.class);

        private final CloseableHttpClient httpClient;
        private final String baseUrl;
        private Gson gson;

        public GetAppMobilitySubscriptionProcess(String baseUrl) {
            this.baseUrl = baseUrl;
            this.httpClient = HttpClients.custom().build();
        }


        public void getAllSubscription() {

            try {

                ///base_URL   /subscriptions/
                String targetUrl = String.format("%s/%s/", this.baseUrl, "subscriptions");

                URIBuilder builder = new URIBuilder(targetUrl);


                logger.info("URI: {}", builder);

                //Create the HTTP GET Request
                HttpGet getSubscriptionsRequest = new HttpGet(builder.build());

                //Add Request Header
                getSubscriptionsRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

                //Execute the GetRequest
                CloseableHttpResponse response = httpClient.execute(getSubscriptionsRequest);

                if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                    //Obtain response body as a String
                    String bodyString = EntityUtils.toString(response.getEntity());

                    logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                    logger.info("Raw Response Body: {}", bodyString);

                    logger.info("test response...");

                    //Deserialize Json String and Log obtained ResponseDescriptor
                    gson = new GsonBuilder().create();

                    //print response

                    GetAppMobilitySubscriptionResponseDescriptor responseDescriptor =
                            gson.fromJson(bodyString, GetAppMobilitySubscriptionResponseDescriptor.class);

                    System.out.println("\n_links:");
                    System.out.println("    self:");
                    System.out.println("        href: " + responseDescriptor.getLinks().getSelf().getHref());
                    System.out.println("subscription:");
                    int i = 0;
                    int sizeList = responseDescriptor.getSubscription().size();
                    for (Subscription subscription : responseDescriptor.getSubscription()) {
                        i = i + 1;
                        System.out.println("\u001B[32m" + "subscription " + i + " / " + sizeList + "\n" +  "\u001B[0m");
                        System.out.println("    href: " + subscription.getHref());
                        System.out.println("    subscriptionType: " + subscription.getSubscriptionType() + "\n");
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

        public void getSubscriptionTypeQueries (String subscriptionType) {

            try {
                ///base_URL   /"subscriptions"
                String targetUrl = String.format("%s/%s/", this.baseUrl, "subscriptions");

                // URI Builder with Parameters
                URIBuilder builder = new URIBuilder(targetUrl);

                if (subscriptionType != null)
                    builder.addParameter("subscriptionType", subscriptionType);


                logger.info("URI: {}", builder);

                //Create the HTTP GET Request
                HttpGet getSubscriptionRequest = new HttpGet(builder.build());

                //Add Request Header
                getSubscriptionRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

                //Execute the GetRequest
                CloseableHttpResponse response = httpClient.execute(getSubscriptionRequest);

                if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                    //Obtain response body as a String
                    String bodyString = EntityUtils.toString(response.getEntity());

                    logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                    logger.info("Raw Response Body: {}", bodyString);

                    logger.info("test response...");

                    //Deserialize Json String and Log obtained ResponseDescriptor
                    gson = new GsonBuilder().create();

                    //print response

                    GetAppMobilitySubscriptionResponseDescriptor responseDescriptor =
                            gson.fromJson(bodyString, GetAppMobilitySubscriptionResponseDescriptor.class);

                    System.out.println("\n_links:");
                    System.out.println("    self:");
                    System.out.println("        href: " + responseDescriptor.getLinks().getSelf().getHref());
                    System.out.println("subscription:");
                    int i = 0;
                    int sizeList = responseDescriptor.getSubscription().size();
                    for (Subscription subscription : responseDescriptor.getSubscription()) {
                        i = i + 1;
                        System.out.println("\u001B[32m" + "subscription " + i + " / " + sizeList  +  "\u001B[0m");
                        System.out.println("    href: " + subscription.getHref());
                        System.out.println("    subscriptionType: " + subscription.getSubscriptionType() + "\n");
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


        public void getSubscriptionWithSubId(String subscriptionId) {

            try {

                ///base_URL   /subscriptions/{subscriptionId}
                String targetUrl = String.format("%s/%s/%s", this.baseUrl, "subscriptions", subscriptionId );

                URIBuilder builder = new URIBuilder(targetUrl);

                logger.info("URI: {}", builder);

                //Create the HTTP GET Request
                HttpGet getSubscriptionRequest = new HttpGet(builder.build());

                //Add Request Header
                getSubscriptionRequest.addHeader(HttpHeaders.USER_AGENT, "DemoIoTInventoryClient-0.0.1");

                //Execute the GetRequest
                CloseableHttpResponse response = httpClient.execute(getSubscriptionRequest);

                if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                    //Obtain response body as a String
                    String bodyString = EntityUtils.toString(response.getEntity());

                    logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                    logger.info("Raw Response Body: {}", bodyString);

                    logger.info("test response...");

                    //Deserialize Json String and Log obtained ResponseDescriptor
                    gson = new GsonBuilder().create();

                    //print response

                    AppMobilitySubscriptionDescriptor responseDescriptor =
                            gson.fromJson(bodyString, AppMobilitySubscriptionDescriptor.class);

                    System.out.println( "\n_links:");
                    System.out.println("    self: ");
                    System.out.println("        href: " + responseDescriptor.getLinks().getSelf().getHref());
                    System.out.println("callbackReference: " + responseDescriptor.getCallbackReference());
                    System.out.println("filterCriteria: ");
                    System.out.println("appInstanceId: " + responseDescriptor.getFilterCriteria().getAppInstanceId());
                    System.out.println("    associateId:");
                    for (AssociateId associateId : responseDescriptor.getFilterCriteria().getAssociateId()){
                        System.out.println("\n        type: " + associateId.getType());
                        System.out.println("        value: " + associateId.getValue());
                    }
                    System.out.println("\n    mobilityStatus: ");
                    for (int mobilityStatus : responseDescriptor.getFilterCriteria().getMobilityStatus())
                        System.out.println("        " + mobilityStatus);
                    System.out.println("subscriptionType: " + responseDescriptor.getSubscriptionType());


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

            String subscriptionId = "sub-v7Lk8fRcfOs5vDZv";

            //Permitted values: mobility_proc or adj_app_info
            String subscriptionType = "mobility_proc";

            GetAppMobilitySubscriptionProcess subscriptionProcess = new GetAppMobilitySubscriptionProcess(baseUrl);

            // GET .../subscriptions/
            subscriptionProcess.getAllSubscription();

            //GET .../subscriptions/     queries
            subscriptionProcess.getSubscriptionTypeQueries(subscriptionType);

            //GET .../subscriptions/{subscriptionId}
            subscriptionProcess.getSubscriptionWithSubId(subscriptionId);

        }
    }
