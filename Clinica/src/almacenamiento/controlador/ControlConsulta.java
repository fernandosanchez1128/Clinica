/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;
import almacenamiento.accesodatos.*;
import proceso.*;
import java.sql.Connection;
/**
 *
 * @author Nelson
 */
public class ControlConsulta {
    DAOConsultas daoCons;
    
    public ControlConsulta(){
        daoCons = new DAOConsultas();
    }
   
    public void connectDB(){
        daoCons.connectDB();
    }
    public Connection getconection(){
        return daoCons.getConn();
    }
    
    public int CrearConsultas(String id, String his, String causa){
        Consultas con=new Consultas(id, his, causa);
        daoCons.connectDB();
        int result=daoCons.CrearConsulta(con);
        return result;
        
    }
    
     public Consultas ConsultarConsultas(String id){
        Consultas con=new Consultas();
        con=daoCons.LeerConsultas(id);
        return con;
    }
}
