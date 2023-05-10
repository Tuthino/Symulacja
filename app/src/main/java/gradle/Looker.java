package gradle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// LOOKER GHOST CLASS
public class Looker extends Character {

    public Looker(double x, double y, String imagePath) {
        super(x, y, imagePath);
        //TODO Auto-generated constructor stub
    }
    

    public String get_random_movement(){
        Random random = new Random();
        ArrayList<String> directions = new ArrayList<>(
            Arrays.asList("UP", "DOWN", "RIGHT", "LEFT")
        );
        int index = random.nextInt(100)%4;
        return directions.get(index);


    }
}
