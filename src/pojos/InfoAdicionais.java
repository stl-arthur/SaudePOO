/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;
/**

 * Classe de informações adicionais, separada do cliente para melhor controle de acesso por parte do médico e também 
 * para que a classe de Paciente fique mais limpa
 */
public class InfoAdicionais {
    private Paciente paciente;
    private boolean fuma,bebe,colesterol,doencaCardiaca;
    private String cirurgia;
    private String alergia;
    
    
    public InfoAdicionais(){
    }

    public InfoAdicionais(Paciente paciente, boolean fuma,boolean bebe,boolean colesterol, boolean doencaCardiaca ,String cirurgia, String alergia) {
        this.paciente = paciente;
        this.fuma = fuma;
        this.bebe = bebe;
        this.colesterol = colesterol;
        this.doencaCardiaca = doencaCardiaca;
        this.cirurgia = cirurgia;
        this.alergia = alergia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean isFuma() {
        return fuma;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public boolean isBebe() {
        return bebe;
    }

    public void setBebe(boolean bebe) {
        this.bebe = bebe;
    }

    public boolean isColesterol() {
        return colesterol;
    }

    public void setColesterol(boolean colesterol) {
        this.colesterol = colesterol;
    }

    public boolean isDoencaCardiaca() {
        return doencaCardiaca;
    }

    public void setDoencaCardiaca(boolean doencaCardiaca) {
        this.doencaCardiaca = doencaCardiaca;
    }

    public String getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(String cirurgia) {
        this.cirurgia = cirurgia;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }
    
}
  