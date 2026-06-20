/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 * Representa uma consulta agendada na clinica.
 * Relaciona um paciente, um médico, data/horario e tipo de consulta
 * @author Giovana B
*/
public class Consulta {
    private String data;
    private String horario;
    private Medicos medico;
    private Paciente paciente;
    private String tipoConsulta;
    
    public Consulta(String data, String horario, Medicos medico, Paciente paciente, String tipoConsulta) {
        this.data = data;
        this.horario = horario;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoConsulta = tipoConsulta;
    }

    
    public Consulta(){}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Medicos getMedico() {
        return medico;
    }

    public void setMedico(Medicos medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipoConsulta() {
        if (tipoConsulta.toUpperCase().equals("N")){
            return "Consulta Normal";
        }
       return "Consulta de Retorno";
    }
    
    
    /**
     * @param tipoConsulta String - um caractere que indica o tipo da consulta n para normal e r para retorno
   */
    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    
}
