import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;


public abstract class Pessoa {
    protected String cpf;
    protected String nome;
    protected LocalDate dataNascimento;
    protected String telefone;

    public Pessoa(){}

    public Pessoa(String cpf, String nome, LocalDate dataNascimento, String telefone) throws RegraNegocioException {
        setCpf(cpf);
        setNome(nome);
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
    }
    Scanner ent = new Scanner(System.in);

    public abstract Acomodacao[] listar();

    public void setCpf(String cpf) {
        if(cpf != null && !cpf.isBlank()){
            this.cpf = cpf.strip();
        } else {
            throw new IllegalArgumentException("O campo cpf não pode estar em branco ou nulo");
            }
        }

    public void setNome(String nome) {
        if(nome != null && !nome.isBlank()) {
            this.nome = nome.strip();
        } else {
            throw new IllegalArgumentException("O campo nome não pode estar em branco ou ser nulo");
        }
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if(dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser uma data futura");
        }
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        if(telefone != null && !telefone.isBlank()){
            this.telefone = telefone.strip();
        } else {
            throw new IllegalArgumentException("O telefone não pode estar em branco ou ser nulo.");
        }
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade(){
        if(dataNascimento == null) return 0;
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getDataNascimentoFormatada() {
        if(this.dataNascimento != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return this.dataNascimento.format(formatter);
        }
        return "";
    }

    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }

    public String getTelefone() {
        return this.telefone;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf= '" + cpf + '\'' +
                ", nome= '" + nome + '\'' +
                ", telefone= '" + telefone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    } 
}