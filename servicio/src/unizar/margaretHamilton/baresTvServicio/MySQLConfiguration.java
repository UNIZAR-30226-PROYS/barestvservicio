package unizar.margaretHamilton.baresTvServicio;

/**
 * Configuracion de una BD MySQL.
 */

public class MySQLConfiguration implements Configuration {

    private static String host="localhost";
    private static String port="3306";
    private static String dbName="nf0n21jv2up9wz1b";

    public MySQLConfiguration() {
    }

    public String getDriver() {
        return "com.mysql.jdbc.Driver";
    }

    public String getURL() {
        return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDBName();
    }

    private String getDBName() {
        return dbName;
    }

    private String getPort() {
        return port == null ? "3306" : port;
    }

    private String getHost() {
        return host;
    }

}