package gradle;

import java.util.ArrayList;
import java.util.List;

public class MainCharacter extends Character {

    private int listening_size = 5;
    private int scaring_level = 0;
    private int character_points = 0;
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

        if(!find_ghost.contains("LEFT"))
            running.add("LEFT");
        if(!find_ghost.contains("RIGHT"))
            running.add("RIGHT");
        if(!find_ghost.contains("UP"))
            running.add("UP");
        if(!find_ghost.contains("DOWN"))
            running.add("DOWN");

        System.out.println("  Ways out: " + running);
        this.setRandomDirectionShorter(running);
        return is_nearby;
    }
    public boolean check_if_food() {
        boolean is_nearby = false;
        List<String> find_food = new ArrayList<>();
        for(int i=0; i<Map.food_list.size(); i++){
            Food food = Map.food_list.get(i);
            double distanceX = Math.abs(this.getMiddleX() - food.getMiddleX());
            double distanceY = Math.abs(this.getMiddleY() - food.getMiddleY());

            if ((distanceY <= this.listening_size) && (this.getMiddleX() > food.getMiddleX()) ){
                find_food.add("LEFT");
                is_nearby = true;
            }
            if ((distanceY <= this.listening_size) && (this.getMiddleX() < food.getMiddleX()) ){
                find_food.add("RIGHT");
                is_nearby = true;
            }
            if ((distanceX <= this.listening_size) && (this.getMiddleY() < food.getMiddleY()) ){
                find_food.add("DOWN");
                is_nearby = true;
            }
            if ((distanceX <= this.listening_size) && (this.getMiddleY() > food.getMiddleY()) ){
                find_food.add("UP");
                is_nearby = true;
            }
        }
        System.out.println("  Food nearby: " + is_nearby);
        System.out.println("  Position of food: " + find_food);
        this.setRandomDirectionShorter(find_food);

        return is_nearby;
    }

    public boolean eating() {
        for(int i=0; i<Map.food_list.size(); i++){
            Food food = Map.food_list.get(i);
            if (this.getBoundsInParent().intersects(food.getBoundsInParent())) {
                character_points += food.getPoints();
                Map.food_list.remove(food);
                Map.root.getChildren().remove(food);                                    // TODO nie usuwa się
                return true;
            }
        }
        return false;
    }

    public boolean getting_scared() {
        for(int i=0; i<Map.ghosts.size(); i++){
            for (int j=0; j<Map.ghosts.get(i).size(); j++) {
                Ghost ghost = Map.ghosts.get(i).get(j);
                if (this.getBoundsInParent().intersects(ghost.getBoundsInParent())) {
                    scaring_level += ghost.getScaringPoints();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dying() {
        boolean died = false;
        if(scaring_level>=20){
            Map.root.getChildren().remove(this);
            Map.main_characters.remove(this);                                                                           // TODO nie możemy bo musimy później wyświetlić dane tego
            died = true;
        }
        return died;
    }

    public int getCharacter_points(){
        return character_points;
    }

    public int getScaring_level(){
        return scaring_level;
    }

    public int getListening_size(){
        return listening_size;
    }
}
