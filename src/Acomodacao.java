public class Acomodacao {
    private int numero;
    private int capacidade;
    private TipoAcomodacao tipo;

    public Acomodacao(int numero, int capacidade, TipoAcomodacao tipo){
        this.numero = numero;
        this.capacidade = capacidade;
        this.tipo = tipo;
    }

    public TipoAcomodacao getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setTipo(TipoAcomodacao tipo) {
        this.tipo = tipo;
    }
}
