package gradle;

import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Character extends Rectangle {
    protected boolean hitted_wall = false;
    protected String name;
    private String moving_direction;
    private int intersected_box_index;
    protected int steps_left;
    protected int step_size = 5;

    public Character(double x, double y, String imagePath, String name) {
        this.setX(x);
        this.setY(y);
        this.setWidth(Map.character_size);
        this.setHeight(Map.character_size);
        this.setName(name);
        Image image = new Image(imagePath);
        this.setFill(new ImagePattern(image));
        this.setRandomDirection(new ArrayList<>(Arrays.asList("UP", "DOWN", "RIGHT", "LEFT")));
    }

    public void move(String direction) {

        switch (direction) {
            case "UP":
                this.moveUp();
                break;
            case "DOWN":
                this.moveDown();
                break;
            case "RIGHT":
                this.moveRight();
                break;
            case "LEFT":
                this.moveLeft();
                break;
        }

    }

    // ###################### MOVING ############################
    public void moveUp() {
        if (this.steps_left > 0) {
            this.steps_left -= 1;
            this.setY(this.getY() - this.step_size);
            if (checkIfWalls(Map.boxes)) {
                this.setY(this.getY() + this.step_size); // cofanie ruchu
                hitted_wall = true;
                this.goAroundBox();
            } else if (checkIfMapBound(Map.scene_size, Map.scene_size)) {
                hitted_wall = true;
            }
        }

    }

    public void moveDown() {
        if (this.steps_left > 0) {
            this.steps_left -= 1;
            this.setY(this.getY() + this.step_size);
            if (checkIfWalls(Map.boxes)) {
                this.setY(this.getY() - this.step_size);
                hitted_wall = true;
                this.goAroundBox();
            } else if (checkIfMapBound(Map.scene_size, Map.scene_size)) {
                hitted_wall = true;
            }
        }

    }

    public void moveLeft() {
        if (this.steps_left > 0) {
            this.steps_left -= 1;
            this.setX(this.getX() - this.step_size);
            if (checkIfWalls(Map.boxes)) {
                this.setX(this.getX() + this.step_size);
                hitted_wall = true;
                this.goAroundBox();
            } else if (checkIfMapBound(Map.scene_size, Map.scene_size)) {
                hitted_wall = true;
            }
        }

    }

    public void moveRight() {
        if (this.steps_left > 0) {
            this.steps_left -= 1;
            this.setX(this.getX() + this.step_size);
            if (checkIfWalls(Map.boxes)) {
                this.setX(this.getX() - this.step_size);
                hitted_wall = true;
                this.goAroundBox();
            } else if (checkIfMapBound(Map.scene_size, Map.scene_size)) {
                hitted_wall = true;
            }
        }

    }

    // ############# MOVING (END) #######################

    public void goAroundBox() {
        if (this.getMovingDirection() == "UP" || this.getMovingDirection() == "DOWN") {
            if (Map.boxes.get(intersected_box_index).getMiddleX() < this.getMiddleX()) {
                this.moveRight();
            } else {
                this.moveLeft();
            }
        } else if (this.getMovingDirection() == "LEFT" || this.getMovingDirection() == "RIGHT") {
            if (Map.boxes.get(intersected_box_index).getMiddleY() > this.getMiddleY()) {
                this.moveUp();
            } else {
                this.moveDown();
            }
        }
    }

    public boolean checkIfWalls(List<Box> boxes) {
        boolean intersects = false;

        for (int i = 0; i < boxes.size(); i++) {
            if (this.getBoundsInParent().intersects(boxes.get(i).getBoundsInParent())) { // getBoundsInParent() - zwraca
                                                                                         // granice wezła w kontekście
                                                                                         // jego rodzica
                intersects = true; // node1.interesects(node2) - interakcja jednego elementu z drugim
                intersected_box_index = i;

            }
        }
        return intersects;
    }

    // ###### CHECK IF WE ARE GOING OUT OF BOUNDS
    // THIS HAS TO BE CHANGED FOR CHARACTER WIDTH
    public boolean checkIfMapBound(double scene_width, double scene_height) {
        if (this.getX() > scene_width) {
            this.setX(this.getX() - this.step_size);
            return true;
        } else if (this.getX() < 0) {
            this.setX(this.getX() + this.step_size);
            return true;
        } else if (this.getY() > scene_height) {
            this.setY(this.getY() - this.step_size);
            return true;
        } else if (this.getY() < 0) {
            this.setY(this.getY() + this.step_size);
            return true;
        }
        return false;

    }

    public void setRandomDirection(ArrayList<String> directions) {
        Random random = new Random();
        int index = random.nextInt(100) % directions.size();
        this.setMovingDirection(directions.get(index));
    }

    public void setRandomDirectionShorter(List<String> directions) {
        Random random = new Random();
        int index = random.nextInt(100) % directions.size();
        this.setMovingDirection(directions.get(index));

    }

    public double getMiddleX() {
        return (this.getX() + this.getWidth()) / 2;
    }

    public double getMiddleY() {
        return (this.getY() + this.getHeight()) / 2;
    }

    public void setMovingDirection(String moving_direction) {
        this.moving_direction = moving_direction;
    }

    public String getMovingDirection() {
        return this.moving_direction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStepSize(int step_size) {
        this.step_size = step_size;
    }

}
