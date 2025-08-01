package model;

public class Posicao{
    private int linha;
    private int coluna;

    public Posicao(int linha, int coluna){
        if(linha < 0 || linha >= 9 || coluna < 0 || coluna >= 9){
            throw new IllegalArgumentException("A posição está fora dos limites: " + (linha + 1) + "," + (coluna + 1) + ".");
        }
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }
}