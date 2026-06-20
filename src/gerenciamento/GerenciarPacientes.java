package gerenciamento;

import java.util.Scanner;
import pojos.Paciente;
import java.util.ArrayList;

/**
 * 
 * @author cintiasb
 * Classe responsável por gerenciar as operações de cadastro, atualização e remoção de pacientes
 */
interface GerenciarPacientes {

    public void cadastrarPaciente(ArrayList<Paciente> listaDePacientes); 
    
    public void atualizarPaciente(Paciente paciente); 
    
    public void removerPaciente(ArrayList<Paciente> listaDePacientes, Paciente paciente); 
        

}