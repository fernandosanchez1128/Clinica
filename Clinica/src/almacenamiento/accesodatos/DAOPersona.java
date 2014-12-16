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
import proceso.Persona;
public class DAOPersona {
    /**
     * @param db objeto encargado de la conexión a la base de datos.
     * @param conn objeto para ejecutar las sentencias de SQL
     * 
     */
    private BaseDatos db;
    Connection conn ;
    /**
     * constructor, inicializa los atributos.
     */
    public DAOPersona(Connection con){  db=new BaseDatos(); conn=con;  }
    
    /**
     * Metodo que permite realizar la conexion a la base de datos
     */
    public void connectDB(){
        conn = db.getConnetion();
    }
    public Connection getConn(){
        return conn;
    }
    /**
    *Metodo para insertar una persona en la base de datos.
    *@param per Objeto de la clase persona
    *@return numRows numero de tuplas insertadas o afectadas. negativo si hay error
    */
    public int CrearPersona(Persona per){
        String sql_save;
        int numRows=0;
        sql_save="INSERT INTO Persona VALUES ('" + per.getId() + 
                "' , '" + per.getNombre() + 
                "', '" + per.getDireccion()+  
                "', '" + per.getTelefono() + "' , true)";
        try{
            Statement sentencia = conn.createStatement();
            numRows = sentencia.executeUpdate(sql_save);           
            System.out.print("En insert: "+numRows);
            return numRows;            
        }
        catch(SQLException e){            
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println("exception dao crear persona");
            System.out.println(e);
        }
        return -1;
    }//fin saveUser
        /**
        * consultar el usuario que tiene como username el parametro.
        * @param username el username del usuario que se quiere consultar.
        * @return null si hay error en la consulta a la base de datos. Objeto tipo Usuario si el objeto del usuario que se consulto. Devuelve 
        */
    public Persona LeerPersona(String req, int tipoCon){
        Persona per= new Persona();
        String sql_select;
        if(tipoCon==1){
            sql_select="SELECT Persona.id, Persona.nombre, Persona.direccion,Persona.telefono FROM  Persona WHERE nombre='" + req +  "'and estado=true";        
        }else{
            sql_select="SELECT Persona.id, Persona.nombre, Persona.direccion,Persona.telefono FROM  Persona WHERE id='" + req +  "' and estado=true";
        }
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            
            while(table.next()){
                //System.out.println("dentro del while");
                per.setId(table.getString(1));
               
                per.setNombre(table.getString(2));
                
                per.setDireccion(table.getString(3));
                
                per.setTelefono(table.getString(4));
                System.out.println("ok");
            return per;
            }            
            
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println("excepcion del dao"); System.out.println(e); }
        return null;
    }//fin readUser

   /**
     * actualizar la informacion de un usuario, con la cedula que entra por parametro.
     * @param us objeto de Usuario con los atributos a modificar en la base de datos.
     * @param cedula la cedula del usuario que se quiere actualizar.
     * @return 1 si el proceso ocurrio bien durante todo el metodo, -3 si el usuario entregado tiene un perfil 
     * inexistente, -2 si hay algun error de sql y -1 si hay cualquier otro error.
     */
    public int ActualizarPersona(Persona per, String cedula){
        String sql_save;
	sql_save="UPDATE persona SET nombre='"+per.getNombre()+
                "', direccion='"+per.getDireccion()+
                "', telefono='"+per.getTelefono()+"' WHERE id='" + per.getId() + "'";
        try{
            Statement statement = conn.createStatement();
            int tm=statement.executeUpdate(sql_save);
            System.out.println("EN UPDATE: "+tm);
            return tm;
        }            
        catch(SQLException e){
            System.out.println(e); 
            return -2;
        }
        catch(Exception e){ 
            System.out.println(e);
            return -1;
        }
        //return 1;
    }//fin updateUser

   /**
     * listar todas las tuplas de los usuarios existentes.
     * @return los objetos tipo Usuario enlistados en un arreglo.
     */ 
    /**
   public Usuario[] listUsers(){
        
        String sql_select;
        sql_select="SELECT usuario.cedula, usuario.name, usuario.lastName,usuario.userName, usuario.contrasena, 
        * usuario.email ,  perfiles.nombre, 
        * usuario.estado FROM  usuario, 
        * perfiles WHERE usuario.id_perfil=perfiles.id_perfil";
        try{
            System.out.println("consultando en la bd");
            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery(sql_select);
            ResultSet table2= table;
            int numRows=0;
            while(table.next()){
                numRows++;
            }
            System.out.println(numRows);
            Usuario us[]= new Usuario[numRows];
            for(int i=0; i<numRows; i++){
                us[i]=new Usuario();
            }
            String sql_conv="";
            int j=0;
            while(table2.next()){
                
                us[j].setCedula(table.getString(1));
               
                us[j].setName(table.getString(2));
                
                us[j].setLastName(table.getString(3));
                
                us[j].setUserName(table.getString(4));               

                us[j].setPassword(table.getString(5));

                us[j].setMail(table.getString(6));
 
                us[j].setProfile(table.getString(7));
                
                us[j].setState(table.getBoolean(8));
                
                if(!us[j].getProfile().equals("Administrador")){
                    sql_conv= "SELECT convocatoria.nombre FROM convoUsuario, 
                    * convocatoria WHERE cedula='"+us[j].getCedula() +"' AND estado=true AND 
                    * convoUsuario.codigo=convocatoria.codigo";
                    ResultSet table3= statement.executeQuery(sql_conv);
                    String nom="";
                    while(table3.next()){
                
                        nom = table3.getString(1);
              
                        //System.out.println("ok");
                    }
                    DAOConvocatoria daoc=new DAOConvocatoria(conn);
                    Convocatoria conv = daoc.readConv(nom);
                    us[j].setConvocatoria(conv);
                }


                j++;
                System.out.println("ok");
            }
           
            return us;
         }
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e); }
        return null;
    }**/
   /**
    * borrar un usuario de la tabla.
    * @param cedula la cedula del usuario que se quiere borrar.
    */
    public int EliminarPersona(String cedula){	
        String sql_save;
        //sql_save="UPDATE usuario SET estado=false WHERE cedula='" + cedula + "'";
        sql_save="UPDATE persona SET estado=false WHERE id='"+cedula+"'";
        try{
            Statement statement = conn.createStatement();

            int tm= statement.executeUpdate(sql_save); 
            System.out.println("EN ELIMINAR: "+tm);
            return tm;            
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
     * cerrar la conexion con la base de datos.
     */
    public void closeConectionDB(){
        db.closeConection(db.getConnetion());
    }
    
}
