package gradle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// LOOKER GHOST CLASS
public class Looker extends Character {
    // Default constructor with "Looker" name
    public Looker(double x, double y, String imagePath) {
        super(x, y, imagePath, "Looker");
        // TODO Auto-generated constructor stub
    }

    public Looker(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
        // TODO Auto-generated constructor stub
    }



    public void lookerMove() {

    }

    public boolean checkIfEnemyIsDiagonal() {
        boolean is_diagonal = false;
        for (MainCharacter main_character : Map.main_characters) {
            // Check poziomo XD
            double y_difference = Math.abs(this.getY() - main_character.getY());
            double x_difference = Math.abs(this.getX() - main_character.getX());
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
            


            // if (Math.abs(y_difference) < Map.character_size) {
            //     System.out.println("y - y: " + (y_difference));
            //     if ((this.getMiddleY() < main_character.getMiddleY())&&(y_difference>Map.character_size)){ // Character is below us
            //         this.setMovingDirection("DOWN");
            //         System.out.println("1");
            //     } else if ((this.getMiddleY() > main_character.getMiddleY())&&(y_difference>Map.character_size)){ // Character is above us
            //         this.setMovingDirection("UP");
            //         System.out.println("2");

            //     }
            //     System.out.println("3");

            //     is_diagonal = true;
            // } else if (Math.abs(x_difference) < Map.character_size) {
            //     System.out.println("x - x: " + (this.getX() - main_character.getX()));
            //     if ((this.getMiddleX() < main_character.getMiddleX())&&(x_difference>Map.character_size)){ //Character is to the right
            //         this.setMovingDirection("RIGHT");
            //         System.out.println("4");

            //     } else if ((this.getMiddleX()>main_character.getMiddleX())&&(x_difference>Map.character_size)){
            //         this.setMovingDirection("LEFT");
            //         System.out.println("5");

            //     }
            //     System.out.println("6");

            //     is_diagonal = true;
            
            System.out.println("IS diagonal?: " + is_diagonal + this.getMovingDirection());
            System.out.println("mainCharacter middle X: "+main_character.getMiddleX()+" Y: "+main_character.getMiddleY()+" GHOST middle X: "+this.getMiddleX()+" Y: "+this.getMiddleY());
            System.out.println("mainCharacter X: "+main_character.getX()+" Y: "+main_character.getY()+" GHOST X: "+this.getX()+" Y: "+this.getY());
            System.out.println("difference X: "+x_difference+" Y: "+y_difference);

        }
        // TODO :D 
        // Now we have to check if there is a wall between them (it is lazy version, because of simplified map ^^)
        return is_diagonal;
    }


    // public void lookerGetDirection(){

    // }

    }