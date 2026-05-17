import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private final List<Produto> produtos;

    public Carrinho() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        produtos.add(produto);
    }

    public java.math.BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Produto produto : produtos) {
            total = total.add(produto.calcularSubtotal());
        }

        return total;
    }

    public void mostrarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto foi adicionado.");
            return;
        }

        System.out.println("\n===== LISTA DE PRODUTOS =====");
        for (Produto produto : produtos) {
            System.out.println(produto.toString());
            System.out.println("-----------------------------");
        }
    }
}