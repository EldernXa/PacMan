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
    private char lastCharacter;
    private PacMan pacMan;
    private static int direction;
    private MapPacman mapPacman;
    private final Scene scene;

    public Fantome(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene);
        this.scene = scene;
        this.mapPacman = map;
        this.pacMan = pacMan;
        this.fantome = getGameImage().getCoordinate();
        //setJ();
        setIA();
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
        //setRandomGoal();
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

        int randomValue = random.nextInt(72);
      setGoal(mapPacman.getRealCoord().get(randomValue));
        /*int randomValueY = random.nextInt(9);
        double valueX = randomValueX + 0.0;
        double valueY = randomValueY + 0.0;
        this.goal = new Coordinate(valueX,valueY);*/
    }

    public void setGoal(Coordinate coordinate){
        this.goal = coordinate;
    }


        public Coordinate getGoal () {
            return goal;
        }



        public double getEuclidianDistanceFromGoal (Coordinate newFantome){


            for (int i = 0 ; i < mapPacman.getRealCoord().size();i++){
                if(mapPacman.getRealCoord().get(i).compare(pacMan.getCoordinate())){
                    double xGF = Math.pow(mapPacman.getWrongCoorFromReal(getGoal()).getPointCoordinate().getX() - newFantome.getX(), 2);
                    double yGF = Math.pow(mapPacman.getWrongCoorFromReal(getGoal()).getPointCoordinate().getY() - newFantome.getY(), 2);
                    return Math.sqrt(xGF + yGF);

                }
            }


           return 0;
        }
    public double getEuclidianDistanceFromPacMan (Coordinate newFantome){


        for (int i = 0 ; i < mapPacman.getRealCoord().size();i++){
            if(mapPacman.getRealCoord().get(i).compare(pacMan.getCoordinate())){

                double xGF = Math.pow(mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getX() - mapPacman.getWrongCoorFromReal(newFantome).getPointCoordinate().getX(), 2);
                double yGF = Math.pow(mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getY() - mapPacman.getWrongCoorFromReal(newFantome).getPointCoordinate().getY(), 2);
                //System.out.println(xGF+yGF);
                return Math.sqrt(xGF + yGF);

            }
        }


        return 0;
    }
        public boolean objectifReach(Coordinate coordinate){
            if(getEuclidianDistanceFromGoal(coordinate) == 0.0){
                return true;
            }else{
                return false;
                }
        }

        public int Chase ( ArrayList<Character> listOfWalls){

            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
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
                //double newY = coordinate.getY() - 50.0;
                Coordinate Newcoordinate = new Coordinate(mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX(), newY);
                //Coordinate Newcoordinate = new Coordinate(coordinate.getX(), newY);

                //System.out.println(smallerDistance);
                //System.out.println(getEuclidianDistance(Newcoordinate));
                if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistanceFromGoal(Newcoordinate);
                    chaR = 'H';

                }

            }

        if (character.contains('D')) {

            double realNewX = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX() + getGameImage().getValueMove();
            double  newX = Math.abs(realNewX);
            //double newX = coordinate.getX() - 50.0;
            Coordinate Newcoordinate = new Coordinate(newX, mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY());
            //Coordinate Newcoordinate = new Coordinate( newX,coordinate.getY());
            //System.out.println(getEuclidianDistance(Newcoordinate));
            if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

                smallerDistance = getEuclidianDistanceFromGoal(Newcoordinate);
                chaR = 'D';
            }

        }
            if (character.contains('B')) {

                double realNewY = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY() + getGameImage().getValueMove();
                double  newY = Math.abs(realNewY);
                //double newY = coordinate.getY() + Double.valueOf(getGameImage().getValueMove());
                Coordinate Newcoordinate = new Coordinate(mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX(), newY);
                //Coordinate Newcoordinate = new Coordinate(coordinate.getX(), newY);
                //System.out.println(getEuclidianDistance(Newcoordinate));
                if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistanceFromGoal(Newcoordinate);
                    chaR = 'B';

                }
            }


            if (character.contains('G')) {

                double realNewX = mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getX() - getGameImage().getValueMove();
                double  newX = Math.abs(realNewX);
                //double newX = coordinate.getX() + Double.valueOf(getGameImage().getValueMove());
                Coordinate Newcoordinate = new Coordinate(newX, mapPacman.getWrongCoorFromReal(getFantome()).getPointCoordinate().getY());
                //Coordinate Newcoordinate = new Coordinate( newX,coordinate.getY());
                //System.out.println(getEuclidianDistance(Newcoordinate));
                if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

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
    public Coordinate anticipation(PacMan pacMan,int cases){
        //pacMan.getCoordinate().affichageCoord();
        switch (pacMan.getDir()){
            case 0:
                //caughtInBetween(pacMan).affichageCoord();
                double x0 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getX() ;
                double y0 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getY() ;
                return   new Coordinate(x0+cases,y0);
                /*double x0 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y0 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return   new Coordinate(x0+cases,y0);*/
            case 1:
                //caughtInBetween(pacMan).affichageCoord();
                double x1 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getX() ;
                double y1 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x1,y1+cases);
                /*double x1 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y1 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x1,y1+cases);*/


            case 2:
                pacMan.getCoordinate().affichageCoord();
                double x2 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getX() ;
                double y2 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x2-cases,y2);
                /*ouble x2 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y2 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x2-cases,y2);*/

            case 3:
                //caughtInBetween(pacMan).affichageCoord();
                double x3 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getX() ;
                double y3 = mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x3,y3-cases);
               /* double x3 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y3 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x3,y3-cases);*/


        }
        return new Coordinate(-1,-1);

    }
    public Coordinate Transition(FantomeChasseur fantomeRouge){
        //fantomeRouge.getFantome().affichageCoord();
        double facteurX = mapPacman.getWrongCoorFromReal(fantomeRouge.getGameImage().getCoordinate()).getPointCoordinate().getX() -  anticipation(pacMan,1).getX();
        double facteurY = mapPacman.getWrongCoorFromReal(fantomeRouge.getGameImage().getCoordinate()).getPointCoordinate().getY() -  anticipation(pacMan,1).getY();
        return new Coordinate(anticipation(pacMan,1).getX()+facteurX,anticipation(pacMan,1).getY()+facteurY);
    }

    /*public Coordinate caughtInBetween(MouvingObject mouvingObject){

                return mapPacman.getWrongCoorFromReal(mouvingObject.getGameImage().getCoordinate()).getPointCoordinate();


           switch (mouvingObject.getDir()){
                case 0:
                    double newXd = mouvingObject.getGameImage().getCoordinate().getX() + 50.0;
                    return new Coordinate(newXd,mouvingObject.getGameImage().getCoordinate().getY());
                case 1:
                    double newYb = mouvingObject.getGameImage().getCoordinate().getY() + 50.0;
                    return new Coordinate(mouvingObject.getGameImage().getCoordinate().getX(),newYb);
                case 2:
                    double newXg = mouvingObject.getGameImage().getCoordinate().getX() - 50.0;
                    return new Coordinate(newXg,mouvingObject.getGameImage().getCoordinate().getY());
                case 3:
                    double newYh = mouvingObject.getGameImage().getCoordinate().getY() - 50.0;
                    return new Coordinate(mouvingObject.getGameImage().getCoordinate().getX(),newYh);
                default:
                    System.out.println("ici");
                    return null;
            }





    }*/

   /* public Character priorityMove(ArrayList<Character> characters){
        if(characters.contains('H')){
            return 'H';
        }
        if(characters.contains('G')){
            return 'G';
        }
        if(characters.contains('B')){
            return 'B';
        }
        if(characters.contains('D')){
            return 'D';
        }
    return '_';
    }*/


}
































































/*mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().affichageCoord();
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
            return nulL;*/
