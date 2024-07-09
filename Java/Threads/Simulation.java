import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.WindowEvent;
import javafx.geometry.Rectangle2D;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
/**
Glowna klasa programu
 */
public class Simulation extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static boolean check(String str) {
        try {
            double d = Integer.parseInt(str);
        } 
        catch (NumberFormatException nfe) {
            return false;
        }
        if(Integer.parseInt(str)<0){
            return false;
        }
        return true;
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulation");
        int m=Integer.parseInt(getParameters().getUnnamed().get(0));
        int n=Integer.parseInt(getParameters().getUnnamed().get(1));
        int k=Integer.parseInt(getParameters().getUnnamed().get(2));
        double p=Double.parseDouble(getParameters().getUnnamed().get(3));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        int gap=5;
        double screenWidth=primaryScreenBounds.getWidth()*0.6;
        double size=(screenWidth-(m-1)*gap)/m;
        double screenHeight=size*n+(n-1)*gap;
        GridPane grid = new GridPane();
        grid.setVgap(5); 
        grid.setHgap(5); 
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                Rectangle field=new Rectangle(size,size);
                final int x=i,y=j;
                //Wylaczanie watkow
                field.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent t) {
                        //Prostokaty z x=0 sa wlaczone a z x=1 wylaczone
                        if(field.getX()==1){
                            field.setX(0);
                            System.out.println("Resume X:"+x+" Y:"+y);
                        }
                        else{
                            field.setX(1);
                            System.out.println("Stop X:"+x+" Y:"+y);
                        }
                    }
                });
                Thread t=new Square(m,n,i,j,k,p,field,grid);
                t.start();
                grid.add(field,i,j);
            }
        }
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        primaryStage.setScene(new Scene(grid,screenWidth,screenHeight));
        primaryStage.show();
    }
}