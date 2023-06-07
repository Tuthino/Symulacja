package gradle;


// LOOKER GHOST CLASS
public class Looker extends Ghost {
    // Default constructor with "Looker" name
    public Looker(double x, double y, String imagePath) {
        super(x, y, imagePath, "Looker");
    }

    public Looker(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }

    public boolean checkIfEnemyIsDiagonal() {
        boolean is_diagonal = false;

        for (MainCharacter main_character : Map.main_characters) {
            // Check poziomo XD
            double y_difference = Math.abs(this.getY() - main_character.getY());                                        // wartość bezwzględna
            double x_difference = Math.abs(this.getX() - main_character.getX());

            if(x_difference < (this.getWidth() + main_character.getWidth()) || (y_difference < (this.getHeight()+main_character.getHeight()))){
                scaring();
            }   

            // Character is above us
            if ( (x_difference <= Map.character_size) && (this.getMiddleY() > main_character.getMiddleY()) ){
                this.setMovingDirection("UP");
                is_diagonal = true;
            } else if ( (x_difference <= Map.character_size) && (this.getMiddleY() < main_character.getMiddleY()) ){
                this.setMovingDirection("DOWN");
                is_diagonal = true;
            } else if ( (y_difference <= Map.character_size) && (this.getMiddleX() < main_character.getMiddleX()) ){
                this.setMovingDirection("RIGHT");
                is_diagonal = true;
            } else if ( (y_difference <= Map.character_size) && (this.getMiddleX() > main_character.getMiddleX()) ){
                this.setMovingDirection("LEFT");
                is_diagonal = true;
            }
                
            // System.out.println("IS diagonal?: " + is_diagonal + this.getMovingDirection());
            // System.out.println("mainCharacter middle X: "+main_character.getMiddleX()+" Y: "+main_character.getMiddleY()+" GHOST middle X: "+this.getMiddleX()+" Y: "+this.getMiddleY());
            // System.out.println("mainCharacter X: "+main_character.getX()+" Y: "+main_character.getY()+" GHOST X: "+this.getX()+" Y: "+this.getY());
            // System.out.println("difference X: "+x_difference+" Y: "+y_difference);
        }
        return is_diagonal;
    }
}