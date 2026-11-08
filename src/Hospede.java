import com.sun.source.tree.BreakTree;

import java.time.LocalDate;
import java.util.Scanner;

public class Hospede extends Pessoa {
    private Acomodacao[] acomodacaosDisponiveis;
    private double avaliacao;
    private double avaliacaoMedia;


    public Hospede(String cpf, String nome, LocalDate dataNascimento, String telefone) throws RegraNegocioException {
        super(cpf, nome, dataNascimento, telefone);
    }


    public Acomodacao[] listar() {
        return acomodacaosDisponiveis;
    }

    public double avaliarHospedagem() {
        if (this.avaliacao <= 5.0) {
            return this.avaliacao;
    } else {
        return 0.0;
    }
}

    public double avaliacaoMedia(int qtde){
        if(qtde > 0){
            double geral = avaliacao * qtde;
            this.avaliacaoMedia = geral / qtde;
            return this.avaliacaoMedia;
        } else {
            return 0.0;
        }
    }

    public void pagar(FormasPagamento forma){
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

    public Acomodacao[] getDisponivel() {
        return disponivel;
    }

    public double getAvaliacao() {
        return this.avaliacao;
    }

    public double getAvaliacaoMedia() {
        return this.avaliacaoMedia;
    }

    public void setDisponivel(Acomodacao[] disponivel) {
        this.disponivel = disponivel;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setAvaliacaoMedia(double avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }
}
