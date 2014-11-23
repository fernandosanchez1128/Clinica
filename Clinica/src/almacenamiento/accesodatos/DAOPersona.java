/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;

/**
 *
 * @author Nelson
 */
import java.sql.*;
public class DAOPersona {
    /**
     * @param db objeto encargado de la conexión a la base de datos.
     * @param conn objeto para ejecutar las sentencias de SQL
     * 
     */
    private BaseDatos db;
    Connection conn ;
    /**
     * constructor, inicializa los atributos.
     */
    public DAOPersona(){  db=new BaseDatos();  }
    
    /**
     * Metodo que permite realizar la conexion a la base de datos
     */
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    
}
