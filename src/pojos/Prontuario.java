/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;


/**

 * Classe de prontuario, separada de Paciente para melhor controle de acesso para o médico e para que 
 * a classe Paciente fique mais limpa
 * @author Giovana B
 */
public class Prontuario {
    private Paciente paciente;
    private String sintomas;
    private String diagnostico;
    private String prescricao;

    
    public Prontuario() {
    }

    
    public Prontuario(Paciente paciente, String sintomas, String diagnostico, String prescricao) {
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
    }
    
     
    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}