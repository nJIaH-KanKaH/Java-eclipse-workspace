import java.util.Observer;
import java.util.Observable;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
  
public class Main extends Application{
	public static class LogSender extends Observable{
		private String information;
		public void setInfo(String i) {
			information=i;
			setChanged();
			notifyObservers();
		}
		public String getInfo() {
			return information;
		}
	}
	
	public static class LogSaver extends TextArea implements Observer{
		@Override
		public void update(java.util.Observable o, Object arg) {
			String info=((LogSender)o).getInfo();
			String oldString=this.getText();
			oldString+=info+System.lineSeparator();
			this.setText(oldString);
		}
	}
	
	public static class AnotherLogSaver implements Observer{

		@Override
		public void update(Observable o, Object arg) {
			String info=((LogSender)o).getInfo();
			System.out.println(info);
		}
	}
     
    public static void main(String[] args) {
          
        Application.launch(args);
    }
      
    @Override
    public void start(Stage stage) throws Exception {
        LogSender sender=new LogSender();
        Label lbl = new Label("Start");
        LogSaver area=new LogSaver();
        
        area.setPrefSize(400, 500);
        
        sender.addObserver(area);
        sender.addObserver(new AnotherLogSaver());
        
        lbl.setFont(new Font(50));
        lbl.setTextFill(Color.GRAY);
        lbl.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        AnchorPane.setLeftAnchor(lbl, 1d);
        AnchorPane.setRightAnchor(area, 1d);
        AnchorPane root = new AnchorPane(lbl,area);
        Scene scene = new Scene(root, 800, 600);
        scene.getRoot().setOnKeyPressed(new EventHandler<KeyEvent>() {
        	@Override
			public void handle(KeyEvent event) {
        		String string=event.getCode().getName();
				sender.setInfo(string);
				lbl.setText(string);
			}
		});
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.setTitle("logger");
        stage.show();
    }

}