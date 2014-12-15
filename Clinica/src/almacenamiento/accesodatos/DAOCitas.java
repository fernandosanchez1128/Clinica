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
    //-------------------------------------------------------------------------//
    /**
     * Metodo que permite ver los medicos disponibles para una cita en horario dado.
     * @param hora : hora en que se va a asignar una cita.
     * @param minutos :  minuto en que se va a asignar la cita
     * @return arreglo con los datos de los medicos que tienen disponibilidad 
     * para ese horario, null en caso de no haber disponibilidad para ese horario
     **/
    public String [] [] medicos_disponibles  (String fecha, int hora,int minutos)
    {
        String sql_consult;
        String [] [] resultado = null; 
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
        sql_consult = "SELECT DISTINCT id_medico, nombre  FROM Medico as M ,Persona as P  WHERE M.id_medico = P.id EXCEPT "
                + "SELECT DISTINCT id_medico,nombre FROM Cita AS C, Persona AS P WHERE estado = 'Programada' AND hora BETWEEN '" + hora_ant_char + "' AND '" +
        hora_desp_char + "' AND fecha = '" + fecha + "' AND p.id = c.id_medico;" ;
                  
        System.out.println(sql_consult);
        try{
            Statement sentencia = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet table = sentencia.executeQuery(sql_consult);
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
        catch(SQLException e){
            
            System.out.println(e); 
            
        }
        catch(Exception e){ 
            
            System.out.println(e);
        }
        return resultado;
    }
    //------------------------------------------------------------------------//
    
    /**
     * Metodo que permite comprobar la disponibilidad para una cita.
     * @param id_paciente : cedula del paciente
     * @param id_medico : cedula del medico
     * @param hora : hora en que se va a asignar una cita.
     * @param minutos :  minuto en que se va a asignar la cita
     * @param fecha : dia de la cita
     * @return int [1] [2]  el primero con el numero de citas que tiene el medico
     * para esa hora el segundo el numero de citas que tiene el paciente a esa hora
     * para poder asignar cita los 2 resultados deben ser 0
     **/
    public int [] [] ConsultarDisponibilidad  (String id_paciente, String id_medico, int hora,int minutos, String fecha)
    {
        String sql_consult,sql_consult1;
        int [] [] resultados = null;
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
        sql_consult = "SELECT * FROM Cita WHERE estado = 'Programada' AND id_medico = '" + id_medico + "' AND hora BETWEEN '" + hora_ant_char + "' AND '" +
        hora_desp_char + "' AND fecha = '" + fecha + "' ;" ;
        
        sql_consult1 = "SELECT * FROM Cita WHERE  estado = 'Programada' AND id_paciente = '" + id_paciente + "' AND hora BETWEEN '" + hora_ant_char + "' AND '" +
        hora_desp_char + "' AND fecha = '" + fecha + "' ;" ;                    
        
             
        System.out.println(sql_consult);
        try{
            Statement sentencia = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet table = sentencia.executeQuery(sql_consult);
            table.last();
            int filas = table.getRow();
            ResultSet table1 = sentencia.executeQuery(sql_consult1);
            table1.last();
            int filas1 = table1.getRow();
            resultados = new int [1] [2];
            resultados [0] [0] = filas;
            resultados [0] [1] = filas1;
        }
        catch(SQLException e){
            
            System.out.println(e); 
            
        }
        catch(Exception e){ 
            
            System.out.println(e);
        }
        return resultados;
    }
    /**
     * Metodo que permite consultar la cedula de medico dado parte de su nombre
     * @param nombre : parte del nombre o nombre completo del medico 
     * @return arreglo con los datos de los medicos que tienen ese nombre o 
     * null en caso de no haber coincidencias.
     **/
    
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
     * Metodo que permite consultar las citas programadas de un cliente dada 
     * su cedula
     * @param id_paciente : cedula del paciente. 
     * @return arreglo con los datos de la citas que tine programadas. 
     * null en caso de no tener citas programadas o error, primer campo en
     * resultado null si ocurrio un problema en la base de datos.
     * 
     **/
     public String[][] CitasPaciente (String id_paciente){	
        String sql_consult;
        String [] [] resultado = null;
        sql_consult ="SELECT DISTINCT C.id,id_paciente,id_medico, P.nombre,fecha, hora,tipo FROM " + 
        "Cita C,Persona P  WHERE id_paciente ='" + id_paciente + "' AND estado = 'Programada' AND C.id_medico = P.id;";
         System.out.println(sql_consult);
        
        try{
            
             Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet table = statement.executeQuery(sql_consult);
            int i = 0;
            table.last();
            int filas = table.getRow();
            if (filas !=0) {resultado = new String [filas][7];}
            table.first();
            table.previous();
            while (table.next ())
            {
                resultado [i][0] = table.getString(1);
                resultado [i][1] = table.getString(2);
                resultado [i][2] = table.getString(3);
                resultado [i][3] = table.getString(4);
                resultado [i][4] = table.getString(5);
                resultado [i][5] = table.getString(6);
                resultado [i][5] = table.getString(7);
                i++;
            }
            
        }
        catch(SQLException e){
            System.out.println(e);
            resultado = new String [1][1];
            resultado [0][0] = null;
        }
        catch(Exception e){ 
            System.out.println(e);
            resultado = new String [1][1];
            resultado [0][0] = null;
        }
        return resultado;
        
    }
     /**
      * metodo que permite cancelar una cita.
      * @param id : id de la cita
      * @return 1 en caso de que la cancelacion de la cita sea exitosa.
      * -1 si la cita no existe, -2 en caso de error.
      */
    public int cancelarCita (String id){	
        String sql_save;

        sql_save="UPDATE Cita SET estado='Cancelada' WHERE id='" + id + "'";
        System.out.println(sql_save);
        try{
            Statement statement = conn.createStatement();

            int resultado = statement.executeUpdate(sql_save);            
            if (resultado == 0) {return -1;}
            else {return resultado;}
        }
        catch(SQLException e){
            System.out.println(e);
            return -2;
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
  

    

