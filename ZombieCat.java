import java.util.Random;

public class ZombieCat extends Creature {

    Creature prey;
    Creature m;
    boolean starved = false;
    int starveCount = 0;
    int age = 0;
    int x;
    int y;

    public ZombieCat(int x, int y, City cty, Random rnd){
        super(x, y, cty, rnd);
        super.lab = LAB_RED;
        stepLen=3;
    }

    public void step(){
        super.step();
    }

    public char getLab(){
        return 'r';
    }


 
    public void takeAction(){
   
        for (Creature m: city.creatures){
            if (this.dist(m)==0){
                if (m.lab == 'b'){
                    m.dead = true;
                    starveCount = 0;
                }
                else if (m.lab == 'c' || m.lab == 'y'){
                    int x = this.getX();
                    int y =  this.getY();
                    city.creaturesToAdd.add(new ZombieCat(x, y, city, rand));
                    m.dead = true;
                    starveCount = 0;
                }
            }
        } 

        if(prey==null || prey.isDead()){
            prey=null;
            this.lab='r';
            this.setPrey();
        }
        else if(prey.isDead() == false){
            int xDiff = Math.abs(getX()-prey.getX());
            int yDiff = Math.abs(getY()-prey.getY());
            if(xDiff>=yDiff){
                if(getX() <= prey.getX()){
                    this.setDir(EAST);
                }
                else{
                    this.setDir(WEST);
                }
            }
            else{
                if(getY() <= prey.getY()){
                    this.setDir(SOUTH);
                }
                else{
                    this.setDir(NORTH);
                }
            }
        }

        if (starveCount == 100){
            dead = true;
        }

        starveCount++;
    }
    
    public void setPrey(){
        for (Creature m: city.creatures){
            if (m.lab == 'b' || m.lab == 'c' || m.lab == 'y'){
                if (this.dist(m)<= 40){
                    prey = m;
                    this.lab = 'k';
                }
            }
        }
    }


    
}