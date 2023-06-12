package gradle;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food extends Rectangle {
    private Random rand ;
    private int points;
    private String name;
    public Image photo;

    Food(String ImagePath, String name, int points){
        rand = new Random();
        this.setX(rand.nextDouble(Map.scene_size));
        this.setY(rand.nextDouble(Map.scene_size));
        this.setWidth(40);
        this.setHeight(40);
        photo = new Image(ImagePath);
        this.setFill(new ImagePattern(photo));
        this.name = name;
        this.points = points;

        choosing_place();
    }
    public void choosing_place(){
        while (checkIfBox() || checkIfFood()){
            this.setX(rand.nextDouble(Map.scene_size));
            this.setY(rand.nextDouble(Map.scene_size));
        }
    }

    public boolean checkIfBox() {
        for (Box box : Map.boxes) {
            if (this.getBoundsInParent().intersects(box.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfFood(){
        for (Food food : Map.food_list) {
            if (this.getBoundsInParent().intersects(food.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public int getPoints(){
        return this.points;
    }

    public double getMiddleX() {
        return (this.getX() + this.getWidth()) / 2;
    }

    public double getMiddleY() {
        return (this.getY() + this.getHeight()) / 2;
    }

    public String getName(){
        return this.name;
    }
}
