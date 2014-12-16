/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

/**
 *
 * @author USUARIO
 */
public class Consultas {
    private String cod_medico, cod_historia, cod_causa;
    
    public Consultas(){  }
    
    public Consultas(String cod_medico, String cod_historia, String cod_causa ){
        this.cod_medico =cod_medico;
        this.cod_historia=cod_historia;
        this.cod_causa=cod_causa;
    }
    
    public String getCod_Medico(){
        return cod_medico;
    }
    public String getCod_Historia(){
        return cod_historia;
    }
    public String getCod_Causa(){
        return cod_causa;
    }
    
    public void setCod_Medico(String cod){
        cod_medico=cod;
    }
    public void setCod_Historia(String his){
         cod_historia=his;
    }
    public void setCod_Causa(String causa){
         cod_causa=causa;
    }
    
}
