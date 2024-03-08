package br.insper.ecommerce.compra;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.pagamento.MeioPagamento;
import br.insper.ecommerce.produto.Produto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CompraService {
    private ArrayList<Compra> compras = new ArrayList<>();

    public void cadastrarCompra(Cliente cliente, ArrayList<Item> items) {

        if (cliente.equals(null) || items.size() == 0) {
            System.out.println("Dados da compra invalidos.");
        } else {
            LocalDateTime localDateTime = LocalDateTime.now();

            Compra compra = new Compra(localDateTime, 0D, cliente, null);
            for (Item item : items) {
                compra.adicionarItem(item);
            }
            compra.calculaPrecoTotal();
            System.out.println("Compra cadastrada com sucesso.");
        }

    }


}
