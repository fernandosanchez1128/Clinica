/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.controlador;
import almacenamiento.accesodatos.DAOMedicamento;
import proceso.*;
import java.sql.Connection;

/**
 *
 * @author fernando
 */
public class ControladorMedicamento {
    
    private DAOMedicamento daoMed;
    
    public ControladorMedicamento(Connection conn){
        daoMed = new DAOMedicamento(conn);
    }
    
      public ControladorMedicamento(){
        daoMed = new DAOMedicamento();
    }
      
      
      
      public void connectDB(){
        daoMed.connectDB();
    }
    /**
     * Metodo que permite agregar un medicamento a la base de datos
     * @param nombre : nombre del medicamento
     * @param descripcion: descripcion del medicamento
     * @param costo : costo del medicamento.
     * @return valor de la ultima formacion registrada exitosamente
     */
    public int crearMedicamento (String nombre, String descripcion, int costo){
        Medicamento med = new Medicamento ("1",nombre,descripcion,costo);
        int result = daoMed.crearMedicamento(med); 
        return result;
    }
    
    /**
     * Metodo que permite consultar la informacion de un 
     * medicamento dado su codigo
     * @param codigo : codigo del medicamento
     * @return medicamento : objeto con la informacion del medicamento
     */
    public Medicamento consultarMedicamentoCod (String codigo){
        Medicamento med = daoMed.consultarMedicamentoCod(codigo);
        return med;
    }
    
    /**
     * Metodo que permite consultar la informacion de un 
     * medicamento dado su nombre
     * @param nombre : nombre del medicamento
     * @return medicamento : objeto con la informacion del medicamento
     */
    public Medicamento consultarMedicamentoNom (String nombre){
        Medicamento med = daoMed.consultarMedicamentoNom(nombre);
        return med;
    }
    
        /**
     * Metodo que permite actualizar la informacion de un medicamento en la BD dado el codigo. 
     * @param codigo: codigo del medicamento a actualizar
     * @param med : objeto con la nueva informacion del medicamento.
     * @return -1 en caso de error , -2 si el Medicamento ya existe y el numero de filas en caso contrario
     */
    public int editarMedicamentoCod(String codigo, Medicamento med){
        int result = daoMed.actualizarMedicamentoCod(codigo, med);
        return result;
    }
    
    /**
     * metodo que permite modificar un medicamento dado el nombre
     * @param nom nombre del medicamento
     * @param med objeto don estan los nuevos datos del medicamento.
     * @return -1 en caso de error , -2 si el Medicamento ya existe y el numero de filas en caso contrario
     */

    public int editarMedicamentoNom(String nom, Medicamento med){
        int result = daoMed.actualizarMedicamentoNom(nom, med);
        return result;
    }
    
    
    /**
     * Metodo que permite eliminar un medicamento
     * @param codigo: codigo del medicamento
     * @return numero de confirmacion
     */
    public int eliminarMedicamento (String codigo){
        int result = daoMed.eliminarMedicamento(codigo);
        return result;
    }
  
}

  
