/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

/**
 *
 * @author Nelson
 */
public class Formula {
    private String id_medico, cod_historia, cod_medicamento, fecha;
    private int precio, cantidad;
    
    public Formula(){}
    
    public Formula(String id_med, String cod_his, String cod_med, int Cantidad, String fecha, int precio){
        id_medico=id_med;
        cod_historia=cod_his;
        cod_medicamento=cod_med;
        cantidad=Cantidad;
        this.fecha=fecha;
        this.precio=precio;
    }
    
    public String getCod_Medico(){
        return id_medico;
    }
    public String getCod_Historia(){
        return cod_historia;
    }
    public String getCod_Medicamento(){
        return cod_medicamento;
    }
    public String getFecha(){
        return fecha;
    }
    public int getPrecio(){
        return precio;
    }
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCod_Medico(String cod){
        id_medico=cod;
    }
    public void setCod_Historia(String his){
         cod_historia=his;
    }
    public void setCod_Medicamento(String med){
         cod_medicamento=med;
    }
    public void setFecha(String Fecha){
        fecha=Fecha;
    }
    public void setCantidad(int cant){
        cantidad=cant;
    }
    public void setPrecio(int precio){
        this.precio=precio;
    }
}
