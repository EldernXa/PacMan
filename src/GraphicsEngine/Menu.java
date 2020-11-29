package GraphicsEngine;

import GamePlay.MenuChoixDifficulte;
import GamePlay.MenuChoixDuJeu;
import GamePlay.MenuDuJeu;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class Menu {
    private static boolean menuChoiceGame;
    private static boolean menuJeu;
    private static boolean menuLevel;

    public Menu(Stage stage, boolean menuJeu, boolean menuLevel){
        Menu.menuChoiceGame = true;
        Menu.menuJeu = menuJeu;
        Menu.menuLevel = menuLevel;
        MenuChoixDuJeu menuChoixDuJeu = new MenuChoixDuJeu(stage);
    }

    public Menu(Stage stage, boolean menuJeu, boolean menuLevel, String gameName){
        Menu.menuChoiceGame = false;
        Menu.menuJeu = menuJeu;
        Menu.menuLevel = menuLevel;
        Game game = new Game(gameName);
        MenuDuJeu menuDuJeu;
        MenuChoixDifficulte menuForLevel;
        Scene scene = new Scene(new VBox());
        stage.setScene(scene);
        if(menuJeu) {
            menuDuJeu = new MenuDuJeu(stage, game, scene);
            stage.setScene(menuDuJeu.getMenuDuJeuScene());
        }
        else if (menuLevel)
            menuForLevel = new MenuChoixDifficulte(stage, game, scene);
        else {
            String nameFileMap = "Map" + game.name;
            try {
                Class <?>classMap = Class.forName("GamePlay." + game.getName() + "." + nameFileMap);
                Class<?>[] parameters = new Class[]{Stage.class, String.class};
                Constructor<?> constructor = classMap.getConstructor(parameters);
                Map map = (Map)constructor.newInstance(stage, "./data/Map/");
                stage.setScene(map.getMapScene());
                System.out.println("test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean getMenuChoiceGame(){
        return menuChoiceGame;
    }

    public static boolean getMenuJeu(){
        return menuJeu;
    }

    public static boolean getMenuLevel(){
        return menuLevel;
    }

}
