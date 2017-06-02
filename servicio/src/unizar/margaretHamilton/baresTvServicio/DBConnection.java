package unizar.margaretHamilton.baresTvServicio;


import java.sql.*;
import java.util.ArrayList;

/**
 * Clase para acceder a una BD Oracle
 */
public class DBConnection {

    /**
     * CaDena de caracteres con el nombre de usuario, o login, a emplear para
     * conectarse a la BD
     */
    String user = "apirest";
    /**
     * Cadena de caracteres con el password, o contrase??a, a emplear para
     * conectarse a la BD
     */
    String password = "apirest";
    /**
     * Conexion con la BD
     */
    Connection connection = null;

    Configuration config = null;

    /**
     * Metodo constructor. Asigna los valores de usuario, password, host, puerto
     * y nombre de la bd, para que posteriormente pueda hacerse la conexion
     * Host, puerto y nombre de la bd vienen en una instancia de una subclase de Configuration,
     * como MySQLCOnfiguration
     * 
     */
    public DBConnection(Configuration config)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.config = config;

        Class.forName(config.getDriver()).newInstance();
    }

    /**
     * Metodo para establecer la conexion JDBC con la BD
     * <p>
     * 
     * @throws SQLException
     * 
     * @exception Lanza
     *                una excepcion en caso de que se produzca algun error
     */
    public void connect() throws SQLException {
        // Estableciendo la conexion con la BD
        connection = DriverManager.getConnection(config.getURL(), user, password);
    }

    @Override
    public String toString() {
        return config.getURL();
    }

    /**
     * Metodo para cerrar la conexion JDBC con la BD
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException sqlE) {
            connection = null;
        }
    }

    /**
     * Metodo para realizar una consulta/orden SQL sin parametros variables o todo escrito por string.
     * Adicionalmente, se visualiza por pantalla del servidor
     * @param sql
     *            sentencia SQL
     */
    public ResultSet executeQuery(String sql) {

        // Creamos una sentencia para poder usarla con la conexion que
        // tenemos abierta
        Statement stmt = null;
        ResultSet rs;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.createStatement();
            // Formulamos la pregunta y obtenemos el resultado
             rs = stmt.executeQuery(sql);

            //Modulo de presentacion de datos

            // Creamos la cabecera de la tabla...
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
            }
            System.out.println();
            System.out
                    .println("---------------------------------------------------------------------------------------");

            // Creamos las filas de la tabla con la informacion de la tuplas            
            while (rs.next()) {// Por cada tupla
                // creamos una linea con la informacion:
                for (int j = 1; j <= numberOfColumns; j++) {
                    System.out.print(" " + rs.getString(j) + "\t | ");
                }
                System.out.println();
            }
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            rs = null;
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            
        }
        return rs;
    }
    
    
    public ArrayList<Object> executeQueryO(String sql) {

        // Creamos una sentencia para poder usarla con la conexion que
        // tenemos abierta
        Statement stmt = null;
        ArrayList<Object> list = new ArrayList<Object>();
        ResultSet rs;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.createStatement();
            // Formulamos la pregunta y obtenemos el resultado
             rs = stmt.executeQuery(sql);

            //Modulo de presentacion de datos

            // Creamos la cabecera de la tabla...
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
            }
            System.out.println();
            System.out
                    .println("---------------------------------------------------------------------------------------");

            // Creamos las filas de la tabla con la informacion de la tuplas            
            while (rs.next()) {// Por cada tupla
                // creamos una linea con la informacion:
                for (int j = 1; j <= numberOfColumns; j++) {
                    System.out.print(" " + rs.getString(j) + "\t | ");
                    list.add(rs.getString(j));
                }
                System.out.println();
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            rs = null;
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            
        }
        return list;
    }
    
    
    /**
     * Metodo para realizar una consulta/orden SQL con 
     * una sola variable condicinal, pensado para "where campo=X"
     * Adicionalmente, se visualiza por pantalla del servidor
     * 
     */ 
    public ResultSet executeQueryWhere(String sqlorig, String campo, String valor) {

        // Creamos una sentencia para poder usarla con la conexion que
        // tenemos abierta
        Statement stmt = null;
        ResultSet rs;
        String sql =sqlorig+ " WHERE "+campo+" = "+valor;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.createStatement();
            // Formulamos la pregunta y obtenemos el resultado
            rs = stmt.executeQuery(sql);

            //Modulo de presentacion de datos
            
            // Creamos la cabecera de la tabla...
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
            }
            System.out.println();
            System.out
                    .println("---------------------------------------------------------------------------------------");

            // Creamos las filas de la tabla con la informacion de la tuplas
            // obtenidas
            while (rs.next()) {// Por cada tupla
                // creamos una linea con la informacion:
                for (int j = 1; j <= numberOfColumns; j++) {
                    System.out.print(" " + rs.getString(j) + "\t | ");
                }
                System.out.println();
            }
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            rs = null;
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();

                } catch (SQLException e) {
                }
            }
        }
        return rs;

    }

    /**
     * Metodo para ejecutar una sentencia SQL que no sea una pregunta, es decir,
     * que no devuelva una tabla como resultado.
     * Sin parámetros explicitamente especificados.
     * 
     * @param sql
     *            sentencia SQL
     */
    public void executeSentence(String sql) {
        Statement stmt = null;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.createStatement();
            int resultado = stmt.executeUpdate(sql);
            System.out.println(resultado);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    /**
     * Metodo para ejecutar una sentencia SQL que no sea una pregunta, es decir,
     * que no devuelva una tabla como resultado.
     * Con parámetros explicitamente especificados.
     * 
     * @param sql
     *            sentencia SQL
     */

    public void executeSentence(String sql, Object... params) {
        PreparedStatement stmt = null;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            int resultado = stmt.executeUpdate();
            System.out.println(resultado);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    /**
     * Metodo para ejecutar una sentencia SQL que no sea una pregunta, es decir,
     * que no devuelva una tabla como resultado.
     * Con parámetros explicitamente especificados.
     * 
     * @param sql
     *            sentencia SQL
     */

    public ResultSet executeSentenceResult(String sql, Object... params) {
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            // int resultado = stmt.executeUpdate();
            // rs=stmt.executeUpdate();
            rs = stmt.executeQuery();
            // System.out.println(resultado);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
            }
            System.out.println();
            System.out
                    .println("---------------------------------------------------------------------------------------");

            // Creamos las filas de la tabla con la informacion de la tuplas
            // obtenidas
            while (rs.next()) {// Por cada tupla
                // creamos una linea con la informacion:
                for (int j = 1; j <= numberOfColumns; j++) {
                    System.out.print(" " + rs.getString(j) + "\t | ");                  
                }
                System.out.println();
            }
             return rs;
            

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            rs = null;
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
         return rs;
        
    }
    
    public ArrayList<Object> executeSentenceResult2(String sql, Object... params) {
        PreparedStatement stmt = null;
        ArrayList<Object> list = new ArrayList<Object>();
        ResultSet rs;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            // int resultado = stmt.executeUpdate();
            // rs=stmt.executeUpdate();
            rs = stmt.executeQuery();
            // System.out.println(resultado);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.print(" " + rsmd.getColumnLabel(i) + "\t | ");
            }
            System.out.println();
            System.out
                    .println("---------------------------------------------------------------------------------------");

            // Creamos las filas de la tabla con la informacion de la tuplas
            // obtenidas
            while (rs.next()) {// Por cada tupla
                // creamos una linea con la informacion:
                for (int j = 1; j <= numberOfColumns; j++) {
                    System.out.print(" " + rs.getString(j) + "\t | ");
                    list.add(rs.getString(j));
                }
                System.out.println();
            }
            // return rs;
            return list;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            rs = null;
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
        // return rs;
        return list;
    }

    
    
    public Cursor executeQueryAndGetCursor(String sql) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            return new Cursor(stmt.executeQuery(sql));
        } catch (SQLException e1) {
            System.out.println("Error: " + e1.getMessage());
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e2) {
                }
            }
        }
        return null;
    }
}