import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            System.out.println("===== COMPRA INTELIGENTE =====");
            
            System.out.print("Digite seu nome: ");
            String nome = entrada.nextLine();
            
            System.out.print("Digite seu orçamento para o mercado: R$ ");
            BigDecimal orcamento = new BigDecimal(entrada.nextLine().trim());
            
            Cliente cliente = new Cliente(nome, orcamento);
            Carrinho carrinho = new Carrinho();
            
            int opcao;
            
            do {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Adicionar produto");
                System.out.println("2 - Ver produtos");
                System.out.println("3 - Ver total da compra");
                System.out.println("4 - Ver orçamento restante");
                System.out.println("5 - Finalizar compra");
                System.out.print("Escolha uma opção: ");
                opcao = entrada.nextInt();
                entrada.nextLine();
                
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome do produto: ");
                        String nomeProduto = entrada.nextLine();
                        
                        System.out.print("Preço do produto: R$ ");
                        BigDecimal preco = new BigDecimal(entrada.nextLine().trim());
                        
                        System.out.print("Quantidade: ");
                        int quantidade = entrada.nextInt();
                        entrada.nextLine();
                        
                        Produto produto = new Produto(nomeProduto, preco, quantidade);
                        carrinho.adicionarProduto(produto);
                        
                        BigDecimal totalAtual = carrinho.calcularTotal();
                        
                        System.out.println("\nProduto adicionado com sucesso!");
                        System.out.println("Total atual: " + CurrencyUtils.format(totalAtual));
                        
                        if (cliente.passouDoOrcamento(totalAtual)) {
                            BigDecimal ultrapassou = totalAtual.subtract(cliente.getOrcamento());
                            System.out.println("Atenção! Você ultrapassou o orçamento em " + CurrencyUtils.format(ultrapassou));
                        } else {
                            System.out.println("Orçamento restante: " + CurrencyUtils.format(cliente.calcularRestante(totalAtual)));
                        }
                    }
                        
                    case 2 -> carrinho.mostrarProdutos();
                        
                    case 3 -> System.out.println("Total da compra: " + CurrencyUtils.format(carrinho.calcularTotal()));
                        
                    case 4 -> {
                        BigDecimal total = carrinho.calcularTotal();
                        
                        if (cliente.passouDoOrcamento(total)) {
                            System.out.println("Você passou do orçamento.");
                            System.out.println("Valor excedido: " + CurrencyUtils.format(total.subtract(cliente.getOrcamento())));
                        } else {
                            System.out.println("Orçamento restante: " + CurrencyUtils.format(cliente.calcularRestante(total)));
                        }
                    }
                        
                    case 5 -> {
                        System.out.println("\n===== RESUMO FINAL =====");
                        System.out.println("Cliente: " + cliente.getNome());
                        carrinho.mostrarProdutos();
                        System.out.println("Orçamento inicial: " + CurrencyUtils.format(cliente.getOrcamento()));
                        System.out.println("Total gasto: " + CurrencyUtils.format(carrinho.calcularTotal()));
                        
                        if (cliente.passouDoOrcamento(carrinho.calcularTotal())) {
                            System.out.println("Status: Você passou do orçamento.");
                        } else {
                            System.out.println("Status: Compra dentro do orçamento.");
                        }
                        
                        System.out.println("Programa finalizado.");
                    }
                        
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
                
            } while (opcao != 5);
        }
    }
}