package unizar.margaretHamilton.baresTvServicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
    
	private String nombre;	
	
	public Categoria(){
		nombre = "";
	}
	
	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Categoria> getAll(DBConnection inst)
		 {
		 MySQLConfiguration db = null;
		// DBConnection inst;
		// TODO Auto-generated method stub
		ArrayList<Categoria> categorias = null;
		
		try{
		//inst = new DBConnection(db);
		PreparedStatement statement = null;
		inst.connect();
		ResultSet rs =  null;
		
		
			

			String sql =  "select * from categoria "; 
			System.out.println(sql);
			statement=inst.connection.prepareStatement(sql);
			categorias = new ArrayList<Categoria>();
			
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
				Categoria c= new Categoria(
						//nombre
						rs.getString("nombrecat"));
				categorias.add(c);
				System.out.println(" " + c.getNombre() + "\t | ");

				
			}
		}catch (Exception e){
			System.out.println("Error al obtener categorias: "+e.getMessage());
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
		return categorias;	
	}
	
	
	
	
	public List<Programa> getProgramasenCategoria(DBConnection database, String categoria) throws SQLException{
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
	}
			
}
