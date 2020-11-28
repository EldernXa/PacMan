package GraphicsEngine;

import javafx.stage.Stage;

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
