/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;
import almacenamiento.accesodatos.*;
import proceso.*;
import java.sql.Connection;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class ControlMedico {
    DAOMedico daomed;
    /**
     * En el constructor se crea el DAO
     */
    public ControlMedico(Connection con){
        daomed=new DAOMedico(con);
    }
    public void connectDB(){
        daomed.connectDB();
    }
    public Connection getconection(){
        return daomed.getConn();
    }
    
    /**
     * Metodo encargado de pasar los datos recibidos a una persona para crearla
     * en la base de datos
     * @param id:Identificacion de la parsona
     * @param nombre:Nombre de la persona
     * @param direccion:direccion de la persona
     * @param telefono:Telefono de la persona
     * @param estado:estado de la persona
     * @return result: Resultado de la consulta
     */
    public int CrearMedico(String id, String especialidad, String num_licencia, String universidad, boolean estado){
        Medico med=new Medico(id, especialidad, num_licencia, universidad, estado);
        daomed.connectDB();
        int result=daomed.CrearMedico(med);
        return result;
        
    }
    /**
     * Metodo que se encarga de verificar si una persona con identificacion id,
     * existe en la base de datos
     * @param id:identificacion de la persona o nombre
     * @param tipocon:1 si se busca por nombre, otro numero si no.
     * @return 
     */
    public Medico ConsultarMedico(String id){
        Medico med=new Medico();
        med=daomed.LeerMedico(id);
        return med;
    }
    
    /**
     * Metodo que edita la informacion de una persona en la base de datos deacerdo
     * a los parametros recibidos
     * @param id:identificacion de la persona
     * @param nombre:nombre de la persona
     * @param direccion:direccion de la persona
     * @param telefono:telefono de la persona
     * @param estado:estado de la persona
     * @return 
     */
    public int EditarMedico(String id, String especialidad, String num_licencia, String universidad, boolean estado){
        int result=0;
        Medico med=new Medico(id, especialidad, num_licencia, universidad, estado);
        result=daomed.ActualizarMedico(med, id);
        return result;
    }
    
    /**
     * Metodo para Eliminar de forma logica una persona, recibe la identificacion
     * y cambia el estado a false en la base de datos
     * @param id:identificacion de la persona.
     * @return resultado de la consulta update.
     */
    public int EliminarMedico(String id){
        return daomed.EliminarMedico(id);
    }
    
    
}
