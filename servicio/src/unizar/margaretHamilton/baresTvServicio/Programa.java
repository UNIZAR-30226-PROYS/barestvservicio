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
    
    public static List<Programa> ObtenerDestacados() throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT * FROM programa "
                + "WHERE destacado = 1 AND fin > ? ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }

    public static List<Programa> actualizarFavoritos(List<Programa> favs) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst = null;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
        int nfavs = favs.size();
        int ind =0;
        
        try {
            inst = new DBConnection(db);
            inst.connect();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }     
        
        for (ind=0;ind<nfavs;ind++){
        
        try {
            
        String sql = "SELECT * FROM programa "
                    + "WHERE  bar = ? and titulo = ? AND fin > a";
            
        statement = null;
        resultSet = null;
            
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, favs.get(0).getBar());
        statement.setString(2, favs.get(0).getTitulo());
        //statement.setString(3, a);
        resultSet = statement.executeQuery();
        
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);

        }
        favs.remove(0);
        
        } finally {
        }
        }
        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        return plist;
        
    }
    
    public static List<Programa> programasBar(String bar) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT * FROM programa "
                + "WHERE bar = ? AND fin > ? ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, bar);
        statement.setString(2, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> filtrarCategoria(String categoria) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT * FROM programa "
                + "WHERE cat = ? AND fin > ? ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, categoria);
        statement.setString(2, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> filtrarTotal(String categoria, int d,int m,int y) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime fecha = LocalDateTime.of(y, m, d, 0, 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(fecha);
        String sql = "SELECT * FROM programa "
                + "WHERE cat = ? AND fin > ? ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, categoria);
        statement.setString(2, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }

    public static List<Programa> filtrarTiempo(int dia, int mes, int anyo) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime fecha = LocalDateTime.of(anyo, mes, dia, 0, 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(fecha);
        String sql = "SELECT * FROM programa "
                + "WHERE fin > ? ORDER By inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> busqueda(String find) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT DISTINCT * FROM ((SELECT * FROM programa "
                + "WHERE bar LIKE ? AND fin > ?) UNION (SELECT * FROM programa "
                + "WHERE titulo LIKE ? AND fin > ?)) A ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, "%"+find+"%");
        statement.setString(2, a);
        statement.setString(3, "%"+find+"%");
        statement.setString(4, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> busquedaCat(String find, String cat) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT DISTINCT * FROM ((SELECT * FROM programa "
                + "WHERE bar LIKE ? AND cat = ? AND fin > ?) UNION (SELECT * FROM programa "
                + "WHERE titulo LIKE ? AND cat = ? AND fin > ?)) A ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, "%"+find+"%");
        statement.setString(2, cat);
        statement.setString(3, a);
        statement.setString(4, "%"+find+"%");
        statement.setString(5, cat);
        statement.setString(6, a);        
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    
    public static List<Programa> busquedaTotal(String find, String cat, int d,int m,int y) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.of(y,m,d,0,0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT DISTINCT * FROM ((SELECT * FROM programa "
                + "WHERE bar LIKE ? AND cat = ? AND fin > ?) UNION (SELECT * FROM programa "
                + "WHERE titulo LIKE ? AND cat = ? AND fin > ?)) A ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, "%"+find+"%");
        statement.setString(2, cat);
        statement.setString(3, a);
        statement.setString(4, "%"+find+"%");
        statement.setString(5, cat);
        statement.setString(6, a);     
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    
    public static List<Programa> busquedaTiempo(String find, int d, int m, int y) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.of(y,m,d,0,0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT DISTINCT * FROM ((SELECT * FROM programa "
                + "WHERE bar LIKE ? AND fin > ?) UNION (SELECT * FROM programa "
                + "WHERE titulo LIKE ? AND fin > ?)) A ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, "%"+find+"%");
        statement.setString(2, a);
        statement.setString(3, "%"+find+"%");
        statement.setString(4, a);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> ObtenerProgramacionBar(String bar) throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        //LocalDateTime now = LocalDateTime.now();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //String a = dtf.format(now);
        String sql = "SELECT * FROM programa "
                + "WHERE bar = ? ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, bar);
        resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
           // if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
        return plist;
    }
    
    public static List<Programa> ObtenerProgramacion() throws SQLException{
        MySQLConfiguration db = new MySQLConfiguration();
        DBConnection inst;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String a = dtf.format(now);
        String sql = "SELECT * FROM programa where FIN > ? ORDER BY inicio";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Programa> plist = new ArrayList<Programa>();
  
        try {
        inst = new DBConnection(db);
        inst.connect();
        statement=inst.connection.prepareStatement(sql);
        statement.setString(1, a);
        resultSet = statement.executeQuery();
        
        
        while (resultSet.next()) {
            Programa pro = new Programa(resultSet.getString("titulo"), resultSet.getString("bar"), resultSet.getString("descr"),
                    resultSet.getString("inicio"), resultSet.getString("fin"), resultSet.getString("cat"));

            plist.add(pro);
        }
        
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
