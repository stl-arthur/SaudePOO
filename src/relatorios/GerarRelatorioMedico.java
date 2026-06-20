/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorios;
import pojos.Medicos;
import pojos.Consulta;
import java.util.ArrayList;
/**
 *Classe responsável por gerar os relatórios médicos
 * @author arthur
 */
public class GerarRelatorioMedico {
    
    
    /**
     * Gera Modelo Básico de receita médica
     * @param consulta - Consulta a qual será feita a receita
    */
    public void gerarReceita(Consulta consulta){
        System.out.println(" --------------------------------------- ");
        System.out.println("----- CLINICA MÉDICA SAÚDE&CIA ----");
        System.out.println("Médico: " + consulta.getMedico().getNome());
        System.out.println("CRM: " + consulta.getMedico().getCrm());
        System.out.println(" -------------- RECEITA -------------- ");
        System.out.println("Paciente: " + consulta.getPaciente().getNome());
        System.out.println("Prescrição: " + consulta.getPaciente().getProntuario().getPrescricao());
        System.out.println(" --------------------------------------- ");
        System.out.println("\n --------------------------------------- ");
        System.out.println("Assinatura do Médico");
    }
    
    /**
     * Gera Modelo Básico de atestado médico
     * @param consulta - Consulta a qual estará relacionado o atestado médico
    */
    public void gerarAtestado(Consulta consulta){
        System.out.println(" --------------------------------------- ");
        System.out.println("----- CLINICA MÉDICA SAÚDE&CIA ----");
        System.out.println("Médico: " + consulta.getMedico().getNome());
        System.out.println("CRM: " + consulta.getMedico().getCrm());
        System.out.println(" -------------- ATESTADO -------------- ");
        System.out.println("Atesto, para os devidos fins, a pedido do interessado, portador do CPF " + consulta.getPaciente().getIdentificacao());
        System.out.println(", foi submetido à consulta médica nesta data, no horário das " + consulta.getHorario() + " horas");
        System.out.println(", sendo portador da afecção " + consulta.getPaciente().getProntuario().getDiagnostico());
        System.out.println("Em decorrência, deverá permanecer afastado por ________ dias, a partir desta data " + consulta.getData());
        System.out.println("");
        System.out.println(" --------------------------------------- ");
        System.out.println("\n --------------------------------------- ");
        System.out.println("Assinatura do Médico");
    }
    
    /**
     * Gera Modelo Básico de Declaração de Acompanhamento Médico
     * @param consulta - consulta a qual estará relacionado a declaração de acompanhamento
    */
    public void gerarDeclaracao(Consulta consulta){
        System.out.println(" --------------------------------------- ");
        System.out.println("----- CLINICA MÉDICA SAÚDE&CIA ----");
        System.out.println("Médico: " + consulta.getMedico().getNome());
        System.out.println("CRM: " + consulta.getMedico().getCrm());
        System.out.println(" -------------- DECLARAÇÃO DE ACOMPANHAMENTO -------------- ");
        System.out.println("Atesto, para os devidos fins, a pedido de ___________________________, portador do CPF ______________" );
        System.out.println(", esteve acompanhando a paciente " + consulta.getPaciente().getNome());
        System.out.println(", a foi submetida à consulta médica nesta data, no horário das " + consulta.getHorario() + " horas");
        System.out.println("");
        System.out.println(" --------------------------------------- ");
        System.out.println("\n --------------------------------------- ");
        System.out.println("Assinatura do Médico");
    }
    
    
    /**
     * Gera o relatório de clientes atendidos no mês, printa os dados das consultas realizadas nos dias anteriores do mês atual
     * @param medico - Traz as consultas feitas pelo médico especificado
     * @param data - Data do dia atual
     */
    public void gerarRelatorioClientesMes(Medicos medico, String data){
        
        ArrayList<Consulta> consultas = medico.getAgendamentos();
        
        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int clientesAtendidos = 0;
        for (Consulta cons: consultas){
            String[] dataConsulta = cons.getData().split("/");
            int diaCons = Integer.parseInt(dataConsulta[0]);
            int mesCons = Integer.parseInt(dataConsulta[1]);
            
            if ((diaCons < dia) && (mesCons == mes)){
                System.out.println("Consulta de: " + cons.getPaciente().getNome());
                System.out.println("Data: "+ cons.getData());
                System.out.println("Horário: "+ cons.getHorario());
                System.out.println("Tipo de consulta: " + cons.getTipoConsulta());
                System.out.println("Tipo de convenio: " + cons.getPaciente().getTipoConvenio());
                System.out.println("Sintomas: " + cons.getPaciente().getProntuario().getSintomas());
                System.out.println("Diagnóstico: " + cons.getPaciente().getProntuario().getDiagnostico());
                System.out.println("Prescrição: " + cons.getPaciente().getProntuario().getPrescricao());
                clientesAtendidos++;
            }
        }
        
        System.out.println("Total de pacientes atendidos no mês " + mes + ": " + clientesAtendidos);
        
    }
    
    
    
}
