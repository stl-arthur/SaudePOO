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
public interface GerarRelatorioConsulta {

    public void gerarRelatorio(ArrayList<Consulta> listaDeConsultas, String dataDeAmanha);
    public boolean datasIguais(String data1, String data2);
}
