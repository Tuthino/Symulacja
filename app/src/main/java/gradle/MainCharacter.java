package gradle;

import java.util.ArrayList;
import java.util.List;

public class MainCharacter extends Character {

    private int listening_size = 100;
    private int scaring_level = 0;
    private int character_points = 0;
    private boolean death = false;

    public MainCharacter(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
        setStepSize(5);

    }

    public boolean check_if_ghost() {
        boolean is_nearby = false;
        List<String> find_ghost = new ArrayList<>();
        List<String> running = new ArrayList<>();

        for(int i=0; i<Map.ghosts.size(); i++){
            for (int j=0; j<Map.ghosts.get(i).size(); j++) {
                Ghost ghost = Map.ghosts.get(i).get(j);
                double distanceX = Math.abs(this.getMiddleX() - ghost.getMiddleX());
                double distanceY = Math.abs(this.getMiddleY() - ghost.getMiddleY());

                if ((distanceY <= this.listening_size) && (this.getMiddleX() > ghost.getMiddleX()) ){
                    find_ghost.add("LEFT");
                    is_nearby = true;
                }
                if ((distanceY <= this.listening_size) && (this.getMiddleX() < ghost.getMiddleX()) ){
                    find_ghost.add("RIGHT");
                    is_nearby = true;
                }
                if ((distanceX <= this.listening_size) && (this.getMiddleY() < ghost.getMiddleY()) ){
                    find_ghost.add("DOWN");
                    is_nearby = true;
                }
                if ((distanceX <= this.listening_size) && (this.getMiddleY() > ghost.getMiddleY()) ){
                    find_ghost.add("UP");
                    is_nearby = true;
                }
            }
        }
        System.out.println("  Ghosts nearby: " + is_nearby);
        System.out.println("  Position of ghosts: " + find_ghost);

        /*if(!find_ghost.contains("LEFT"))
            running.add("LEFT");
        if(!find_ghost.contains("RIGHT"))
            running.add("RIGHT");
        if(!find_ghost.contains("UP"))
            running.add("UP");
        if(!find_ghost.contains("DOWN"))
            running.add("DOWN");

        System.out.println("  Ways out: " + running);
        setRandomDirectionShorter(running);*/

        //getting_scared();

        return is_nearby;
    }

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
            setRandomDirectionShorter(find_food);

            eating();
        }
        return is_nearby;
    }

    public boolean eating() {
        for (Food food : Map.food_list ){
            if (this.getBoundsInParent().intersects(food.getBoundsInParent())) {
                character_points += food.getPoints();
                System.out.println("Eeating");
                food.setX(1000);
                food.setY(1000);
                Map.food_list.remove(food);
                return true;
            }
        }
        return false;
    }

    public boolean getting_scared() {
        for (int i = 0; i < Map.ghosts.size(); i++) {
            for (int j = 0; j < Map.ghosts.get(i).size(); j++) {
                Ghost ghost = Map.ghosts.get(i).get(j);
                if (this.getBoundsInParent().intersects(ghost.getBoundsInParent())) {
                    scaring_level += ghost.getScaringPoints();
                    System.out.println("  scaring_level: " + this.scaring_level);
                    if(dying()){
                        death = true;
                        System.out.println("  death status: " + this.death);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dying() {
        boolean died = false;
        if (scaring_level >= 30) {
            died = true;
        }
        return died;
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

    public boolean getDeathStatus(){
        return death;
    }
}
