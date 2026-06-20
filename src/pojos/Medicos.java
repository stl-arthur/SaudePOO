/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;
import java.util.ArrayList;
/**
 * @author Giovana B
*/

public class Medicos {
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