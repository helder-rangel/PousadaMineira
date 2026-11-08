import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Menu {


    // --- ENUMS ---
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

        public String getDescricao() {
            return descricao;
        }

        public int getCapacidade() {
            return capacidade;
        }

        public double getValorDiaria() {
            return valorDiaria;
        }
    }

    enum TipoServico {
        CAFE_COLONIAL("Café Colonial", 45.0),
        PASSEIO_GUIADO("Passeio Guiado", 100.0),
        TRASLADO("Traslado Aeroporto/Cidade", 150.0);

        private final String descricao;
        private final double valor;

        TipoServico(String descricao, double valor) {
            this.descricao = descricao;
            this.valor = valor;
        }

        public String getDescricao() {
            return descricao;
        }

        public double getValor() {
            return valor;
        }
    }


    class Acomodacao {
        private int numero;
        private TipoAcomodacao tipo;

        public Acomodacao(int numero, TipoAcomodacao tipo) {
            this.numero = numero;
            this.tipo = tipo;
        }

        public int getNumero() {
            return numero;
        }

        public TipoAcomodacao getTipo() {
            return tipo;
        }
    }

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

        public int getId() {
            return id;
        }

        public Hospede getHospede() {
            return hospede;
        }

        public Acomodacao getAcomodacao() {
            return acomodacao;
        }

        public LocalDate getDataEntrada() {
            return dataEntrada;
        }

        public LocalDate getDataSaida() {
            return dataSaida;
        }

        public boolean isAtiva() {
            return ativa;
        }

        public boolean isContaFechada() {
            return contaFechada;
        }

        public void cancelar() {
            this.ativa = false;
        }

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

        public double getValorTotalPago() {
            return valorTotalPago;
        }
    }

    // --- SERVIÇO / GERENCIADOR ---
    class PousadaService {
        private List<Hospede> hospedes = new ArrayList<>();
        private List<Acomodacao> acomodacoes = new ArrayList<>();
        private List<Reserva> reservas = new ArrayList<>();
        private double faturamentoTotal = 0.0;
    }
}