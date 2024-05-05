package gioco.model;

public class Pane {
    @SuppressWarnings("unused")
    private byte puntiPane;
    @SuppressWarnings("unused")
    private TipoPane tipoPane;
    public static final byte BRIOCHE_VALUE=15;
    public static final byte BAGUETTE_VALUE=5;
    public static final byte DONUT_VALUE=50;
    
    public Pane(TipoPane tipoPane) {
        this.tipoPane = tipoPane;
        switch (tipoPane) {
            case BAGUETTE:
                puntiPane=BAGUETTE_VALUE;

                break;
            case BRIOCHE:
                puntiPane=BRIOCHE_VALUE;

                break;
            case DONUT:
                puntiPane=DONUT_VALUE;

                break;
            default:
                break;
        }
    }

}
