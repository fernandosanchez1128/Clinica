
package proceso;


/**
 *
 * @author Nelson
 */

public class Empleado {
    private String idEmpleado, cargo, email, idJefe;
    private Integer salario;
    
    private boolean estado;

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
