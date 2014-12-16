/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;
import java.sql.*;
import proceso.Formula;
/**
 *
 * @author Nelson
 */
public class DAOFormula {
    private BaseDatos db;
    Connection conn ;
    public DAOFormula(Connection con){db=new BaseDatos(); conn=con;}
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    public int CrearFormula(Formula form){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO medicacion VALUES ('" + form.getCod_Medico()+ 
                "' , '" + form.getCod_Historia()+ 
                "', '" + form.getCod_Medicamento()+
                "', " + form.getCantidad()+
                ", '" + form.getFecha()+
                "', " + form.getPrecio()+")";
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
    
    public Formula LeerFormulas(String req){
        Formula form= new Formula();
        String sql_select;
        
            sql_select="SELECT id_medico, cod_historia, cod_medicamento, cantidad, fecha, precio FROM  medicacion WHERE id_medico='" + req +  "'";
        
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                form.setCod_Medico(table.getString(1));
               
                form.setCod_Historia(table.getString(2));
                
                form.setCod_Medicamento(table.getString(3));
                
                form.setCantidad(table.getInt(4));
                
                form.setFecha(table.getString(5));
                
                form.setPrecio(table.getInt(6));
                
               // med.setUniversidad(table.getString(4));
                System.out.println("ok");
            return form;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }
}
