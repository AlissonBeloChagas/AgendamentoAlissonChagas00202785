package br.com.unipar.agendaconsulta;

public class Agendamento {
    private String nome, data, medico, telefone, email;

    public String getNome() {
        return  nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getMedico(){
        return medico;
    }

    public void setMedico(String Medico){
        this.medico = medico;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
