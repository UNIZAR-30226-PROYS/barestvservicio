package unizar.margaretHamilton.baresTvServicio;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		//TODO Modulo para controlar solo administrador del bar/root pueda anyadir
		
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
	
	public ResultSet ObtenerProgramacion(DBConnection database,String bar){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String a = dtf.format(now);
		String sql = "SELECT * FROM programa "
				+ "WHERE fin > "+"'"+a+"'"+ " and bar = "+"'"+bar+"'";
		//System.out.println(sql);
		
		return database.executeQuery(sql);
	}
	
	
	
}
