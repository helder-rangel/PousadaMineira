enum TipoAcomodacao {
    QUARTO_SIMPLES("Quarto Simples", 2, 120.0),
    SUITE("Suíte Presidencial", 3, 250.0),
    CHALE("Chalé Mineiro", 5, 400.0);

    private final String descricao;
    private final int capacidade;
    private final double valorDiaria;

    TipoAcomodacao(String descricao, int capacidade, double valorDiaria) {
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.valorDiaria = valorDiaria;
    }

    public String getDescricao() { return descricao; }
    public int getCapacidade() { return capacidade; }
    public double getValorDiaria() { return valorDiaria; }
}