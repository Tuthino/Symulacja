package gradle;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food extends Rectangle {
    private Random rand ;
    //private long seed = 0;
    private int points;
    private String name;
    public Image photo;

    Food(String ImagePath, String name){
        //this.setX(x);
        //this.setY(y);
        //rand = new Random(seed);
        rand = new Random();
        this.setX(rand.nextDouble(Map.scene_size));
        this.setY(rand.nextDouble(Map.scene_size));
        this.setWidth(40);
        this.setHeight(40);
        photo = new Image(ImagePath);
        this.setFill(new ImagePattern(photo));
        this.name = name;

        choosing_place();
    }
    public void choosing_place(){
        while (checkIfBox() || checkIfFood() || checkIfButton()){
            this.setX(rand.nextDouble(Map.scene_size));
            this.setY(rand.nextDouble(Map.scene_size));
            //System.out.println(getX() + " " + getY());
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

    public boolean checkIfButton(){
        if (this.getBoundsInParent().intersects(Map.closing_button.getBoundsInParent())) {
            return true;
        }
        return false;
    }

    public int getPoints(){
        if(this.name == "Ham")
            return points = 5;
        else if(this.name == "Pancake")
            return points = 10;
        else if(this.name == "Scooby_crisp")
            return points = 15;
        else
            return points = 0;
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
