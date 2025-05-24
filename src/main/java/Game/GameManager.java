package Game;

public class GameManager {
    private GameStates currentState;

    public GameStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameStates currentState) {
        this.currentState = currentState;
    }
}