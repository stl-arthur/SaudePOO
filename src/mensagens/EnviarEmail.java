/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensagens;

/*Slasse filha de enviar mensagens, com o método enviar, que simula o envio de um Email*/
public class EnviarEmail extends EnviarMensagens{
    
    public void enviar(String nomeP, String dataC, String horaC) {
        System.out.println("Email enviado para: " + nomeP);
        System.out.println("Sua consulta é dia " + dataC + " às " + horaC);
    }
}
