package br.insper.ecommerce;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.cliente.ClienteService;
import br.insper.ecommerce.compra.CompraService;
import br.insper.ecommerce.compra.Item;
import br.insper.ecommerce.pagamento.Boleto;
import br.insper.ecommerce.pagamento.MeioPagamento;
import br.insper.ecommerce.produto.Produto;
import br.insper.ecommerce.produto.ProdutoService;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String opcao = "0";

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        CompraService compraService = new CompraService();

        while(!opcao.equalsIgnoreCase("9")) {

            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Listar Clientes
                    3 - Excluir Cliente
                    4 - Cadastrar Produto
                    5 - Listar Produtos
                    6 - Excluir Produto
                    7 - Cadastrar Compra
                    8 - Listar Compras
                    9 - Sair
                    """);
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("1")) {
                System.out.println("Digite o nome do cliente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o CPF do cliente:");
                String cpf = scanner.nextLine();

                clienteService.cadastrarCliente(nome, cpf);
            }

            if (opcao.equalsIgnoreCase("2")) {
                clienteService.listarClientes();
            }

            if (opcao.equalsIgnoreCase("3")) {
                System.out.println("Digite o nome do cliente para deletar:");
                String cpf = scanner.nextLine();
                clienteService.excluirClientes(cpf);
            }

            if (opcao.equalsIgnoreCase("4")) {
                String nome = scanner.nextLine();
                String precoString = scanner.nextLine();
                Double preco = Double.parseDouble(precoString);
                produtoService.cadastrarProduto(nome, preco);
            }

            if (opcao.equalsIgnoreCase("5")) {
                produtoService.listarProdutos();
            }

            if (opcao.equalsIgnoreCase("6")) {
                System.out.println("Digite o nome do produto para deletar:");
                String nome = scanner.nextLine();
                produtoService.excluirProduto(nome);
            }

            if (opcao.equalsIgnoreCase("7")) {
                Cliente clienteSelecionado = null;
                ArrayList<Cliente> clientes = clienteService.listarClientes();

                System.out.println("Digite o CPF do cliente:");
                String cpf = scanner.nextLine();

                for (Cliente cliente : clientes) {
                    if (cpf.equalsIgnoreCase(cliente.getCpf())) {
                        clienteSelecionado = cliente;
                    }
                }

                if (opcao.equalsIgnoreCase("8")) {
                    compraService.listarCompras();
                }

                if (opcao.equalsIgnoreCase("9")) {
                    System.out.println("Até a próxima!");
                }

                if (clienteSelecionado != null) {
                    ArrayList<Produto> produtos = produtoService.listarProdutos();

                    ArrayList<Item> items = new ArrayList<>();

                    System.out.println("Digite quantidade de produtos:");
                    Integer quantidadeProdutos = scanner.nextInt();

                    for (int i = 0; i < quantidadeProdutos; i++) {
                        System.out.println("Digite nome do produto");
                        String nomeProduto = scanner.nextLine();

                        Produto produtoSelecionado = null;

                        for (Produto produto : produtos) {
                            if (nomeProduto.equalsIgnoreCase(produto.getNome())) {
                                produtoSelecionado = produto;
                            }
                        }

                        if (produtoSelecionado != null) {
                            System.out.println("Digite a quantidade do produto");
                            Integer quantidadeProduto = scanner.nextInt();

                            Item item = new Item(quantidadeProduto, produtoSelecionado);
                            items.add(item);

                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    }

                    compraService.cadastrarCompra(clienteSelecionado, items);



                } else {
                    System.out.println("Cliente não encontrado.");
                }

            }

        }
    }

}