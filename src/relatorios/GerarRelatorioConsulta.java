/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorios;

import java.util.ArrayList;
import pojos.Consulta;

/**
 * Classe responsável por gerar os relatórios para consultas do dia seguinte, filtra os pacientes por email/telefone
 * @author cintiasb
 * 
 */
public class GerarRelatorioConsulta {

    /**
     * Gera relatório completo das consultas do dia seguinte,
     * separando pacientes com email, com telefone e sem contato cadastrado.
     * @param listaDeConsultas - lista de consultas do sistema
     * @param dataDeAmanha - data do dia seguinte no formato dd/mm/aaaa
     */
    public void gerarRelatorio(ArrayList<Consulta> listaDeConsultas, String dataDeAmanha) {

        System.out.println("CONSULTAS DO DIA " + dataDeAmanha);

        System.out.println("\n-- Notificar por EMAIL --");
        for (int i = 0; i < listaDeConsultas.size(); i++) {
            Consulta consulta = listaDeConsultas.get(i);
            if (datasIguais(consulta.getData(), dataDeAmanha)) {
                String contato = consulta.getPaciente().getContato();
                if (contato.charAt(0) == 'E') {
                    System.out.println("- " + consulta.getPaciente().getNome() +
                            " | " + consulta.getHorario() +
                            " | " + consulta.getTipoConsulta() +
                            " | " + contato);
                }
            }
        }
       
        System.out.println("\n-- Notificar por SMS --");
        for (int i = 0; i < listaDeConsultas.size(); i++) {
            Consulta consulta = listaDeConsultas.get(i);
            if (datasIguais(consulta.getData(), dataDeAmanha)) {
                String contato = consulta.getPaciente().getContato();
                if (contato.charAt(0) == 'T') {
                    System.out.println("- " + consulta.getPaciente().getNome() +
                            " | " + consulta.getHorario() +
                            " | " + consulta.getTipoConsulta() +
                            " | " + contato);
                }
            }
        }

    }

    /**
     * Metodo Axuiliar: Verifica se duas datas no formato dd/mm/aaaa são iguais,
     * @param data1 - primeira data
     * @param data2 - segunda data
     * @return true se as datas forem iguais, false caso contrário
     */
    private boolean datasIguais(String data1, String data2) {
        for (int i = 0; i < data1.length(); i++) {
            if (data1.charAt(i) != data2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
