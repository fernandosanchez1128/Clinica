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
import proceso.Enfermera;
public class DAOEnfermera {
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
    public DAOEnfermera(){  db=new BaseDatos();  }
    
    /**
     * Metodo que permite realizar la conexion a la base de datos
     */
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    
    public int CrearEnfermera(Enfermera enf){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Enfermera VALUES ('" + enf.getIdEnfermera()+ 
                "' , '" + enf.getExperiencia()+ 
                "', '" + enf.getCodArea()+  
                "', '" + enf.getEstado()+ "')";
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
    }//fin saveUser
    
    public Enfermera LeerEnfermera(String req){
        Enfermera enf= new Enfermera();
        String sql_select;
        
            sql_select="SELECT Enfermera.id_Enfermera, Enfermera.experiencia, Enfermera.cod_area FROM  Enfermera WHERE id_Enfermera='" + req +  "' AND estado=true";
        
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                enf.setIdEnfermera(table.getString(1));
               
                enf.setExperiencia(table.getInt(2));
                
                enf.setCodArea(table.getString(3));
                
                System.out.println("ok");
            }            
            return enf;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser
    
    public int ActualizarEnfermera(Enfermera enf, String cedula){
        String sql_save;
	sql_save="UPDATE Enfermera SET experiencia='"+enf.getExperiencia()+
                "', cod_area='"+enf.getCodArea()+"' WHERE id_Enfermera='" + enf.getIdEnfermera()+ "'";
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
    
    public int EliminarEnfermera(String cedula){	
        String sql_save;
        //sql_save="UPDATE usuario SET estado=false WHERE cedula='" + cedula + "'";
        sql_save="UPDATE Enfermera SET estado=false WHERE id_Enfermera='"+cedula+"'";
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
