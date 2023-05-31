package gradle;

public class MainCharacter extends Character {

    private int listening_size = 100;
    private int scaring_level = 0;
    private int character_points = 0;

    public MainCharacter(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }

    public boolean check_if_ghost() {
        boolean is_nerby = false;
        for (int i = 0; i < Map.ghosts.size(); i++) {
            for (int j = 0; j < Map.ghosts.get(i).size(); j++) {
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
        boolean is_nearby = false;
        for (int i = 0; i < Map.food_list.size(); i++) {
            Food food = Map.food_list.get(i);
            double x_difference = Math.abs(this.getMiddleX() - food.getMiddleX());
            double y_difference = Math.abs(this.getMiddleY() - food.getMiddleY());
            if ((x_difference <= listening_size) && ((y_difference <= listening_size))) {
                System.out.println("Food nearby");
                is_nearby = true;
                // We have to know if it is closer on Y axis or X axis

                // we have to move right or left
                System.out.println(food);
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
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dying() {
        boolean died = false;
        if (scaring_level >= 20) {
            Map.root.getChildren().remove(this);
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
}
