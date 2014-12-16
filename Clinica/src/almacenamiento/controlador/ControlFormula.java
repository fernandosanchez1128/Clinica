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
public class ControlFormula {
    DAOFormula daoForm;
    
    public ControlFormula(){
        daoForm = new DAOFormula();
    }
   
    public void connectDB(){
        daoForm.connectDB();
    }
    public Connection getconection(){
        return daoForm.getConn();
    }
    
    public int CrearFormulas(String id, String his, String causa, int cant, String fecha, int precio){
        Formula formu=new Formula(id, his, causa, cant, fecha, precio);
        daoForm.connectDB();
        int result=daoForm.CrearFormula(formu);
        return result;
        
    }
    
     public Formula ConsultarFormula(String id){
        Formula form=new Formula();
        form=daoForm.LeerFormulas(id);
        return form;
    }
}
