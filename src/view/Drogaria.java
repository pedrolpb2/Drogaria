package view;
import beans.Higiene;
import beans.Medicamento;
import dao.HigieneDao;
import dao.MedicamentosDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Drogaria {

    public static void main(String[] args) throws SQLException {

        Scanner ler = new Scanner(System.in);
        String menu = "_________________________________________" +
                "\n1 - Cadastrar Novo Produto." +
                "\n2 - Listar todos os produtos." +
                "\n3 - Atualizar Dados de um Produto." +
                "\n4 - Excluir Produto."+
                "\n5 - Sair do Sistema." +
                "\n_________________________________________";
        int opcaoMenu = 0;
        int opcaoSubMenu = 0;

        HigieneDao higieneDao = new HigieneDao();
        MedicamentosDao medicamentosDao = new MedicamentosDao();

        while (opcaoMenu!=5){
            System.out.println(menu + "\n\nDigite uma opção do menu: ");
            ler = new Scanner(System.in);
            opcaoMenu = ler.nextInt();
            switch (opcaoMenu){
                case 1:{
                    System.out.println("\n*************Seleciona o tipo de produto*************" +
                            "\nSe você deseja cadastrar um produto de higiene, digite 1." +
                            "\nSe você desja cadastrar um medicamento, digite 2" +
                            "\n****************************************************");
                    opcaoSubMenu = ler.nextInt();
                    if (opcaoSubMenu==1){
                        System.out.println("Digite o código do produto de Higiene:");
                        int codigo = ler.nextInt();
                        ler = new Scanner(System.in);
                        System.out.println("Digite a descrição do produto de Higiene:");
                        String descricao = ler.next();
                        System.out.println("Digite o preço de compra do produto de Higiene:");
                        double precoCompra = ler.nextDouble();
                        System.out.println("Digite o percentual de lucro do produto de Higiene:");
                        double percentualLucro = ler.nextDouble();
                        ler = new Scanner(System.in);

                        Higiene novoHigiene = new Higiene(codigo,descricao,precoCompra,percentualLucro);
                        try {
                            higieneDao.cadastrar(novoHigiene);
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                            break;
                        }
                        double retorno = higieneDao.calculaLucro(precoCompra, percentualLucro);
                        System.out.println("O lucro de venda desse produto será de: " + retorno);

                    }else if(opcaoSubMenu==2){
                        System.out.println("Digite o código do produto de Medicamento:");
                        int codigo = ler.nextInt();
                        ler = new Scanner(System.in);
                        System.out.println("Digite a descrição do Medicamento:");
                        String descricao = ler.next();
                        ler = new Scanner(System.in);
                        System.out.println("Digite o preço de compra do Medicamento:");
                        double precoCompra = ler.nextDouble();
                        System.out.println("Digite o percentual de lucro do Medicamento:");
                        double percentualLucro = ler.nextDouble();
                        ler = new Scanner(System.in);
                        System.out.println("Digite a data de vencimento do Medicamento: ");
                        String dataVencimento = ler.nextLine();

                        Medicamento novoMedicamento = new Medicamento(codigo, descricao, precoCompra, percentualLucro, dataVencimento);
                        try {
                            medicamentosDao.cadastrar(novoMedicamento);
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                            break;
                        }
                        double retorno = medicamentosDao.calculaLucro(precoCompra, percentualLucro);
                        System.out.println("O lucro de venda desse produto será de: " + retorno);

                    }

                }break;
                case 2:{
                    List<Higiene> higienes = higieneDao.listar();
                    List<Medicamento> medicamentos = medicamentosDao.listar();
                    System.out.println("************PRODUTOS DE HIGIENE:************");
                    System.out.println("\n"+higienes+"\n");
                    System.out.println("************MEDICAMENTOS:************");
                    System.out.println("\n"+medicamentos+"\n");
                }break;
                case 3:{
                    System.out.println("Digite o código do produto que deseja atualizar: ");
                    int codigo = ler.nextInt();
                    Higiene higiene = higieneDao.busca(codigo);
                    Medicamento medicamento = (Medicamento) medicamentosDao.busca(codigo);
                    if (higiene != null) {
                        System.out.println("\nFoi encontrado o seguinte produto de Higiene com esse código: ");
                        System.out.println("\nCódigo: "+higiene.getCodigo()+"\nDescrição: "+higiene.getDescricao()+"\nPreço de compra: "+higiene.getPrecoCompra()+"\nPercentual Lucro: "+higiene.getPercentualLucro());
                        ler = new Scanner(System.in);
                        System.out.println("\nDigite a nova descrição: ");
                        String descricao = ler.nextLine();
                        System.out.println("Digite o novo preço de compra: ");
                        double precoCompra = ler.nextDouble();
                        System.out.println("digite o novo percentual de lucro: ");
                        double percentualLucro = ler.nextDouble();

                        Higiene novoHigiene = new Higiene(codigo, descricao, precoCompra, percentualLucro);
                        higieneDao.atualizar(novoHigiene, codigo);

                        double retorno = higieneDao.calculaLucro(precoCompra, percentualLucro);
                        System.out.println("O novo lucro de venda desse produto será de: " + retorno);
                    }
                    if (medicamento != null) {
                        System.out.println("\nFoi encontrado o seguinte medicamento com esse código: ");
                        System.out.println("\nCódigo: "+medicamento.getCodigo()+"\nDescrição: "+medicamento.getDescricao()+"\nPreço de compra: "+medicamento.getPrecoCompra()+"\nPercentual Lucro: "+medicamento.getPercentualLucro()+"\nData Vencimento: "+medicamento.getDataVencimento());
                        ler = new Scanner(System.in);
                        System.out.println("\nDigite a descrição: ");
                        String descricao2 = ler.nextLine();
                        System.out.println("Digite o preço de compra: ");
                        double precoCompra2 = ler.nextDouble();
                        System.out.println("digite o percentual de lucro: ");
                        double percentualLucro2 = ler.nextDouble();
                        ler = new Scanner(System.in);
                        System.out.println("Digite a data de vencimento:");
                        String dataVencimento = ler.nextLine();

                        Medicamento medicamento2 = new Medicamento(codigo, descricao2, precoCompra2, percentualLucro2, dataVencimento);
                        medicamentosDao.atualizar(medicamento2, codigo);

                        double retornoa = higieneDao.calculaLucro(precoCompra2, percentualLucro2);
                        System.out.println("O novo lucro de venda desse produto será de: " + retornoa);
                    }
                }break;
                case 4:{
                    System.out.println("Favor digite o código do produto que deseja excluir: ");
                    int codigo = ler.nextInt();
                    Higiene higiene = higieneDao.busca(codigo);
                    Medicamento medicamento = medicamentosDao.busca(codigo);

                    if(higiene != null){
                        System.out.printf("Tem certeza que deseja exluir permanentemente o produto de higiene com o código: "+higiene.getCodigo()+" com a descrição: "+higiene.getDescricao()+"?");
                        System.out.println("\n1 - Sim.\n2 - Não.");
                        int opcao = ler.nextInt();
                        if (opcao == 1){
                            higieneDao.excluir(codigo);
                            System.out.println("Excluido com sucesso!!");
                        }else if (opcao == 2){
                            System.out.println("Produto não excluido");
                        }else {
                            System.out.println("opção invalida");
                        }
                    }
                    if (medicamento != null){
                        System.out.printf("Tem certeza que deseja exluir permanentemente o medicamento de código: "+medicamento.getCodigo()+" com a descrição: "+medicamento.getDescricao()+"?");
                        System.out.println("\n1 - Sim.\n2 - Não.");
                        int opcao = ler.nextInt();
                        if (opcao == 1){
                            medicamentosDao.excluir(codigo);
                            System.out.println("Excluido com sucesso!!");
                        }else if (opcao == 2){
                            System.out.println("Produto não excluido");
                        }else {
                            System.out.println("opção invalida");
                        }
                    }else {
                        System.out.println("Não existe nenhum produto cadastrado com o código informado");
                    }

                }

            }
        }
        System.out.println("_________________________________________" +
                "\nObrigado por utilizar o sistema!!" +
                "\n_________________________________________");
        ler.close();
    }
}
