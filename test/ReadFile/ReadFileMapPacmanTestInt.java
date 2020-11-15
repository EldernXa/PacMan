package ReadFile;

import GraphicsEngine.MenuChoixDifficulte;
import GraphicsEngine.MenuChoixDuJeu;
import GraphicsEngine.MenuDuJeu;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileMapPacmanTestInt {

    Pane root = new Pane();
    Scene scene = new Scene(root,655,365);
    private ReadFileMapPacman readFile  = new ReadFileMapPacman(scene, root, "./data/Map/PacmanMap1");
    Stage primaryStage;
    MenuChoixDuJeu menuChoixDuJeu = new MenuChoixDuJeu(primaryStage);
    MenuDuJeu menuDuJeu = new MenuDuJeu(menuChoixDuJeu.getStage(),menuChoixDuJeu.getCurrentGame(),menuChoixDuJeu.getMenuScene());
    MenuChoixDifficulte menuChoixDifficulte = new MenuChoixDifficulte(menuDuJeu.getStage(),menuDuJeu.getJeu(),menuDuJeu.getMenuDuJeuScene());

    @org.junit.jupiter.api.Test
    void testConstructeur(){
        assertDoesNotThrow(()->{
            readFile = new ReadFileMapPacman(menuChoixDifficulte.getScene(), root, "./data/Map/PacmanMap1");
        });
    }

    @org.junit.jupiter.api.Test
    void getFile() {

        assertNotNull(readFile.getFile());
        assertEquals(readFile.getFile().getClass(), String[].class);
    }

    @org.junit.jupiter.api.Test
    void getLine() {

        assertNotNull(readFile.getLine());
        assertEquals(readFile.getLine().getClass(), ArrayList.class);
    }

    @org.junit.jupiter.api.Test
    void getVisualObjects() {
        assertNotNull(readFile.getVisualObjects());
        assertEquals(readFile.getVisualObjects().getClass(), ArrayList.class);
    }

    @org.junit.jupiter.api.Test
    void getScene() {
        assertEquals(readFile.getScene(), scene);
    }

    @org.junit.jupiter.api.Test
    void getPane() {
        assertEquals(readFile.getPane(), root);
    }

    @org.junit.jupiter.api.Test
    void getWidth() {
        assertEquals(655, readFile.getWidth());
        assertDoesNotThrow(()->{
            readFile.getWidth();
        });
    }

    @org.junit.jupiter.api.Test
    void getHeight() {
        assertEquals(365, readFile.getHeight());
        assertDoesNotThrow(()->{
            readFile.getHeight();
        });
    }
}