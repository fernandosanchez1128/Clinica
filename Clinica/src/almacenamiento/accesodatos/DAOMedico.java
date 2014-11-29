/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;

/**
 *
 * @author USUARIO
 */
import java.sql.*;
import proceso.Medico;
public class DAOMedico {
    /**
     * @param db objeto encargado de la conexión a la base de datos.
     * @param conn objeto para ejecutar las sentencias de SQL
     * 
     */
    private BaseDatos db;
    Connection conn ;
    /**
     * constructor, inicializa los atributos.
     */
    public DAOMedico(){ db=new BaseDatos(); }
    /**
     * Metodo que permite realizar la conexion a la base de datos
     */
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    
    /**
    *Metodo para insertar un medico  en la base de datos.
    *@param med Objeto de la clase persona
    *@return numRows numero de tuplas insertadas o afectadas. negativo si hay error
    */
    public int CrearMedico(Medico med){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Medico VALUES ('" + med.getIdMedico()+ 
                "' , '" + med.getEspecialidad()+ 
                "', '" + med.getNumLicencia()+  
                "', '" + med.getUniversidad()+ "' , true)";
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
            System.out.println("exception dao crear Medico");
            System.out.println(e);
        }
        return -1;
    }//fin saveUser
        /**
        * consultar el usuario que tiene como username el parametro.
        * @param req el username del usuario que se quiere consultar.
        * @return null si hay error en la consulta a la base de datos. Objeto tipo Usuario si el objeto del usuario que se consulto. Devuelve 
        */
    public Medico LeerMedico(String req){
        Medico med= new Medico();
        String sql_select;
        
            sql_select="SELECT Medico.id_medico, Medico.especialidad, Medico.num_licencia,Medico.universidad FROM  Medico WHERE id_medico='" + req +  "' AND estado=true";
        
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                med.setIdMedico(table.getString(1));
               
                med.setEspecialidad(table.getString(2));
                
                med.setNumLicencia(table.getString(3));
                
                med.setUniversidad(table.getString(4));
                System.out.println("ok");
            return med;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser
    
    /**
     * actualizar la informacion de un usuario, con la cedula que entra por parametro.
     * @param Med objeto de Usuario con los atributos a modificar en la base de datos.
     * @param cedula la cedula del usuario que se quiere actualizar.
     * @return 1 si el proceso ocurrio bien durante todo el metodo, -3 si el usuario entregado tiene un perfil 
     * inexistente, -2 si hay algun error de sql y -1 si hay cualquier otro error.
     */
    public int ActualizarMedico(Medico med, String cedula){
        String sql_save;
	sql_save="UPDATE Medico SET especialidad='"+med.getEspecialidad()+
                "', num_licencia='"+med.getNumLicencia()+
                "', universidad='"+med.getUniversidad()+"' WHERE id_medico='" + med.getIdMedico()+ "'";
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
    
    /**
    * borrar un medico de la tabla.
    * @param cedula la cedula del usuario que se quiere borrar.
    */
    public int EliminarMedico(String cedula){	
        String sql_save;
        //sql_save="UPDATE usuario SET estado=false WHERE cedula='" + cedula + "'";
        sql_save="UPDATE medico SET estado=false WHERE id_medico='"+cedula+"'";
        try{
            Statement statement = conn.createStatement();

            return statement.executeUpdate(sql_save);                        
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
    /**
     * cerrar la conexion con la base de datos.
     */
    public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }
    
    
}
