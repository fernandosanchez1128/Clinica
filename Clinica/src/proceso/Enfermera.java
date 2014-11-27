package proceso;
/**
 *
 * @author Nelson
 */

public class Enfermera {
    
    private String idEnfermera, experiencia, codArea;
    private boolean estado;

    public Enfermera() {
    }

    public Enfermera(String idEnfermera, String experiencia, String codArea, boolean estado) {
        this.idEnfermera = idEnfermera;        
        this.experiencia=experiencia;
        this.codArea=codArea;       
        this.estado=estado;
    }

    public String getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(String idEnfermera) {
        this.idEnfermera = idEnfermera;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getCodArea() {
        return codArea;
    }

    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 
    
}
