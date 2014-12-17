package proceso;
/**
 *
 * @author Nelson
 */

public class Enfermera {
    
    private String idEnfermera, codArea;
    private boolean estado;
    int experiencia;

    public Enfermera() {
    }

    public Enfermera(String idEnfermera, int experiencia, String codArea, boolean estado) {
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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
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
