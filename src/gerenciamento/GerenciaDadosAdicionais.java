/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import pojos.Paciente;

/**
 * Classe responsavel pela modificação das informações adicionais de um paciente
 * só será acessada pelo usuário médico, a classe tem poder de adicionar, alterar e excluir dados adicionais relacionados à um paciente
 * @author arthur
 * 
 */
interface GerenciaDadosAdicionais {
    
    public void CadastraDadosAdicionais(Paciente paciente);
    
    public void AtualizaDadosAdicionais(Paciente paciente);
    
    public void RemoveDadosAdicionais(Paciente paciente);

}
