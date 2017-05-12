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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
        JSONArray array = new JSONArray(lista);
        JSONObject obj = null;
        List<Programa> a = new ArrayList<Programa>();
        for (int i=0; i < array.length(); i++) {
            try {
                obj = array.getJSONObject(i);
                a.add(new Programa(obj.getString("Titulo"),obj.getString("Bar")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(lista);
        String b = null;
        try {
            b = Programa.actualizarFavoritos(a).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
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
    public String busquedaCat(@QueryParam("find") String find, @QueryParam("cat") String cat,
            @QueryParam("day") int day, @QueryParam("month") int month, @QueryParam("year") int year) {
        System.out.println(find);
        System.out.println(cat);
        System.out.println(day);
        
        
        if (find.equals("")) {
            if (cat.equals("")) {
                if (day == 0) {
                    return new Bar("hola2",19,19).toString();
                } else {
                    try {
                        return Programa.filtrarTiempo(day,month,year).toString();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (day == 0) {
                    try {
                        return Programa.filtrarCategoria(cat).toString();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        return Programa.filtrarTotal(cat, day, month, year).toString();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (cat.equals("")) {
                if (day==0) {
                   try {
                       return Programa.busqueda(find).toString();
                   } catch (SQLException e) {
                    e.printStackTrace();
                   } 
                } else {
                    try {
                        return Programa.busquedaTiempo(find, day, month, year).toString();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (day == 0) {
                    try {
                        return Programa.busquedaCat(find, cat).toString();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        return Programa.busquedaTotal(find, cat, day, month, year).toString();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
           
        }
        return cat;
    }
}
