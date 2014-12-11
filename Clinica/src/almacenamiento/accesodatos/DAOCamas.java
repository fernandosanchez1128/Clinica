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
import proceso.*;

/**
 *
 * @author fernando
 */
public class DAOCamas 
{
        private BaseDatos db;
    private Connection conn;
    //private Connection conn ;
    public DAOCamas(Connection conect){
        db = new BaseDatos();
        conn = conect; 
        //conn = db.getConnetion();
    }
    public DAOCamas(){
     db = new BaseDatos();
    }
    public void connectDB(){
        conn = db.getConnetion();
    }
    /**
     * Metodo que permite insertar una cama  en la base de datos
     * @param cama la cama a ser almacenado
     * @return -1 en caso de error , -2 si el Cama ya existe y el numero de filas en caso contrario
     */
    
    public int crearCama (Cama cama){
        String sql_save;
        int numRows=0;
        
        sql_save = "INSERT INTO Cama (cod_area,descripcion,estado,activa) VALUES ( '"+ cama.getArea().getCodArea()+ "','" + cama.getDescripcion() + "','" 
                 + cama.getEstado()+ "',true );";
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
     * Metodo que permite consultar la informacion de una cmaa dado su codigo
     * @param codigo : codigo de la cama
     * @return: el meciamento encontrado , con atributos null si no existe o null en caso de error
     */
    public Cama consultarCamaCod (String codigo){
        Cama med = new Cama();
        String sql_select;
        sql_select="SELECT cod_cama, A.cod_area , A.nombre,C.descripcion,C.estado FROM Cama as C, Areas as A " + 
                " WHERE C.cod_area = A.cod_area AND cod_cama = '" +  codigo + 
                "' AND activa = true ;";
        System.out.println("consulta cama " + sql_select);
         try{
            System.out.println("consultando en la bd");
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_select);
            while(table.next()){
                
                med.setCodCama(table.getString(1));
                med.setDescripcion(table.getString(4));
                med.setEstado(table.getString(5));
                //objeto de area
                
                Areas area = new Areas ();
                area.setCodArea(table.getString(2));
                area.setNombre(table.getString(3));
                
                med.setArea(area);
            }
           
            return med;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e); }
        return null;
    }
    
    
    /**
     * metodo que permite modificar una cama dado su codigo
     * @param codigo codigo de la cama
     * @param med objeto don estan los nuevos datos de la cama.
     * @return -1 en caso de error , -2 si el Cama ya existe y el numero de filas en caso contrario
     */
    
    public int actualizarCamaCod (String codigo, Cama cama){
        String sql1,sql2,sql3,sql4;
	
        sql2="UPDATE Cama SET descripcion ='"+cama.getDescripcion()+"' WHERE cod_cama ='" + codigo + "';";
        sql3="UPDATE Cama SET estado ='"+cama.getEstado()+"' WHERE cod_cama='" + codigo + "';";
        System.out.println(sql3);
        sql4="UPDATE Cama SET cod_area ='"+cama.getArea().getCodArea() +"' WHERE cod_cama='" + codigo + "';";
        
        
        
        
        try{
                Statement sentencia = conn.createStatement();

                sentencia.executeUpdate(sql2);
                sentencia.executeUpdate(sql3);
                sentencia.executeUpdate(sql4);
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
    * metodo que permite el borrado logico de una cama en la BD.
    * @param codigo : codigo de la cama.
    * @return -1 en caso de error , -2 si el Cama ya existe y el numero de filas en caso contrario
    */
    public int eliminarCama (String codigo){	
        String sql_save;

        sql_save="UPDATE Cama SET activa = false WHERE cod_cama ='" + codigo + "'";
        
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
