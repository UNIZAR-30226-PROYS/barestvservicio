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
    public String bares(@QueryParam("lat") float lat,@QueryParam("lng") float lng,@QueryParam("radio") float radio) {
        String bares = null;
        try {
            bares = Bar.getAllInRadius(lat, lng, radio).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bares;
        
    }
    @GET
    @Produces("application/json")
    @Path("/categorias")
    public String categorias() {
        String categorias = null;
        try {
            categorias = Categoria.getAll().toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
        
    }
    
    @GET
    @Produces("application/json")
    @Path("/actualizar")
    public String bares(@QueryParam("lista") String lista) {
        System.out.println(lista);
        return new Bar(lista).toString();
        
    }
    
    @GET
    @Produces("application/json")
    @Path("/destacados")
    public String destacados() {
        String a = null;
        try {
            a = Programa.ObtenerDestacados().toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    
    @GET
    @Produces("application/json")
    @Path("/programacion")
    public String programacion() {
        String a = null;
        try {
            a = Programa.ObtenerProgramacion().toString();
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
        return a;
    }
    
    @GET
    @Produces("application/json")
    @Path("/busqueda/filtro/categoria")
    public String busquedaCat(@QueryParam("find") String find, @QueryParam("cat") String cat) {
        String a = null;
        try {
            a = Programa.busquedaCat(find,cat).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    
    /*
    @GET
    @Produces("application/json")
    @Path("/busqueda/filtro/tiempo")
    public String busquedaCat(@QueryParam("find") String find, @QueryParam("day") int day,
            @QueryParam("month") int month, @QueryParam("year") int year) {
        String a = null;
        try {
            a = Programa.busquedaTiempo(find,cat).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }*/
    
    /*
    @GET
    @Produces("application/json")
    @Path("/busqueda/filtro/total")
    public String busquedaCat(@QueryParam("find") String find, @QueryParam("cat") String cat, 
            @QueryParam("day") int day, @QueryParam("month") int month, @QueryParam("year") int year) {
        String a = null;
        try {
            a = Programa.busquedaTotal(find,cat).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }*/
    
    @GET
    @Produces("application/json")
    @Path("/filtro/categoria")
    public String filtrarCategoria(@QueryParam("cat") String cat){
        String a = null;
        try {
            a = Programa.filtrarCategoria(cat).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    /*
    @GET
    @Produces("application/json")
    @Path("/filtro/categoria")
    public String filtrarCategoria(@QueryParam("cat") String cat){
        String a = null;
        try {
            a = Programa.filtrarCategoria(cat).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }*/
}
