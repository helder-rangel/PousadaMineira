# Pousada Mineira — Sistema de Gestão de Hospedagem

Trabalho Prático da disciplina de Programação Orientada a Objetos (POO). Sistema desenvolvido em Java com interface gráfica utilizando Java Swing para gestão de reservas inspirado no Airbnb.com.br, serviços extras e faturamento de uma pousada em cidade histórica de Minas Gerais.

---

## 📋 Respostas da Ficha de Planejamento

### 1. Sistema Escolhido e Elemento Original
* **Tema:** Pousada Mineira
* **Elemento Original:** **Suíte Temática Colonial** (uma subclasse original de acomodação decorada com itens históricos, que aplica um acréscimo percentual fixo no valor total da diária devido à experiência cultural oferecida).

---

### 2. Classes do Sistema
* `Acomodacao` (Abstrata): Representa genericamente uma acomodação da pousada, contendo número, capacidade e diária base.
* `QuartoSimples`: Representa uma acomodação básica com valor de diária padrão.
* `Suite`: Representa uma acomodação de luxo que inclui taxa adicional por comodidades (hidromassagem/varanda).
* `SuiteTematicaColonial`: Representa a acomodação especial (elemento original), que inclui taxa de experiência cultural.
* `ServicoExtra` (Abstrata): Representa qualquer serviço adicional contratado pelo hóspede durante a estadia.
* `CafeColonial`: Representa o serviço extra de café da manhã típico mineiro cobrado por pessoa.
* `PasseioGuiado`: Representa o serviço de tour histórico cobrado por grupo/duração.
* `Traslado`: Representa o serviço de transporte cobrado por distância (km).
* `Hospede`: Representa o cliente cadastrado na pousada (nome, CPF, telefone).
* `Reserva`: Associa um hóspede a uma acomodação em determinado período, registrando serviços consumidos e calculando a conta final.
* `GerenciadorPousada`: Classe controladora que gerencia os vetores fixos de acomodações, hóspedes e reservas.
* `ReservaInvalidaException`: Exceção personalizada para violação de regras de negócio.
* `TelaPrincipalSwing`: Classe de interface gráfica (Swing) que disponibiliza a navegação e menus do sistema.

---

### 3. Classe Mãe e Classes Filhas

#### Hierarquia 1: Acomodações
* **Classe Mãe:** `Acomodacao`, `Pessoa`
* **Classes Filhas:** 
  * `QuartoSimples`: Mantém o cálculo de diária padrão sem acréscimos extras.
  * `Suite`: Sobrescreve o cálculo de diária adicionando um valor fixo de taxa de luxo/comodidade.
  * `SuiteTematicaColonial` (Elemento Original): Sobrescreve o cálculo adicionando 25% de taxa de preservação e experiência cultural histórica.
  * `Hospede`: Falta escolher método a ser implementado a partir da classe Pessoa.
  * `Anfitriao`: Falta escolher método a ser implementado a partir da classe Pessoa.

#### Hierarquia 2: Serviços Extras
* **Classe Mãe:** `ServicoExtra`
* **Classes Filhas:**
  * `CafeColonial`: Calcula o valor total multiplicando a taxa por quantidade de pessoas.
  * `PasseioGuiado`: Calcula o valor com taxa fixa mais adicional por hora de tour.
  * `Traslado`: Calcula o valor com base na distância percorrida em quilômetros.

---

### 4. Classe Abstrata e Método Abstrato

* **Classe Abstrata:** `Acomodacao`
  * **Método Abstrato:** `public abstract double calcularDiaria();`  
  *(Cada tipo de acomodação implementa de forma polimórfica a sua própria regra de precificação).*

* **Classe Abstrata:** `ServicoExtra`
  * **Método Abstrato:** `public abstract double calcularPrecoTotal();`  
  *(Cada tipo de serviço extra possui sua própria fórmula de cálculo).*

---

### 5. Interface e Classes Implementadoras

* **Interface:** `Faturavel` (Interface para elementos que geram receita e calculam tributos/taxas de serviço da pousada).
  * **Método:** `double calcularImposto();`
* **Classes que implementam:** `Reserva` e `ServicoExtra`.

---

### 6. Situação de Lançamento da Exceção Personalizada (`ReservaInvalidaException`)

A exceção personalizada `ReservaInvalidaException` é lançada nas seguintes situações de violação de regras de negócio:
1. Tentativa de efetuar reserva em uma **acomodação já ocupada/reservada** no período selecionado.
2. Tentativa de cadastrar uma reserva com número de hóspedes **superior à capacidade máxima** da acomodação.
3. Informar uma **data de checkout/saída anterior à data de checkin/entrada**.

---
### 7. Enums (Enumeradores)
* **`StatusReserva`:** Define os estados de uma reserva (`PENDENTE`, `CONFIRMADA`, `CANCELADA`, `FINALIZADA`).
* **`TipoServico`:** Categoriza os serviços extras oferecidos (`ALIMENTACAO`, `LAZER`, `TRANSPORTE`).

## 🛠️ Requisitos Técnicos Implementados

1. **Encapsulamento:** Atributos `private`/`protected` com getters e setters.
2. **Construtores:** Uso de construtores padrão e sobrecarregados nas entidades principais.
3. **Modificadores:** Emprego correto de `private`, `protected`, `public`, `static` e `final`.
4. **Herança & Polimorfismo:** Hierarquia de acomodações e serviços extras armazenados e manipulados via vetores de referências da classe mãe.
5. **Interface e Classe Abstrata:** `Faturavel`, `Acomodacao` e `ServicoExtra`.
6. **Vetores Fixos:** Armazenamento manual via arrays com rotinas de inserção, remoção (com deslocamento de elementos), busca e listagem.
7. **Tratamento de Exceções:** Blocos `try-catch` capturando e exibindo pop-ups na interface Swing para a `ReservaInvalidaException`.
8. **Interface Gráfica (Swing):** Formulários, tabelas e janelas para controle do fluxo da pousada.
