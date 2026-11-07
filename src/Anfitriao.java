public class Anfitriao extends Pessoa {
    private Acomodacao[] oferta;

    public Anfitriao(String nome, String cpf, String telefone){
        super(nome, cpf, telefone);
    }

    public Acomodacao[] listar(){

        return oferta;
    }


}
