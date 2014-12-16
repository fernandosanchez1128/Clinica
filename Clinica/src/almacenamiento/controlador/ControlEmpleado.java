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
public class ControlEmpleado {
    DAOEmpleado daoemp;
    /**
     * En el constructor se crea el DAO
     */
    public ControlEmpleado(Connection con){
        daoemp=new DAOEmpleado(con);
    }
    public void connectDB(){
        daoemp.connectDB();
    }
    public Connection getconection(){
        return daoemp.getConn();
    }
    
     public int CrearEmpleado(String idEmpleado,String cargo, String email,String idJefe,int salario, String username, String password, String perfil, boolean estado){
        Empleado emp=new Empleado(idEmpleado, cargo, email, idJefe, salario, username, password,perfil, estado);
        int result=daoemp.CrearEmpleado(emp);
        return result;
    }
     
    public Empleado ConsultarEmpleado(String id){
        Empleado emp=new Empleado();
        emp=daoemp.LeerEmpleado(id);
        return emp;
    }
    
    public int EditarEmpleado(String idEmpleado,String cargo, String email,String idJefe,int salario, String username, String password, String perfil,  boolean estado){
        int result=0;
        Empleado emp=new Empleado(idEmpleado, cargo, email, idJefe, salario, username, password, perfil, estado);
        result=daoemp.ActualizarEmpleado(emp, idEmpleado);
        return result;
    }
    
    public int EliminarEmpleado(String id){
        return daoemp.EliminarEmpleado(id);
    }
    
}
