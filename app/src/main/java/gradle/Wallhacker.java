package gradle;

public class Wallhacker extends Ghost {

    public Wallhacker(double x, double y, String imagePath) {
        super(x, y, imagePath, "Wallhacker");
    }

    public Wallhacker(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }

    public void wallhackerMove() {
    }

    public boolean checkWhereEnemy(){
        for (MainCharacter main_character : Map.main_characters){
            double y_difference = Math.abs(this.getMiddleY() - main_character.getMiddleY()); // wartość bezwzględna
            double x_difference = Math.abs(this.getMiddleX() - main_character.getMiddleX());
            if((this.getMiddleX() >= main_character.getMiddleX()) && x_difference>= 20){
                this.setMovingDirection("LEFT");
            } else if((this.getMiddleX() < main_character.getMiddleX()) && x_difference> 20){
                this.setMovingDirection("RIGHT");
            }
            else if(this.getMiddleY() >= main_character.getMiddleY()){
                this.setMovingDirection("UP");
            } else if (this.getMiddleY() < main_character.getMiddleY()){
                this.setMovingDirection("DOWN");
            }
        }
        return true;
    }
}
