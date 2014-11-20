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
@Table(name = "medicamento")
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m")})
public class Medicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_medicamento")
    private String codMedicamento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "costo")
    private Integer costo;

    public Medicamento() {
    }

    public Medicamento(String codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(String codMedicamento) {
        this.codMedicamento = codMedicamento;
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

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMedicamento != null ? codMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.codMedicamento == null && other.codMedicamento != null) || (this.codMedicamento != null && !this.codMedicamento.equals(other.codMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proceso.Medicamento[ codMedicamento=" + codMedicamento + " ]";
    }
    
}
