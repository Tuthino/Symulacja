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
    private int step_size = 5;

    public Character(double x, double y, String imagePath, String name) {
        this.setX(x);
        this.setY(y);
        this.setWidth(Map.character_size);
        this.setHeight(Map.character_size);
        this.setName(name);
        Image image = new Image(imagePath);
        this.setFill(new ImagePattern(image));
        this.setRandomDirection();
    }

    public void move(String direction) {
        System.out.println(this.getMovingDirection());
        System.out.println(direction);

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
        System.out.println(this.getClass().getSimpleName() + "X: " + this.getX() + " Y: " + this.getY());
        System.out.println(this.getMovingDirection());

    }

    // ###################### MOVING ############################
    public void moveUp() {
        if (this.steps_left>0){
            this.steps_left-=1;
            this.setY(this.getY() - this.step_size);
            if (checkIfWalls(Map.boxes) || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
                this.setY(this.getY() + this.step_size);                                                                // cofanie ruchu
                hitted_wall = true;
                this.goAroundBox();
            }
        }
        // System.out.println(this.getClass().getSimpleName() + "X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveDown() {
        if (this.steps_left>0){
            this.steps_left-=1;
              this.setY(this.getY() + this.step_size);
            if (checkIfWalls(Map.boxes) || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
                this.setY(this.getY() - this.step_size);
                hitted_wall = true;
                this.goAroundBox();
            }
         }
        // System.out.println(this.getClass().getSimpleName() + "X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveLeft() {
        if (this.steps_left>0){
            this.steps_left-=1;
            this.setX(this.getX() - this.step_size);
            if (checkIfWalls(Map.boxes) || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
                this.setX(this.getX() + this.step_size);
                hitted_wall = true;
                this.goAroundBox();
            }
        }
        // System.out.println(this.getClass().getSimpleName() + "X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveRight() {
            if (this.steps_left>0){
                this.steps_left-=1;
            this.setX(this.getX() + this.step_size);
            if (checkIfWalls(Map.boxes) || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
                this.setX(this.getX() - this.step_size);
                hitted_wall = true;
                this.goAroundBox();
            }
        }
        // System.out.println(this.getClass().getSimpleName() + "X: " + this.getX() + " Y: " + this.getY());
    }

    // ############# MOVING (END) #######################

    public void goAroundBox(){
        if (this.getMovingDirection() == "UP" || this.getMovingDirection() == "DOWN"){
            if (Map.boxes.get(intersected_box_index).getMiddleX() < this.getMiddleX()) {
                this.moveRight();
            } else {
                this.moveLeft();
            }
        } else if (this.getMovingDirection() == "LEFT" || this.getMovingDirection() == "RIGHT"){
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
            if (this.getBoundsInParent().intersects(boxes.get(i).getBoundsInParent())) {                                // getBoundsInParent() - zwraca granice wezła w kontekście jego rodzica
                intersects = true;                                                                                      // node1.interesects(node2) - interakcja jednego elementu z drugim
                intersected_box_index = i;
                System.out.println(boxes.get(i));
                System.out.println(boxes.get(i).getClass().getSimpleName());
            }
        }
        return intersects;
    }

    // ###### CHECK IF WE ARE GOING OUT OF BOUNDS
    // THIS HAS TO BE CHANGED FOR CHARACTER WIDTH
    public boolean checkIfMapBound(double scene_width, double scene_height) {
        if (this.getX() > scene_width - this.getWidth() || this.getX() < 0) {
            return true;
        } else if (this.getY() > scene_height - this.getHeight() || this.getY() < 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setRandomDirection() {
        Random random = new Random();
        ArrayList<String> directions = new ArrayList<>(Arrays.asList("UP", "DOWN", "RIGHT", "LEFT"));
        int index = random.nextInt(100) % 4;
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

    public void setStepSize(int step_size){
        this.step_size = step_size;
    }

}
