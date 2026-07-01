/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import mensagens.EnviarEmail;
import mensagens.EnviarMensagens;
import mensagens.EnviarSMS;
import pojos.Consulta;
import java.util.ArrayList;

/**
 *
 * @author arthur
 */
public class UserGerenciadorMsg {
    
    public void enviarMensagem(ArrayList<Consulta> lstConsultas, String dataAmanha){
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
}
