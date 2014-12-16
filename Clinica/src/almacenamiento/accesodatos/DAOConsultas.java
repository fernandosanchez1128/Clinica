/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;
import java.sql.*;
import proceso.Consultas;
/**
 *
 * @author Nelson
 */
public class DAOConsultas {
    private BaseDatos db;
    Connection conn ;
    public DAOConsultas(){db=new BaseDatos();}
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    public int CrearConsulta(Consultas cons){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO registro VALUES ('" + cons.getCod_Medico()+ 
                "' , '" + cons.getCod_Historia()+ 
                "', '" + cons.getCod_Causa()+"')";
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
            System.out.println("exception dao crear Consultas");
            System.out.println(e);
        }
        return -1;
    }
    
    public Consultas LeerConsultas(String req){
        Consultas con= new Consultas();
        String sql_select;
        
            sql_select="SELECT Registro.cod_medico, Registro.cod_historia, Registro.codigo_causa FROM  Registro WHERE id_medico='" + req +  "'";
        
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                con.setCod_Medico(table.getString(1));
               
                con.setCod_Historia(table.getString(2));
                
                con.setCod_Causa(table.getString(3));
                
               // med.setUniversidad(table.getString(4));
                System.out.println("ok");
            return con;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }
}
