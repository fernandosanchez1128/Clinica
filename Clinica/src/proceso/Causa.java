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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "causa")
@NamedQueries({
    @NamedQuery(name = "Causa.findAll", query = "SELECT c FROM Causa c")})
public class Causa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_causa")
    private String codigoCausa;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    public Causa() {
    }

    public Causa(String codigoCausa) {
        this.codigoCausa = codigoCausa;
    }

    public String getCodigoCausa() {
        return codigoCausa;
    }

    public void setCodigoCausa(String codigoCausa) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCausa != null ? codigoCausa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Causa)) {
            return false;
        }
        Causa other = (Causa) object;
        if ((this.codigoCausa == null && other.codigoCausa != null) || (this.codigoCausa != null && !this.codigoCausa.equals(other.codigoCausa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proceso.Causa[ codigoCausa=" + codigoCausa + " ]";
    }
    
}
