//Importaciones
package presentacion;
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;



/**
 * Clase Validador. Clase que realiza las validaciones al dato ingresado por el usuario
 * @author Nelson Portilla, Juan Diego Prado, Fernando Sanchez
 */
public class Validador {
    
    private double valorDouble;
    private  int valorInt;
    
    /**
     * Método que devuelve la variable valorDouble
     * @return El valor de valorDouble
     */
    public Validador ()
    {
    }
    
    public double consultarDouble( ){
        return valorDouble;
    }
            
    public int consultarEntero( ){
        return valorInt;
    }
    /**
     * Método que revisa si el tipo de dato es correcto y está dentro del rango ingresado
     * @param parentComponent
     * @param datoDouble Cadena con el dato a validar
     * @param min Doble con el rango mínimo posible a ingresar
     * @param max Doble con el rango máximo posible a ingresar
     * @return Un booleano: true si el dato es válido o false si el dato no es válido
     */
    
    public boolean capturarEntero (Component parentComponent,String datoEntero,String campo  )
    {
         
        boolean flag=true;
        
        try{
            //Convierte la cadena datoDouble a un Doble y revisa si está en el rango
            valorInt=Integer.parseInt(datoEntero);
            
        }
        catch (NumberFormatException nfe){
            //Si el dato no es un Doble, despliega este mensaje
            String msjError=datoEntero+" no es un numero. campo " + campo;
            //Si el dato tiene longitud 0, despliega el mensaje "Cadena vacía"
            if (datoEntero.length()==0) {msjError = "Cadena vacia en " + campo;}
            desplegarMensajeDialogo(parentComponent,
                                msjError,
                                "Error en formato de entrada",
                                JOptionPane.ERROR_MESSAGE);
            return false;
        }        
        
        return flag;
    }

    public boolean capturarDoubleEnRango(Component parentComponent,String datoDouble, double min, double max)
    {
        boolean flag=true;
        
        try{
            //Convierte la cadena datoDouble a un Doble y revisa si está en el rango
            valorDouble=Double.parseDouble(datoDouble);
            if ( (valorDouble<min) || (valorDouble>max) ){
                //Si no está en el rango válido lanza la excepción IllegalArgumentException
                throw (new IllegalArgumentException());
            }
        }
        catch (NumberFormatException nfe){
            //Si el dato no es un Doble, despliega este mensaje
            String msjError=datoDouble+" no es un numero.";
            //Si el dato tiene longitud 0, despliega el mensaje "Cadena vacía"
            if (datoDouble.length()==0) {msjError = "Cadena vacia.";}
            desplegarMensajeDialogo(parentComponent,
                                msjError,
                                "Error en formato de entrada",
                                JOptionPane.ERROR_MESSAGE);
            return false;
        }        
        catch (IllegalArgumentException iae){
            //Despliega un mensaje para decir que el Doble no está en el rango válido
            desplegarMensajeDialogo(parentComponent,
                                datoDouble+" esta fuera de rango valido",
                                "Error en argumento",
                                JOptionPane.ERROR_MESSAGE);
            return false;            
        }
        
        return flag;
    }
    public boolean ValidaVacios(Component parentComponent, String cadena,String campo){
        boolean bandera= true;
        if (cadena.length()==0){
            bandera=false;
        }else{
            bandera=true;
        }
        if (!bandera) {desplegarMensajeDialogo(parentComponent, "error cadena vacia en " + campo, "error en validacion", JOptionPane.ERROR_MESSAGE);}
        return bandera;
    }
    
    /**
     * Método que muestra un mensaje en pantalla
     * @param parentComponent
     * @param message Mensaje a imprimir en pantalla
     * @param title Título de la ventana a imprimir
     * @param messageType Tipo del mensaje a imprimir
     * @throws HeadlessException
     */
    public void desplegarMensajeDialogo (Component parentComponent,
                                        Object message,
                                        String title,
                                        int messageType) throws HeadlessException {
        
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
        
    }
            
}
