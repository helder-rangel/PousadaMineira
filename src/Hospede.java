import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Hospede extends Pessoa {
    private Acomodacao[] disponivel;

    public Hospede(String cpf, String nome, String telefone){
        super(cpf,nome,telefone );
    }


    public Acomodacao[] listar(){
        return disponivel;
    }

    public void pagar(FormasPagamento forma){
        Scanner ent = new Scanner(System.in);
        switch (forma){
            case Pix pix -> {
                System.out.println("Iforme sua chave pix");
                String chave = ent.nextLine();
                if (forma.pagar(chave)) {
                    System.out.println("Recebemos do Pix " + chave);
                } else {
                    System.out.println("Pagamento inválido");
                }
            }

            case Credito credito -> {
                System.out.println("Informe o número de seu cartão de crédito");
                String numero = ent.nextLine();
                if (forma.pagar(numero)) {
                    System.out.println("Recebemos do Cartão " + numero);
                } else {
                    System.out.println("Pagamento inválido");
                }
            }
            default -> {
                throw new IllegalArgumentException("Forma de pagamento inválida");
            }
        }
    }
}
