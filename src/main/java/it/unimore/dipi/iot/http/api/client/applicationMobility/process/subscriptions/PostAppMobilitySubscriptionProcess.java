package it.unimore.dipi.iot.http.api.client.applicationMobility.process.subscriptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.AppMobilitySubscriptionDescriptor;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.AssociateId;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.FilterCriteria;
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

public class PostAppMobilitySubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostAppMobilitySubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public PostAppMobilitySubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void CreateAppMobilitySubscription(AppMobilitySubscriptionDescriptor requestDescriptor) {
        try {
            //baseURL/subscriptions/
            String targetUrl = String.format("%s/%s/", baseUrl, "subscriptions");

            logger.info("Target Url: {}", targetUrl);

            Gson gson = new GsonBuilder().create();

            String jsonBody = gson.toJson(requestDescriptor);

            //Create the HTTP Post Request
            HttpPost createRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createRequest);

            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 = Arrays.stream(response.getHeaders("http")).findFirst();
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info("Response Header: {}", Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Header: {}", Header);
                logger.info("Testing Response...");

                AppMobilitySubscriptionDescriptor responseDescriptor = gson.fromJson(bodyString, AppMobilitySubscriptionDescriptor.class);

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

        logger.info("Starting  Create Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/amsi/v1";

        String appInstanceId = "68a2d065-5a6d-40f3-8695-f5d497b6e51f";

        PostAppMobilitySubscriptionProcess appMobilitySubscriptionProcess = new PostAppMobilitySubscriptionProcess(baseUrl);

        //create request

        AppMobilitySubscriptionDescriptor requestDescriptor = new AppMobilitySubscriptionDescriptor();

        requestDescriptor.setSubscriptionType("MobilityProcedureSubscription");
        requestDescriptor.setCallbackReference("http://52a1-62-211-88-203.eu.ngrok.io/application_mobility/amsi/v1/amsi-mobility-procedure/TEST01");

        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setAppInstanceId(appInstanceId);

        AssociateId associateId = new AssociateId();
        associateId.setType(1);
        associateId.setValue("10.100.0.3");

        List<AssociateId> associateIdList = new ArrayList<>();
        associateIdList.add(associateId);

        filterCriteria.setAssociateId(associateIdList);

        requestDescriptor.setFilterCriteria(filterCriteria);

        //POST .../subscriptions/
        appMobilitySubscriptionProcess.CreateAppMobilitySubscription(requestDescriptor);

    }
}
