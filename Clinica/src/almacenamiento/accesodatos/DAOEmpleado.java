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
                "', " + emp.getSalario()+  
                ", '" + emp.getEmail()+ 
                "', '"+ emp.getIdJefe()+
                "', '"+ emp.getUsername()+
                "', '"+ emp.getPassword()+"' , true)"; ///CONVERTIR DATE
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
        sql_select="SELECT Id_empleado, Cargo, salario,email,id_jefe,nombre_usuario,password FROM  Empleado WHERE id_empleado='" + req +  "' and estado=true";
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                emp.setIdEmpleado(table.getString(1));
               
                emp.setCargo(table.getString(2));
                
                emp.setSalario(table.getInt(3));
                
                emp.setEmail(table.getString(4));
                
                emp.setIdJefe(table.getString(5));
                
                emp.setUsername(table.getString(6));
                
                emp.setPassword(table.getString(7));
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
                "', email='"+emp.getEmail()+
                "', id_jefe='"+emp.getIdJefe()+
                "', salario="+emp.getSalario()+                
                ", nombre_usuario='"+emp.getUsername()+
                "', password='"+ emp.getPassword()+"' WHERE id_empleado='" + emp.getIdEmpleado()+ "'";
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
        sql_save="UPDATE Empleado SET estado=false WHERE id_empleado='"+cedula+"'";
        try{
            Statement statement = conn.createStatement();            
            int tm=statement.executeUpdate(sql_save);                        
            return tm;
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
