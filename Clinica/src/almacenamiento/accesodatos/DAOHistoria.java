/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;

/**
 *
 * @author Nelson
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import proceso.Campanna;
public class DAOHistoria {
    private BaseDatos db;
    Connection conn ;
    /**
     * constructor, inicializa los atributos.
     */
    public DAOHistoria(){  db=new BaseDatos();  }
    
    /**
     * Metodo que permite realizar la conexion a la base de datos
     */
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    
    public int CrearCampanna(Campanna cam){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Campanna (nombre, objetivo, fecha_realizacion, id_medico, estado) "
                + "VALUES ('" + cam.getNombre()+ 
                "' , '" + cam.getObjetivo()+ 
                "', '" + cam.getFechaRealizacion()+                   
                "', '"+ cam.getIdMedico()+"' , true)"; ///CONVERTIR DATE
        try{
            Statement sentencia = conn.createStatement();
            numRows = sentencia.executeUpdate(sql_save);           
            return numRows;            
        }
        catch(SQLException e){            
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println("exception dao crear campanna");
            System.out.println(e);
        }
        return -1;
    }
    
    public int AgregarPaciente(String cedula, String nomCam){
        String codCam=LeerCampanna(nomCam).getCodCampanna();
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Paciente_Campanna "
                + "VALUES ('" + cedula+ 
                "' , '" + codCam+"')"; 
        try{
            Statement sentencia = conn.createStatement();
            numRows = sentencia.executeUpdate(sql_save);           
            return numRows;            
        }
        catch(SQLException e){            
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println("exception dao crear campanna");
            System.out.println(e);
        }
        return -1;
    }
    
    public Campanna LeerCampanna(String req){
        Campanna cam= new Campanna();
        String sql_select;
        sql_select="SELECT Campanna.cod_campanna, Campanna.nombre, Campanna.objetivo, Campanna.fecha_realizacion, Campanna.id_medico, Campanna.estado FROM Campanna WHERE nombre='" + req +  "' and estado=true";
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                cam.setCodCampanna(table.getString(1));
               
                cam.setNombre(table.getString(2));
                
                cam.setObjetivo(table.getString(3));
                
                cam.setFechaRealizacion(table.getString(4));
                
                cam.setIdMedico(table.getString(5));
                System.out.println("ok");
            return cam;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser
    
    public int numRegistros(String req){
        int resultado=0;
        Statement sentencia;
        ResultSet table;
        String sql = "SELECT Persona.nombre, Causa.nombre, Causa.descripcion FROM Persona, Medico, "
                    + "Registro, Causa, Historia WHERE Persona.id=Medico.id_medico AND Medico.id_medico="
                    + "Registro.id_medico AND Registro.cod_causa=Causa.codigo_causa AND Registro.cod_historia"
                    + "=Historia.cod_historia AND Historia.id_paciente = '"+req+"'";
        try{
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            if(table.next()){
                resultado=table.getInt(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String[][] registros(String req){
        String[][] resultado;
        int longitud;
        longitud=numRegistros(req);
        Statement sentencia;
        ResultSet table;
        resultado= new String[longitud][3];
        try{
            String sql = "SELECT Persona.nombre, Causa.nombre, Causa.descripcion FROM Persona, Medico, "
                    + "Registro, Causa, Historia WHERE Persona.id=Medico.id_medico AND Medico.id_medico="
                    + "Registro.id_medico AND Registro.cod_causa=Causa.codigo_causa AND Registro.cod_historia"
                    + "=Historia.cod_historia AND Historia.id_paciente = '"+req+"'";
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            int i = 0;
            while(table.next()){
                resultado[i][0] = table.getString(1);
                System.out.println("Nombre Medico:"+resultado[i][0]+"\n");
                resultado[i][1] = table.getString(2);
                System.out.println("Nombre Causa:"+resultado[i][1]+"\n");
                resultado[i][2] = table.getString(3);
                System.out.println("Descripcion:"+resultado[i][2]+"\n");
                i++;
            }
            
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public int numMedicaciones(String req){
        int resultado=0;
        Statement sentencia;
        ResultSet table;
        String sql = "SELECT Persona.nombre, Medicamento.nombre, Medicacion.cantidad, "
                    + "Medicacion.fecha, Medicacion.precio FROM Persona, Medico, Medicacion, "
                    + "Medicamento, Historia WHERE Persona.id=Medico.id_medico AND "
                    + "Medico.id_medico=Medicacion.id_medico AND Medicacion.cod_medicamento="
                    + "Medicamento.cod_medicamento AND Medicacion.cod_historia=Historia.cod_historia "
                    + "AND Historia.id_paciente = '"+req+"'";
        try{
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            if(table.next()){
                resultado=table.getInt(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String[][] medicaciones(String req){
        String[][] resultado;
        int longitud;
        longitud=numRegistros(req);
        Statement sentencia;
        ResultSet table;
        resultado= new String[longitud][5];
        try{
            String sql = "SELECT Persona.nombre, Medicamento.nombre, Medicacion.cantidad, "
                    + "Medicacion.fecha, Medicacion.precio FROM Persona, Medico, Medicacion, "
                    + "Medicamento, Historia WHERE Persona.id=Medico.id_medico AND "
                    + "Medico.id_medico=Medicacion.id_medico AND Medicacion.cod_medicamento="
                    + "Medicamento.cod_medicamento AND Medicacion.cod_historia=Historia.cod_historia "
                    + "AND Historia.id_paciente = '"+req+"'";
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            int i = 0;
            while(table.next()){
                resultado[i][0] = table.getString(1);
                System.out.println("Nombre Medico:"+resultado[i][0]+"\n");
                resultado[i][1] = table.getString(2);
                System.out.println("Nombre Medicamento:"+resultado[i][1]+"\n");
                resultado[i][2] = table.getString(3);
                System.out.println("Cantidad:"+resultado[i][2]+"\n");
                resultado[i][2] = table.getString(4);
                System.out.println("Fecha:"+resultado[i][3]+"\n");
                resultado[i][2] = table.getString(5);
                System.out.println("Precio:"+resultado[i][4]+"\n");
                i++;
            }
            
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public int numCitas(String req){
        int resultado=0;
        Statement sentencia;
        ResultSet table;
        String sql = "SELECT Persona.nombre, Cita.fecha, Cita.hora, Cita.tipo, Cita.costo "
                    + "FROM Persona, Medico, Cita, Historia WHERE Persona.id=Medico.id_medico "
                    + "AND Medico.id_medico=Cita.id_medico AND Cita.id_paciente=Historia.id_paciente"
                    + "AND Historia.id_paciente = '"+req+"'";
        try{
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            if(table.next()){
                resultado=table.getInt(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String[][] citas(String req){
        String[][] resultado;
        int longitud;
        longitud=numRegistros(req);
        Statement sentencia;
        ResultSet table;
        resultado= new String[longitud][5];
        try{
            String sql = "SELECT Persona.nombre, Cita.fecha, Cita.hora, Cita.tipo, Cita.costo "
                    + "FROM Persona, Medico, Cita, Historia WHERE Persona.id=Medico.id_medico "
                    + "AND Medico.id_medico=Cita.id_medico AND Cita.id_paciente=Historia.id_paciente"
                    + "AND Historia.id_paciente = '"+req+"'";
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            int i = 0;
            while(table.next()){
                resultado[i][0] = table.getString(1);
                System.out.println("Nombre Medico:"+resultado[i][0]+"\n");
                resultado[i][1] = table.getString(2);
                System.out.println("Fecha cita:"+resultado[i][1]+"\n");
                resultado[i][2] = table.getString(3);
                System.out.println("Hora cita:"+resultado[i][2]+"\n");
                resultado[i][2] = table.getString(4);
                System.out.println("Tipo:"+resultado[i][3]+"\n");
                resultado[i][2] = table.getString(5);
                System.out.println("Costo:"+resultado[i][4]+"\n");
                i++;
            }
            
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String nomPaciente(String req){
        String resultado="";
        Statement sentencia;
        ResultSet table;
        String sql = "SELECT Persona.nombre FROM Paciente, Persona WHERE Paciente.id_paciente=Persona.id"
                    + "AND Paciente.id_paciente = '"+req+"'";
        try{
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            if(table.next()){
                resultado=table.getString(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String numHistoria(String req){
        String resultado="";
        Statement sentencia;
        ResultSet table;
        String sql = "SELECT cod_historia FROM Paciente, Historia WHERE Paciente.id_paciente=Historia.id_paciente"
                    + "AND Paciente.id_paciente = '"+req+"'";
        try{
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            if(table.next()){
                resultado=table.getString(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }
    
}
