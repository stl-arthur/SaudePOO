/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;


import pojos.Consulta;
import pojos.Medicos;
import pojos.Paciente;
import java.util.ArrayList;

/**
 * Gerencia as operações de cadastro, atualização, remoção e exibição de consultas.
 * @author cintiasb
 */
interface GerenciarConsultas {

    public void cadastrarConsulta(ArrayList<Consulta> listaDeConsultas, Paciente paciente, Medicos medico);
   
    public void atualizarConsulta(Consulta consulta);
    
    public void removerConsulta(ArrayList<Consulta> listaDeConsultas, Consulta consulta); 
}