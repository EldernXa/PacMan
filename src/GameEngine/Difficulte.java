package GameEngine;

import javafx.scene.Scene;

/**
 * Représente les difficultés.
 */
public class Difficulte {
    String name;
    Scene scene;


    /**
     *
     * @param name nom de la difficultés.
     */
    public Difficulte(String name) {
        this.name = name;
    }

    /**
     *
     * @return nom de la difficulté.
     */
    public String getName() {
        return name;
    }

    public Scene getScene() {
        return scene;
    }
}
