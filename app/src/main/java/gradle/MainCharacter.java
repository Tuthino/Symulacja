package gradle;

import javafx.application.Platform;

public class MainCharacter extends Character {

    private int listening_size = Map.scene_size;
    private int ghost_detecting_size = 70;
    private int scaring_level = 0;
    private int character_points = 0;
    private boolean is_alive = true;
    private int time;

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
                        System.out.println("  Ghost detected");
                        this.setMovingDirection("DOWN");
                        is_nearby = true;
                    } else if ( (x_difference <= Map.character_size) && (this.getMiddleY() < ghost.getMiddleY()) && y_difference > Map.character_size/2){
                        this.setMovingDirection("UP");
                        System.out.println("  Ghost detected");
                        is_nearby = true;
                    } else if ( (y_difference <= Map.character_size) && (this.getMiddleX() <= ghost.getMiddleX()) ){
                        this.setMovingDirection("LEFT");
                        System.out.println("  Ghost detected");
                        is_nearby = true;
                    } else if ( (y_difference <= Map.character_size) && (this.getMiddleX() > ghost.getMiddleX()) ){
                        this.setMovingDirection("RIGHT");
                        System.out.println("  Ghost detected");
                        is_nearby = true;
                    }
                }
            }
        }
        return is_nearby;
    }

    // @TODO We may add checking which food is closer to us

    public boolean check_if_food() { // Checks if we can smell the food ;p
        boolean is_nearby = false;
        for (int i = 0; i < Map.food_list.size(); i++) {
            Food food = Map.food_list.get(i);
            double x_difference = Math.abs(this.getMiddleX() - food.getMiddleX());
            double y_difference = Math.abs(this.getMiddleY() - food.getMiddleY());
            if ((x_difference <= listening_size) && ((y_difference <= listening_size))) {
                System.out.println("  Food nearby");
                is_nearby = true;
                // We have to know if it is closer on Y axis or X axis

                // we have to move right or left
                // System.out.println(food);
                if ((this.getMiddleX() >= food.getMiddleX()) && x_difference >= 20) {
                    this.setMovingDirection("LEFT");
                } else if ((this.getMiddleX() < food.getMiddleX()) && x_difference > 20) {
                    this.setMovingDirection("RIGHT");
                } else if (this.getMiddleY() >= food.getMiddleY()) {
                    this.setMovingDirection("UP");
                } else if (this.getMiddleY() < food.getMiddleY()) {
                    this.setMovingDirection("DOWN");
                }
            }
            eating(); // Confusing name, but it checks if we are intersection food, so we have to call it
            // Everytime we are near food
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
            this.viewResults();
            Map.timeline.stop();
            Map.executor.shutdownNow();
            //Map.stage.setScene(Map.closing_scene);
            //Platform.exit();
            //System.exit(0);
        }
    }

    public void dying() {
            this.setX(Map.scene_size*2);
            this.setY(Map.scene_size*2);
            this.is_alive = false;
    }

    public void viewResults(){
        System.out.println("\n  Level: " + Map.level);
        System.out.println("  Ghost: " + Map.ghost);
        System.out.println("  Scaring level: " + this.getScaring_level());
        System.out.println("  Points: " + this.getCharacter_points());
        System.out.println("  Time: " + Map.seconds + " seconds");
        time = Map.seconds;
    }

    public void increaseScaringLvl(int scaringPoints){
        this.scaring_level+=scaringPoints;
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

    public boolean getIsAlive(){
        return is_alive;
    }

    public int getTime(){
        return time;
    }
}
