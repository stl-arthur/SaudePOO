/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import pojos.Paciente;
import pojos.Consulta;
import pojos.Medicos;
import java.util.ArrayList;
import java.util.Scanner;
import mensagens.EnviarEmail;
import mensagens.EnviarMensagens;
import mensagens.EnviarSMS;

/**
 *Classe principal, controla o acesso por perfil (secretaria ou mdico) e chama as classes de gerenciamento de acordo com a opçao do usuario.
 */
public class Principal {
    public static void main(String[] args){
        
        Scanner leitura = new Scanner(System.in);
        
        ArrayList<Paciente> lstPacientes = new ArrayList<>();
        ArrayList<Consulta> lstConsultas = new ArrayList<>();
        ArrayList<Medicos> lstMedicos = new ArrayList<>();
        
        Medicos medico = new Medicos("Dr. Carlos Silva", "CRM-12345", "Clinico Geral"); //medico fixo para teste
        lstMedicos.add(medico);
        medico.setAgendamentos(lstConsultas);
        
        
        UserMedico usuarioMedico = new UserMedico();
        UserSecretaria usuarioSecretaria = new UserSecretaria();
        GerarRelatorioConsulta relConsulta  = new GerarRelatorioConsulta();
        GerarRelatorioMedico relMedico      = new GerarRelatorioMedico();
        
        int usuario = -1;
        while (usuario != 0) {

            System.out.println("\n========================================");
            System.out.println("     CLINICA SAUDE & CIA");
            System.out.println("========================================");
            System.out.println("1 - Secretaria");
            System.out.println("2 - Medico");
            System.out.println("0 - Sair");
            System.out.print("Escolha o perfil de acesso: ");
            usuario = leitura.nextInt();
            leitura.nextLine();

            if (usuario == 1) {
                menuSecretaria(leitura, lstPacientes, lstConsultas,
                               lstMedicos, usuarioSecretaria, relConsulta);

            } else if (usuario == 2) {
                menuMedico(leitura, lstPacientes, lstConsultas,
                           usuarioMedico, relMedico, medico);
            }
        }

        System.out.println("Sistema encerrado.");
        leitura.close();
    }
  
    /**
     * mostra o menu da secretaria e controla as opcoes disponiveis.
     */
    static void menuSecretaria(Scanner leitura, ArrayList<Paciente> lstPacientes,
                               ArrayList<Consulta> lstConsultas, ArrayList<Medicos> lstMedicos,
                               UserSecretaria usuarioSecretaria,GerarRelatorioConsulta relConsulta ) {
        int opcao = -1;
        while (opcao != 4) {

            System.out.println("\n========================================");
            System.out.println("     SECRETARIA");
            System.out.println("========================================");
            System.out.println("1 - Gerenciar pacientes");
            System.out.println("2 - Gerar relatório de consultas");
            System.out.println("3 - Gerenciar consultas");
            System.out.println("4 - Alterar usuário");
            System.out.print("Opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            if (opcao == 1) {
                menuGerenciarPacientes(leitura, lstPacientes, usuarioSecretaria);

            } else if (opcao == 2) {
                menuRelatorioConsultas(leitura, lstConsultas, relConsulta);

            } else if (opcao == 3) {
                menuGerenciarConsultas(leitura, lstPacientes, lstConsultas,
                                       lstMedicos, usuarioSecretaria);
            }
        }
    }

 

    /**
     * gerenciamento de pacientes da secretaria com opcoes de cadastrar, alterar, remover.
     */
    static void menuGerenciarPacientes(Scanner leitura, ArrayList<Paciente> lstPacientes,
                                       UserSecretaria usuarioSecretaria) {
        int opcao = -1;
        while (opcao != 4) {

            System.out.println("\n--- Gerenciar Pacientes ---");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Alterar paciente");
            System.out.println("3 - Excluir paciente");
            System.out.println("4 - Voltar");
            System.out.print("Opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            if (opcao == 1) {
                usuarioSecretaria.cadastrarPaciente(lstPacientes);

            } else if (opcao == 2) {
                Paciente paciente = selecionarPaciente(leitura, lstPacientes);
                if (paciente != null) {
                    usuarioSecretaria.atualizarPaciente(paciente);
                }

            } else if (opcao == 3) {
                Paciente paciente = selecionarPaciente(leitura, lstPacientes);
                if (paciente != null) {
                    usuarioSecretaria.removerPaciente(lstPacientes, paciente);
                }
            }
        }
    }


    /**
     * Gera o relatorio das consultas do dia seguinte
     */
    static void menuRelatorioConsultas(Scanner leitura, ArrayList<Consulta> lstConsultas,
                                       GerarRelatorioConsulta relConsulta) {
        System.out.print("\nInforme a data do dia seguinte (dd/mm/aaaa): ");
        String dataAmanha = leitura.nextLine();

        relConsulta.gerarRelatorio(lstConsultas, dataAmanha);

        // envia lembretes via email ou sms para cada consulta do dia seguinte
        System.out.println("\n--- Enviando lembretes ---");
        for (int i = 0; i < lstConsultas.size(); i++) {
            Consulta consulta = lstConsultas.get(i);

            // verifica se a data da consulta e igual a data informada
            if (consulta.getData().equals(dataAmanha)) {
                String contato = consulta.getPaciente().getContato();
                EnviarMensagens msg;

                // se o contato comeca com 'E' e email, senao e telefone (SMS)
                if (contato.charAt(0) == 'E') {
                    msg = new EnviarEmail();
                } else {
                    msg = new EnviarSMS();
                }

                msg.enviar(
                    consulta.getPaciente().getNome(),
                    consulta.getData(),
                    consulta.getHorario()
                );
            }
        }
    }

    /**
     * gerenciamento de consultas da secretaria, com opcoes de agendar, alterar, remover.
     */
    static void menuGerenciarConsultas(Scanner scan, ArrayList<Paciente> listaDePacientes,
                                       ArrayList<Consulta> listaDeConsultas, ArrayList<Medicos> listaDeMedicos,
                                       UserSecretaria usuarioSecretaria) {
        int opcao = -1;
        while (opcao != 4) {

            System.out.println("\n--- Gerenciar Consultas ---");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Alterar consulta");
            System.out.println("3 - Excluir consulta");
            System.out.println("4 - Voltar");
            System.out.print("Opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            if (opcao == 1) {
                Paciente paciente = selecionarPaciente(scan, listaDePacientes);
                Medicos medico = selecionarMedico(scan, listaDeMedicos);
                if (paciente != null) {
                    usuarioSecretaria.cadastrarConsulta(listaDeConsultas, paciente, medico);
                    //adiciona a consulta tambem nos agendamentos do medico
                    medico.setAgendamentos(listaDeConsultas);
                }

            } else if (opcao == 2) {
                Consulta consulta = selecionarConsulta(scan, listaDeConsultas);
                if (consulta != null) {
                    usuarioSecretaria.atualizarConsulta(consulta);
                }

            } else if (opcao == 3) {
                Consulta consulta = selecionarConsulta(scan, listaDeConsultas);
                if (consulta != null) {
                    usuarioSecretaria.removerConsulta(listaDeConsultas, consulta);
                }
            }
        }
    }

 
    /**
     * Exibe o menu do medico e controla as opcoes disponiveis.
     * Permanece no loop ate o usuario escolher alterar usuario (opcao 4).
     */
    static void menuMedico(Scanner scan, ArrayList<Paciente> listaDePacientes,
                           ArrayList<Consulta> listaDeConsultas,
                           UserMedico usuarioMedico,
                           GerarRelatorioMedico relMedico, Medicos medico) {
        int opcao = -1;
        while (opcao != 4) {

            System.out.println("\n========================================");
            System.out.println("     MÉDICO");
            System.out.println("========================================");
            System.out.println("1 - Gerenciar dados adicionais do paciente");
            System.out.println("2 - Gerenciar prontuario");
            System.out.println("3 - Gerar relatorio medico");
            System.out.println("4 - Alterar usuario");
            System.out.print("Opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            if (opcao == 1) {
                menuDadosAdicionais(scan, listaDePacientes, usuarioMedico);

            } else if (opcao == 2) {
                menuProntuario(scan, listaDePacientes, usuarioMedico);

            } else if (opcao == 3) {
                menuRelatorioMedico(scan, listaDeConsultas, relMedico, medico);
            }
        }
    }

    /**
     * gerenciamento dos dados adicionais de saude do paciente, acesso exclusivo do medico.
     */
    static void menuDadosAdicionais(Scanner scan, ArrayList<Paciente> listaDePacientes,
                                    UserMedico usuarioMedico) {
        int opcao = -1;
        while (opcao != 4) {

            System.out.println("\n--- Dados Adicionais do Paciente ---");
            System.out.println("1 - Cadastrar dados de saude");
            System.out.println("2 - Alterar dados de saude");
            System.out.println("3 - Excluir dados de saude");
            System.out.println("4 - Voltar");
            System.out.print("Opção: ");
            opcao = scan.nextInt();
            scan.nextLine();

            if (opcao == 1) {
                Paciente paciente = selecionarPaciente(scan, listaDePacientes);
                if (paciente != null) {
                    usuarioMedico.CadastraDadosAdicionais(paciente);
                }

            } else if (opcao == 2) {
                Paciente paciente = selecionarPaciente(scan, listaDePacientes);
                if (paciente != null) {
                    if (paciente.getAdicionais() == null) {
                        System.out.println("Este paciente não tem dados adicionais cadastrados.");
                    } else {
                        usuarioMedico.AtualizaDadosAdicionais(paciente);
                    }
                }

            } else if (opcao == 3) {
                Paciente paciente = selecionarPaciente(scan, listaDePacientes);
                if (paciente != null) {
                    if (paciente.getAdicionais() == null) {
                        System.out.println("Este paciente não tem dados adicionais cadastrados.");
                    } else {
                        usuarioMedico.RemoveDadosAdicionais(paciente);
                    }
                }
            }
        }
    }


    /**gerenciamento de prontuarios com acesso exclusivo do medico.
     */
    static void menuProntuario(Scanner leitura, ArrayList<Paciente> lstPacientes,
                               UserMedico usuarioMedico) {
        int opcao = -1;
        while (opcao != 4) {

            System.out.println("\n--- Gerenciar Prontuario ---");
            System.out.println("1 - Cadastrar prontuario");
            System.out.println("2 - Alterar prontuario");
            System.out.println("3 - Excluir prontuario");
            System.out.println("4 - Voltar");
            System.out.print("Opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            if (opcao == 1) {
                Paciente paciente = selecionarPaciente(leitura, lstPacientes);
                if (paciente != null) {
                    usuarioMedico.CadastraProntuarioPaciente(paciente);
                }

            } else if (opcao == 2) {
                Paciente paciente = selecionarPaciente(leitura, lstPacientes);
                if (paciente != null) {
                    if (paciente.getProntuario() == null) {
                        System.out.println("Este paciente não tem prontuario cadastrado.");
                    } else {
                        usuarioMedico.AlterarProntuario(paciente);
                    }
                }

            } else if (opcao == 3) {
                Paciente paciente = selecionarPaciente(leitura, lstPacientes);
                if (paciente != null) {
                    if (paciente.getProntuario() == null) {
                        System.out.println("Este paciente não tem prontuario cadastrado.");
                    } else {
                        usuarioMedico.ExcluirProntuario(paciente);
                    }
                }
            }
        }
    }



    /**
     * geracao de relatorios medicos com opcoes de receita, atestado, declaracao, clientes do mes.
     */
    static void menuRelatorioMedico(Scanner leitura, ArrayList<Consulta> lstConsultas,
                                    GerarRelatorioMedico relMedico, Medicos medico) {
        int opcao = -1;
        while (opcao != 5) {

            System.out.println("\n--- Relatórios Médicos ---");
            System.out.println("1 - Gerar receita");
            System.out.println("2 - Gerar atestado");
            System.out.println("3 - Gerar declaração de acompanhamento");
            System.out.println("4 - Clientes atendidos no mês");
            System.out.println("5 - Voltar");
            System.out.print("Opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            if (opcao == 1) {
                Consulta consulta = selecionarConsulta(leitura, lstConsultas);
                if (consulta != null) {
                    if (consulta.getPaciente().getProntuario() == null) {
                        System.out.println("Paciente sem prontuário cadastrado.");
                    } else {
                        relMedico.gerarReceita(consulta);
                    }
                }

            } else if (opcao == 2) {
                Consulta consulta = selecionarConsulta(leitura, lstConsultas);
                if (consulta != null) {
                    if (consulta.getPaciente().getProntuario() == null) {
                        System.out.println("Paciente sem prontuário cadastrado.");
                    } else {
                        relMedico.gerarAtestado(consulta);
                    }
                }

            } else if (opcao == 3) {
                Consulta consulta = selecionarConsulta(leitura, lstConsultas);
                if (consulta != null) {
                    relMedico.gerarDeclaracao(consulta);
                }

            } else if (opcao == 4) {
                System.out.print("Informe a data atual (dd/mm/aaaa): ");
                String dataAtual = leitura.nextLine();
                relMedico.gerarRelatorioClientesMes(medico, dataAtual);
            }
        }
    }

   
    /**
     * Exibe a lista de pacientes e permite selecionar um pelo numero.
     * @param leitura scanner compartilhado
     * @param lstPacientes lista de pacientes do sistema
     * @return paciente selecionado ou null se a lista estiver vazia
     */
    static Paciente selecionarPaciente(Scanner leitura, ArrayList<Paciente>  lstPacientes) {
        if ( lstPacientes.size() == 0) {
            System.out.println("\"Não há pacientes cadastrados.");
            return null;
        }

        System.out.println("\n-- Selecione o paciente --");
        for (int i = 0; i <  lstPacientes.size(); i++) {
            System.out.println((i + 1) + " - " +  lstPacientes.get(i).getNome()
                + " | " +  lstPacientes.get(i).getIdentificacao());
        }

        System.out.print("Número do paciente: ");
        int num = leitura.nextInt();
        leitura.nextLine();

        if (num < 1 || num >  lstPacientes.size()) {
            System.out.println("Opção invalida.");
            return null;
        }

        return  lstPacientes.get(num - 1);
    }
    
    
     /**
     * Exibe a lista de médicos e permite selecionar um pelo numero.
     * @param leitura scanner compartilhado
     * @param lstMedicos lista de médicos do sistema
     * @return medico selecionado ou null se a lista estiver vazia
     */
    static Medicos selecionarMedico(Scanner leitura, ArrayList<Medicos> lstMedicos){
        if (lstMedicos.isEmpty()){
            System.out.println("\"Não há médicos cadastrados.");
            return null;
        }
        System.out.println("\n-- Selecione o médico --");
        for (int i = 0; i < lstMedicos.size(); i++){
            System.out.println((i + 1) + " - " + lstMedicos.get(i).getNome()
             + " | " + lstMedicos.get(i).getCrm());
        }
        
        System.out.println("Número do médico: ");
        int num = leitura.nextInt();
        leitura.nextLine();
        
        if (num < 1 || num > lstMedicos.size()){
            System.out.println("Opção inválida.");
            return null;
        }
        return lstMedicos.get(num-1);
    }

    /**
     * Exibe a lista de consultas e permite selecionar uma pelo numero.
     * @param leitura scanner compartilhado
     * @param lstConsultas lista de consultas do sistema
     * @return consulta selecionada ou null se a lista estiver vazia
     */
    static Consulta selecionarConsulta(Scanner leitura, ArrayList<Consulta> lstConsultas) {
        if (lstConsultas.size() == 0) {
            System.out.println("Nenhuma consulta cadastrada.");
            return null;
        }

        System.out.println("\n-- Selecione a consulta --");
        for (int i = 0; i < lstConsultas.size(); i++) {
            Consulta c = lstConsultas.get(i);
            System.out.println((i + 1) + " - " + c.getData()
                + " | " + c.getHorario()
                + " | " + c.getPaciente().getNome()
                + " | " + c.getTipoConsulta());
        }

        System.out.print("Numero da consulta: ");
        int num = leitura.nextInt();
        leitura.nextLine();

        if (num < 1 || num > lstConsultas.size()) {
            System.out.println("Opção invalida.");
            return null;
        }

        return lstConsultas.get(num - 1);
    }
}