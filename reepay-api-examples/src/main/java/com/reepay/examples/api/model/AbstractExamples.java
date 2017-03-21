package com.reepay.examples.api.model;

import com.reepay.examples.api.ReepayClient;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.logging.Level;

public abstract class AbstractExamples {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExamples.class);

    @Inject
    protected ReepayClient client;

    protected String createToken(String cardno, String cvv, int expMonth, int expYear, boolean recurring) {
        Client httpClient = ClientBuilder.newClient();
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AbstractExamples.class.toString());
        httpClient.register(new LoggingFeature(logger, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, Integer.valueOf(1024)));
        WebTarget target = httpClient.target("https://card.reepay.com");
        
        MultivaluedMap<String, String> params = new MultivaluedHashMap<>();
        params.putSingle("number", cardno);
        params.putSingle("month", Integer.toString(expMonth));
        params.putSingle("year", Integer.toString(expYear));
        params.putSingle("cvv", cvv);
        if (!recurring) {
            params.putSingle("recurring", "false");
        }
        params.putSingle("pkey", client.getPublicKey());

        Response response = target.path("/v1/token").request().post(Entity.form(params));
        if (response.getStatus() != 200) {
            LOG.error("Request failed");
            System.exit(-1);
        }
        Map<String, Object> res = response.readEntity(new GenericType<Map<String, Object>>() {
        });
        return res.get("id").toString();
    }
    
    protected static <T> T readResponse(Response response, Class<T> clazz) {
        if (response.getStatus() != 200) {
            LOG.error("Request failed");
            System.exit(-1);
        }
        return response.readEntity(clazz);
    }

    protected static ErrorResponse getErrorResponse(Response response) {
        return response.readEntity(ErrorResponse.class);
    }
    
}
