package unizar.margaretHamilton.baresTvServicio;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Consulta {
    
    /*Basado en https://stackoverflow.com/questions/6514876/
     * most-efficient-conversion-of-resultset-to-json
     * Permite la tranformación de resultados de consultas
     * en JSON para facilitar el trabajo con estos
    */
    public JSONArray transform(ResultSet s) 
            throws SQLException, JSONException {
        JSONArray json = new JSONArray();
        ResultSetMetaData meta = s.getMetaData();

        while(s.next()) {
          int numColumns = meta.getColumnCount();
          JSONObject obj = new JSONObject();

          for (int i=1; i<numColumns+1; i++) {
            String column_name = meta.getColumnName(i);

            if(meta.getColumnType(i)==java.sql.Types.CHAR){
             obj.put(column_name, s.getString(column_name));
            }
            else if(meta.getColumnType(i)==java.sql.Types.VARCHAR){
                obj.put(column_name, s.getString(column_name));
            }
            else if(meta.getColumnType(i)==java.sql.Types.TINYINT){
                obj.put(column_name, s.getBoolean(column_name));
            }
            else if(meta.getColumnType(i)==java.sql.Types.DECIMAL){
                obj.put(column_name, s.getDouble(column_name)); 
            }
            else if(meta.getColumnType(i)==java.sql.Types.TIMESTAMP){
                obj.put(column_name, s.getTimestamp(column_name));   
            }
            else{
             obj.put(column_name, s.getObject(column_name));
            }
          }

          json.put(obj);
        }

        return json;
    }
}
