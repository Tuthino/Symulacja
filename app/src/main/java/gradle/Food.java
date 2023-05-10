package gradle;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {
    Food(int x, int y, String ImagePath){
        this.setX(x);
        this.setY(y);
        this.setWidth(20);
        this.setHeight(20);
        Image photo = new Image(ImagePath);
        this.setFill(new ImagePattern(photo));
    }
}
