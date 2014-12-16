/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;
import proceso.*;
import almacenamiento.accesodatos.*;
import java.sql.Connection;

/**
 *
 * @author USUARIO
 */
public class ControlPersona {
    DAOPersona daoper;
    /**
     * En el constructor se crea el DAO
     */
    public ControlPersona(Connection con){
        daoper=new DAOPersona(con);
    }
    public void connectDB(){
        daoper.connectDB();
    }
    public Connection getconection(){
        return daoper.getConn();
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
    public int CrearPersona(String id, String nombre, String direccion, String telefono, boolean estado){
        Persona per=new Persona(id, nombre, direccion, telefono, estado);
        int result=daoper.CrearPersona(per);
        return result;
        
    }
    /**
     * Metodo que se encarga de verificar si una persona con identificacion id,
     * existe en la base de datos
     * @param id:identificacion de la persona o nombre
     * @param tipocon:1 si se busca por nombre, otro numero si no.
     * @return 
     */
    public Persona ConsultarPersona(String id, int tipocon){
        Persona per=new Persona();
        per=daoper.LeerPersona(id, tipocon);
        return per;
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
    public int EditarPersona(String id, String nombre, String direccion, String telefono, boolean estado){
        int result=0;
        Persona per=new Persona(id, nombre, direccion, telefono, estado);
        result=daoper.ActualizarPersona(per, id);
        return result;
    }
    
    /**
     * Metodo para Eliminar de forma logica una persona, recibe la identificacion
     * y cambia el estado a false en la base de datos
     * @param id:identificacion de la persona.
     * @return resultado de la consulta update.
     */
    public int EliminarPersona(String id){
        return daoper.EliminarPersona(id);
    }
    
    
}
