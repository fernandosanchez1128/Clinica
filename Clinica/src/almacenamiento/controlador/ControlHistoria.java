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

public class ControlHistoria {
    
    DAOHistoria daocam;
    /**
     * En el constructor se crea el DAO
     */
    public ControlHistoria(){
        daocam=new DAOHistoria();
    }
    public void connectDB(){
        daocam.connectDB();
    }
    public Connection getconection(){
        return daocam.getConn();
    }
    
    
    public String[][] ConsultaRegistros(String cedula){
        String[][] registros=daocam.registros(cedula);
        if(registros[0][0]==null){
            return null;
        }
        
        return registros;
    }
    
    public int numRegistros(String cedula){
        int consulta;
        consulta=daocam.numRegistros(cedula);
        return consulta;
    }
    
    public String[][] ConsultaMedicaciones(String cedula){
        String[][] registros=daocam.medicaciones(cedula);
        if(registros[0][0]==null){
            return null;
        }
        
        return registros;
    }
    
    public int numMedicaciones(String cedula){
        int consulta;
        consulta=daocam.numMedicaciones(cedula);
        return consulta;
    }
    
    public String[][] ConsultaCitas(String cedula){
        String[][] registros=daocam.citas(cedula);
        if(registros[0][0]==null){
            return null;
        }
        
        return registros;
    }
    
    public int numCitas(String cedula){
        int consulta;
        consulta=daocam.numCitas(cedula);
        return consulta;
    }
    
    public String nomPaciente(String cedula){
        String consulta;
        consulta=daocam.nomPaciente(cedula);
        return consulta;
    }
    
    public String numHistoria(String cedula){
        String consulta;
        consulta=daocam.numHistoria(cedula);
        return consulta;
    }
    
}
