
package proceso;

/**
 *
 * @author Nelson
 */

public class Persona  {
   
    private String id, nombre, direccion, telefono;
    private boolean estado;
   

    public Persona() {
    }

    public Persona(String id, String nombre, String direccion, String telefono, boolean estado) {
        this.id = id;
        this.nombre=nombre;
        this.direccion=direccion;
        this.telefono=telefono;
        this.estado=estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    } 

    
}
