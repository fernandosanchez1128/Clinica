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
public class DAOCampana {
    private BaseDatos db;
    Connection conn ;
    
    
    /**
     * constructor, inicializa los atributos.
     * @param conexion Constructor con conexion a la bd
     */
    public DAOCampana(Connection conexion){  
    conn=conexion;}
    
    
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
        sql_select="SELECT Campanna.cod_campanna, Campanna.nombre, Campanna.objetivo, Campanna.fecha_realizacion, Campanna.id_medico, Campanna.estado FROM Campanna WHERE nombre='" + req +  "'";
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
                
                cam.setEstado(table.getBoolean(6));
                System.out.println("ok");
            return cam;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser
    
    public int numSeleccionados(String req){
        int resultado=0;
        Statement sentencia;
        ResultSet table;
        String sql= "SELECT COUNT(*) FROM Campanna NATURAL JOIN Paciente_Campanna WHERE estado=true AND Campanna.nombre = '"+req+"'";
        try{
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            if(table.next()){
                resultado=table.getInt(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOCampana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String[][] pacientes(String req){
        String[][] resultado;
        int longitud;
        longitud=numSeleccionados(req);
        Statement sentencia;
        ResultSet table;
        resultado= new String[longitud][3];
        try{
            String sql = "SELECT Paciente.id_paciente, Persona.nombre, Paciente.num_seguridad_social FROM Persona NATURAL JOIN Paciente NATURAL JOIN Paciente_Campanna NATURAL JOIN Campanna WHERE estado=true AND Campanna.nombre = '"+req+"'";
            sentencia = conn.createStatement();
            table = sentencia.executeQuery(sql);
            int i = 0;
            while(table.next()){
                resultado[i][0] = table.getString(1);
                System.out.println("Id:"+resultado[i][0]+"\n");
                resultado[i][1] = table.getString(2);
                System.out.println("Nombre:"+resultado[i][1]+"\n");
                resultado[i][2] = table.getString(3);
                System.out.println("Seguridad:"+resultado[i][2]+"\n");
                i++;
            }
            
        }catch(SQLException ex){
            Logger.getLogger(DAOCampana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public int ActualizarCampanna(Campanna cam, String codCam){
        String sql_save;
	sql_save="UPDATE Campanna SET nombre='"+cam.getNombre()+
                "', objetivo='"+cam.getObjetivo()+
                "', fecha_realizacion='"+cam.getFechaRealizacion()+"', id_medico='"+cam.getIdMedico()+"', estado="+Boolean.toString(cam.getEstado())+ " WHERE cod_campanna='" + codCam+ "'";
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql_save);
            
        }            
        catch(SQLException e){
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println(e);
            return -1;
        }
        return 1;
    }//fin updateUser
    
    public int EliminarCampanna(String nombre){	
        String sql_save;
        //sql_save="UPDATE usuario SET estado=false WHERE cedula='" + cedula + "'";
        sql_save="UPDATE Campanna SET estado=false WHERE nombre='"+nombre+"'";
        try{
            Statement statement = conn.createStatement();

            return statement.executeUpdate(sql_save);            
            
        }
        catch(SQLException e){
            System.out.println(e);
            return -1;
        }
        catch(Exception e){ 
            System.out.println(e);
            
        }
        return -2;
        
    }
    
    public int EliminarPaciente(String cedula, String nomCam){	
        String codCam=LeerCampanna(nomCam).getCodCampanna();
        String sql_save;
        sql_save="DELETE FROM Paciente_Campanna WHERE id_paciente='"+cedula+"', AND cod_campanna='"+codCam+"'";
        try{
            Statement statement = conn.createStatement();

            return statement.executeUpdate(sql_save);            
            
        }
        catch(SQLException e){
            System.out.println(e);
            return -1;
        }
        catch(Exception e){ 
            System.out.println(e);
            
        }
        return -2;
        
    }
    
    public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }
    
}
