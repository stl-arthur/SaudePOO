/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensagens;
/*Classe abstrata que não pode ser instanciada diretamente, ela vai servir de modelo para as classes filhas, tem apenas um método abstrato chamado enviar, que obriga as classes filhas a implementarem do seu jeito*/
public abstract class EnviarMensagens {
    
    public abstract void enviar(String nomeP, String dataC, String horaC);
      
}