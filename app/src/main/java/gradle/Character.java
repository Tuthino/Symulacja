package gradle;

import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import gradle.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Character extends Rectangle {
    public Character(double x, double y, String imagePath) {
        this.setX(x);
        this.setY(y);
        this.setWidth(45);
        this.setHeight(45);
        Image image = new Image(imagePath);
        this.setFill(new ImagePattern(image));

    }

    public void move(Character character, Group root, List<Box> boxes, String direction) {
        switch (direction) {
            case "UP":
                character.moveUp(character, root, boxes);
                break;
            case "DOWN":
                character.moveDown(character, root, boxes);
                break;
            case "RIGHT":
                character.moveRight(character, root, boxes);
                break;
            case "LEFT":
                character.moveLeft(character, root, boxes);
                break;
        }

    }

    public void moveUp(Character character, Group root, List<Box> boxes) {
        // Check all 8 boxes
        if (checkIfWalls(character, boxes)) {
            while (checkIfWalls(character, boxes)) {
                character.setY(character.getY() + 2);
            }

        } else {
            character.setY(character.getY() - 5);
            System.out.println("X: " + character.getX() + " Y: " + character.getY());
        }
    }

    public void moveDown(Character character, Group root, List<Box> boxes) {
        if (checkIfWalls(character, boxes)) {
            while (checkIfWalls(character, boxes)) {
                character.setY(character.getY() - 2);
            }

        } else {
            character.setY(character.getY() + 5);
            System.out.println("X: " + character.getX() + " Y: " + character.getY());

        }
    }

    public void moveLeft(Character character, Group root, List<Box> boxes) {
        if (checkIfWalls(character, boxes)) {
            while (checkIfWalls(character, boxes)) {
                character.setX(character.getX() + 2);
            }

        } else {
            character.setX(character.getX() - 5);
            System.out.println("X: " + character.getX() + " Y: " + character.getY());
        }
    }

    public void moveRight(Character character, Group root, List<Box> boxes) {
        if (checkIfWalls(character, boxes)) {
            while (checkIfWalls(character, boxes)) {
                character.setX(character.getX() - 2);
            }

        } else {
            character.setX(character.getX() + 5);
            System.out.println("X: " + character.getX() + " Y: " + character.getY());
        }
    }

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

    // This method gets all nodes from parent

    // TODO!! CONVERT THIS TO ONLY ADD WALLS ^^
    // THANKS TO THAT IT WILL HAVE LESS ITERATIONS, SO IT WILL NOT HAVE TO
    // GO THROUGH EVERYNODE, but only walls
    public static ArrayList<Node> getAllBoxes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendentsBoxes(root, nodes);
        return nodes;
    }

    private static void addAllDescendentsBoxes(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            System.out.println(node.getClass().getSimpleName());
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendentsBoxes((Parent) node, nodes);
        }
    }
}

// private <T> List<T> getNodesOfType(Pane parent, Class<T> type) {
// List<T> elements = new ArrayList<>();
// for (Node node : parent.getChildren()) {
// if (node instanceof Pane) {
// elements.addAll(getNodesOfType((Pane) node, type));
// } else if (type.isAssignableFrom(node.getClass())) {
// //noinspection unchecked
// elements.add((T) node);
// }
// }
// return Collections.unmodifiableList(elements);
