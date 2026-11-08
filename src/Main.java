import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static PousadaService service = new PousadaService();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao = -1;

        do {
            System.out.println("\n================ POUSADA MINEIRA ================");
            System.out.println("1. Cadastrar Hóspede");
            System.out.println("2. Fazer Reserva");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Adicionar Serviço Extra à Reserva");
            System.out.println("5. Realizar Check-out (Fechar Conta)");
            System.out.println("6. Exibir Relatório (Taxa de Ocupação & Faturamento)");
            System.out.println("0. Sair");
            System.out.println("=================================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> cadastrarHospede();
                    case 2 -> fazerReserva();
                    case 3 -> cancelarReserva();
                    case 4 -> adicionarServico();
                    case 5 -> fecharConta();
                    case 6 -> exibirRelatorio();
                    case 0 -> System.out.println("Encerrando o sistema da Pousada Mineira... Até logo!");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void cadastrarHospede() {
        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();

        service.cadastrarHospede(cpf, nome);
        System.out.println(">> Hóspede cadastrado com sucesso!");
    }

    private static void fazerReserva() {
        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();

        System.out.println("\nAcomodações disponíveis:");
        for (Acomodacao a : service.getAcomodacoes()) {
            System.out.println(" - N° " + a.getNumero() + " | " + a.getTipo().getDescricao() +
                    " | Cap: " + a.getTipo().getCapacidade() + " pessoas | R$ " + a.getTipo().getValorDiaria() + "/dia");
        }
        System.out.print("Número da acomodação: ");
        int numAcomodacao = Integer.parseInt(scanner.nextLine());

        System.out.print("Data de entrada (dd/MM/yyyy): ");
        LocalDate entrada = LocalDate.parse(scanner.nextLine(), fmt);

        System.out.print("Data de saída (dd/MM/yyyy): ");
        LocalDate saida = LocalDate.parse(scanner.nextLine(), fmt);

        System.out.print("Quantidade de hóspedes: ");
        int numPessoas = Integer.parseInt(scanner.nextLine());

        Reserva res = service.criarReserva(cpf, numAcomodacao, entrada, saida, numPessoas);
        System.out.println(">> Reserva #" + res.getId() + " criada com sucesso!");
    }

    private static void cancelarReserva() {
        System.out.print("ID da reserva a cancelar: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.cancelarReserva(id);
        System.out.println(">> Reserva #" + id + " cancelada com sucesso!");
    }

    private static void adicionarServico() {
        System.out.print("ID da reserva: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("\nServiços Extras:");
        TipoServico[] servicos = TipoServico.values();
        for (int i = 0; i < servicos.length; i++) {
            System.out.println((i + 1) + ". " + servicos[i].getDescricao() + " (R$ " + servicos[i].getValor() + ")");
        }
        System.out.print("Escolha o serviço (1-" + servicos.length + "): ");
        int opcaoServico = Integer.parseInt(scanner.nextLine());

        if (opcaoServico < 1 || opcaoServico > servicos.length) {
            System.out.println("Serviço inválido.");
            return;
        }

        service.adicionarServico(id, servicos[opcaoServico - 1]);
        System.out.println(">> Serviço '" + servicos[opcaoServico - 1].getDescricao() + "' adicionado à reserva!");
    }

    private static void fecharConta() {
        System.out.print("ID da reserva para Check-out: ");
        int id = Integer.parseInt(scanner.nextLine());

        Reserva r = service.buscarReserva(id);
        if (r == null || !r.isAtiva()) {
            System.out.println("Reserva inexistente ou inativa.");
            return;
        }

        System.out.println("\n--- RESUMO DA CONTA ---");
        System.out.println("Hóspede: " + r.getHospede().getNome());
        System.out.println("Acomodação: " + r.getAcomodacao().getTipo().getDescricao());
        System.out.println("Diárias (" + r.getQtdDiarias() + "x): R$ " + (r.getQtdDiarias() * r.getAcomodacao().getTipo().getValorDiaria()));
        System.out.println("Consumo de Serviços Extras: R$ " + r.getSubtotalServicos());

        double total = service.fecharConta(id);
        System.out.println("-----------------------");
        System.out.println(">> VALOR TOTAL PAGO: R$ " + total);
        System.out.println(">> Check-out realizado com sucesso!");
    }

    private static void exibirRelatorio() {
        System.out.print("Informe a data para consulta de ocupação (dd/MM/yyyy): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), fmt);

        double taxa = service.calcularTaxaOcupacao(data);
        System.out.println("\n================ RELATÓRIO PUSADA MINEIRA ================");
        System.out.printf("Taxa de Ocupação em %s: %.2f%%\n", data.format(fmt), taxa);
        System.out.printf("Faturamento Total acumulado (contas fechadas): R$ %.2f\n", service.getFaturamentoTotal());
        System.out.println("==========================================================");
    }
}