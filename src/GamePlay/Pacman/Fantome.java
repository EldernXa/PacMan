package GamePlay.Pacman;
    import GraphicsEngine.*;


    import PhysicsEngine.ActionContinue;
    import PhysicsEngine.MouvingObject;
    import ReadFile.ReadFileCommandes;
    import javafx.scene.Scene;

    import java.util.ArrayList;
import java.util.Random;

public class Fantome extends MouvingObject {
    private float valueTps = (float) 10;
    private Coordinate goal;
    private Coordinate fantome;
    //private Timeline timeline;
    //private int indTimeline;
    private char lastCharacter;
    private PacMan pacMan;
    private static int direction;
    private MapPacman mapPacman;
    private final Scene scene;

    public Fantome(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene);
        this.scene = scene;
        //setGoal(pacMan.getCoordinate());
        this.mapPacman = map;
        this.pacMan = pacMan;
        this.fantome = getGameImage().getCoordinate();
        //new ActionContinueFantome(getGameImage(),scene,valueTps,this,map,Chase(pacmanCoordinate,map.getWrongCoorFromReal(getFantome()).getListOfWalls()));
        //Test(scene, map);
        /*setRandomGoal();
        addAction( new ActionContinueFantome(getGameImage(),scene,valueTps,this,0,map,pacMan));
        addAction( new ActionContinueFantome(getGameImage(),scene,valueTps,this,1,map,pacMan));
        addAction( new ActionContinueFantome(getGameImage(),scene,valueTps,this,2,map,pacMan));
        addAction( new ActionContinueFantome(getGameImage(),scene,valueTps,this,3,map,pacMan));*/
        setJ();
    }

    public void setJ(){
        clearListAction();
        ReadFileCommandes pacmanControle = new ReadFileCommandes("./data/Controles/Pacman/controles.txt",true);
        for(int i = 0; i<pacmanControle.getDirectionMulti().size(); i++){

            addAction(new ActionContinue(getScene(),
                    pacmanControle.getToucheMulti().get(i), pacmanControle.getxCoordSolo().get(i),
                    pacmanControle.getyCoordMulti().get(i), i, pacmanControle.getDirectionSolo().get(i),
                    valueTps, this));
        }
    }

    public void setIA(){
        setRandomGoal();
        clearListAction();
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 0, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 1, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 2, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 3, mapPacman, pacMan));
    }

    public Scene getScene(){
        return scene;
    }

    public static void setDirection(int direction) {
        Fantome.direction = direction;
    }

    public static int getDirection() {
        return direction;
    }



    public void setRandomGoal() {

        Random random = new Random();
        int randomValueX = random.nextInt(8);
        int randomValueY = random.nextInt(9);
        double valueX = randomValueX + 0.0;
        double valueY = randomValueY + 0.0;
        this.goal = new Coordinate(valueX,valueY);
    }

    public void setGoal(Coordinate coordinate){
        this.goal = coordinate;
    }


        public Coordinate getGoal () {
            return goal;
        }



        public double getEuclidianDistance (Coordinate coordinate){

            double xGF = Math.pow(mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX() - coordinate.getX(), 2);
            double yGF = Math.pow(mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY() - coordinate.getY(), 2);
            return Math.sqrt(xGF + yGF);


        }
        public boolean objectifReach(Coordinate coordinate){
            if(getEuclidianDistance(coordinate) == 0.0){
                return true;
            }else{
                return false;
                }
        }

        public int Chase ( ArrayList<Character> listOfWalls){

            mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().affichageCoord();
            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            if(objectifReach(getGoal())){
                System.out.println("Objectif atteint");
                setRandomGoal();
            }else if (getEuclidianDistance(mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate()) <= 4.0) {

                switch (bestAction(pacMan.getCoordinate(), charactersFeasable)) {
                    case 'H':
                        setLastCharacter('H');
                        int temp = 3;
                        return temp;
                    case 'B':
                        setLastCharacter('B');
                        int temp1 = 1;
                        return temp1;
                    case 'D':
                        setLastCharacter('D');
                        int temp2 = 0;
                        return temp2;
                    case 'G':
                        setLastCharacter('G');
                        int temp3 = 2;
                        return temp3;

                }

            } else {
                switch (bestAction(getGoal(), charactersFeasable)){
                    case 'H':
                        setLastCharacter('H');
                        int temp = 3;
                        return temp;
                    case 'B':
                        setLastCharacter('B');
                        int temp1 = 1;
                        return temp1;
                    case 'D':
                        setLastCharacter('D');
                        int temp2 = 0;
                        return temp2;
                    case 'G':
                        setLastCharacter('G');
                        int temp3 = 2;
                        return temp3;

                }


            }
            int nulL = -1;
            return nulL;
        }

        public ArrayList<Character> actionPossible (ArrayList <Character> list) {
            ArrayList<Character> characters = new ArrayList<>();

            if (list.contains('H') && list.contains('G') && list.contains('B')) {

                characters.add('D');
                return characters;


            }
            if (list.contains('H') && list.contains('D') && list.contains('B')) {
                characters.add('G');
                return characters;

            }
            if (list.contains('H') && list.contains('D') && list.contains('G')) {
                characters.add('B');
                return characters;

            }
            if (list.contains('B') && list.contains('D') && list.contains('G')) {
                characters.add('H');
                return characters;

            }
            if (list.contains('H') && list.contains('G')) {
                characters.add('B');
                characters.add('D');
                return characters;
            }
            if (list.contains('H') && list.contains('D')) {
                characters.add('B');
                characters.add('G');
                return characters;


            }
            if (list.contains('H') && list.contains('B')) {
                characters.add('G');
                characters.add('D');
                return characters;


            }
            if (list.contains('B') && list.contains('G')) {
                characters.add('H');
                characters.add('D');
                return characters;

            }
            if (list.contains('B') && list.contains('D')) {
                characters.add('H');
                characters.add('G');
                return characters;

            }
            if (list.contains('G') && list.contains('D')) {
                characters.add('H');
                characters.add('B');
                return characters;

            }
            if (list.contains('H')) {
                characters.add('B');
                characters.add('D');
                characters.add('G');
                return characters;
            }
            if (list.contains('B')) {
                characters.add('H');
                characters.add('D');
                characters.add('G');
                return characters;

            }

            if (list.contains('G')) {
                characters.add('H');
                characters.add('D');
                characters.add('B');
                return characters;

            }

            if (list.contains('D')) {
                characters.add('H');
                characters.add('G');
                characters.add('B');
                return characters;

            }

            return characters;

        }

    public Character getLastCharacter() {
        return lastCharacter;
    }

    public Character bestAction (Coordinate coordinate, ArrayList<Character> character){
            character = removeBackwardDirection(character,oppositeDirection(getLastCharacter()));
            double smallerDistance = 1000000;
            Character chaR = new Character(' ');
            if (character.contains('H')) {

                double realNewY = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY()  - getGameImage().getValueMove();
                double  newY = Math.abs(realNewY);
                //double newY = coordinate.getY() - getGameImage().getValueMove();
                Coordinate Newcoordinate = new Coordinate(mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX(), newY);

                //System.out.println(smallerDistance);
                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'H';

                }

            }
            if (character.contains('B')) {

                double realNewY = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY() + getGameImage().getValueMove();
                double  newY = Math.abs(realNewY);
                //double newY = coordinate.getY() + Double.valueOf(getGameImage().getValueMove());
                Coordinate Newcoordinate = new Coordinate(mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX(), newY);

                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'B';

                }
            }

            if (character.contains('D')) {

                double realNewX = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX() - getGameImage().getValueMove();
                double  newX = Math.abs(realNewX);
                //double newX = coordinate.getX() - Double.valueOf(getGameImage().getValueMove());
                Coordinate Newcoordinate = new Coordinate(newX, mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY());

                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'D';
                }

            }
            if (character.contains('G')) {

                double realNewX = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX() + getGameImage().getValueMove();
                double  newX = Math.abs(realNewX);
                //double newX = coordinate.getX() + Double.valueOf(getGameImage().getValueMove());
                Coordinate Newcoordinate = new Coordinate(newX, mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY());

                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    chaR = 'G';
                }

            }

            return chaR;

        }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        if(visualObjects!=null && visualObjects.getClass()==PacMan.class){
            PacMan pacman = ((PacMan) visualObjects);
            if(pacman.isSuperPacman()){
                death();
            }
            else {
                pacman.death();
            }

        }

        return false;
    }
    public void death(){
        setCoordinate(super.getGameImage().getCoordInit());
        super.initAnimation();

    }

    public void setCoordinate(Coordinate coordinate) {
        this.fantome = new Coordinate(coordinate.getX(),coordinate.getY());
        super.getImageView().setX(coordinate.getX());
        super.getImageView().setY(coordinate.getY());
        super.move(coordinate.getX(),coordinate.getY());
    }

    public Character oppositeDirection(char character){

        char newChar = new Character( ' ');
        if(character == 'H')newChar = 'B';
        if(character == 'B')newChar = 'H';
        if(character == 'D')newChar = 'G';
        if(character == 'G')newChar = 'D';
        return newChar;
    }

    public ArrayList<Character> removeBackwardDirection(ArrayList<Character> characters,Character character){
        characters.remove(character);
        return characters;
    }

    public Coordinate getFantome() {
        return fantome;
    }

    public void setLastCharacter(char lastCharacter) {
        this.lastCharacter = lastCharacter;
    }
}



