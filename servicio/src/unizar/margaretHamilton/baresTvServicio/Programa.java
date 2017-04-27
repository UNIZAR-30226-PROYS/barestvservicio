package unizar.margaretHamilton.baresTvServicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

public class Programa {
	private String titulo;
	private String bar;
	private String descripcion;
	private String categoria;
	private String inicio;
	private String fin;
	private int destacado;
	
	
	
	public Programa(String titulo, String bar){
		this.titulo=titulo;
		this.bar=bar;
		this.destacado=0;
		this.descripcion=null;
		this.categoria=null;
		this.inicio=null;
		this.fin=null;		
	}
	
	public Programa(String titulo, String bar, 
			String descripcion,String inicio,String fin, String categoria){
		
		this.titulo=titulo;
		this.bar=bar;
		this.destacado=0;
		this.descripcion=descripcion;
		this.categoria=categoria;
		this.inicio=inicio;
		this.fin=fin;
		
	}
	
	public void Insertar(DBConnection database){
		//TODO Modulo para controlar solo administrador del bar/root pueda añadir
		
		try{
		
		String sql= "INSERT INTO programa " +
                "(titulo, bar, descr, destacado, inicio, fin, cat )"+
            	"VALUES (?,?,?,?,?,?,?)";
		//System.out.println(sql);
		database.executeSentence(sql, titulo,bar,descripcion,0,inicio,fin,categoria);
		}
		catch (Exception e){
			System.out.println("Error al insertar programa");
		}
		
	}
	
	/*public ResultSet ObtenerProgramacion(DBConnection database,String bar){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String a = dtf.format(now);
		String sql = "SELECT * FROM programa "
				+ "WHERE fin > "+"'"+a+"'"+ " and bar = "+"'"+bar+"'";
		//System.out.println(sql);
		
		return database.executeQuery(sql);
	}*/
	
	public List<Programa> ObtenerProgramacion(DBConnection database,String bar) throws SQLException{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String a = dtf.format(now);
		String sql = "SELECT * FROM programa "
				+ "WHERE fin > "+"'"+a+"'"+ " and bar = "+"'"+bar+"'";
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
	}
	
	public String to_String(){
		String st="";
		st=st+" || "+this.titulo+" || "+this.bar+" || "+this.destacado+" || "+this.descripcion+" || "+this.categoria+
				" || "+this.inicio+" || "+this.fin;
		return st;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	public String getBar(){
		return this.bar;
	}
	public int getDest(){
		return this.destacado;
	}
	public String getDesc(){
		return this.descripcion;
	}
	public String getCategoria(){
		return this.categoria;
	}
	public String getInicio(){
		return this.inicio;
	}
	public String getFin(){
		return this.fin;
	}
	
	
	
}
