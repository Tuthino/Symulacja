package gradle;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food extends Rectangle {
    private Random rand ;
    //private long seed = 0;
    Food(String ImagePath){
        //this.setX(x);
        //this.setY(y);
        //rand = new Random(seed);
        rand = new Random();
        this.setX(rand.nextDouble(Map.scene_size));
        this.setY(rand.nextDouble(Map.scene_size));
        this.setWidth(20);
        this.setHeight(20);
        Image photo = new Image(ImagePath);
        this.setFill(new ImagePattern(photo));

        choosing_place();
    }
    public void choosing_place(){
        while (checkIfBox()){
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
}
