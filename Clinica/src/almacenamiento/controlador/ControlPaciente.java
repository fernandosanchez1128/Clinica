/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;

/**
 *
 * @author Nelson
 */
import proceso.*;
import almacenamiento.accesodatos.*;
import java.sql.Connection;
import java.util.Date;

public class ControlPaciente {
    
    DAOPaciente daopac;
    /**
     * En el constructor se crea el DAO
     */
    public ControlPaciente(){
        daopac=new DAOPaciente();
    }
    public void connectDB(){
        daopac.connectDB();
    }
    public Connection getconection(){
        return daopac.getConn();
    }
    
     public int CrearPaciente(String idPaciente, String numSeguridadSocial,String actividadEconomica,String fechaNac, boolean estado){
        Paciente pac=new Paciente(idPaciente, numSeguridadSocial, actividadEconomica, fechaNac, estado);
        int result=daopac.CrearPaciente(pac);
        return result;
    }
     
    public Paciente ConsultarPaciente(String id){
        Paciente pac=new Paciente();
        pac=daopac.LeerPaciente(id);
        return pac;
    }
    
    public int EditarPaciente(String idPaciente, String numSeguridadSocial,String actividadEconomica,String fechaNac, boolean estado){
        int result=0;
        Paciente per=new Paciente(idPaciente, numSeguridadSocial, actividadEconomica,  fechaNac, estado);
        result=daopac.ActualizarPaciente(per, idPaciente);
        return result;
    }
    
    public int EliminarPaciente(String id){
        return daopac.EliminarPaciente(id);
    }
    
}
