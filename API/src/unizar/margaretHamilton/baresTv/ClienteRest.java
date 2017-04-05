package unizar.margaretHamilton.baresTv;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.*;
import org.glassfish.jersey.message.internal.MediaTypes;



public class ClienteRest {
    private String Uri = "http://localhost:8080/unizar.margarethamilton.baresTvServicio/rest";
    public void getBares() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(UriBuilder.fromUri(Uri).build());

        Response response = target.path("rest").
                                                path("hello").
                                                request().
                                                accept(MediaType.APPLICATION_JSON).
                                                get(Response.class);
    }
}
