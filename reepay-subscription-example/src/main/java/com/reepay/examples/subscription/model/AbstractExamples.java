package com.reepay.examples.subscription.model;

import com.reepay.examples.subscription.ReepayClient;
import com.reepay.examples.subscription.model.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public abstract class AbstractExamples {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExamples.class);

    @Inject
    protected ReepayClient client;

    protected static <T> T readResponse(Response response, Class<T> clazz) {
        if (response.getStatus() != 200) {
            //TODO: Handle this in a graceful way.
            LOG.error("Request failed");
            throw ServiceException.create(getErrorResponse(response));
        }
        return response.readEntity(clazz);
    }

    protected static ErrorResponse getErrorResponse(Response response) {
        return response.readEntity(ErrorResponse.class);
    }

}
