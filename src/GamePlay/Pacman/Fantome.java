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
    private boolean isPlayable;
    private boolean isWaiting;

    /**
     *
     * @param path  Permet de charger le sprite désiré
     * @param coordinate Permet de savoir ou charger le sprite
     * @param scene Permet d'ajouter notre fantôme
     * @param map Cela sert à passer des coordonnées réelles à celles de grilles
     * @param pacMan Cela sert à passer les coordonnées du PacMan au fantôme
     *
     *
     */
    public Fantome(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene);
        isPlayable = false;
        this.scene = scene;
        this.mapPacman = map;
        this.pacMan = pacMan;
        this.fantome = getGameImage().getCoordinate();
        setIA();
    }

    /**
     *Cette methode nous permet de connaître
     * si le fantôme est dans l'état jouable ou non .
     * @return un boolean
     */
    public boolean getPlayable(){
        return isPlayable;
    }

    /**
     * Cette méthode initialise le fantôme
     * joueur . Cette méthode n'est appelé qu'une seule fois ;
     * lors de la création du fantôme.
     */
    public void setJ(){
        isPlayable = true;
        clearListAction();
        ReadFileCommandes pacmanControle = new ReadFileCommandes("./data/Controles/Pacman/controles.txt",true);
        for(int i = 0; i<pacmanControle.getDirectionMulti().size(); i++){

            addAction(new ActionContinue(getScene(),
                    pacmanControle.getToucheMulti().get(i), pacmanControle.getxCoordSolo().get(i),
                    pacmanControle.getyCoordMulti().get(i), i, pacmanControle.getDirectionSolo().get(i),
                    valueTps, this));
            changeSpriteAnimation("./data/SpriteMouvement/FantomeJoueur/");
            initAnimation();
        }
    }

    /**
     * Cette méthode initialise le fantôme
     * autonome . Cette méthode n'est appelé qu'une seule fois ;
     * lors de la création du fantôme.
     */
    public void setIA(){
        clearListAction();
        isPlayable = false;
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, -1, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 0, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 1, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 2, mapPacman, pacMan));
        addAction(new ActionContinueFantome(getGameImage(), getScene(), valueTps, this, 3, mapPacman, pacMan));
        initSpriteAnimation();
        initAnimation();
    }


    /**
     *
     * @return La scène actuel
     */
    public Scene getScene(){
        return scene;
    }

    /**
     *
     * @param direction
     *  Cette méthode permet d'attribuer une direction
     *  au fantôme.
     */
    public static void setDirection(int direction) {
        Fantome.direction = direction;
    }

    /**
     *
     * @return direction
     * Cette méthode permet  d'obtenir,
     * la direction du fantôme.
     */
    public static int getDirection() {
        return direction;
    }

    /**
     *
     * @return un boolean
     * Cette méthode permet de savoir ,
     * si le fantôme est dans un état d'attente ou non .
     */
    public boolean isWaiting() {
        return isWaiting;
    }

    /**
     *
     * @param waiting
     * Cette méthode permet de signaler
     * au fantôme s'il doit attendre ou non .
     */
    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    /**
     * Cette méthode permet de d'affecter
     * un objectif aléatoire dans la carte.
     */
    public void setRandomGoal() {

        Random random = new Random();

        int randomValue = random.nextInt(72);
      setGoal(mapPacman.getRealCoord().get(randomValue));

    }

    /**
     *
     * @param coordinate
     * Cette méthode permet d'affecter
     * un objectif , selon la coordonnée donnée .
     *
     */

    public void setGoal(Coordinate coordinate){
        this.goal = coordinate;
    }

    /**
     *
     * @return Les coordonnées de l'objectif
     *
     */
     public Coordinate getGoal () {
            return goal;
        }


    /**
     *
     * @param newFantome
     * @return La distance entre le fantôme et son objectif .
     */
        public double getEuclidianDistanceFromGoal (Coordinate newFantome){


            Coordinate temp = closeFakeCoordinate(getGoal());
            double xGF = Math.pow(temp.getX() - newFantome.getX(), 2);
            double yGF = Math.pow(temp.getY() - newFantome.getY(), 2);
            return Math.sqrt(xGF + yGF);


        }

    /**
     *
     * @param newFantome
     * @return La distance entre le fantôme et Pacman .
     */
    public double getEuclidianDistanceFromPacMan (Coordinate newFantome){

        Coordinate temp = closeFakeCoordinate(pacMan.getGameImage().getCoordinate());
        Coordinate temp1 = closeFakeCoordinate(newFantome);
        double xGF = Math.pow(temp.getX() - temp1.getX(), 2);
        double yGF = Math.pow(temp.getY() - temp1.getY(), 2);
        return Math.sqrt(xGF + yGF);
    }

    /**
     *
     * @param coordinate
     * @param coordinate1
     * @return La distance entre deux coordonnées .
     */
    public double getEuclidianDistance (Coordinate coordinate,Coordinate coordinate1){
                double xGF = Math.pow(coordinate.getX() - coordinate1.getX(), 2);
                double yGF = Math.pow(coordinate.getY() - coordinate1.getY(), 2);
                return Math.sqrt(xGF + yGF);

    }

    /**
     *
     * @param listOfWalls
     * @return Le numéro de l'action à effectuer
     * pour atteindre son objectif.
     */
        public int Chase ( ArrayList<Character> listOfWalls){

            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            Coordinate entrance = new Coordinate(4,2);
            if(isWaiting()){
                return -1;
            }

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


    /**
     *
     * @param list (la liste des murs à un endroit donné)
     * @return Une liste des actions possibles à un endroit donné
     */

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

    /**
     *
     * @return Le dernier character utilisé.
     */
    public Character getLastCharacter() {
        return lastCharacter;
    }

    /**
     *
     * @param character
     * @return Le meilleur choix d'action possible.
     * Avec un ordre de priorité si les actions sont à
     * la même distance.(Haut>Droite>Bas>Gauche)
     */
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

    /**
     *
     * @param visualObjects
     * @return Faux s'il n'y à pas eu de collision
     * Autrement cela implique soit la mort du Pacman
     * ou bien du fantôme.
     */
    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        if(visualObjects!=null && visualObjects.getClass()==PacMan.class){
            PacMan pacman = ((PacMan) visualObjects);
            if(pacman.isSuperPacman()){
                death();

            }
            else {
                pacman.death();
                death();
            }

        }

        return false;
    }

    /**
     * Cette méthode effectue la réinitialisation
     * du fantôme, tels que les coordonnées et l'animation.
     */
    public void death(){
        setCoordinate(super.getGameImage().getCoordInit());
        super.initAnimation();

    }

    /**
     *
     * @param coordinate
     * Cette méthode permet d'affecter au fantôme des coordonnées.
     */
    public void setCoordinate(Coordinate coordinate) {
        this.fantome = new Coordinate(coordinate.getX(),coordinate.getY());
        super.getImageView().setX(coordinate.getX());
        super.getImageView().setY(coordinate.getY());
        super.move(coordinate.getX(),coordinate.getY());
    }

    /**
     *
     * @param character
     * @return La direction opposé ,
     * d'une direction donné.
     */
    public Character oppositeDirection(char character){

        char newChar = new Character( ' ');
        if(character == 'H')newChar = 'B';
        if(character == 'B')newChar = 'H';
        if(character == 'D')newChar = 'G';
        if(character == 'G')newChar = 'D';
        return newChar;
    }

    /**
     *
     * @param characters
     * @param character
     * @return Une nouvelle arraylist sans le character opposé.
     */
    public ArrayList<Character> removeBackwardDirection(ArrayList<Character> characters,Character character){
        characters.remove(character);
        return characters;
    }

    /**
     *
     * @return La coordonné du fantôme.
     */
    public Coordinate getFantome() {
        return fantome;
    }

    /**
     *
     * @param lastCharacter
     * Enregistre le dernier character utilisé
     */
    public void setLastCharacter(char lastCharacter) {
        this.lastCharacter = lastCharacter;
    }

    /**
     *
     * @param pacMan
     * @param cases
     * @return La coordonnée anticipé de x cases du Pacman.
     */
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


    /**
     *
     * @param fantomeRouge
     * @return Une coordonnée .
     * On regarde la coordonnée ou se trouvera le Pacman dans une cases,
     * puis on regarde le vecteur depuis cette coordonnée et celle du fantôme
     * chasseur , et on multiplie ce vecteur par deux afin d'obtenir la coordonnée désiré.
     */
    public Coordinate Transition(FantomeChasseur fantomeRouge){
        Coordinate coordinate = closeFakeCoordinate(fantomeRouge.getGameImage().getCoordinate());
        Coordinate coordinate1 = closeFakeCoordinate(anticipation(pacMan,1));
        double facteurX =  coordinate1.getX() - coordinate.getX() ;
        double facteurY =  coordinate1.getY() - coordinate.getY() ;
        return new Coordinate(coordinate1.getX() +facteurX,coordinate1.getY()+facteurY);
    }

    /**
     *
     * @param coordinate
     * @return Une coordonné
     * On prend une coordonnée réelle
     * et on renvoie la coordonnée
     * de la grille la plus proche.
     */
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

    /**
     *
     * @param coordinate
     * @param characters
     * @return Les actions possbiles lorsque le fantôme
     * se retrouve devant le spawn.
     * Afin d'empecher le fantôme d'y retourner.
     */
   public ArrayList<Character> inFrontSpawn(Coordinate coordinate,ArrayList<Character> characters){

       if (closeFakeCoordinate(getFantome()).compare(coordinate)){
           characters.remove(0);

       }
       return characters;
   }


}
