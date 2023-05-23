package gradle;

public class Ghost extends Character{
    private int scaringPoints;
    Ghost(double x, double y, String ImagePath, String name){
        super(x, y, ImagePath, name);
    }

    public int getScaringPoints(){
        return scaringPoints;
    }

    public void setScaringPoints(int scaringPoints){
        this.scaringPoints = scaringPoints;
    }

}
