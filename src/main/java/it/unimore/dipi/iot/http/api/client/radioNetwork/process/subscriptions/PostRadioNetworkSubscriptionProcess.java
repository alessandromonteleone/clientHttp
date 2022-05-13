package it.unimore.dipi.iot.http.api.client.radioNetwork.process.subscriptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimore.dipi.iot.http.api.client.radioNetwork.model.*;
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

public class PostRadioNetworkSubscriptionProcess {

    final static protected Logger logger = LoggerFactory.getLogger(PostRadioNetworkSubscriptionProcess.class);

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public PostRadioNetworkSubscriptionProcess(String baseUrl) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.custom().build();
    }

    public void CreateRadioNetworkSubscription(RadioNetworkSubscriptionInfoDescriptor requestDescriptor) {
        try{
            //baseURL/rni/v2/subscriptions
            String targetUrl = String.format("%s/%s",baseUrl,"subscriptions");

            logger.info("Target Url: {}", targetUrl);

            String jsonBody = this.objectMapper.writeValueAsString(requestDescriptor);

            //Create the HTTP Post Request
            HttpPost createRequest = new HttpPost(targetUrl);

            //Add Content Type Header
            createRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            //Set Payload
            createRequest.setEntity(new StringEntity(jsonBody));

            //Execute the GetRequest
            CloseableHttpResponse response = httpClient.execute(createRequest);

            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
                //Obtain response body as a String
                String bodyString = EntityUtils.toString(response.getEntity());

                //Extract the Header
                //String Header = Arrays.stream(response.getHeaders("http")).findFirst().get().getValue();

                String Header = "";
                Optional<org.apache.http.Header> opt1 =  Arrays.stream(response.getHeaders("http")).findFirst() ;
                if (opt1.isPresent()) {
                    Header = opt1.get().getValue();
                    logger.info("Response Header: {}", Header);
                }

                logger.info("Response Code: {}", response.getStatusLine().getStatusCode());
                logger.info("Response Header: {}", Header);

                Gson gson = new GsonBuilder().create();

                RadioNetworkSubscriptionInfoDescriptor responseDescriptor =
                        gson.fromJson(bodyString, RadioNetworkSubscriptionInfoDescriptor.class);

                logger.info("testing... ");

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

            }
            else {
                logger.error(String.format("Error executing the request ! Status Code: %d -> Response Body: %s",
                        response != null ? response.getStatusLine().getStatusCode() : -1,
                        response != null ? EntityUtils.toString(response.getEntity()) : null));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        logger.info("Starting  Create Tester ...");

        String baseUrl = "https://try-mec.etsi.org/sbx1hio0m7/mep1/rni/v2";

        PostRadioNetworkSubscriptionProcess radioNetworkSubscriptionProcess = new PostRadioNetworkSubscriptionProcess(baseUrl);

        RadioNetworkSubscriptionInfoDescriptor requestDescriptor = new RadioNetworkSubscriptionInfoDescriptor();


        requestDescriptor.setSubscriptionType("CellChangeSubscription");
        requestDescriptor.setCallbackReference("http://my.callback.com/rni-cell-change/some-id");

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

        requestDescriptor.setFilterCriteriaAssocHo(filterCriteriaAssocHo);

        radioNetworkSubscriptionProcess.CreateRadioNetworkSubscription(requestDescriptor);

    }

}