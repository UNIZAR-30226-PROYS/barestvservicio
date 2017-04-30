package unizar.margaretHamilton.baresTvServicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class Programa {
    @XmlElement(name="Titulo")
    private String titulo;
    @XmlElement(name="Bar")
    private String bar;
    @XmlElement(name="Descr")
    private String descripcion;
    @XmlElement(name="Cat")
    private String categoria;
    @XmlElement(name="Inicio")
    private String inicio;
    @XmlElement(name="Fin")
    private String fin;
    @XmlElement(name="Dest")
    private int destacado;
    
    public Programa(){
        
    }
    
    
    
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
    public Programa(String titulo, String bar, 
            String descripcion,String inicio,String fin){
        
        this.titulo=titulo;
        this.bar=bar;
        this.destacado=0;
        this.descripcion=descripcion;
        this.inicio=inicio;
        this.fin=fin;
        
    }
    
    public Programa(String titulo,String bar, 
            String descripcion, String categoria, String inicio, String fin, int destacado){
        this.titulo=titulo;
        this.bar=bar;
        this.descripcion=descripcion;
        this.categoria=categoria;
        this.inicio=inicio;
        this.fin=fin;
        this.destacado=destacado;
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
    
    public static List<Programa> ObtenerDestacados() throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration("127.0.0.1","3306","barestv");
        DBConnection inst;
        //LocalDateTime now = LocalDateTime.now();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //String a = dtf.format(now);
        String sql = "SELECT * FROM programa "
                + "WHERE destacado = 1";
        //Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
        //System.out.println(sql);
  
        try {
        inst = new DBConnection(db,"root","root");
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        //statement = connection.prepareStatement("SELECT id, name, value FROM Biler");
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> filtrarCategoria(String categoria) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration("127.0.0.1","3306","barestv");
        DBConnection inst;
        //LocalDateTime now = LocalDateTime.now();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //String a = dtf.format(now);
        String sql = "SELECT * FROM programa "
                + "WHERE cat = "+"'"+categoria+"'";
        //Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
        //System.out.println(sql);
  
        try {
        inst = new DBConnection(db,"root","root");
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        //statement = connection.prepareStatement("SELECT id, name, value FROM Biler");
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> busqueda(String find) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration("127.0.0.1","3306","barestv");
        DBConnection inst;
        //LocalDateTime now = LocalDateTime.now();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //String a = dtf.format(now);
        String sql = "SELECT DISTINCT * FROM ((SELECT * FROM programa "
                + "WHERE bar = "+"'"+find+"') UNION (SELECT * FROM programa "
                + "WHERE titulo = "+"'"+find+"')) A";
        //Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
        //System.out.println(sql);
  
        try {
        inst = new DBConnection(db,"root","root");
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        //statement = connection.prepareStatement("SELECT id, name, value FROM Biler");
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
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
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
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
    
    @Override
    public String toString(){
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject().put("Titulo", titulo).put("Bar", bar).put("Descr",descripcion).put("Cat", categoria).put("Inicio",inicio).put("Fin", fin).toString();
        } catch (JSONException e) {
            return null;
        }
    }
    
    
    
}