/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.util.Date;


/**
 *
 * @author fernando
 */
public class Campanna {
    private String codCampanna;
    private String nombre;
    private String objetivo;
    private boolean estado;
    private String fechaRealizacion;
    private String idMedico;

    public Campanna() {
    }

    public Campanna(String codCampanna) {
        this.codCampanna = codCampanna;
    }
    
    public Campanna(String nomCam, String idMedico, String obj, String fechaR, boolean estado){
        nombre=nomCam;
        this.idMedico=idMedico;
        objetivo=obj;
        fechaRealizacion=fechaR;
        this.estado=estado;
    }

    public String getCodCampanna() {
        return codCampanna;
    }

    public void setCodCampanna(String codCampanna) {
        this.codCampanna = codCampanna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "proceso.Campanna[ codCampanna=" + codCampanna + " ]";
    }
    
}
