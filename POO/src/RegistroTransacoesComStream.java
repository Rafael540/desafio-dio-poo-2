import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegistroTransacoesComStream {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double saldo = scanner.nextDouble();
        int quantidadeTransacoes = scanner.nextInt();

        List<Transacao> transacoes = new ArrayList<>();

        for (int i = 1; i <= quantidadeTransacoes; i++) {
            char tipoTransacao = scanner.next().charAt(0);
            double valorTransacao = scanner.nextDouble();

            // Criar uma nova transação e adicioná-la à lista de transações
            Transacao transacao = new Transacao(tipoTransacao, valorTransacao);
            transacoes.add(transacao);

            // Verifica e atualiza o saldo da conta com base no tipo de transação
            if (Character.toUpperCase(transacao.getTipo()) == 'D') {
                saldo += valorTransacao;
            } else if (Character.toUpperCase(transacao.getTipo()) == 'S') {
                saldo -= valorTransacao;
            }
        }

        // Fechar o scanner para evitar vazamentos de recursos
        scanner.close();

        // Exibir saldo final com uma casa decimal
        System.out.print("Saldo : " + formatarComUmaCasaDecimal(saldo) + "\nTransacoes:\n");

        // Exibir transações com uma casa decimal
        transacoes.stream()
                .map(transacao -> transacao.getTipo() + " de " + formatarComUmaCasaDecimal(transacao.getValor()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    // Método auxiliar para formatar valores com uma casa decimal
    private static String formatarComUmaCasaDecimal(double valor) {
        return String.format("%.1f", valor).replace(',', '.');
    }
}

class Transacao {
    private char tipo;
    private double valor;

    public Transacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public char getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }
}
