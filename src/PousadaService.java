import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PousadaService {
    private List<Hospede> hospedes = new ArrayList<>();
    private List<Acomodacao> acomodacoes = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private double faturamentoTotal = 0.0;

    public PousadaService() {
        // Acomodações padrão pré-cadastradas
        acomodacoes.add(new Acomodacao(101, TipoAcomodacao.QUARTO_SIMPLES));
        acomodacoes.add(new Acomodacao(102, TipoAcomodacao.SUITE));
        acomodacoes.add(new Acomodacao(103, TipoAcomodacao.CHALE));
    }

    public void cadastrarHospede(String cpf, String nome) {
        if (buscarHospede(cpf) != null) {
            throw new IllegalArgumentException("Hóspede já cadastrado com este CPF.");
        }
        hospedes.add(new Hospede(cpf, nome));
    }

    public Hospede buscarHospede(String cpf) {
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) return h;
        }
        return null;
    }

    public Acomodacao buscarAcomodacao(int numero) {
        for (Acomodacao a : acomodacoes) {
            if (a.getNumero() == numero) return a;
        }
        return null;
    }

    public Reserva buscarReserva(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public Reserva criarReserva(String cpfHospede, int numAcomodacao, LocalDate entrada, LocalDate saida, int numPessoas) {
        Hospede hospede = buscarHospede(cpfHospede);
        if (hospede == null) {
            throw new IllegalArgumentException("Hóspede não encontrado. Cadastre-o primeiro.");
        }

        Acomodacao acomodacao = buscarAcomodacao(numAcomodacao);
        if (acomodacao == null) {
            throw new IllegalArgumentException("Acomodação não encontrada.");
        }

        // Regra: Data de saída anterior ou igual à de entrada
        if (!saida.isAfter(entrada)) {
            throw new IllegalArgumentException("Regra Violada: A data de saída deve ser posterior à data de entrada.");
        }

        // Regra: Capacidade excedida
        if (numPessoas > acomodacao.getTipo().getCapacidade()) {
            throw new IllegalArgumentException("Regra Violada: Número de hóspedes (" + numPessoas +
                    ") excede a capacidade da acomodação (" + acomodacao.getTipo().getCapacidade() + ").");
        }

        // Regra: Acomodação já reservada nas datas (choque de datas)
        for (Reserva r : reservas) {
            if (r.isAtiva() && r.getAcomodacao().getNumero() == numAcomodacao) {
                boolean sobrepoe = entrada.isBefore(r.getDataSaida()) && saida.isAfter(r.getDataEntrada());
                if (sobrepoe) {
                    throw new IllegalArgumentException("Regra Violada: Acomodação já está reservada no período solicitado.");
                }
            }
        }

        Reserva novaReserva = new Reserva(hospede, acomodacao, entrada, saida, numPessoas);
        reservas.add(novaReserva);
        return novaReserva;
    }

    public void cancelarReserva(int idReserva) {
        Reserva r = buscarReserva(idReserva);
        if (r == null || !r.isAtiva()) {
            throw new IllegalArgumentException("Reserva não encontrada ou já inativa/fechada.");
        }
        r.cancelar();
    }

    public void adicionarServico(int idReserva, TipoServico servico) {
        Reserva r = buscarReserva(idReserva);
        if (r == null || !r.isAtiva()) {
            throw new IllegalArgumentException("Reserva não encontrada ou inativa.");
        }
        r.adicionarServico(servico);
    }

    public double fecharConta(int idReserva) {
        Reserva r = buscarReserva(idReserva);
        if (r == null || !r.isAtiva()) {
            throw new IllegalArgumentException("Reserva não encontrada ou já finalizada.");
        }
        double total = r.fecharConta();
        this.faturamentoTotal += total;
        return total;
    }

    public double calcularTaxaOcupacao(LocalDate data) {
        int ocupadas = 0;
        for (Reserva r : reservas) {
            if (r.isAtiva() && !data.isBefore(r.getDataEntrada()) && data.isBefore(r.getDataSaida())) {
                ocupadas++;
            }
        }
        return ((double) ocupadas / acomodacoes.size()) * 100;
    }

    public double getFaturamentoTotal() { return faturamentoTotal; }
    public List<Acomodacao> getAcomodacoes() { return acomodacoes; }
}
