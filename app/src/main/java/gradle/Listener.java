package gradle;

public class Listener extends Ghost {
    public Listener(double x, double y, String imagePath) {
        super(x, y, imagePath, "Listener");
    }

    public Listener(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }

    private double listening_size;


    public boolean checkIfNearby(){
        boolean is_nearby = false;
        for (MainCharacter main_character : Map.main_characters){
            double y_difference = Math.abs(this.getMiddleY() - main_character.getMiddleY());                                        // wartość bezwzględna
            double x_difference = Math.abs(this.getMiddleX() - main_character.getMiddleX());
            // sprawdzamy czy jest w obrebie "listening_size"
            if(( x_difference <= listening_size) && (this.getMiddleY() > main_character.getMiddleY()) ){
                this.setMovingDirection("UP");
                is_nearby = true;
            }     else if ( (x_difference <= listening_size) && (this.getMiddleY() < main_character.getMiddleY()) ){
                this.setMovingDirection("DOWN");
                is_nearby = true;
            } else if ( (y_difference <= listening_size) && (this.getMiddleX() < main_character.getMiddleX()) ){
                this.setMovingDirection("RIGHT");
                is_nearby = true;
            } else if ( (y_difference <= listening_size) && (this.getMiddleX() > main_character.getMiddleX()) ){
                this.setMovingDirection("LEFT");
                is_nearby = true;
            }
        



    }    
    return is_nearby;
}
}

