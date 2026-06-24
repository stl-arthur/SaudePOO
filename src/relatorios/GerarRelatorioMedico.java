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
public interface GerarRelatorioMedico {
    
    
    /**
     * Gera Modelo Básico de receita médica
     * @param consulta - Consulta a qual será feita a receita
    */
    public void gerarReceita(Consulta consulta);

    /**
     * Gera Modelo Básico de atestado médico
     * @param consulta - Consulta a qual estará relacionado o atestado médico
    */
    public void gerarAtestado(Consulta consulta);
    
    /**
     * Gera Modelo Básico de Declaração de Acompanhamento Médico
     * @param consulta - consulta a qual estará relacionado a declaração de acompanhamento
    */
    public void gerarDeclaracao(Consulta consulta);
    
    
    /**
     * Gera o relatório de clientes atendidos no mês, printa os dados das consultas realizadas nos dias anteriores do mês atual
     * @param medico - Traz as consultas feitas pelo médico especificado
     * @param data - Data do dia atual
     */
    public void gerarRelatorioClientesMes(Medicos medico, String data);
    

}
