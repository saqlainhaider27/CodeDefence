package Game;

public class GameEconomics {
    private int points;
    public void addPoints(int amount){
        points+= amount;
        System.out.println(points);
    }
    public void removePoints(int amount){
        points-= amount;
        System.out.println(points);
    }

    public int getPoints() {
        return points;
    }
}
