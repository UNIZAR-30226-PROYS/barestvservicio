/*
 * Programa de ejemplo para que se vea como se usan las clases para conectarse a la base de datos, insertar y buscar
 * obviamente, habr� que borrarlo en alg�n punto.
 * 
 * */


package unizar.margaretHamilton.baresTvServicio;

import java.sql.SQLException;

public class Pruebaconex {

	public static void main(String[] args) {
		
		
		
		//Configura como es la conexion con la base de datos
		MySQLConfiguration db = new MySQLConfiguration("127.0.0.1","3306","baresTV");
		
			DBConnection inst;
			try {
				//Establece una conexion con el usuario root, contrasegna root en mi caso, poned la que corresponda
				//en el tercer parametro
				inst = new DBConnection(db,"root","root");
				inst.connect();
				
				inst.executeQuery("Select * from programa");
				//Creo dos instancias de Programa, una que insertare, y otra cualquiera que uso para busca
				//realmente no tiene mucho sentido que la funcion de buscar sea propia de Programa, se quitara cuando
				//se anyada una jerarquia de usuarios con distintos accesos a los metodos.
				Programa pr = new Programa("cualquiernombre", "barejemplo");
				Programa pr2 = new Programa("programa guay", "barejemplo","programa de ejemplo mas guay que el anterior porqu lo inserto desde java",
										"2017-10-10 14:20:00.0","2017-10-10 17:00:00.0","ejemplo");
				
				//ObtenerProgramacion obtiene solo entradas cuyo fin > fecha actual del sistema.
				pr.ObtenerProgramacion(inst, "barejemplo");
				pr2.Insertar(inst);
				pr.ObtenerProgramacion(inst, "barejemplo");
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


		
	}

}
