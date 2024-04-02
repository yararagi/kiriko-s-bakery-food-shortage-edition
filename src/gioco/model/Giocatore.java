package gioco.model;

import java.io.Serializable;

public class Giocatore implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private int score;
    private String nome;
    //transient altro che serve per giocare boh;
    public Giocatore(int score, String nome) {
        this.score = score;
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "Giocatore [score=" + score + ", nome=" + nome + "]";
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
