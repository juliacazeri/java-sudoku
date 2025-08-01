package model;

public class Tabuleiro{
    private final int tamanho = 9;
    private int[][] numeros;
    private boolean[][] numeroFixo;

    public Tabuleiro(){
        numeros = new int[tamanho][tamanho];
        numeroFixo = new boolean[tamanho][tamanho];
    }

    public int getNumeros(Posicao pos){
        return numeros[pos.getLinha()][pos.getColuna()];
    }

    public void setNumeros(Posicao pos, int numero, boolean ehFixo){
        numeros[pos.getLinha()][pos.getColuna()] = numero;

        if(ehFixo){
            numeroFixo[pos.getLinha()][pos.getColuna()] = true;
        }
    }

    public int getTamanho(){
        return tamanho;
    }

    public boolean ehFixo(Posicao pos){
        return numeroFixo[pos.getLinha()][pos.getColuna()];
    }

    public void limparNumero(Posicao pos){
        if (!numeroFixo[pos.getLinha()][pos.getColuna()]){
            numeros[pos.getLinha()][pos.getColuna()] = 0;
        }
    }

    public void limparNumeros(){
        for(int l = 0; l < tamanho; l++){
            for(int c = 0; c < tamanho; c++){
                if(!numeroFixo[l][c]) numeros[l][c] = 0;
            }
        }
    }
}