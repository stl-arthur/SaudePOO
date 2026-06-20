/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import pojos.Paciente;
import pojos.InfoAdicionais;
import java.util.Scanner;

/**
 * Classe responsavel pela modificação das informações adicionais de um paciente
 * só será acessada pelo usuário médico, a classe tem poder de adicionar, alterar e excluir dados adicionais relacionados à um paciente
 * @author arthur
 * 
 */
public class GerenciaDadosAdicionais {
    
    Scanner scan = new Scanner(System.in);
    
    
    /**
     * Cadastra as informações adicionais de um paciente especificado como parametro do método
     * @param paciente - tem como parametro um paciente, sao utilizados dados adicionais para serem cadastrados ao paciente
     * É passado dado a dado, sendo atribuido valores através do scanner
     * 
    */
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
}
