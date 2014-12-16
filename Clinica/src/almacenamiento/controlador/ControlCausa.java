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
public class ControlCausa {
    DaoCausa daocau;
    Connection con;
    /**
     * En el constructor se crea el DAO
     */
    public ControlCausa(Connection con){        
        this.con=con;
        daocau=new DaoCausa(con);
    }
    public void connectDB(){
        daocau.connectDB();
    }
    public Connection getconection(){
        return daocau.getConn();
    }
    
    
    public int CrearCausa(String id, String nom, String desc, boolean estado){
        Causa cau=new Causa(id, nom, desc, estado);
        daocau.connectDB();
        int result=daocau.CrearCausa(cau);
        return result;
        
    }
    
    public Causa ConsultarCausa(String id){
        Causa causa=new Causa();
        causa=daocau.LeerCausa(id);
        return causa;
    }
    
   
    public int EditarCausa(Causa objcausa, String nuevoCod){
        int result=0;
        //Causa caus=new Medico(id, especialidad, num_licencia, universidad, estado);
        result=daocau.ActualizarCausa(objcausa, nuevoCod);
        return result;
    }
    
    /**
     * Metodo para Eliminar de forma logica una persona, recibe la identificacion
     * y cambia el estado a false en la base de datos
     * @param id:identificacion de la persona.
     * @return resultado de la consulta update.
     */
    public int EliminarCausa(String id){
        return daocau.EliminarCausa(id);
    }
    
    
    
    
}
