/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;

import almacenamiento.accesodatos.DAOCamas;
import java.sql.Connection;
import proceso.Areas;
import proceso.Cama;

/**
 *
 * @author fernando
 */
public class ControlCama 
{
     private DAOCamas daoCama;
    
    public ControlCama(Connection conn){
        daoCama = new DAOCamas(conn);
    }
    
      public ControlCama(){
        daoCama = new DAOCamas();
    }
      
      
      
      public void connectDB(){
        daoCama.connectDB();
    }
    /**
     * Metodo que permite agregar una cama a la base de datos
     * @param descripcion: descripcion de la cama
     * @param costo : costo del medicamento.
     * @return valor de la ultima formicacion registrada exitosamente
     */
    public int crearCama (String descripcion, String estado, Areas area ){
        Cama cama = new Cama ("1",descripcion,estado,area);
        int result = daoCama.crearCama(cama); 
        return result;
    }
    
    public String [][] camasDisponbles ()
    {
        return daoCama.camasDisponibles();
    }
    
    public int asignarCama (String cedula_paciente,String codigo_cama,String fecha)
    {
        return daoCama.asignarCama(cedula_paciente, codigo_cama, fecha);
    }
    
    public int getCodigo ()
    {
        return daoCama.getCodigo();
    }
    
    /**
     * Metodo que permite consultar la informacion de  
     * una cama dado su codigo
     * @param codigo : codigo de la cama
     * @return cama : objeto con la informacion de la cama
     */
    public Cama consultarCama (String codigo){
        Cama cama = daoCama.consultarCamaCod(codigo);
        return cama;
    }
    
        /**
     * Metodo que permite actualizar la informacion de una cama en la BD dado su codigo. 
     * @param codigo: codigo de la cama  a actualizar
     * @param cama : objeto con la nueva informacion de la cama.
     * @return -1 en caso de error , -2 si el Cama ya existe y el numero de filas en caso contrario
     */
    public int editarCamaCod(String codigo, Cama cama){
        int result = daoCama.actualizarCamaCod(codigo, cama);
        return result;
    }
    
    /**
     * Metodo que permite eliminar una cama
     * @param codigo: codigo de la cama
     * @return numero de confirmacion
     */
    public int eliminarCama (String codigo){
        int result = daoCama.eliminarCama(codigo);
        return result;
    }

    
}
