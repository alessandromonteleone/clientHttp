package it.unimore.dipi.iot.http.api.client.applicationMobility.process.subscriptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.applicationMobility.model.*;
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

    public class PutAppMobilitySubscriptionProcess {

        final static protected Logger logger = LoggerFactory.getLogger(PutAppMobilitySubscriptionProcess .class);
        private final CloseableHttpClient httpClient;
        private final String baseUrl;

        public PutAppMobilitySubscriptionProcess (String baseUrl) {
            this.baseUrl = baseUrl;
            this.httpClient = HttpClients.custom().build();
        }

        public void UpdateSubscription(String subscriptionId, AppMobilitySubscriptionDescriptor requestDescriptor) {

            try {


                //BaseUrl /subscriptions/{subscriptionId}
                String targetUrl = String.format("%s/subscriptions/%s", this.baseUrl, subscriptionId);
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

            logger.info("Starting Tester ...");
            String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/amsi/v1";
            String subscriptionId = "sub-f1MMuV9c1TlOW3Pa";
            String appInstanceId = "6c75a9b3-9c95-4039-8d0a-29666fe420d4";

            //process

            PutAppMobilitySubscriptionProcess subscriptionProcess = new PutAppMobilitySubscriptionProcess(baseUrl);

            //request Descriptor

            AppMobilitySubscriptionDescriptor requestDescriptor = new AppMobilitySubscriptionDescriptor();

            requestDescriptor.setSubscriptionType("MobilityProcedureSubscription");
            requestDescriptor.setCallbackReference("http://my.callback.com/amsi-mobility-procedure/some-id");

            Links links = new Links();
            Self self = new Self();
            self.setHref(String.format("http://meAppServer.example.com/amsi/v1/subscriptions/%s",subscriptionId));
            links.setSelf(self);
            requestDescriptor.setLinks(links);

            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setAppInstanceId(appInstanceId);

            AssociateId associateId = new AssociateId();
            associateId.setType(1);
            associateId.setValue("10.100.0.1");

            List<AssociateId> associateIdList = new ArrayList<>();
            associateIdList.add(associateId);

            filterCriteria.setAssociateId(associateIdList);

            requestDescriptor.setFilterCriteria(filterCriteria);

            //PUT .../subscriptions/{subscriptionId}
            subscriptionProcess.UpdateSubscription(subscriptionId,requestDescriptor);

        }
    }

