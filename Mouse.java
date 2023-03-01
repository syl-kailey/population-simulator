import java.util.Random;

public class Mouse extends Creature {

    int age = 0;
    int x;
    int y;
    int[] dirY = {-1,0,1,0};
    int[] dirX = {0, 1, 0, -1};
    GridPoint point;

    public Mouse(int x, int y, City cty, Random rnd){
        super(x, y, cty, rnd);
        lab = LAB_BLUE;
        stepLen=1;
    }
    
    public char getLab(){
        return 'b';
    }

    public void randomTurn() {
        int number = rand.nextInt(5);
        if (number == 1){
            int dir = rand.nextInt(4);
            setDir(dir);
        }
    }

   public void takeAction(){
    
        age++;
        if (age == 20){
            int x = this.getX();
            int y =  this.getY();
            city.creaturesToAdd.add(new Mouse(x, y, city, rand));
        }
        if (age == 30){
            dead = true;
        }
    }

}