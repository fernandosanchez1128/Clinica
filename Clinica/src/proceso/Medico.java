/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;
import java.util.List;
/**
 *
 * @author nelson
 */

public class Medico {
    
    private String idMedico,especialidad, numLicencia, universidad;
    private List<Campanna> campannaList;
    private boolean estado;

    public Medico() {
    }

    public Medico(String idMedico, String especialidad, String numLicencia, String universidad, List<Campanna> campannaList, boolean estado) {
        this.idMedico = idMedico;
        this.especialidad=especialidad;
        this.numLicencia=numLicencia;
        this.universidad=universidad;
        this.estado=estado;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public List<Campanna> getCampannaList() {
        return campannaList;
    }

    public void setCampannaList(List<Campanna> campannaList) {
        this.campannaList = campannaList;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 
    
}
