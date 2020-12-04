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

    }

    public void setGoal(Coordinate coordinate){
        this.goal = coordinate;
    }


        public Coordinate getGoal () {
            return goal;
        }



        public double getEuclidianDistanceFromGoal (Coordinate newFantome){


            Coordinate temp = closeFakeCoordinate(getGoal());
            double xGF = Math.pow(temp.getX() - newFantome.getX(), 2);
            double yGF = Math.pow(temp.getY() - newFantome.getY(), 2);
            return Math.sqrt(xGF + yGF);


        }
    public double getEuclidianDistanceFromPacMan (Coordinate newFantome){

        Coordinate temp = closeFakeCoordinate(pacMan.getGameImage().getCoordinate());
        Coordinate temp1 = closeFakeCoordinate(newFantome);
        double xGF = Math.pow(temp.getX() - temp1.getX(), 2);
        double yGF = Math.pow(temp.getY() - temp1.getY(), 2);
        return Math.sqrt(xGF + yGF);
    }

    public double getEuclidianDistance (Coordinate coordinate,Coordinate coordinate1){
                double xGF = Math.pow(coordinate.getX() - coordinate1.getX(), 2);
                double yGF = Math.pow(coordinate.getY() - coordinate1.getY(), 2);
                return Math.sqrt(xGF + yGF);

    }


        public int Chase ( ArrayList<Character> listOfWalls){

            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            Coordinate entrance = new Coordinate(4,2);

            switch (bestAction(inFrontSpawn(entrance,charactersFeasable))){
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

    public Character bestAction (ArrayList<Character> character){

            character = removeBackwardDirection(character,oppositeDirection(getLastCharacter()));
            double smallerDistance = 1000000;
            Character chaR = new Character(' ');
            if (character.contains('H')) {
                Coordinate temp = closeFakeCoordinate(getFantome());
                Coordinate Newcoordinate = new Coordinate(temp.getX(),temp.getY() - getGameImage().getValueMove());

                if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistanceFromGoal(Newcoordinate);
                    chaR = 'H';

                }

            }

        if (character.contains('D')) {


            Coordinate temp = closeFakeCoordinate(getFantome());
            Coordinate Newcoordinate = new Coordinate(temp.getX() + getGameImage().getValueMove(), temp.getY());

            if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

                smallerDistance = getEuclidianDistanceFromGoal(Newcoordinate);
                chaR = 'D';
            }

        }
            if (character.contains('B')) {


                Coordinate temp = closeFakeCoordinate(getFantome());
                Coordinate Newcoordinate = new Coordinate(temp.getX(),temp.getY()+getGameImage().getValueMove());

                if (getEuclidianDistanceFromGoal(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistanceFromGoal(Newcoordinate);
                    chaR = 'B';

                }
            }


            if (character.contains('G')) {

                Coordinate temp = closeFakeCoordinate(getFantome());
                Coordinate Newcoordinate = new Coordinate( temp.getX()-getGameImage().getValueMove(),temp.getY());

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
        double pas = 50.0*cases;
        switch (pacMan.getDir()){
            case 0:
                double x0 = pacMan.getCoordinate().getX() ;
                double y0 = pacMan.getCoordinate().getY() ;
                if(x0+ pas <= 419.0){

                    return   new Coordinate(x0+pas,y0);
            }
                return   new Coordinate(x0,y0);
            case 1:
                //caughtInBetween(pacMan).affichageCoord();
                double x1 = pacMan.getCoordinate().getX() ;
                double y1 = pacMan.getCoordinate().getY() ;
                if(y1+pas <= 419.0){
                    return  new Coordinate(x1,y1+pas);
                }
                return  new Coordinate(x1,y1);

            case 2:
                double x2 = pacMan.getCoordinate().getX() ;
                double y2 = pacMan.getCoordinate().getY() ;
                if(x2-pas >=19){
                    return  new Coordinate(x2-pas,y2);
                }
                return  new Coordinate(x2,y2);


            case 3:
                double x3 = pacMan.getCoordinate().getX() ;
                double y3 = pacMan.getCoordinate().getY() ;
                if(y3-pas >= 19.0){
                    return  new Coordinate(x3,y3-pas);
                }
                return  new Coordinate(x3,y3);
        }
        return new Coordinate(-1,-1);

    }
    public Coordinate Transition(FantomeChasseur fantomeRouge){
        Coordinate coordinate = closeFakeCoordinate(fantomeRouge.getGameImage().getCoordinate());
        Coordinate coordinate1 = closeFakeCoordinate(anticipation(pacMan,1));
        double facteurX =  coordinate1.getX() - coordinate.getX() ;
        double facteurY =  coordinate1.getY() - coordinate.getY() ;
        return new Coordinate(coordinate1.getX() +facteurX,coordinate1.getY()+facteurY);
    }

   public Coordinate closeFakeCoordinate(Coordinate coordinate){
       double distance = 100000.0;
       int indexCloser = 0 ;
       for (int i = 0 ; i < mapPacman.getRealCoord().size(); i++){
           if(getEuclidianDistance(coordinate,mapPacman.getRealCoord().get(i))< distance){
               distance = getEuclidianDistance(coordinate,mapPacman.getRealCoord().get(i));
               indexCloser = i;
           }

       }
       return mapPacman.getWrongCoorFromReal(mapPacman.getRealCoord().get(indexCloser)).getPointCoordinate();
   }

   public ArrayList<Character> inFrontSpawn(Coordinate coordinate,ArrayList<Character> characters){

       if (closeFakeCoordinate(getFantome()).compare(coordinate)){
           characters.remove(0);

       }
       return characters;
   }


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
