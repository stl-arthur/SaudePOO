/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import java.util.ArrayList;
import pojos.Paciente;
import pojos.InfoAdicionais;
import java.util.Scanner;
import pojos.Consulta;
import pojos.Medicos;
import pojos.Prontuario;
import relatorios.GerarRelatorioMedico;

/**
 *
 * @author arthur
 */
public class UserMedico implements GerenciaDadosAdicionais, GerenciaProntuario, GerarRelatorioMedico {
    
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
        
        
        System.out.println("Diagnóstico do paciente:");
        String diagnostico = scan.nextLine();
        pront.setDiagnostico(diagnostico);
        
        
        System.out.println("Prescrição de remédios para o paciente:");
        String prescricao = scan.nextLine();
        pront.setPrescricao(prescricao);
        
        
        paciente.setProntuario(pront);
        System.out.println("Prontuário Cadastrado!");
        
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
    
    // RELÁTORIO
    
    /**
     * Gera Modelo Básico de receita médica
     * @param consulta - Consulta a qual será feita a receita
    */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
