package view;
import beans.Higiene;
import beans.Medicamento;
import dao.HigieneDao;
import dao.MedicamentosDao;

import java.util.Scanner;
public class Drogaria {

    public static void main(String[] args){

        Scanner ler = new Scanner(System.in);
        String menu = "_________________________________________" +
                "\n1 - Cadastrar Novo Produto." +
                "\n2 - Informações do Produto Mais Caro." +
                "\n3 - Relatorio de Todos os Produtos." +
                "\n4 - Alterar Dados de um Produto." +
                "\n5 - Sair do Sistema." +
                "\n_________________________________________";
        int opcaoMenu = 0;
        int opcaoSubMenu = 0;

        HigieneDao higieneDao = new HigieneDao();
        higieneDao.inicializaVetor();
        MedicamentosDao medicamentosDao = new MedicamentosDao();
        medicamentosDao.inicializaVetor();

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
                        double percentualLucro = ler.nextInt();
                        ler = new Scanner(System.in);

                        boolean a = higieneDao.verificaCodigo(codigo);
                        boolean b = medicamentosDao.verificaCodigo(codigo);

                        if (a==true && b==true){
                            Higiene novoHigiene = new Higiene(codigo,descricao,precoCompra,percentualLucro);
                            higieneDao.persisteHigiene(novoHigiene);
                            double retorno = higieneDao.calculaLucro(precoCompra, percentualLucro);
                            System.out.println("O lucro de venda desse produto será de: " + retorno);
                        }else if(a==false || b==false) {
                        System.out.println("Produto não cadastrado pois já existe outro produto com esse código");
                        }


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
                        double percentualLucro = ler.nextInt();
                        System.out.println("Digite a data de vencimento do Medicamento: ");
                        String dataVencimento = ler.next();
                        ler = new Scanner(System.in);

                        boolean a = medicamentosDao.verificaCodigo(codigo);
                        boolean b = higieneDao.verificaCodigo(codigo);

                        if (b==true && a==true){
                            Medicamento novoMedicamento = new Medicamento(codigo, descricao, precoCompra, percentualLucro, dataVencimento);
                            medicamentosDao.persisteMedicamentos(novoMedicamento);
                            double retorno = medicamentosDao.calculaLucro(precoCompra, percentualLucro);
                            System.out.println("O lucro de venda desse produto será de: " + retorno);
                        }else if (b==false || a==false){
                            System.out.println("Produto não cadastrado pois já existe outro produto com esse código");
                        }
                    }

                }break;
                case 2:{
                    Higiene retornoHigiene = higieneDao.buscaProdutoMaisCaro();
                    Medicamento retornoMedicamento = (Medicamento) medicamentosDao.buscaProdutoMaisCaro();
                    try {
                        if (retornoHigiene.getPrecoCompra() > retornoMedicamento.getPrecoCompra()) {
                            System.out.println("As informações do produto mais caro são: \n" + retornoHigiene );
                        } else if (retornoMedicamento.getPrecoCompra() > retornoHigiene.getPrecoCompra()) {
                            System.out.println("As informações do produto mais caro são: \n" + retornoMedicamento);
                        } else if (retornoHigiene.getPrecoCompra() == retornoMedicamento.getPrecoCompra()) {
                            System.out.println("As informações dos dois produtos mais caros sâo: \n" + retornoHigiene + "\n" + retornoMedicamento);
                        }
                    }catch (NullPointerException exception){
                        if (retornoHigiene == null && retornoMedicamento == null){
                            System.out.println("Naõ tem nenhum produto castrado na base");
                        }else if (retornoHigiene == null){
                            System.out.println(retornoMedicamento);
                        }else if (retornoMedicamento == null){
                            System.out.println(retornoHigiene);
                        }
                    }

                }break;
                case 3:{
                    higieneDao.relatorio();
                    medicamentosDao.relatorio();
                }break;
                case 4:{
                    System.out.println("Digite o código do produto que deseja alterar: ");
                    int codigo = ler.nextInt();
                    Higiene retorno = higieneDao.busca(codigo);
                    Medicamento retorno2 = (Medicamento) medicamentosDao.busca(codigo);

                    if (retorno != null) {

                        System.out.println("Digite a descrição: ");
                        String descricao = ler.nextLine();
                        System.out.println("Digite o preço de compra: ");
                        double precoCompra = ler.nextDouble();
                        System.out.println("digite o percentual de lucro: ");
                        double percentualLucro = ler.nextDouble();

                        higieneDao.alterar(codigo, descricao, precoCompra, percentualLucro);

                        double retornoa = higieneDao.calculaLucro(precoCompra, percentualLucro);
                        System.out.println("O lucro de venda desse produto será de: " + retornoa);
                    }
                    if (retorno2 != null) {

                        System.out.println("Digite a descrição: ");
                        String descricao2 = ler.nextLine();
                        System.out.println("Digite o preço de compra: ");
                        double precoCompra2 = ler.nextDouble();
                        System.out.println("digite o percentual de lucro: ");
                        double percentualLucro2 = ler.nextDouble();
                        System.out.println("Digite a data de vencimento:");
                        String dataVencimento = ler.next();

                        medicamentosDao.alterar(codigo, descricao2, precoCompra2, percentualLucro2, dataVencimento);
                        double retornoa = higieneDao.calculaLucro(precoCompra2, percentualLucro2);
                        System.out.println("O lucro de venda desse produto será de: " + retornoa);
                    }
                }break;

            }
        }
        System.out.println("_________________________________________" +
                "\nObrigado por utilizar o sistema!!" +
                "\n_________________________________________");
        ler.close();
    }
}
