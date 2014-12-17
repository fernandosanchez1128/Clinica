
package proceso;

import java.util.Date;


/**
 *
 * @author Nelson
 */

public class Paciente {
    private String idPaciente, numSeguridadSocial, actividadEconomica;
    private String fechaNac;
    private boolean estado;

    public Paciente() {
    }

    public Paciente(String idPaciente, String numSeguridadSocial,String actividadEconomica,String fechaNac, boolean estado) {
        this.idPaciente = idPaciente;
        this.numSeguridadSocial=numSeguridadSocial;
        this.actividadEconomica=actividadEconomica;
        this.fechaNac=fechaNac;
        this.estado=estado;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(String numSeguridadSocial) {
        this.numSeguridadSocial = numSeguridadSocial;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }    
    
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 
    
}
