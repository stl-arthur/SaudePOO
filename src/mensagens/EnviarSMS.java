/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensagens;

/*Classe filha de enviar mensagens, com o método enviar, que simula o envio de um SMS*/
public class EnviarSMS extends EnviarMensagens{

    public void enviar(String nomeP, String dataC, String horaC) {
        System.out.println("SMS enviado para: " + nomeP);
        System.out.println("Sua consulta é dia " + dataC + " às " + horaC);
    }
}