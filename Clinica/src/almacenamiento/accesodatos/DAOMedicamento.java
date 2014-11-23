/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import proceso.*;

/**
 *
 * @author fernando
 */
public class DAOMedicamento {
    
    private BaseDatos db;
    private Connection conn;
    //private Connection conn ;
    public DAOMedicamento(Connection conect){
        db = new BaseDatos();
        conn = conect; 
        //conn = db.getConnetion();
    }
    public DAOMedicamento(){
     db = new BaseDatos();
    }
    public void connectDB(){
        conn = db.getConnetion();
    }
    /**
     * Metodo que permite crear un Medicament en la base de datos
     * @param med el medicamneto a ser almacenado
     * @return -1 en caso de error , -2 si el Medicamento ya existe y el numero de filas en caso contrario
     */
    
    public int crearMedicamento (Medicamento med){
        String sql_save;
        int numRows=0;
        
        sql_save = "INSERT INTO Medicamento (nombre,descripcion,costo,estado) VALUES ( '"+ med.getNombre()+ "','" + med.getDescripcion() + "'," 
                 + med.getCosto() + ",true );";
        System.out.println(sql_save);
        try{
            Statement sentencia = conn.createStatement();

            numRows = sentencia.executeUpdate(sql_save);            
            System.out.println("numRowsDAO: " + numRows);
            return numRows;
        }
        catch(SQLException e){
            
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            
            System.out.println(e);
        }
        return -1;
    } 
    /**
     * Metodo que permite consultar la informacion de un medicamento dado el codigo
     * @param codigo : codigo del medicamento
     * @return: el meciamento encontrado , con atributos null si no existe o null en caso de error
     */
    public Medicamento consultarMedicamentoCod (String codigo){
        Medicamento med = new Medicamento();
        String sql_select;
        sql_select="SELECT codigo, nombre, descripcion, costo FROM Medicamento WHERE cod_medicamento = '" +  codigo + 
                "AND estado = true ');";
        System.out.println("consulta aspirante " + sql_select);
         try{
            System.out.println("consultando en la bd");
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_select);
            while(table.next()){
                
                med.setCodMedicamento(table.getString(1));
                med.setNombre(table.getString(2));
                med.setDescripcion(table.getString(3));
                med.setCosto(table.getInt(4));
               
                 
            }
           
            return med;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e); }
        return null;
    }
    
    /**
     * Metodo que permite consultar la informacion de un medicamento dado el nombre
     * @param nombre : nombre del medicamento
     * @return: el meciamento encontrado , con atributos null si no existe o null en caso de error
     */
    public Medicamento consultarMedicamentoNom (String nombre){
        Medicamento med = new Medicamento();
        String sql_select;
        sql_select="SELECT codigo, nombre, descripcion, costo FROM Medicamento WHERE cod_medicamento = '" +  nombre +
                "AND estado = true ');"; 
        System.out.println("consulta aspirante " + sql_select);
         try{
            System.out.println("consultando en la bd");
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_select);
            while(table.next()){
                
                med.setCodMedicamento(table.getString(1));
                med.setNombre(table.getString(2));
                med.setDescripcion(table.getString(3));
                med.setCosto(table.getInt(4));
                 
            }
           
            return med;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e); }
        return null;
    }
    
    /**
     * 
     * @param codigo codigo del medicamento
     * @param medicamento los nuevos datos del medicamento.
     */
    
    public int actualizarMedicamento (String codigo, Medicamento med){
        String sql1,sql2,sql3;
	
        
        sql1="UPDATE Medicamento SET nombre='"+med.getNombre()+"' WHERE cod_medicamento='" + codigo + "';";
        sql2="UPDATE Medicamento SET descripcion ='"+med.getDescripcion()+"' WHERE cod_medicamento='" + codigo + "';";
        sql3="UPDATE Medicamento SET costo ='"+med.getCosto()+"' WHERE cod_medicamento='" + codigo + "';";
        
        
        
        
        try{
                Statement sentencia = conn.createStatement();

                sentencia.executeUpdate(sql1);
                sentencia.executeUpdate(sql2);
                sentencia.executeUpdate(sql3);
                return 1;
            }
        catch(SQLException e){
            System.out.println(e); 
            return -2;
            }
        catch(Exception e){ 
            System.out.println(e);
            return -1;
        }
    }
    
    /**
    * metodo que permite el borrado logico de un medicamento en la BD.
    * @param codigo : codigo del medicamento.
    */
    public int eliminarMedicamento (String codigo){	
        String sql_save;

        sql_save="UPDATE Medicamento SET estado=false WHERE cod_medicamento ='" + codigo + "'";
        
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
        
        /**
         * metodo usado para cerrar la conexion
         */
        public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }

    
}
  