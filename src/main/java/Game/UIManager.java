package Game;

import Core.Canvas.Canvas;
import Core.Canvas.Elemets.Button;
import Core.Canvas.Elemets.Image;
import Main.Launcher;
import Utils.Interfaces.IBehaviour;

public class UIManager implements IBehaviour {
    private Canvas canvas;
    public UIManager(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void start() {
        Button attackSpeedB = new Button();
        attackSpeedB.setPosition(50,50);
        attackSpeedB.setScale(50,50);

        Button healB = new Button();
        healB.setPosition(150,50);
        healB.setScale(50,50);

        attackSpeedB.onButtonClicked = () -> {
            if (CodeDefense.getGameEconomics().getPoints() >= 100){
                CodeDefense codeDefense = (CodeDefense) Launcher.getGame();
                codeDefense.getTurret().shooter.shootDelay *= 1/2f;
                CodeDefense.getGameEconomics().removePoints(100);
            }
        };
        healB.onButtonClicked = () -> {
            if (CodeDefense.getGameEconomics().getPoints() >= 100){
                CodeDefense codeDefense = (CodeDefense) Launcher.getGame();
                codeDefense.getTurret().healthComponent.setHealth(codeDefense.getTurret().healthComponent.health * 2);
                CodeDefense.getGameEconomics().removePoints(100);
            }
        };
        canvas.addUIObject(attackSpeedB);
        canvas.addUIObject(healB);

    }

    @Override
    public void update() {

    }
}
