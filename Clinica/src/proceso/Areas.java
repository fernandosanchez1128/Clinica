/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "areas")
@NamedQueries({
    @NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a")})
public class Areas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_area")
    private String codArea;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArea")
    private List<Enfermera> enfermeraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArea")
    private List<Cama> camaList;

    public Areas() {
    }
    
    public Areas (String codigo, String nom,String desc)
    {
        codArea = codigo;
        nombre = nom;
        descripcion = desc;
    }
    public Areas(String codArea) {
        this.codArea = codArea;
    }

    public String getCodArea() {
        return codArea;
    }

    public void setCodArea(String codArea) {
        this.codArea = codArea;
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

    public List<Enfermera> getEnfermeraList() {
        return enfermeraList;
    }

    public void setEnfermeraList(List<Enfermera> enfermeraList) {
        this.enfermeraList = enfermeraList;
    }

    public List<Cama> getCamaList() {
        return camaList;
    }

    public void setCamaList(List<Cama> camaList) {
        this.camaList = camaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codArea != null ? codArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.codArea == null && other.codArea != null) || (this.codArea != null && !this.codArea.equals(other.codArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proceso.Areas[ codArea=" + codArea + " ]";
    }
    
}
