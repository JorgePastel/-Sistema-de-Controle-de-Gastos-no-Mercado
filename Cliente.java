import java.math.BigDecimal;

public class Cliente {
    private String nome;
    private BigDecimal orcamento;

    public Cliente(String nome, BigDecimal orcamento) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente inválido");
        }
        if (orcamento == null || orcamento.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Orçamento não pode ser negativo");
        }

        this.nome = nome;
        this.orcamento = orcamento;
    }

    public boolean passouDoOrcamento(java.math.BigDecimal total) {
        return total.compareTo(orcamento) > 0;
    }

    public java.math.BigDecimal calcularRestante(java.math.BigDecimal total) {
        return orcamento.subtract(total);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s\nOrçamento: %s", nome, CurrencyUtils.format(orcamento));
    }
}