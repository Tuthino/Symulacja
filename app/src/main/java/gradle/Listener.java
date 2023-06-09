package gradle;


public class Listener extends Ghost {

    public Listener(double x, double y, String imagePath) {
        super(x, y, imagePath, "Listener");
    }

    public Listener(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }

    private double listening_size = 100;

    public void listenerMove() {

    }

    public boolean checkIfNearby() {
        boolean is_nearby = false;
        for (MainCharacter main_character : Map.main_characters) {
            double y_difference = Math.abs(this.getMiddleY() - main_character.getMiddleY()); // wartość bezwzględna
            double x_difference = Math.abs(this.getMiddleX() - main_character.getMiddleX());
            if(x_difference < (this.getWidth() + main_character.getWidth()) || (y_difference < (this.getHeight()+main_character.getHeight()))){
                scaring();
            }            
            if ((x_difference <= listening_size) && ((y_difference <= listening_size))) {
                is_nearby = true;
                // We have to know if it is closer on Y axis or X axis
                
                    // we have to move right or left
                    if((this.getMiddleX() >= main_character.getMiddleX()) && x_difference> 20){
                        this.setMovingDirection("LEFT");
                    } else if((this.getMiddleX() < main_character.getMiddleX()) && x_difference> 20){
                        this.setMovingDirection("RIGHT");
                    }
                    else if(this.getMiddleY() >= main_character.getMiddleY()){
                        this.setMovingDirection("UP");
                    } else if (this.getMiddleY() < main_character.getMiddleY()){
                        this.setMovingDirection("DOWN");
                    }
        }}    
        return is_nearby;
    

}
}