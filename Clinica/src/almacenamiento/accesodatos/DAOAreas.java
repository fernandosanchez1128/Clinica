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
import java.util.logging.Level;
import java.util.logging.Logger;
import proceso.*;

/**
 *
 * @author fernando
 */
public class DAOAreas {
    
    private BaseDatos db;
    private Connection conn;
    //private Connection conn ;
    public DAOAreas(Connection conect){
        db = new BaseDatos();
        conn = conect; 
        //conn = db.getConnetion();
    }
    public DAOAreas(){
     db = new BaseDatos();
    }
    public void connectDB(){
        conn = db.getConnetion();
    }
    /**
     * Metodo que permite crear un Medicament en la base de datos
     * @param area el area que se va a crear en la base de datos
     * @return -1 en caso de error , -2 si el area ya existe y el numero de filas en caso contrario
     */
    
    public int crearArea (Areas area){
        String sql_save;
        int numRows=0;
        
        sql_save = "INSERT INTO Areas (nombre,descripcion,estado) VALUES ( '"+ area.getNombre()+ "','" + area.getDescripcion() + "'," +
                  "true );";
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
     * Metodo que permite consultar la informacion de un area dado el codigo
     * @param codigo : codigo del area
     * @return: el area encontrada , con atributos null si no existe o null en caso de error
     */
    public Areas consultarAreaCod (String codigo){
        Areas area = new Areas();
        String sql_select;
        sql_select="SELECT cod_area, nombre, descripcion FROM Areas WHERE cod_area= '" +  codigo + 
                "' AND estado = true ;";
        System.out.println("consulta aspirante " + sql_select);
         try{
            System.out.println("consultando en la bd");
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_select);
            while(table.next()){
                
                area.setCodArea(table.getString(1));
                area.setNombre(table.getString(2));
                area.setDescripcion(table.getString(3));
               
                 
            }
           
            return area;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e); }
        return null;
    }
    
    /**
     * Metodo que permite consultar la informacion de un area dado el nombre
     * @param nombre : nombre del area
     * @return: el area encontrada , con atributos null si no existe o null en caso de error
     */
    public Areas consultarAreasNom (String nombre){
        Areas area = new Areas();
        String sql_select;
        sql_select="SELECT cod_area, nombre, descripcion FROM Areas WHERE nombre = '" +  nombre +
                "' AND estado = true ;"; 
        System.out.println("consulta medicamento " + sql_select);
         try{
            System.out.println("consultando en la bd");
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_select);
            while(table.next()){
                
                area.setCodArea(table.getString(1));
                area.setNombre(table.getString(2));
                area.setDescripcion(table.getString(3));
                 
            }
           
            return area;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e); }
        return null;
    }
    
    /**
     * metodo que permite modificar un area dado el codigo
     * @param codigo codigo del area
     * @param area objeto don estan los nuevos datos del area.
     * @return -1 en caso de error , -2 si el Areas ya existe y el numero de filas en caso contrario
     */
    
    public int actualizarAreasCod (String codigo, Areas area){
        String sql1,sql2;
	
        
        sql1="UPDATE Areas SET nombre='"+area.getNombre()+"' WHERE cod_area='" + codigo + "';";
        sql2="UPDATE Areas SET descripcion ='"+area.getDescripcion()+"' WHERE cod_area='" + codigo + "';";
        
        try{
                Statement sentencia = conn.createStatement();

                sentencia.executeUpdate(sql1);
                sentencia.executeUpdate(sql2);
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
    * metodo que permite el borrado logico de un area en la BD.
    * @param codigo : codigo del area.
    */
    public int eliminarAreas (String codigo){	
        String sql_save;

        sql_save="UPDATE Areas SET estado=false WHERE cod_area='" + codigo + "'";
        
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
        
        public String [] [] ConsultaEmpleados  () 
    {
       
        String sql = "SELECT * FROM Persona, Empleado WHERE id = id_empleado ORDER BY area;";
        String[][] resultado = null ;
        try {
            System.out.println("consultando en la bd");
            Statement sentence = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet table = sentence.executeQuery(sql);
            table.last();
            int filas = table.getRow();
            table.first();
            table.previous();
            resultado = new String [filas][9];
            int i =0;
            while(table.next()){
                resultado[i][0] = table.getString(1);
                resultado[i][1] = table.getString(2);
                resultado[i][2] = table.getString(3);
                resultado[i][3] = table.getString(4);
                resultado[i][4] = table.getString(6);
                int salario = table.getInt(7);
                resultado[i][5] = Integer.toString(salario);
                resultado[i][6] = table.getString(8);
                resultado[i][7] = table.getString(9);
                resultado[i][8] = table.getString(10);
                i++;
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOAreas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }

    
}
  