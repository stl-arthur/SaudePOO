/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import pojos.Paciente;
import pojos.InfoAdicionais;
import java.util.Scanner;
import pojos.Prontuario;

/**
 *
 * @author arthur
 */
public class UserMedico extends GerarRelatorioMedico implements GerenciaDadosAdicionais, GerenciaProntuario{
    
    Scanner scan = new Scanner(System.in);
    
    
    // ========================== GERENCIAMENTO DE DADOS ADICIONAIS ==========================
    
    /**
     * Cadastra as informações adicionais de um paciente especificado como parametro do método
     * @param paciente - tem como parametro um paciente, sao utilizados dados adicionais para serem cadastrados ao paciente
     * É passado dado a dado, sendo atribuido valores através do scanner
     * 
    */
    @Override
    public void CadastraDadosAdicionais(Paciente paciente){
        InfoAdicionais info = new InfoAdicionais();
        
        System.out.println("Cadastro de Informações adicionais de " + paciente.getNome());
        
        System.out.println("O paciente fuma? (s ou n)");
        String opcao = scan.nextLine();
        
        if (opcao.toUpperCase().equals("S")){
            info.setFuma(true);
        } else{
            info.setFuma(false);
        }
        
        System.out.println("O paciente bebe? (s ou n)");
        opcao = scan.nextLine();
        
        if (opcao.toUpperCase().equals("S")){
            info.setBebe(true);
        } else{
            info.setBebe(false);
        }
        
        System.out.println("O paciente tem colesterol alto? (s ou n)");
        opcao = scan.nextLine();
        
        if (opcao.toUpperCase().equals("S")){
            info.setColesterol(true);
        } else{
            info.setColesterol(false);
        }
        
        System.out.println("O paciente tem Doenças cardíacas? (s ou n)");
        opcao = scan.nextLine();
        
        if (opcao.toUpperCase().equals("S")){
            info.setDoencaCardiaca(true);
        } else{
            info.setDoencaCardiaca(false);
        }
        
        System.out.println("Cirurgias:");
        opcao = scan.nextLine();
        info.setCirurgia(opcao);
        
        System.out.println("Alergias:");
        opcao = scan.nextLine();
        info.setAlergia(opcao);
        
        paciente.setAdicionais(info);
        
        System.out.println("Dados Adicionais Cadastrados!!");
    }
    
    
    /**
     * Atualiza os dados de um paciente, os dados a serem modificados são apontados pelo médico através do menu de escolha dentro do método
     * @param paciente - recebe como parametro um paciente, e diretamente através dos dados dele, os mesmos são alterados,
     * os dados booleanos são alterados diretamente apenas com a negação do valor anteriormente inserido,
     * dados de texto são atualizados com valores passados através do scanner pelo user
    */
    @Override
    public void AtualizaDadosAdicionais(Paciente paciente){
        InfoAdicionais info = paciente.getAdicionais();
        int repete = 1;
        while (repete == 1){
        
            System.out.println("Que dado você deseja alterar?");
            System.out.println("""
                               1 - Fuma
                               2 - Bebe
                               3 - Colesterol Alto
                               4 - Doencas Cardiacas
                               5 - Cirurgias
                               6 - Alergias
                               """);

            int opcao = scan.nextInt();
            scan.nextLine(); //Limpa o buffer do teclado

            switch(opcao){
                case 1:
                    paciente.getAdicionais().setFuma(!(info.isFuma()));
                    System.out.println("Informação Alterada com Sucesso!");
                    break;
                case 2:
                    paciente.getAdicionais().setBebe(!(info.isBebe()));
                    System.out.println("Informação Alterada com Sucesso!");
                    break;
                case 3:
                    paciente.getAdicionais().setColesterol(!(info.isColesterol()));
                    System.out.println("Informação Alterada com Sucesso!");
                    break;
                case 4:
                    paciente.getAdicionais().setDoencaCardiaca(!(info.isDoencaCardiaca()));
                    System.out.println("Informação Alterada com Sucesso!");
                    break;
                case 5:
                    System.out.println("Alteração de Cirurgias que o paciente fez:");
                    String cirurgias = scan.nextLine();
                    paciente.getAdicionais().setCirurgia(cirurgias);
                    System.out.println("Informação Alterada com Sucesso!");
                    break;
                case 6:
                    System.out.println("Alteração de Alergias listadas pelo paciente:");
                    String alergias = scan.nextLine();
                    paciente.getAdicionais().setAlergia(alergias);
                    System.out.println("Informação Alterada com Sucesso!");
                    break;
                default:
                    System.out.println("Opcao não listada");

            }
            
            System.out.println("Deseja alterar mais algum dado? (1 ou 0)");
            repete = scan.nextInt();
            scan.nextLine(); //limpa o buffer do teclado
        }
    }
    
    
    /**
     * Exclui as informações textuais contidas nas informações adicionais do paciente, também pode excluir todas as 
     * informações adicionais contidas pelo paciente
     * @param paciente - entra com um objeto paciente, retorna opção de excluir as cirurgias cadastradas, alergias,
     * ou então a exclusão de todas as informações adicionais do paciente
    */
    @Override
    public void RemoveDadosAdicionais(Paciente paciente){
        System.out.println("Que dado você deseja remover?");
        System.out.println("""
                           1 - Cirurgias
                           2 - Alergias
                           3 - Remover Tudo
                           """);

        int opcao = scan.nextInt();
        scan.nextLine(); //Limpa o buffer do teclado

        switch(opcao){
            case 1:
                System.out.println("Exclusão de Cirurgias que o paciente fez:");
                paciente.getAdicionais().setCirurgia("");
                System.out.println("Informação Excluída com Sucesso!");
                break;
            case 2:
                System.out.println("Exclusão de Alergias listadas pelo paciente:");
                paciente.getAdicionais().setAlergia("");
                System.out.println("Informação Excluída com Sucesso!");
                break;
            case 3:
                InfoAdicionais infoVazia = new InfoAdicionais();
                paciente.setAdicionais(infoVazia);
                System.out.println("Informações Excluídas com sucesso");
                break;
            default:
                System.out.println("Opcao não listada");

            }
            
        
    }
    
    //========================== GERENCIAMENTO DE PRONTUARIOS ==========================
    
    /**
     * Cria um prontuario para o paciente especificado
     * @param paciente - Paciente a ter um prontuario cadastrado
    */
    @Override
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
    @Override
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
    @Override
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
