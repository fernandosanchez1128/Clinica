
package proceso;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import proceso.Areas;

/**
 *
 * @author Nelson
 */
public class Empleado implements Serializable {
   
   
    private String idEmpleado;
    private String cargo;
    private Integer salario;
    private String email;
    private Persona persona;
    private List<Empleado> empleadoList;
    private Empleado idJefe;
    private Areas area;

    public Empleado() {
    }

    public Empleado(String idEmpleado,String cargo, String email,String idJefe,Integer salario, boolean estado) {
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
        this.email = email;
        this.idJefe = idJefe;
        this.salario = salario;
        this.estado = estado;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public String getIdJefe() {
        return idJefe;
    }
    
    public void setArea(Areas area ) {
        this.area = area;
    }

    public Areas getArea() {
        return area;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 
    
}
