/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "campanna")
@NamedQueries({
    @NamedQuery(name = "Campanna.findAll", query = "SELECT c FROM Campanna c")})
public class Campanna implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_campanna")
    private String codCampanna;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "objetivo")
    private String objetivo;
    @Column(name = "fecha_realizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaRealizacion;
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    @ManyToOne(optional = false)
    private Medico idMedico;

    public Campanna() {
    }

    public Campanna(String codCampanna) {
        this.codCampanna = codCampanna;
    }

    public String getCodCampanna() {
        return codCampanna;
    }

    public void setCodCampanna(String codCampanna) {
        this.codCampanna = codCampanna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCampanna != null ? codCampanna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campanna)) {
            return false;
        }
        Campanna other = (Campanna) object;
        if ((this.codCampanna == null && other.codCampanna != null) || (this.codCampanna != null && !this.codCampanna.equals(other.codCampanna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proceso.Campanna[ codCampanna=" + codCampanna + " ]";
    }
    
}
