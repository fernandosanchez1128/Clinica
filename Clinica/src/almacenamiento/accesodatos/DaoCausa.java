/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;
import java.sql.*;
import proceso.Causa;
/**
 *
 * @author Nelson
 */
public class DaoCausa {
    private BaseDatos db;
    Connection conn ;
    /**
     * constructor, inicializa los atributos.
     */
    public DaoCausa(){ db=new BaseDatos(); }
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
    public int CrearCausa(Causa cau){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Causa VALUES ('" + cau.getCodCausa()+ 
                "' , '" + cau.getNombre()+ 
                "', '" + cau.getDescripcion()+"' , true)";
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
            System.out.println("exception dao crear Causa");
            System.out.println(e);
        }
        return -1;
    }//fin saveUser
        /**
        * consultar el usuario que tiene como username el parametro.
        * @param req el username del usuario que se quiere consultar.
        * @return null si hay error en la consulta a la base de datos. Objeto tipo Usuario si el objeto del usuario que se consulto. Devuelve 
        */
    public Causa LeerCausa(String req){
        Causa cau= new Causa();
        String sql_select;
        
            sql_select="SELECT codigo_causa, nombre, descripcion FROM  Causa WHERE codigo_causa='" + req +  "' AND estado=true";
        
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                cau.setCodCausa(table.getString(1));
               
                cau.setNombre(table.getString(2));
                
                cau.setDescripcion(table.getString(3));
                
                System.out.println("ok");
            return cau;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser
    
    
    public int ActualizarCausa(Causa cau, String codigo){
        String sql_save;
	sql_save="UPDATE causa SET nombre='"+cau.getNombre()+
                "', descripcion='"+cau.getDescripcion()+
                "', codigo_causa='"+codigo+"' WHERE codigo_causa='" + cau.getCodCausa()+ "'";
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
    public int EliminarCausa(String cedula){	
        String sql_save;
        //sql_save="UPDATE usuario SET estado=false WHERE cedula='" + cedula + "'";
        sql_save="UPDATE Causa SET estado=false WHERE codigo_causa='"+cedula+"'";
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
