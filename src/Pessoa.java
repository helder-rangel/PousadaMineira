public abstract class Pessoa {
    private String cpf;
    private String nome;
    private String telefone;

    public Pessoa(){}

    public Pessoa(String cpf, String nome, String telefone){
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public abstract Acomodacao[] listar();

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
