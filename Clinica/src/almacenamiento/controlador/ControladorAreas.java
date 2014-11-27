/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;
import almacenamiento.accesodatos.DAOAreas;
import proceso.*;
import java.sql.Connection;

/**
 *
 * @author fernando
 */
public class ControladorAreas {
    
    private DAOAreas daoAreas;
    
    public ControladorAreas(Connection conn){
        daoAreas = new DAOAreas(conn);
    }
    
      public ControladorAreas(){
        daoAreas = new DAOAreas();
    }
      
      
      
      public void connectDB(){
        daoAreas.connectDB();
    }
    /**
     * Metodo que permite agregar un area a la base de datos
     * @param nombre : nombre del area
     * @param descripcion: descripcion del area
     * @return -1 en caso de error , -2 si el area ya existe y el numero de filas en caso contrario
     */
    public int crearAreas (String nombre, String descripcion){
        Areas area = new Areas ("1",nombre,descripcion);
        int result = daoAreas.crearArea(area); 
        return result;
    }
    
    /**
     * Metodo que permite consultar la informacion de un area dado el codigo
     * @param codigo : codigo del area
     * @return: el area encontrada , con atributos null si no existe o null en caso de error
     */
    public Areas consultarAreasCod (String codigo){
        Areas med = daoAreas.consultarAreaCod(codigo);
        return med;
    }
    
    /**
     * Metodo que permite consultar la informacion de un area dado el nombre
     * @param nombre : nombre del area
     * @return: el area encontrada , con atributos null si no existe o null en caso de error
     */
    public Areas consultarAreasNom (String nombre){
        Areas med = daoAreas.consultarAreasNom(nombre);
        return med;
    }
    
       /**
     * metodo que permite modificar un area dado el codigo
     * @param codigo codigo del area
     * @param area objeto don estan los nuevos datos del area.
     * @return -1 en caso de error , -2 si el Areas ya existe y el numero de filas en caso contrario
     */
    public int editarAreasCod(String codigo, Areas area){
        int result = daoAreas.actualizarAreasCod(codigo, area);
        return result;
    }
    
    
    
     /**
    * metodo que permite el borrado logico de un area en la BD.
    * @param codigo : codigo del area.
    */
    public int eliminarAreas (String codigo){
        int result = daoAreas.eliminarAreas(codigo);
        return result;
    }
  
}

  
