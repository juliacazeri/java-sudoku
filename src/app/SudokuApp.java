package app;

import model.Posicao;
import model.Sudoku;

import java.util.Scanner;

public class SudokuApp{
    private static final Scanner scanner = new Scanner(System.in);
    private static final Sudoku sudoku = new Sudoku();
    private static String[] initialArgs;

    public static void main (String[] args){
        initialArgs = args;
        int opcao;

        do{
            System.out.println("===== Menu model.Sudoku =====");
            System.out.println("1 - Iniciar um novo jogo.");
            System.out.println("2 - Colocar um novo número.");
            System.out.println("3 - Remover um número.");
            System.out.println("4 - Verificar jogo (mostrar tabuleiro).");
            System.out.println("5 - Verificar status do jogo.");
            System.out.println("6 - Limpar jogo.");
            System.out.println("7 - Finalizar o jogo.");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    iniciarNovoJogo();
                    break;
                case 2:
                    colocarNumero();
                    break;
                case 3:
                    removerNumero();
                    break;
                case 4:
                    verificarJogo();
                    break;
                case 5:
                    verificarStatus();
                    break;
                case 6:
                    limpar();
                    break;
                case 7:
                    if(finalizar()){
                        scanner.close();
                        return;
                    }
                    break;
                case 8:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(true);
    }

    private static void iniciarNovoJogo(){
        sudoku.iniciarNovoJogo(initialArgs);
        System.out.println("O jogo foi iniciado.");
        sudoku.imprimirTabuleiro();
    }

    private static void colocarNumero(){
        if(!sudoku.foiIniciado()){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1 no menu.");
            return;
        }

        try{
            System.out.print("Informe o número a ser colocado (1-9): ");
            int valor = lerNumero();

            System.out.print("Informe o índice horizontal (linha 1-9): ");
            int linha = lerNumero() - 1;

            System.out.print("Informe o índice vertical (coluna 1-9): ");
            int coluna = lerNumero() - 1;

            Posicao posicao = new Posicao(linha, coluna);
            boolean numeroInserido = sudoku.colocarNumero(posicao, valor);

            if(numeroInserido){
                System.out.println("O número foi inserido com sucesso!");
            }
        } catch(Exception e){
            System.out.println("Entrada inválida: " + e.getMessage() + ".");
        }
    }

    private static void removerNumero(){
        if(!sudoku.foiIniciado()){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1 no menu.");
            return;
        }

        try{
            System.out.print("Informe o índice horizontal do número para remover: ");
            int linha = lerNumero() - 1;

            System.out.print("Informe o índice vertical do número para remover: ");
            int coluna = lerNumero() - 1;

            Posicao posicao = new Posicao(linha, coluna);
            boolean numeroInserido = sudoku.removerNumero(posicao);

            if(numeroInserido){
                System.out.println("O número foi removido com sucesso!");
            }
        } catch(Exception e){
            System.out.println("Entrada inválida: " + e.getMessage() + ".");
        }
    }

    private static void verificarJogo(){
        if(!sudoku.foiIniciado()){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1 no menu.");
            return;
        }
        sudoku.imprimirTabuleiro();
    }

    private static void verificarStatus(){
        Sudoku.Status status = sudoku.getStatus();
        boolean erros = sudoku.temErros();

        System.out.println("O status do jogo no momemento é: " + status + ".");
        System.out.println("Contém erros? " + (erros ? "SIM" : "NÃO") + ".");
    }

    private static void limpar(){
        if(!sudoku.foiIniciado()){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1 no menu.");
            return;
        }
        sudoku.limpar();
        System.out.println("Os números inseridos foram removidos!");
    }

    private static boolean finalizar(){
        Sudoku.Status status = sudoku.getStatus();
        boolean erros = sudoku.temErros();

        if(status == Sudoku.Status.Completo && !erros){
            System.out.println("Parabéns! Você terminou o sudoku!");
            return true;
        } else{
            if(erros){
                System.out.println("Você não pode finalizar o sudoku, pois existem conflitos no tabuleiro.");
            }
            if(status != Sudoku.Status.Completo){
                System.out.println("Você não pode finalizar o sudoku, pois o jogo está incompleto.");
            }
            return false;
        }
    }

    private static int lerNumero(){
        while(true){
            try{
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch(NumberFormatException e){
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }
}