package gradle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box extends Rectangle {
    String name = "Box";

    Box(int i, int j){
        this.setX((50.0 + j * 100.0));
        this.setY(50.0 + i * 100.0);
        this.setWidth(49);
        this.setHeight(49);
        this.setFill(Color.BLUE);
    }

    public double getMiddleX(){
        return (this.getX()+this.getWidth())/2;
    }
    public double getMiddleY(){
        return (this.getY()+this.getHeight())/2;
    }
}