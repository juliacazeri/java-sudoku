package model;

public class Sudoku{
    private Tabuleiro tabuleiro;
    private boolean iniciado = false;

    public enum Status{
        NaoIniciado,
        Incompleto,
        Completo
    }

    public Sudoku(){
        tabuleiro = new Tabuleiro();
    }

    public void iniciarNovoJogo (String[] args){
        tabuleiro = new Tabuleiro();
        iniciado = true;

        for(String arg : args){
            String[] partes = arg.split(",");

            if(partes.length != 3){
                System.out.println("Ignorado o argumento que está inválido: " + arg + ".");
                continue;
            }

            try{
                int linha = Integer.parseInt(partes[0].trim()) - 1;
                int coluna = Integer.parseInt(partes[1].trim()) - 1;
                int valor = Integer.parseInt(partes[2].trim());
                Posicao posicao = new Posicao(linha, coluna);

                if(valor < 1 || valor > 9){
                    System.out.println("O valor inválido no argumento é: " + arg + ".");
                    continue;
                }

                if(podeColocar(posicao, valor)){
                    tabuleiro.setNumeros(posicao, valor, true);
                } else{
                    System.out.printf("Houve um conflito ao inserir inicial em (%d,%d)=%d. Ignorado.%n", linha + 1, coluna + 1, valor);
                }
            } catch(Exception e){
                System.out.println("Houve erro ao processar argumento: " + arg + ".");
            }
        }
    }

    public boolean foiIniciado(){
        return iniciado;
    }

    public boolean colocarNumero(Posicao posicao, int valor){
        if(!iniciado){
            System.out.println("O jogo não foi iniciado.");
            return false;
        }

        if(tabuleiro.ehFixo(posicao)){
            System.out.println("A posição é fixa e não pode ser modificada.");
            return false;
        }

        if(tabuleiro.getNumeros(posicao) != 0){
            System.out.println("Está posição já foi preenchida.");
            return false;
        }

        if(!podeColocar(posicao, valor)){
            System.out.println("O número selecionado é conflitante com as regras.");
            return false;
        }
        tabuleiro.setNumeros(posicao, valor, false);
        return true;
    }

    public boolean removerNumero(Posicao posicao){
        if(!iniciado){
            System.out.println("O jogo não foi iniciado.");
            return false;
        }

        if(tabuleiro.ehFixo(posicao)){
            System.out.println("O número fixo não pode ser removido.");
            return false;
        }

        if(tabuleiro.getNumeros(posicao) == 0){
            System.out.println("Não há nenhum número nessa posição.");
            return false;
        }
        tabuleiro.limparNumero(posicao);
        return true;
    }

    public Status getStatus(){
        if(!iniciado){
            return Status.NaoIniciado;
        }

        for(int l = 0; l < tabuleiro.getTamanho(); l++){
            for(int c = 0; c < tabuleiro.getTamanho(); c++){
                if(tabuleiro.getNumeros(new Posicao(l,c)) == 0)
                    return Status.Incompleto;
            }
        }
        return Status.Completo;
    }

    public boolean temErros(){
        for(int l = 0; l < tabuleiro.getTamanho(); l++){
            for(int c = 0; c < tabuleiro.getTamanho(); c++){
                Posicao posicao = new Posicao(l, c);
                int valor = tabuleiro.getNumeros(posicao);

                if(valor == 0) {
                    continue;
                }
                if(temConflito(posicao, valor)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean temConflito(Posicao posicao, int valor){
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();

        for(int c = 0; c < tabuleiro.getTamanho(); c++){
            if(c != coluna && tabuleiro.getNumeros(new Posicao(linha,c)) == valor)
                return true;
        }

        for(int l = 0; l < tabuleiro.getTamanho(); l++){
            if(l != linha && tabuleiro.getNumeros(new Posicao(l,coluna)) == valor)
                return true;
        }

        int bl = (linha/3)*3;
        int bc = (coluna/3)*3;
        for(int l = bl; l < bl + 3; l++){
            for(int c = bc; c < bc + 3; c++){

                if((l != linha || c != coluna) && tabuleiro.getNumeros(new Posicao(l, c)) == valor){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean podeColocar(Posicao posicao, int valor){
        return !temConflito(posicao, valor);
    }

    public void limpar(){
        tabuleiro.limparNumeros();
    }

    public void imprimirTabuleiro(){
        System.out.println("    1 2 3   4 5 6   7 8 9");
        System.out.println("  +-------+-------+-------+");

        for (int l = 0; l < tabuleiro.getTamanho(); l++){
            System.out.print((l + 1) + " |");

            for (int c = 0; c < tabuleiro.getTamanho(); c++){
                Posicao posicao = new Posicao(l, c);

                int valor = tabuleiro.getNumeros(posicao);
                String imprimir = valor == 0 ? "." : Integer.toString(valor);

                if(tabuleiro.ehFixo(posicao)){
                    System.out.print(" " + imprimir);
                } else{
                    System.out.print(" " + imprimir);
                }

                if ((c + 1) % 3 == 0){
                    System.out.print(" |");
                }
            }
            System.out.println();

            if ((l + 1) % 3 == 0){
                System.out.println("  +-------+-------+-------+");
            }
        }
    }
}