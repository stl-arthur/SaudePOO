/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;

import java.util.Scanner;
import pojos.Consulta;
import pojos.Medicos;
import pojos.Paciente;
import java.util.ArrayList;

/**
 * Gerencia as operações de cadastro, atualização, remoção e exibição de consultas.
 * @author cintiasb
 */
public class GerenciarConsultas {
    Scanner scan = new Scanner(System.in);

    public GerenciarConsultas() {}

    /**
     * Cadastra uma nova consulta na lista, vinculando paciente e médico já existentes.
     * @param listaDeConsultas     - lista de consultas do sistema
     * @param paciente - paciente da consulta
     * @param medico - médico da consulta
     */
    public void cadastrarConsulta(ArrayList<Consulta> listaDeConsultas, Paciente paciente, Medicos medico) {
        Consulta consulta = new Consulta();
        ArrayList<Consulta> consultasMedico = medico.getAgendamentos();
        
        System.out.println("Data da consulta (dd/mm/aaaa):");
        consulta.setData(scan.nextLine());
        
        String horario = "";
        boolean conflito = true;
        while (conflito) {
            System.out.println("Horário (hh:mm):");
            horario = scan.nextLine();

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
                    consulta.setData(scan.next());
                    break;
                case 2:
                    System.out.println("Novo horário (hh:mm):");
                    consulta.setHorario(scan.next());
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
}