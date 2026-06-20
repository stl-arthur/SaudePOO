/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import pojos.Prontuario;
import pojos.Paciente;
import java.util.Scanner;
/**
 *Classe responsável pela modificação de prontuários de pacientes, só é acessada pelo médico, pode cadastrar, alterar e excluir 
 * o prontuário relacionado a um paciente
 * @author arthur
 */
public class GerenciaProntuario {
    Scanner scan = new Scanner(System.in);
    
    /**
     * Cria um prontuario para o paciente especificado
     * @param paciente - Paciente a ter um prontuario cadastrado
    */
    public void CadastraProntuarioPaciente(Paciente paciente){
        Prontuario pront = new Prontuario();
        
        System.out.println("Síntomas apresentados pelo paciente:");
        String sintoma = scan.nextLine();
        pront.setSintomas(sintoma);
        scan.nextLine(); //limpa buffer do teclado
        
        System.out.println("Diagnóstico do paciente:");
        String diagnostico = scan.nextLine();
        pront.setDiagnostico(diagnostico);
        scan.nextLine(); //limpa buffer do teclado
        
        System.out.println("Prescrição de remédios para o paciente:");
        String prescricao = scan.nextLine();
        pront.setPrescricao(prescricao);
        scan.nextLine(); //limpa buffer do teclado
        
        paciente.setProntuario(pront);
        System.out.println("Prontuário Cadastrado!");
        scan.nextLine(); //limpa buffer do teclado
    }
    
    
    /**
     * Altera o prontuário de um paciente especificado
     * @param paciente - entrada com um paciente para que seu prontuário seja alterado pelo médico
    */
    public void AlterarProntuario(Paciente paciente){
        int repete = 1;
        while (repete ==  1){
            System.out.println("""
                               Qual informações do prontuário você deseja alterar:
                               1 - Síntomas
                               2 - Diagnóstico
                               3 - Prescrição
                               """);
            int opcao = scan.nextInt();
            scan.nextLine(); //limpa buffer do teclado
            
            switch(opcao){
                case 1:
                    System.out.println("Síntomas apresentados pelo paciente:");
                    String sintoma = scan.nextLine();
                    paciente.getProntuario().setSintomas(sintoma);
                    break;
                case 2:
                    System.out.println("Diagnóstico do paciente:");
                    String diagnostico = scan.nextLine();
                    paciente.getProntuario().setDiagnostico(diagnostico);
                    break;
                case 3:
                    System.out.println("Prescrição de remédios para o paciente:");
                    String prescicao = scan.nextLine();
                    paciente.getProntuario().setPrescricao(prescicao);
                    break;
                default:
                    System.out.println("Opção não listada");
            }
            System.out.println("Deseja alterar mais alguma informação do prontuário? (1 ou 0)");
            repete = scan.nextInt();
            scan.nextLine(); //limpa buffer do teclado
        }
    }
    
    
    /**
     * Exclui informações do prontuário tornando elas em String vazia
     * @param paciente - Tem como entrada um paciente, são excluídos os dados do prontuário do paciente indicado
    */
    public void ExcluirProntuario(Paciente paciente){
        System.out.println("""
                           Qual informações do prontuário você deseja Excluir:
                           1 - Síntomas
                           2 - Diagnóstico
                           3 - Prescrição
                           4 - Todas
                           """);
        int opcao = scan.nextInt();
        scan.nextLine(); //limpa buffer do teclado

        switch(opcao){
            case 1:
                paciente.getProntuario().setSintomas("");
                System.out.println("Síntomas Excluídos com sucesso!");
                break;
            case 2:
                paciente.getProntuario().setDiagnostico("");
                System.out.println("Diagnóstico Excluído com sucesso!");
                break;
            case 3:
                paciente.getProntuario().setPrescricao("");
                System.out.println("Prescrição Excluída com sucesso!");
                break;
            case 4:
                Prontuario pront = new Prontuario();
                paciente.setProntuario(pront);
                System.out.println("Prontuário Excluído com sucesso");
            default:
                System.out.println("Opção não listada");
        }
    }
}
