package presenter;

public class InactivePlayer extends PlayerState {
    /**
     * singleton
     */
    private InactivePlayer(){}
    private static InactivePlayer instance = new InactivePlayer();
    public static InactivePlayer getInstance(){
        return instance;
    }

  //intentionally featureless
}
