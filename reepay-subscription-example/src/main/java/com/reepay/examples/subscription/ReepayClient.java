package com.reepay.examples.subscription;

/**
 * Created by mikkel on 03/04/2017.
 */
import com.google.common.base.Strings;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Component
public class ReepayClient {

    private static final Logger LOG = LoggerFactory.getLogger(ReepayClient.class);

    private final String publicKey;

    private final String authHeader;

    private final WebTarget target;

    public ReepayClient(@Value("${public_key:}") String publicKey, @Value("${private_key:}") String privateKey) {
        if (Strings.isNullOrEmpty(publicKey)) {
            LOG.error("Missing public_key argument");
            System.exit(-1);
        }
        if (Strings.isNullOrEmpty(privateKey)) {
            LOG.error("Missing private_key argument");
            System.exit(-1);
        }
        this.publicKey = publicKey;
        this.authHeader = "Basic " + new String(Base64.getEncoder().encode((privateKey + ":").getBytes()));
        Client client = ClientBuilder.newClient();
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReepayClient.class.toString());
        client.register(new LoggingFeature(logger, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, Integer.valueOf(1024)));
        this.target = client.target("https://api.reepay.com");
    }

    public <T> Response post(String path, T entity) {
        return _request(path).post(Entity.json(entity));
    }

    public Response get(String path) {
        return _request(path).get();
    }

    public <T> Response get(String path, Map<String, List<T>> params){
        WebTarget target = this.target;
        for(Map.Entry<String, List<T>> param: params.entrySet()){
            for(T obj : param.getValue()){
                target = target.queryParam(param.getKey(), obj);
            }
        }
        return _request(target, path).get();
    }

    public String getPublicKey() {
        return publicKey;
    }

    private Invocation.Builder _request(String path) {
        return this.target.path(path).request().header("Authorization", authHeader);
    }

    private Invocation.Builder _request(WebTarget target, String path) {
        return target.path(path).request().header("Authorization", authHeader);
    }


}
