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
        return "Giocatore [punteggio=" + score + ", nome=" + nome + "]";
    }
    /**
     * serve per ricevere i punti che ha totalizato il giocatoe in una partita
     * @return restituiscie lo score del giocatore
     */
    public int getScore() {     
        return score;
    }
    /**
     * serve per settare i punti che l'utente ha fatto
     * @param score
     */
    public void setScore(short score) {
        this.score = score;
    }
    /**
     * serve per ricevere il nome che l'utente ha deciso
     * @return restituiscie il nome del giocatore
     */
    public String getNome() {
        return nome;
    }
    /**
     * serve per settare il nome che l'utente ha deciso
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
