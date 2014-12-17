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

public class ControlCampana {
    
    DAOCampana daocam;
    Connection conn;
   
    
    /**
     * En el constructor se crea el DAO
     * @param conexion Objeto con la conexion a la bd
     */
    public ControlCampana(Connection conexion){
        conn=conexion;
        daocam=new DAOCampana(conexion);
    }
        
     public int CrearCampana(String nomCam, String idMedico, String objetivo, String fechaR, boolean estado){
        Campanna cam=new Campanna(nomCam, idMedico, objetivo, fechaR, estado);
        int result=daocam.CrearCampanna(cam);
        return result;
    }
     
    public Campanna ConsultarCampanna(String nomCampanna){
        Campanna cam;
        cam=daocam.LeerCampanna(nomCampanna);
        return cam;
    }
    
    public String[][] PacientesCampanna(String nomCampanna){
        String[][] pacientes=daocam.pacientes(nomCampanna);
        if(pacientes==null){
            return null;
        }
        
        return pacientes;
    }
    
    public int numSeleccionados(String req){
        int consulta;
        consulta=daocam.numSeleccionados(req);
        return consulta;
    }
    
    public int EditarCampanna(String codCam, String nomCam, String idMedico, String objetivo, String fechaR, boolean estado){
        int result=0;
        Campanna cam=new Campanna(nomCam, idMedico, objetivo, fechaR, estado);
        result=daocam.ActualizarCampanna(cam, codCam);
        return result;
    }
    
    public int EliminarCampanna(String nom){
        return daocam.EliminarCampanna(nom);
    }
    
    public int AgregarPaciente(String cedula, String nomCam){
        int result=0;
        result=daocam.AgregarPaciente(cedula, nomCam);
        return result;
    }
    
    public int EliminarPaciente(String cedula, String nomCam){
        return daocam.EliminarPaciente(cedula, nomCam);
    }
    
}
