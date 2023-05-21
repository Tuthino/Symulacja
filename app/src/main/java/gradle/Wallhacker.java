package gradle;

public class Wallhacker extends Ghost {
    public Wallhacker(double x, double y, String imagePath) {
        super(x, y, imagePath, "Wallhacker");
    }

    public Wallhacker(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }



    public boolean checkWhereEnemy(){
        for (MainCharacter main_character : Map.main_characters){
            if  (this.getMiddleY() > main_character.getMiddleY()){
                this.setMovingDirection("UP");
                break;
            } else if (  (this.getMiddleY() < main_character.getMiddleY()) ){
                this.setMovingDirection("DOWN");
                break;
            } else if (  (this.getMiddleX() < main_character.getMiddleX()) ){
                this.setMovingDirection("RIGHT");
                break;
            } else if ( (this.getMiddleX() > main_character.getMiddleX()) ){
                this.setMovingDirection("LEFT");
                break;
            }

    }
    return true;
}
}
