/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;

import java.util.ArrayList;
import java.util.Scanner;
import pojos.Consulta;
import pojos.Medicos;
import pojos.Paciente;
import relatorios.GerarRelatorioConsulta;

/**
 *
 * @author arthur
 */
public class UserSecretaria implements GerenciarConsultas, GerenciarPacientes, GerarRelatorioConsulta{
    
    Scanner scan = new Scanner(System.in);


    // ========================== GERENCIAMENTO DE CONSULTAS ==========================
    
    /**
     * Cadastra uma nova consulta na lista, vinculando paciente e médico já existentes.
     * @param listaDeConsultas     - lista de consultas do sistema
     * @param paciente - paciente da consulta
     * @param medico - médico da consulta
     */
    @Override
    public void cadastrarConsulta(ArrayList<Consulta> listaDeConsultas, Paciente paciente, Medicos medico) {
        Consulta consulta = new Consulta();
        ArrayList<Consulta> consultasMedico = medico.getAgendamentos();
        
        System.out.println("Data da consulta (dd/mm/aaaa):");
        consulta.setData(scan.nextLine());
        scan.nextLine();
        
        String horario = "";
        boolean conflito = true;
        while (conflito) {
            System.out.println("Horário (hh:mm):");
            horario = scan.nextLine();
            scan.nextLine();

            // aceita direto se não tem consultas agendadas
            if (consultasMedico.isEmpty()) {
                conflito = false;
                break;
            }

            boolean temConflito = false;
            for (Consulta consM : consultasMedico) {
                //só verifica conflito se for no mesmo dia
                if (!consM.getData().equals(consulta.getData())) continue;

                String[] horaStr = consM.getHorario().split(":");
                int inicioMed = Integer.parseInt(horaStr[0]) * 60 + Integer.parseInt(horaStr[1]);
                int fimMed;

                if (consM.getTipoConsulta().equals("Consulta Normal")) { // Normal = 60 min
                    fimMed = inicioMed + 60;
                } else { // Retorno = 30 min
                    fimMed = inicioMed + 30;
                }

                String[] horas = horario.split(":");
                int inicioNovo = Integer.parseInt(horas[0]) * 60 + Integer.parseInt(horas[1]);

                // conflito se o novo horário cai dentro do intervalo da consulta existente
                if (inicioNovo >= inicioMed && inicioNovo < fimMed) {
                    System.out.println("O Dr. estará em consulta nesse horário, selecione outro.");
                    temConflito = true;
                    break;
                }
            }
            if (!temConflito) {
                conflito = false; //sai do while só quando não tem conflito
            }
        }

        consulta.setHorario(horario);

        System.out.println("Tipo de consulta:\n1 - Normal  (60 min)\n2 - Retorno (30 min)");
        String tipo = scan.nextLine();
        //converte "1"/"2" para "n"/"r" que o getTipoConsulta espera
        if (tipo.equals("1")) {
            consulta.setTipoConsulta("n");
        } else {
            consulta.setTipoConsulta("r");
}

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        listaDeConsultas.add(consulta);
        System.out.println("Consulta cadastrada com sucesso!");
    }

    /**
     * Atualiza os dados de uma consulta já existente.
     * Paciente e médico não são alterados aqui.
     * @param consulta - consulta a ser atualizada
     */
    @Override
    public void atualizarConsulta(Consulta consulta) {
        int repete = 1;
        while (repete == 1) {
            System.out.println("""
                    Qual informação deseja alterar?
                    1 - Data
                    2 - Horário
                    3 - Tipo de consulta
                    """);
            int opcao = scan.nextInt();
            scan.nextLine(); //limpa buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.println("Nova data (dd/mm/aaaa):");
                    consulta.setData(scan.nextLine());
                    scan.nextLine();
                    break;
                case 2:
                    System.out.println("Novo horário (hh:mm):");
                    consulta.setHorario(scan.nextLine());
                    break;
                case 3:
                    System.out.println("Tipo:\n1 - Normal (n) (60 min)\n2 - Retorno (r) (30 min)");
                    String tipo = scan.nextLine();
                    consulta.setTipoConsulta(tipo);
                    break;
                default:
                    System.out.println("Opção não listada");
            }

            System.out.println("Deseja alterar mais alguma informação?\n Se sim, digite 1\n Caso contrário, aperte 0.");
            repete = scan.nextInt();
        }
        System.out.println("Consulta atualizada com sucesso!");
    }

    /**
     * Remove uma consulta da lista comparando por referência de objeto.
     * @param listaDeConsultas - lista de consultas do sistema
     * @param consulta - consulta a ser removida
     */
    @Override
    public void removerConsulta(ArrayList<Consulta> listaDeConsultas, Consulta consulta) {
        for (int i = 0; i < listaDeConsultas.size(); i++) {
            if (listaDeConsultas.get(i).equals(consulta)) {
                listaDeConsultas.remove(i);
                System.out.println("Consulta removida com sucesso!");
                return;
            }
        }
        System.out.println("Consulta não encontrada.");
    }
    
    
    // ========================== GERENCIAMENTO DE PACIENTES ==========================
    
    /**
     * Cadastra um novo paciente na lista
     * @param listaDePacientes - lista de pacientes do sistema
     */
    @Override
    public void cadastrarPaciente(ArrayList<Paciente> listaDePacientes) {
        Paciente paciente = new Paciente();

        System.out.println("Nome do paciente:");
        paciente.setNome(scan.nextLine());
       

        System.out.println("Identificação:");
        paciente.setIdentificacao(scan.nextLine());
        

        System.out.println("Data de nascimento (dd/mm/aaaa):");
        paciente.setDtNascimento(scan.nextLine());
        

        System.out.println("Endereço:");
        paciente.setEndereco(scan.nextLine());
        

        System.out.println("Informações de Contato:");
        paciente.setContato(scan.nextLine());
        

        System.out.println("Tipo de convênio:\n0 - Particular\n1 - Plano de Saúde");
        int tipo = scan.nextInt();
        paciente.setTipoConvenio(tipo);
        

        
        listaDePacientes.add(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    /**
     * Atualiza os dados editáveis de um paciente já existente na lista
     * @param paciente - paciente a ser atualizado
     */
    @Override
    public void atualizarPaciente(Paciente paciente) {
        int repete = 1;
        while (repete == 1) {
            System.out.println("""
                    Qual informação deseja alterar?
                    1 - Endereço
                    2 - Contato
                    3 - Tipo de convênio
                    """);
            int opcao = scan.nextInt();
            scan.nextLine(); //limpa buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.println("Novo endereço:");
                    paciente.setEndereco(scan.nextLine());
                    break;
                case 2:
                    System.out.println("Novo Contato:");
                    paciente.setContato(scan.nextLine());
                    break;
                case 3:
                    System.out.println("Tipo de convênio:\n1 - Particular\n2 - Plano de Saúde");
                    int tipo = scan.nextInt();
                    paciente.setTipoConvenio(tipo);
                    break;
                default:
                    System.out.println("Opção não listada");
            }

            System.out.println("Deseja alterar mais alguma informação? \n Se sim, digite 1\n Caso contrário, aperte 0");
            repete = scan.nextInt();
            scan.nextLine(); //limpa buffer do teclado
        }
        System.out.println("Paciente atualizado com sucesso!");
    }

    /**
     * Remove um paciente da lista
     * @param listaDePacientes - lista de pacientes do sistema
     * @param paciente - paciente a ser removido
     */
    @Override
    public void removerPaciente(ArrayList<Paciente> listaDePacientes, Paciente paciente) {
        for (int i = 0; i < listaDePacientes.size(); i++) {
            if (listaDePacientes.get(i).equals(paciente)) {
                listaDePacientes.remove(i);
                System.out.println("Paciente removido com sucesso!");
                return;
            }
        }
        System.out.println("Paciente não encontrado.");
    }
    
    
    //==================== Relatório //====================
    
     /**
     * Gera relatório completo das consultas do dia seguinte,
     * separando pacientes com email, com telefone e sem contato cadastrado.
     * @param listaDeConsultas - lista de consultas do sistema
     * @param dataDeAmanha - data do dia seguinte no formato dd/mm/aaaa
     */
    @Override
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
    @Override
    public boolean datasIguais(String data1, String data2) {
        for (int i = 0; i < data1.length(); i++) {
            if (data1.charAt(i) != data2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}



