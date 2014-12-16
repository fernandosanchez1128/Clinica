
package proceso;


/**
 *
 * @author Nelson
 */

public class Empleado {
    private String idEmpleado, cargo, email, idJefe,  username,  password, perfil;
    private Integer salario;
    
    private boolean estado;

    public Empleado() {
    }

    public Empleado(String idEmpleado,String cargo, String email,String idJefe,Integer salario,  String username, String password, String perfil, boolean estado) {
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
        this.email = email;
        this.idJefe = idJefe;
        this.salario = salario;
        this.estado = estado;
        this.password=password;
        this.username=username;
        this.perfil=perfil;
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

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 
    
}
