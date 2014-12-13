/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento.accesodatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import proceso.Areas;

/**
 *
 * @author fernando
 */
public class DAOCitas {
    private BaseDatos db;
    private Connection conn;
    //private Connection conn ;
    public DAOCitas(Connection conect){
        db = new BaseDatos();
        conn = conect; 
        //conn = db.getConnetion();
    }
    public DAOCitas(){
     db = new BaseDatos();
    }
    public void connectDB(){
        conn = db.getConnetion();
    }
    /**
     * Metodo que permite programar una cita
     * @param cita datos de la nueva cita a aceptar
     * @return -1 en caso de error , -2 si el medico ya tiene programacion para ese horario y el numero de filas en caso contrario
     */
    
    public int crearCita (String id_paciente, String id_medico, String  hora, String fecha, String tipo , int costo){
        String sql_save;
        int numRows=0;
        
        sql_save = "INSERT INTO Cita (id_paciente,iD_medico,hora,fecha, tipo,costo) VALUES ( '"+ id_paciente+ "','" + id_medico + "','" +
                    hora + "','"+ fecha + "','" + tipo + "'," + costo +");";
        
                   
        System.out.println(sql_save);
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
            
            System.out.println(e);
        }
        return -1;
    } 
    
    
    public int ConsultarDisponiilidad  (String id_medico, int hora,int minutos, String fecha)
    {
        String sql_consult;
        int numRows=0;
        int hora_ant,hora_desp,minutos_ant = 0,minutos_desp = 0;
        if (minutos == 30 ) {hora_ant = hora;hora_desp = hora+1;minutos = 0; }
        else 
        {
            if (minutos > 30) {hora_ant = hora; minutos_ant = minutos - 30;}
            else {hora_ant = hora -1; minutos_ant = 60 - (30-minutos);};
            if (minutos < 30) {hora_desp = hora;minutos_desp = minutos +30;}
            else {hora_desp = hora + 1; minutos_desp = 0 + (minutos -30);};
        }
        String hora_ant_char = Integer.toString(hora_ant) + ":" + Integer.toString(minutos_ant) ;
        String hora_desp_char = Integer.toString(hora_desp) + ":" + Integer.toString(minutos_desp) ;
        sql_consult = "SELECT * FROM Cita WHERE id_medico = '" + id_medico + "' AND hora BETWEEN '" + hora_ant_char + "' AND '" +
        hora_desp_char + "' AND fecha = '" + fecha + "' ;" ;
                    
        
             
        System.out.println(sql_consult);
        try{
            Statement sentencia = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet table = sentencia.executeQuery(sql_consult);
            table.last();
            int filas = table.getRow();
            return filas;
        }
        catch(SQLException e){
            
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            
            System.out.println(e);
        }
        return -1;
    }
    
    public String [][] BuscarMedico (String nombre){	
        String sql_save;

        sql_save="SELECT id,nombre FROM Persona, Empleado WHERE id = id_empleado AND cargo = 'Medico' AND nombre like '%" + nombre + "%';";
        String [] [] resultado= null;
        try{
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet table = statement.executeQuery(sql_save);
            int i = 0;
            table.last();
            int filas = table.getRow();
            if (filas !=0) {resultado = new String [filas][2];}
            table.first();
            table.previous();
            while (table.next ())
            {
                resultado [i][0] = table.getString(1);
                resultado [i][1] = table.getString(2);
                i++;
            }
            
            
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOAreas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
        
    }
    /**
    * metodo que permite cancelar una cita.
    * @param codigo : codigo de la cita.
    */
    public int eliminarAreas (String id){	
        String sql_save;

        sql_save="UPDATE Citas SET estado='cancelado' WHERE id=" + id + "'";
        
        try{
            Statement statement = conn.createStatement();

            statement.executeUpdate(sql_save);            
            return 1;
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
        
        /**
         * metodo usado para cerrar la conexion
         */
        public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }
        
           
}
  

    

