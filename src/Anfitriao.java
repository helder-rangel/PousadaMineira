import java.time.LocalDate;

public class Anfitriao extends Pessoa {
    private Acomodacao[] oferta;

    public Anfitriao(String nome, String cpf, LocalDate dataNascimento, String telefone) throws RegraNegocioException {
        super(nome, cpf, dataNascimento, telefone);
    }
}
