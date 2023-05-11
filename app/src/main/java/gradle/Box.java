package gradle;

import javafx.scene.shape.Rectangle;

public class Box extends Rectangle {
    String name = "Box";


public double getMiddleX(){
    return (this.getX()+this.getWidth())/2;
}
public double getMiddleY(){
    return (this.getY()+this.getHeight())/2;
}
}