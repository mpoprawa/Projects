import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import java.util.Random;
/**
Nowa klasa watku
 */
public class Square extends Thread {
    private int m;
    private int n;
    private int x;
    private int y;
    private int k;
    private double p;
    private Rectangle rec;
    private GridPane grid;
    private Random random = new Random();
    /**
    Konstruktor watku
     */
    public Square(int m, int n,int x,int y,int k,double p,Rectangle rec,GridPane grid) {
        this.m=m;
        this.n=n;
        this.x=x;
        this.y=y;
        this.k=k;
        this.p=p;
        rec.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        this.rec=rec;
        this.grid=grid;
    }
    /**
    Obsluga watku
    */
    public synchronized void run() {
        while(true) {
            //Odstep miedzy zmianami koloru
            try{
                Thread.sleep((random.nextInt(2*k)+k)/2);
            }
            catch(Exception e){}
            //Watek jest uspiony dla prostokotow z x=1
            try {
                while(rec.getX()==1){
                    wait(100);
                }
            }
            catch (InterruptedException e){}
            System.out.println("Start X:"+x+" Y:"+y);
            //Losowy kolor
            if(random.nextDouble()<=p){
                rec.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            }
            //Srednia sasiadow
            else{
                int red=0;
                int green=0;
                int blue=0;
                int counter=0;
                //Lewy sasiad
                if(x==0){
                    if(((Rectangle)grid.getChildren().get((y+1)*m-1)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get((y+1)*m-1)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                else{
                    if(((Rectangle)grid.getChildren().get(y*m+x-1)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get(y*m+x-1)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                //Prawy sasiad
                if(x==m-1){
                    if(((Rectangle)grid.getChildren().get(y*m)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get(y*m)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                else{
                    if(((Rectangle)grid.getChildren().get(y*m+x+1)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get(y*m+x+1)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                //Gorny sasiad
                if(y==0){
                    if(((Rectangle)grid.getChildren().get((n-1)*m+x)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get((n-1)*m+x)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                else{
                    if(((Rectangle)grid.getChildren().get((y-1)*m+x)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get((y-1)*m+x)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                //Dolny sasiad
                if(y==n-1){
                    if(((Rectangle)grid.getChildren().get(x)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get(x)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                else{
                    if(((Rectangle)grid.getChildren().get((y+1)*m+x)).getX()!=1){
                        Color c = (Color) ((Rectangle)grid.getChildren().get((y+1)*m+x)).getFill();
                        red+=(int)(c.getRed()*255);
                        green+=(int)(c.getGreen()*255);
                        blue+=(int)(c.getBlue()*255);
                        counter+=1;
                    }
                }
                if(counter>0){
                    rec.setFill(Color.rgb(red/counter,green/counter,blue/counter));
                }
            }
            System.out.println("End X:"+x+" Y:"+y);
        }
    }
}