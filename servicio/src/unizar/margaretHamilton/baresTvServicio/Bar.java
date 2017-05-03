package unizar.margaretHamilton.baresTvServicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Bar {
    
	private String nickbar;
	private String nombre;
	private String descripcion;
	private String direccion;
	private String URL;
	private float lat;
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
		// DBConnection inst;
		// TODO Auto-generated method stub
		ArrayList<Bar> bares = null;
		
		try{
		//inst = new DBConnection(db);
		PreparedStatement statement = null;
		inst.connect();
		ResultSet rs =  null;
		
		
			

			String sql =  "select * from bar "; 
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
	
	
	
	
	/*public List<Programa> getProgramasenCategoria(DBConnection database, String categoria) throws SQLException{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String a = dtf.format(now);
		String sql = "SELECT * FROM programa "
				+ "WHERE fin > "+"'"+a+"'"+ " and cat = "+"'"+categoria+"'";
		//Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List<Programa> plist = new ArrayList<Programa>();
		//System.out.println(sql);
	    
	    try {

	    statement=database.connection.prepareStatement(sql);
        //statement = connection.prepareStatement("SELECT id, name, value FROM Biler");
        resultSet = statement.executeQuery();
        
        
        ResultSetMetaData rsmd = resultSet.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();

		for (int i = 1; i <= numberOfColumns; i++) {
			System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
		}
		System.out.println();
		System.out
				.println("---------------------------------------------------------------------------------------");
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
            		resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
            System.out.println(pro.to_String());
        }
        
	    } finally {
	        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	       // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
		
		return plist;
	}*/
			
}

