package unizar.margaretHamilton.baresTvServicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class Bar {
	private String nickbar;
	@XmlElement(name="Nombre")
	private String nombre;
	private String descripcion;
	private String direccion;
	private String URL;
	@XmlElement(name="Lat")
	private float lat;
	@XmlElement(name="Lng")
	private float lng;
	
	public Bar(){
		nombre = "";
		nickbar = "";
		descripcion = "";
		direccion = "";
		URL = "";
		lat = 0;
		lng = 0;
	}
	
	public Bar(String nickbar,String nombre, String descripcion, float lat, float lng, String direccion, String URL) {
		this.nombre = nombre;
		this.nickbar = nickbar;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.URL = URL;
		this.lat = lat;
		this.lng = lng;
	}

	public Bar(String string, float float1, float float2) {
        nombre=string;
        lat=float1;
        lng=float2;
    }
	
	public Bar(String name) {
	    nombre=name;
	    lat=0;
	    lng=0;
	}

    public String getNick() {
		return nickbar;
	}

	public void setNick(String nick) {
		this.nickbar = nick;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descr) {
		this.descripcion = descr;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String dir) {
		this.direccion = dir;
	}
	
	public String getImgURL() {
		return URL;
	}

	public void setImgURL(String URL) {
		this.URL = URL;
	}
	
	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}
	
	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}
	
	public ArrayList<Bar> getAll(DBConnection inst)
		 {
		 MySQLConfiguration db = null;
		ArrayList<Bar> bares = null;
		
		try{
		//inst = new DBConnection(db);
		PreparedStatement statement = null;
		inst.connect();
		ResultSet rs =  null;
		
		
			

			String sql =  "select * from bar where activado = 1"; 
			System.out.println(sql);
			statement=inst.connection.prepareStatement(sql);
			bares = new ArrayList<Bar>();
			
	        rs = statement.executeQuery();
	        
	        ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
			}
			System.out.println();
			System.out
					.println("---------------------------------------------------------------------------------------");
			
            
			while (rs.next()){                              					                    
				Bar b= new Bar(
						//nombre
						rs.getString("nickbar"), rs.getString("nombre"), rs.getString("descrbar"), rs.getFloat("lat"),
						rs.getFloat("lng"),rs.getString("direccion"), rs.getString("urlimagen") );
				bares.add(b);
				System.out.println(" " + b.getNick() + "\t | "+ b.getNombre() + "\t | "+ b.getDescripcion() + "\t | "+ b.getLat() + "\t | "
						+ b.getLng() + "\t | "+ b.getDireccion() + "\t | "+ b.getImgURL() + "\t | ");

				//String nickbar,String nombre, String descripcion, float lat, float lng, String direccion, String URL
			}
		}catch (Exception e){
			System.out.println("Error al obtener bares: "+e.getMessage());
			e.printStackTrace();
			//throw new Exception(e.getMessage());
		}
		finally {
			try{
				
				//inst.disconnect();
			}catch (Exception e1){
				System.out.println("Error cerrando la conexi?n");
			}
		}
		return bares;	
	}
	
	public static List<Bar> getAllInRadius(float fromLat, float fromLng, float radiusInKm) throws SQLException{
	    Configuration db = new MySQLConfiguration();
        DBConnection inst; 
		List<Bar> bares = new ArrayList<Bar>();
		
		PreparedStatement statement = null;
        ResultSet resultSet = null;
		
		try{
		    inst = new DBConnection(db);
	        inst.connect();
		
		
			String sql =  "SELECT *, "
                                + "( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
                                + "FROM bar "
                                + "WHERE activado = 1 "
                                + "HAVING distance < ? ORDER BY distance"; 
			statement=inst.connection.prepareStatement(sql);
            statement.setFloat(1, fromLat);
            statement.setFloat(2, fromLng);
            statement.setFloat(3, fromLat);
            statement.setFloat(4, radiusInKm);
       
	        resultSet = statement.executeQuery();
	        
			while (resultSet.next()){                              					                    
				Bar b= new Bar(resultSet.getString("nombre"), resultSet.getFloat("lat"),resultSet.getFloat("lng"));
				bares.add(b);

				//String nickbar,String nombre, String descripcion, float lat, float lng, String direccion, String URL
			}
			inst.disconnect();
		}catch (Exception e){
			System.out.println("Error al obtener bares cercanos: "+e.getMessage());
			e.printStackTrace();
			//throw new Exception(e.getMessage());
		}
		finally {
			try{
				
				//inst.disconnect();
			}catch (Exception e1){
				System.out.println("Error cerrando la conexi?n");
			}
		}
		return bares;	
	}
	
	@Override
    public String toString(){
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject().put("Nombre", nombre).put("Lat", lat).put("Lng",lng).toString();
        } catch (JSONException e) {
            return null;
        }
    }
			
}

