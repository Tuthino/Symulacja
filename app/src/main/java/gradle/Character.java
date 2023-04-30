package gradle;

import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Character extends Rectangle {
    public Character(double x, double y, String imagePath){
        this.setX(x);
        this.setY(y);
        this.setWidth(50);
        this.setHeight(50);
        Image image = new Image(imagePath);
        this.setFill(new ImagePattern(image));
    }

}
