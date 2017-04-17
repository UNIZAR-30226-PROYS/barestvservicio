package unizar.margaretHamilton.baresTvServicio;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/server")
public class Server {
    @GET
    @Produces("application/json")
    @Path("/bar")
    public String bares() {
        List<Programa> lista = new ArrayList<Programa>();
        lista.add(new Programa("uno","bar1"));
        return lista.toString();
    }

}
