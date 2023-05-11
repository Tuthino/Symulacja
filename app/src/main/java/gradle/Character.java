package gradle;

import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.Parent;

public class Character extends Rectangle {
    protected boolean hitted_wall = false;
    protected String name;
    private String moving_direction ;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // ###################### MOVING ############################
    public void moveUp() {

        this.setY(this.getY() - 5);
        while (checkIfWalls(Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setY(this.getY() + 2);
            hitted_wall = true;
        }
        System.out.println(this.getClass().getSimpleName()+"X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveDown() {
        this.setY(this.getY() + 5);
        while (checkIfWalls( Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setY(this.getY() - 2);
            hitted_wall = true;

        }
        System.out.println(this.getClass().getSimpleName()+"X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveLeft() {

        this.setX(this.getX() - 5);
        while (checkIfWalls(Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setX(this.getX() + 2);
            hitted_wall = true;
        }
        System.out.println(this.getClass().getSimpleName()+"X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveRight() {
        this.setX(this.getX() + 5);
        while (checkIfWalls(Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setX(this.getX() - 2);
            hitted_wall = true;
        }
        System.out.println(this.getClass().getSimpleName()+"X: " + this.getX() + " Y: " + this.getY());
    }

    // ############# MOVING (END) #######################3

    public boolean checkIfWalls(List<Box> boxes) {
        boolean intersects = false;

        for (int i = 0; i < boxes.size(); i++) {
            if (this.getBoundsInParent().intersects(boxes.get(i).getBoundsInParent())) {
                intersects = true;
                // System.out.println(boxes.get(i));
                // System.out.println(boxes.get(i).getClass().getSimpleName());
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
    public void setMovingDirection(String moving_direction){
        this.moving_direction = moving_direction;
    }
    public String getMovingDirection(){
        return this.moving_direction;
    }
    public double getMiddleX(){
        return (this.getX()+this.getWidth())/2;
    }
    public double getMiddleY(){
        return (this.getY()+this.getHeight())/2;
    }
    public void setRandomDirection() {
        Random random = new Random();
        ArrayList<String> directions = new ArrayList<>(
                Arrays.asList("UP", "DOWN", "RIGHT", "LEFT"));
        int index = random.nextInt(100) % 4;
        this.setMovingDirection(directions.get(index));
    }
}
