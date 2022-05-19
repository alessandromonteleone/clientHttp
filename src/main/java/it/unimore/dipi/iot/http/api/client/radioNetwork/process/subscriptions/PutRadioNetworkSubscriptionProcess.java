package it.unimore.dipi.iot.http.api.client.radioNetwork.process.subscriptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.*;
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

public class PutRadioNetworkSubscriptionProcess {
    final static protected Logger logger = LoggerFactory.getLogger(PutRadioNetworkSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final String baseUrl;

    public PutRadioNetworkSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.custom().build();
    }

    public void UpdateSubscription(String subscriptionId, RadioNetworkSubscriptionInfoDescriptor requestDescriptor) {

        try {

            //{BaseUrl}/subscriptions/{subscriptionId}
            String targetUrl = String.format("%s/subscriptions/%s", this.baseUrl ,subscriptionId);
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


            //200 "ok"
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

                // test response
                logger.info("Testing response...");



                RadioNetworkSubscriptionInfoDescriptor responseRadioNetworkSubscription =
                        gson.fromJson(bodyString, RadioNetworkSubscriptionInfoDescriptor.class);

                System.out.println("\n_links: ");
                System.out.println("    self: ");
                System.out.println("        href: " +  responseRadioNetworkSubscription.getLinks().getSelf().getHref());

                System.out.println("callbackReference: " + responseRadioNetworkSubscription.getCallbackReference());
                System.out.println("filterCriteriaAssocHo:" );
                System.out.println("    associateId:");
                for (AssociateId associateId : responseRadioNetworkSubscription.getFilterCriteriaAssocHo().getAssociateId()){
                    System.out.println("        type: " + associateId.getType());
                    System.out.println("        value: " + associateId.getValue());
                }

                System.out.println("    ecgi: ");
                for (Ecgi ecgi : responseRadioNetworkSubscription.getFilterCriteriaAssocHo().getEcgi()){
                    System.out.println("        cellId:" + ecgi.getCellId());
                    System.out.println("        plmn");
                    System.out.println("            mcc: " + ecgi.getPlmn().getMcc());
                    System.out.println("            mnc: " + ecgi.getPlmn().getMnc());
                }

                System.out.println("    hoStatus: ");
                for (int hoStatus : responseRadioNetworkSubscription.getFilterCriteriaAssocHo().getHoStatus())
                    System.out.println("        " + hoStatus);

                System.out.println("subscriptionType: " + responseRadioNetworkSubscription.getSubscriptionType() + "\n");


            } else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        logger.info("Starting ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/rni/v2";

        String subscriptionId = "1";

        PutRadioNetworkSubscriptionProcess radioNetworkSubscriptionProcess = new PutRadioNetworkSubscriptionProcess(baseUrl);


        //request descriptor
        RadioNetworkSubscriptionInfoDescriptor requestDescriptor = new RadioNetworkSubscriptionInfoDescriptor();


        requestDescriptor.setSubscriptionType("CellChangeSubscription");
        requestDescriptor.setCallbackReference("http://bc42-79-32-252-29.eu.ngrok.io/rni/v2/subscriptions/001");

        FilterCriteriaAssocHo filterCriteriaAssocHo = new FilterCriteriaAssocHo();

        //---
        AssociateId associateId = new AssociateId();
        associateId.setType(1);
        associateId.setValue("10.100.0.1");

        List<AssociateId> associateIdList = new ArrayList<>();
        associateIdList.add(associateId);

        filterCriteriaAssocHo.setAssociateId(associateIdList);

        //---
        Plmn plmn = new Plmn();
        plmn.setMnc("001");
        plmn.setMcc("001");

        Ecgi ecgi = new Ecgi();
        ecgi.setPlmn(plmn);
        ecgi.setCellId("6060606");

        List<Ecgi> ecgiList = new ArrayList<>();
        ecgiList.add(ecgi);

        filterCriteriaAssocHo.setEcgi(ecgiList);

        Self self = new Self();
        self.setHref("http://[hostIP]/sbox-xyz123/rni/v2/subscriptions/1");

        Links links = new Links();

        links.setSelf(self);

        requestDescriptor.setLinks(links);

        requestDescriptor.setFilterCriteriaAssocHo(filterCriteriaAssocHo);

        //PUT /subscriptions/{subscriptionId}
        radioNetworkSubscriptionProcess.UpdateSubscription(subscriptionId, requestDescriptor );

    }

}

