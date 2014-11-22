/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

/**
 *
 * @author fernando
 */

public class Medicamento {
    
    
    private String codMedicamento;
    private String nombre;
    private String descripcion;
    private Integer costo;
    
    
    public Medicamento (String codigo, String nombre_med,String descripcion_med, int costo_med) {
        codMedicamento = codigo;
        nombre = nombre_med;
        descripcion = descripcion_med;
        costo = costo_med;
    }
    
    public Medicamento() {
    }

    public Medicamento(String codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(String codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }    
}
