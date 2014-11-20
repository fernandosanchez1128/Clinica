/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "enfermera")
@NamedQueries({
    @NamedQuery(name = "Enfermera.findAll", query = "SELECT e FROM Enfermera e")})
public class Enfermera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_enfermera")
    private String idEnfermera;
    @Column(name = "experiencia")
    private String experiencia;
    @JoinColumn(name = "cod_area", referencedColumnName = "cod_area")
    @ManyToOne(optional = false)
    private Areas codArea;

    public Enfermera() {
    }

    public Enfermera(String idEnfermera) {
        this.idEnfermera = idEnfermera;
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

    public Areas getCodArea() {
        return codArea;
    }

    public void setCodArea(Areas codArea) {
        this.codArea = codArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnfermera != null ? idEnfermera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enfermera)) {
            return false;
        }
        Enfermera other = (Enfermera) object;
        if ((this.idEnfermera == null && other.idEnfermera != null) || (this.idEnfermera != null && !this.idEnfermera.equals(other.idEnfermera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proceso.Enfermera[ idEnfermera=" + idEnfermera + " ]";
    }
    
}
