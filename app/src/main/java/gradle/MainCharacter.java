package gradle;

public class MainCharacter extends Character {

    private int listening_size = 7;
    private int scaring_level = 0;
    private int character_points = 0;
    public MainCharacter(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
        setStepSize(5);
    }

    public boolean check_if_ghost() {
        boolean is_nerby = false;
        for(int i=0; i<Map.ghosts.size(); i++){
            for (int j=0; j<Map.ghosts.get(i).size(); j++) {
                Ghost ghost = Map.ghosts.get(i).get(j);
                double distanceX = Math.abs(this.getMiddleX() - ghost.getMiddleX());
                double distanceY = Math.abs(this.getMiddleY() - ghost.getMiddleY());

                if (distanceX <= listening_size && this.getMiddleX() < ghost.getMiddleX()) {
                    setMovingDirection("LEFT");
                    is_nerby = true;
                } else if (distanceX <= listening_size && this.getMiddleX() > ghost.getMiddleX()) {
                    setMovingDirection("RIGHT");
                    is_nerby = true;
                } else if (distanceY <= listening_size && this.getMiddleY() < ghost.getMiddleY()) {
                    setMovingDirection("DOWN");
                    is_nerby = true;
                } else if (distanceY <= listening_size && this.getMiddleY() > ghost.getMiddleY()) {
                    setMovingDirection("UP");
                    is_nerby = true;
                }
            }
        }
        return is_nerby;
    }
    public boolean check_if_food() {
        boolean is_nerby = false;
        for(int i=0; i<Map.food_list.size(); i++){
            Food food = Map.food_list.get(i);
            double distanceX = Math.abs(this.getMiddleX() - food.getMiddleX());
            double distanceY = Math.abs(this.getMiddleY() - food.getMiddleY());

            if (distanceX <= listening_size && this.getMiddleX() < food.getMiddleX()) {
                setMovingDirection("RIGHT");
                is_nerby = true;
            } else if (distanceX <= listening_size && this.getMiddleX() > food.getMiddleX()) {
                setMovingDirection("LEFT");
                is_nerby = true;
            } else if (distanceY <= listening_size && this.getMiddleY() < food.getMiddleY()) {
                setMovingDirection("UP");
                is_nerby = true;
            } else if (distanceY <= listening_size && this.getMiddleY() > food.getMiddleY()) {
                setMovingDirection("DOWN");
                is_nerby = true;
            }
        }
        return is_nerby;
    }

    public boolean eating() {
        for(int i=0; i<Map.food_list.size(); i++){
            Food food = Map.food_list.get(i);
            if (this.getBoundsInParent().intersects(food.getBoundsInParent())) {
                character_points += food.getPoints();
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
