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

public class ControlEnfermera {
    DAOEnfermera daoenf;
    public ControlEnfermera(){
        daoenf=new DAOEnfermera();
    }
    public void connectDB(){
        daoenf.connectDB();
    }
    public Connection getconection(){
        return daoenf.getConn();
    }
    
    
    public int CrearEnfermera(String idEnfermera, String experiencia, String codArea, boolean estado){
        Enfermera enf=new Enfermera(idEnfermera, experiencia, codArea, estado);
        int result=daoenf.CrearEnfermera(enf);
        return result;
        
    }
    
    
    public Enfermera ConsultarEnfermera(String id){
        Enfermera enf=new Enfermera();
        enf=daoenf.LeerEnfermera(id);
        return enf;
    }
    
    
    public int EditarEnfermera(String idEnfermera, String experiencia, String codArea, boolean estado){
        int result=0;
        Enfermera enf=new Enfermera(idEnfermera, experiencia, codArea, estado);
        result=daoenf.ActualizarEnfermera(enf, idEnfermera);
        return result;
    }
    
    public int EliminarEnfermera(String id){
        return daoenf.EliminarEnfermera(id);
    }
    
    
}
