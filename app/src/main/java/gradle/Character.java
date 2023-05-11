package gradle;

import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;

public class Character extends Rectangle {
    protected boolean hitted_wall = false;
    protected String name;

    public Character(double x, double y, String imagePath, String name) {
        this.setX(x);
        this.setY(y);
        this.setWidth(45);
        this.setHeight(45);
        this.setName(name);
        Image image = new Image(imagePath);
        this.setFill(new ImagePattern(image));

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
        System.out.println("X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveDown() {
        this.setY(this.getY() + 5);
        while (checkIfWalls( Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setY(this.getY() - 2);
            hitted_wall = true;

        }
        System.out.println("X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveLeft() {

        this.setX(this.getX() - 5);
        while (checkIfWalls(Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setX(this.getX() + 2);
            hitted_wall = true;
        }
        System.out.println("X: " + this.getX() + " Y: " + this.getY());
    }

    public void moveRight() {
        this.setX(this.getX() + 5);
        while (checkIfWalls(Map.boxes)
                || checkIfMapBound(Map.scene.getWidth(), Map.scene.getHeight())) {
            this.setX(this.getX() - 2);
            hitted_wall = true;
        }
        System.out.println("X: " + this.getX() + " Y: " + this.getY());
    }

    // ############# MOVING (END) #######################3

    public boolean checkIfWalls(List<Box> boxes) {
        boolean intersects = false;

        for (int i = 0; i < boxes.size(); i++) {
            if (this.getBoundsInParent().intersects(boxes.get(i).getBoundsInParent())) {
                intersects = true;
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
}
