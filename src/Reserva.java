import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Reserva {
    private static int contadorId = 1;

    private int id;
    private Hospede hospede;
    private Acomodacao acomodacao;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private int qtdHospedes;
    private List<TipoServico> servicosConsumidos;
    private boolean ativa;
    private boolean contaFechada;
    private double valorTotalPago;

    public Reserva(Hospede hospede, Acomodacao acomodacao, LocalDate entrada, LocalDate saida, int qtdHospedes) {
        this.id = contadorId++;
        this.hospede = hospede;
        this.acomodacao = acomodacao;
        this.dataEntrada = entrada;
        this.dataSaida = saida;
        this.qtdHospedes = qtdHospedes;
        this.servicosConsumidos = new ArrayList<>();
        this.ativa = true;
        this.contaFechada = false;
        this.valorTotalPago = 0.0;
    }

    public int getId() { return id; }
    public Hospede getHospede() { return hospede; }
    public Acomodacao getAcomodacao() { return acomodacao; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }
    public boolean isAtiva() { return ativa; }
    public boolean isContaFechada() { return contaFechada; }

    public void cancelar() { this.ativa = false; }

    public void adicionarServico(TipoServico servico) {
        this.servicosConsumidos.add(servico);
    }

    public long getQtdDiarias() {
        return java.time.temporal.ChronoUnit.DAYS.between(dataEntrada, dataSaida);
    }

    public double getSubtotalServicos() {
        double total = 0;
        for (TipoServico s : servicosConsumidos) {
            total += s.getValor();
        }
        return total;
    }

    public double fecharConta() {
        double valorDiarias = getQtdDiarias() * acomodacao.getTipo().getValorDiaria();
        this.valorTotalPago = valorDiarias + getSubtotalServicos();
        this.ativa = false;
        this.contaFechada = true;
        return this.valorTotalPago;
    }

    public double getValorTotalPago() { return valorTotalPago; }
}