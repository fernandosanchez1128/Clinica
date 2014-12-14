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
public class Causa{
    
    private String codigoCausa;
    private String nombre;
    private String descripcion;
    private boolean estado;
    public Causa() {
    }

    public Causa(String codigoCausa, String nombre, String descripcion, boolean estado) {
        this.codigoCausa = codigoCausa;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.estado=estado;
    }

    public String getCodCausa() {
        return codigoCausa;
    }

    public void setCodCausa(String codigoCausa) {
        this.codigoCausa = codigoCausa;
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

   
    
}
