/*
package ReadFile;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileMapPacmanTest {

    Pane root = new Pane();
    Scene scene = new Scene(root,655,365);
    private ReadFileMapPacman readFile  = new ReadFileMapPacman(scene, root, "./data/Map/PacmanMap1");

    @org.junit.jupiter.api.Test
    void testConstructeur(){
        assertDoesNotThrow(()->{
            readFile = new ReadFileMapPacman(scene, root, "./data/Map/PacmanMap1");
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
}*/
