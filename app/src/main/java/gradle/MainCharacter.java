package gradle;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class MainCharacter extends Character {

    private int listening_size = 100;
    private int ghost_detecting_size = 70;
    private int scaring_level = 0;
    private int character_points = 0;
    private boolean is_alive = true;

    public MainCharacter(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
        this.step_size = 6;
    }

    public boolean detect_ghost() {
        // With detecting ghosts we have to be first check if it is near us (in some kind of radius)
        // There is no sense of running if ghost is away from us
        // After that we have to check if it is diagonal with us (so there are no boxes between us)


        boolean is_nearby = false;
        for (int i = 0; i < Map.ghosts.size(); i++) {
            for (Ghost ghost : Map.ghosts.get(i)) {
                double x_difference = Math.abs(this.getMiddleX() - ghost.getMiddleX());
                double y_difference= Math.abs(this.getMiddleY() - ghost.getMiddleY());

                // @TODO Here we also could add something like.... check which ghost is the nearest
                // Because in current state we are taking first ghost in list that meets criteria
                // But I don't have time for it now :(

                if ((x_difference <= ghost_detecting_size) && ((y_difference <= ghost_detecting_size))) {
                    if ( (x_difference <= Map.character_size) && (this.getMiddleY() >= ghost.getMiddleY() && y_difference > Map.character_size/2) ){
                        System.out.println("Ghost detected");
                        this.setMovingDirection("DOWN");
                        is_nearby = true;
                    } else if ( (x_difference <= Map.character_size) && (this.getMiddleY() < ghost.getMiddleY()) && y_difference > Map.character_size/2){
                        this.setMovingDirection("UP");
                        System.out.println("Ghost detected");
                        is_nearby = true;
                    } else if ( (y_difference <= Map.character_size) && (this.getMiddleX() <= ghost.getMiddleX()) ){
                        this.setMovingDirection("LEFT");
                        System.out.println("Ghost detected");
                        is_nearby = true;
                    } else if ( (y_difference <= Map.character_size) && (this.getMiddleX() > ghost.getMiddleX()) ){
                        this.setMovingDirection("RIGHT");
                        System.out.println("Ghost detected");
                        is_nearby = true;
                    }
                }
            }
        }
        return is_nearby;
    }

    // @TODO We may add checking which food is closer to us
    public boolean check_if_food() {
        boolean is_nearby = false;
        List<String> find_food = new ArrayList<>();

        for (int i = 0; i < Map.food_list.size(); i++) {
            Food food = Map.food_list.get(i);
            double distanceX = Math.abs(this.getMiddleX() - food.getMiddleX());
            double distanceY = Math.abs(this.getMiddleY() - food.getMiddleY());

            if ((distanceX <= listening_size) && ((distanceY <= listening_size))) {
                System.out.println("  Food nearby: " + food);
                is_nearby = true;
                // We have to know if it is closer on Y axis or X axis

                // we have to move right or left
                if ((this.getMiddleX() >= food.getMiddleX()) && distanceX >= 20) {
                    find_food.add("LEFT");
                }
                if ((this.getMiddleX() < food.getMiddleX()) && distanceX > 20) {
                    find_food.add("RIGHT");
                }
                if (this.getMiddleY() >= food.getMiddleY()) {
                    find_food.add("UP");
                }
                if (this.getMiddleY() < food.getMiddleY()) {
                    find_food.add("DOWN");
                }
            }
            System.out.println("  Food nearby: " + is_nearby);
            System.out.println("  Position of food: " + find_food);

            if (is_nearby){
                setRandomDirectionShorter(find_food);
            }

            eating();
        }
        return is_nearby;
    }

    public void eating() {
        for (int i=0; i<Map.food_list.size();i++ ){
            Food food = Map.food_list.get(i);
            if (this.getBoundsInParent().intersects(food.getBoundsInParent())) {
                character_points += food.getPoints();
                System.out.println("  Eating");
                food.setX(Map.scene_size*2);
                food.setY(Map.scene_size*2);
                Map.food_list.remove(i);
            }
        }

        if(Map.food_list.isEmpty()){
            //End of the game
            System.out.println("Scooby ate everything :D");
            Platform.exit();
            System.exit(0);
        }
    }

    public void dying() {
            this.setX(Map.scene_size*2);
            this.setY(Map.scene_size*2);
            this.is_alive = false;
    }

    public int getCharacter_points() {
        return character_points;
    }

    public int getScaring_level() {
        return scaring_level;
    }

    public int getListening_size() {
        return listening_size;
    }
    public void increaseScaringLvl(int scaringPoints){
        this.scaring_level+=scaringPoints;
    }
}
