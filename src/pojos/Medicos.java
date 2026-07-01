/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;
import java.util.ArrayList;
/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/
/**
 * @author Giovana B
*/
//@Entity
//@Table (name = "MEDICOS")

public class Medicos {
    //@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private String nome;
    private String crm;
    private String especialidade;
    private ArrayList<Consulta> agendamentos;
    
    public Medicos(){
    }
    
    public Medicos(String nome, String crm, String especialidade){
        this.agendamentos = new ArrayList<>();
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public ArrayList<Consulta> getAgendamentos() {
        return agendamentos;
    }
    
    /**
     * @param agendamentos ArrayList - Uma lista de Consultas relacionadas ao medico 
     */
    public void setAgendamentos(ArrayList<Consulta> agendamentos) {
        this.agendamentos = agendamentos;
    }

    
    
    
}