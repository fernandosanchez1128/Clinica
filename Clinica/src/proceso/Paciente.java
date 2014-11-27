
package proceso;

import java.util.Date;


/**
 *
 * @author Nelson
 */

public class Paciente {
    private String idPaciente, numSeguridadSocial, actividadEconomica, historia;
    private Date fechaNac;
    private boolean estado;

    public Paciente() {
    }

    public Paciente(String idPaciente, String numSeguridadSocial,String actividadEconomica,String historia,Date fechaNac, boolean estado) {
        this.idPaciente = idPaciente;
        this.numSeguridadSocial=numSeguridadSocial;
        this.actividadEconomica=actividadEconomica;
        this.historia=historia;
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

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }    

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 
    
}
