package unizar.margaretHamilton.baresTvServicio;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/server")
public class Server {
    @GET
    @Produces("application/json")
    @Path("/bar")
    public String bares() {
        List<Programa> lista = new ArrayList<Programa>();
        lista.add(new Programa("uno","bar1"));
        lista.add(new Programa("dos","bar1","Xian es tonto",LocalDateTime.of(2017, 05,29,18,30).toString(),LocalDateTime.of(2017, 05,29,19,30).toString()));
        return lista.toString();
    }
    @GET
    @Produces("application/json")
    @Path("/destacados")
    public String destacados() {
        /*List<Programa> lista = new ArrayList<Programa>();
        lista.add(new Programa("XianTonto 2","bar1","Xian es tonto",LocalDateTime.of(2017, 05,29,18,30).toString(),LocalDateTime.of(2017, 05,29,19,30).toString()));
        return lista.toString();*/
        String a = null;
        try {
            a = Programa.ObtenerDestacados().toString();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
    
    @GET
    @Produces("application/json")
    @Path("/busqueda")
    public String busqueda(@QueryParam("find") String find) {
        String a = null;
        try {
            a = Programa.busqueda(find).toString();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
    
    @GET
    @Produces("application/json")
    @Path("/filtro/categoria")
    public String filtrarCategoria(@QueryParam("cat") String cat){
        String a = null;
        try {
            a = Programa.filtrarCategoria(cat).toString();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
}
