package gioco.model;

public class ModelGioco {
    public Kiriko kiriko;
    public Bancone bancone;
    public Quest quest;

    public ModelGioco(){
        bancone= new Bancone();
        quest= new Quest((short) 1);
        kiriko= new Kiriko(quest, bancone);
    }
}
