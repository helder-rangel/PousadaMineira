public class Acomodacao {
    private long id;
    private int capacidade;
    private TipoAcomodacao tipo;
    private String[] comodidades;
    int qtdeComod;

    public Acomodacao(long id, int capacidade, TipoAcomodacao tipo, String[] comodidades){
        this.id = id;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.comodidades = new String[qtdeComod];
    }

    public TipoAcomodacao getTipo() {
        return tipo;
    }

    public long getid() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setTipo(TipoAcomodacao tipo) {
        this.tipo = tipo;
    }
}
