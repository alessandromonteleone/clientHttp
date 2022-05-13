package it.unimore.dipi.iot.http.api.client.radioNetwork.process.subscriptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.AssociateId;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.Ecgi;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.RadioNetworkSubscriptionInfoDescriptor;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.Subscription;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.response.GetRadioNetworkSubscriptionResponseDescriptor;
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


public class GetRadioNetworkSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(GetRadioNetworkSubscriptionProcess.class);
    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public GetRadioNetworkSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }


    public void getAllSubscriptions() {

        try {

            ///base_URL  /subscriptions
            String targetUrl = String.format("%s/%s", baseUrl,"subscriptions");

            // URI Builder with Parameters
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
                Gson gson = new GsonBuilder().create();

                GetRadioNetworkSubscriptionResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetRadioNetworkSubscriptionResponseDescriptor.class);
                printRadioNetworkSubscriptionRensponse(responseDescriptor);


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getSubsciptionsWithType(String subscriptionType ) {

        try {

            ///base_URL   /subscriptions
            String targetUrl = String.format("%s/%s", baseUrl, "subscriptions");

            // URI Builder with Parameters
            URIBuilder builder = new URIBuilder(targetUrl);

            builder.addParameter("subscription_type", subscriptionType);

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
                Gson gson = new GsonBuilder().create();
                GetRadioNetworkSubscriptionResponseDescriptor responseDescriptor =
                        gson.fromJson(bodyString, GetRadioNetworkSubscriptionResponseDescriptor.class);

                printRadioNetworkSubscriptionRensponse(responseDescriptor);


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getSubsciptionWithId(String subscriptionId ) {

        try {

            ///base_URL   /subscriptions
            String targetUrl = String.format("%s/%s/%s", baseUrl, "subscriptions",subscriptionId);

            // URI Builder with Parameters
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
                Gson gson = new GsonBuilder().create();

                RadioNetworkSubscriptionInfoDescriptor responseDescriptor =
                        gson.fromJson(bodyString, RadioNetworkSubscriptionInfoDescriptor.class);


                System.out.println("\n_links: ");
                System.out.println("    self: ");
                System.out.println("        href: " +  responseDescriptor.getLinks().getSelf().getHref());

                System.out.println("callbackReference: " + responseDescriptor.getCallbackReference());
                System.out.println("filterCriteriaAssocHo:" );
                System.out.println("    associateId:");
                for (AssociateId associateId : responseDescriptor.getFilterCriteriaAssocHo().getAssociateId()){
                    System.out.println("        type: " + associateId.getType());
                    System.out.println("        value: " + associateId.getValue());
                }

                System.out.println("    ecgi: ");
                for (Ecgi ecgi : responseDescriptor.getFilterCriteriaAssocHo().getEcgi()){
                    System.out.println("        cellId:" + ecgi.getCellId());
                    System.out.println("        plmn");
                    System.out.println("            mcc: " + ecgi.getPlmn().getMcc());
                    System.out.println("            mnc: " + ecgi.getPlmn().getMnc());
                }

                System.out.println("    hoStatus: ");
                for (int hoStatus : responseDescriptor.getFilterCriteriaAssocHo().getHoStatus())
                    System.out.println("        " + hoStatus);

                System.out.println("subscriptionType: " + responseDescriptor.getSubscriptionType() + "\n");


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printRadioNetworkSubscriptionRensponse
            (GetRadioNetworkSubscriptionResponseDescriptor responseDescriptor ){

        System.out.println("\n_links: ");
        System.out.println("    self: ");
        System.out.println("        href: " +  responseDescriptor.getLinks().getSelf().getHref());

        int i = 0;
        int sizeList = responseDescriptor.getLinks().getSubscription().size();

        System.out.println("subscription:\n");
        for (Subscription subscription : responseDescriptor.getLinks().getSubscription()){
            i = i + 1;
            System.out.println("\u001B[32m" + "Subscription " + i + " / " + sizeList + "\u001B[0m");
            System.out.println("    href: " + subscription.getHref());
            System.out.println("    subscriptionType: " + subscription.getSubscriptionType());
        }

    }


    public static void main(String[] args) {

        String baseURL = "https://try-mec.etsi.org/sbx1hio0m7/mep1/rni/v2";

        // Permitted values: cell_change, rab_est, rab_mod, rab_rel,
        // meas_rep_ue, nr_meas_rep_ue, timing_advance_ue, ca_reconf, s1_bearer
        String subscriptionType = "cell_change";

        //find it in response of Post subscription
        String subscriptionId = "1";

        GetRadioNetworkSubscriptionProcess radioNetworkSubscriptionProcess =
                new GetRadioNetworkSubscriptionProcess(baseURL);

        //GET .../subscriptions
        radioNetworkSubscriptionProcess.getAllSubscriptions();

        //GET .../subscriptions    query
        radioNetworkSubscriptionProcess.getSubsciptionsWithType(subscriptionType);

        //GET .../subscriptions/{subscriptionId}
        radioNetworkSubscriptionProcess.getSubsciptionWithId(subscriptionId);

    }
}
