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
@Table(name = "cama")
@NamedQueries({
    @NamedQuery(name = "Cama.findAll", query = "SELECT c FROM Cama c")})
public class Cama implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_cama")
    private String codCama;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "cod_area", referencedColumnName = "cod_area")
    @ManyToOne(optional = false)
    private Areas codArea;

    public Cama() {
    }

    public Cama(String codCama) {
        this.codCama = codCama;
    }

    public String getCodCama() {
        return codCama;
    }

    public void setCodCama(String codCama) {
        this.codCama = codCama;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        hash += (codCama != null ? codCama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cama)) {
            return false;
        }
        Cama other = (Cama) object;
        if ((this.codCama == null && other.codCama != null) || (this.codCama != null && !this.codCama.equals(other.codCama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proceso.Cama[ codCama=" + codCama + " ]";
    }
    
}
