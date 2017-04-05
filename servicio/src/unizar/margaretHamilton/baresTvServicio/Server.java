package unizar.margaretHamilton.baresTvServicio;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/server")
public class Server {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Path("/bar")
  @Produces(MediaType.APPLICATION_JSON)
  public String consultaBar(@QueryParam("bar") String bar) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
      /*MySQLConfiguration db = new MySQLConfiguration("127.0.0.1","3306","barestv");
      ResultSet a;
      DBConnection inst;
      try {
          //Establece una conexion con el usuario root, contrasegna root en mi caso, poned la que corresponda
          //en el tercer parametro
          inst = new DBConnection(db,"root","root");
          inst.connect();
          a = inst.executeQuery("Select * from bar where bar="+bar);
          return new Consulta().transform(a);
      } finally {
          
      }*/
      return "Bar";
     
  }
  
  @GET
  @Path("/usuario")
  @Produces(MediaType.TEXT_PLAIN)
  public String User(@QueryParam("user") String user) {
      return "Hi"+user;
  }

  /* This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
  }*/

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/hola")
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }

}

