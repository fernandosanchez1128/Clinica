/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;

/**
 *
 * @author Nelson
 */
import java.sql.*;
import proceso.Empleado;
import proceso.Paciente;
public class DAOEmpleado {
    private BaseDatos db;
    Connection conn ;
    /**
     * constructor, inicializa los atributos.
     */
    public DAOEmpleado(){  db=new BaseDatos();  }
    
    /**
     * Metodo que permite realizar la conexion a la base de datos
     */
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    
    public int CrearEmpleado(Empleado emp){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Empleado VALUES ('" + emp.getIdEmpleado()+ 
                "' , '" + emp.getCargo()+ 
                "', '" + emp.getEmail()+  
                "', '" + emp.getIdJefe()+ 
                "', '"+ emp.getSalario()+"' , true)"; ///CONVERTIR DATE
        try{
            Statement sentencia = conn.createStatement();
            numRows = sentencia.executeUpdate(sql_save);           
            return numRows;            
        }
        catch(SQLException e){            
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println("exception dao crear persona");
            System.out.println(e);
        }
        return -1;
    }//endCrear
    
    public Empleado LeerEmpleado(String req){
        Empleado emp= new Empleado();
        String sql_select;
        sql_select="SELECT Empleado.IdEmpleado, Empleado.Cargo, Empleado.Email,Empleado.IdJefe,Empleado.Salario FROM  Empleado WHERE idEmpleado='" + req +  "'";
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                emp.setIdEmpleado(table.getString(1));
               
                emp.setCargo(table.getString(2));
                
                emp.setEmail(table.getString(3));
                
                emp.setIdJefe(table.getString(4));
                
                emp.setSalario(table.getInt(5));
                System.out.println("ok");
            }            
            return emp;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser
    
    
    public int ActualizarEmpleado(Empleado emp, String cedula){
        String sql_save;
	sql_save="UPDATE Empleado SET cargo='"+emp.getCargo()+
                "', ActividadEconomica='"+emp.getEmail()+
                "', Historia='"+emp.getIdJefe()+
                "', FechaNac='"+emp.getSalario()+" WHERE idEmpleado='" + emp.getIdEmpleado()+ "'";
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql_save);
            
        }            
        catch(SQLException e){
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println(e);
            return -1;
        }
        return 1;
    }//fin updateUser
    
     public int EliminarEmpleado(String cedula){	
        String sql_save;
        //sql_save="UPDATE usuario SET estado=false WHERE cedula='" + cedula + "'";
        sql_save="UPDATE Empleado SET estado=false WHERE idEmpleado='"+cedula+"'";
        try{
            Statement statement = conn.createStatement();

            statement.executeUpdate(sql_save);            
            return 1;
        }
        catch(SQLException e){
            System.out.println(e);
            return -1;
        }
        catch(Exception e){ 
            System.out.println(e);
            
        }
        return -2;
        
    }
    
     public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }
    
}
