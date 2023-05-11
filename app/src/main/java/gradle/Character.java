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

    public void move(Character character, Map map, String direction) {

        switch (direction) {
            case "UP":
                character.moveUp(character);
                break;
            case "DOWN":
                character.moveDown(character);
                break;
            case "RIGHT":
                character.moveRight(character);
                break;
            case "LEFT":
                character.moveLeft(character);
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
    public void moveUp(Character character) {

        character.setY(character.getY() - 5);
        while (checkIfWalls(character, Map.boxes)
                || checkIfMapBound(character, Map.scene.getWidth(), Map.scene.getHeight())) {
            character.setY(character.getY() + 2);
            hitted_wall = true;
        }
        System.out.println("X: " + character.getX() + " Y: " + character.getY());
    }

    public void moveDown(Character character) {
        character.setY(character.getY() + 5);
        while (checkIfWalls(character, Map.boxes)
                || checkIfMapBound(character, Map.scene.getWidth(), Map.scene.getHeight())) {
            character.setY(character.getY() - 2);
            hitted_wall = true;

        }
        System.out.println("X: " + character.getX() + " Y: " + character.getY());
    }

    public void moveLeft(Character character) {

        character.setX(character.getX() - 5);
        while (checkIfWalls(character, Map.boxes)
                || checkIfMapBound(character, Map.scene.getWidth(), Map.scene.getHeight())) {
            character.setX(character.getX() + 2);
            hitted_wall = true;
        }
        System.out.println("X: " + character.getX() + " Y: " + character.getY());
    }

    public void moveRight(Character character) {
        character.setX(character.getX() + 5);
        while (checkIfWalls(character, Map.boxes)
                || checkIfMapBound(character, Map.scene.getWidth(), Map.scene.getHeight())) {
            character.setX(character.getX() - 2);
            hitted_wall = true;
        }
        System.out.println("X: " + character.getX() + " Y: " + character.getY());
    }

    // ############# MOVING (END) #######################3

    public boolean checkIfWalls(Character character, List<Box> boxes) {
        boolean intersects = false;

        for (int i = 0; i < boxes.size(); i++) {
            if (character.getBoundsInParent().intersects(boxes.get(i).getBoundsInParent())) {
                intersects = true;
                System.out.println(boxes.get(i));
                System.out.println(boxes.get(i).getClass().getSimpleName());
            }
        }
        return intersects;
    }

    // ###### CHECK IF WE ARE GOING OUT OF BOUNDS
    // THIS HAS TO BE CHANGED FOR CHARACTER WIDTH
    public boolean checkIfMapBound(Character character, double scene_width, double scene_height) {
        if (character.getX() > scene_width - character.getWidth() || character.getX() < 0) {
            return true;
        } else if (character.getY() > scene_height - character.getHeight() || character.getY() < 0) {
            return true;
        } else {
            return false;
        }
    }
}
