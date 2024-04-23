package gioco.model;

public class Model {
    public Kiriko kiriko;
    public Bancone bancone;
    public Quest quest;

    public Model(){
        bancone= new Bancone();
        quest= new Quest((short) 1);
        kiriko= new Kiriko(quest, bancone);
    }
}
