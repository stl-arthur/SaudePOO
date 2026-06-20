/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciamento;
import pojos.Paciente;
/**
 *Classe responsável pela modificação de prontuários de pacientes, só é acessada pelo médico, pode cadastrar, alterar e excluir 
 * o prontuário relacionado a um paciente
 * @author arthur
 */
interface GerenciaProntuario {
    
    public void CadastraProntuarioPaciente(Paciente paciente);
    
    public void AlterarProntuario(Paciente paciente);
    

    public void ExcluirProntuario(Paciente paciente);

}
