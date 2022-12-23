import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;

//Tests 4 		wrong
// 		4.5 	right
// 		2e3		right
// 		-7		wrong




  
public class Main extends Application{
	public static final String STRING="";
	public static final String UINT="unsigned Integer";
	public static final String INT="Integer";
	public static final String FLOAT="Floating point";
	public static final String DATE="Date (yyyy/mm/dd)";
	public static final String EMAIL="Email";
	public static final String TIME="Time hh:mm:ss";
     
    public static void main(String[] args) {
          
        Application.launch(args);
    }
      
    @Override
    public void start(Stage stage) throws Exception {
          
        ObservableList<String> items = FXCollections.observableArrayList(UINT, INT, FLOAT, DATE,EMAIL,TIME);
        ComboBox<String> comboBox = new ComboBox<String>(items);
        comboBox.setValue("Integer");
         
        Label lbl = new Label("WW");
        TextField fld=new TextField();
        fld.setPrefSize(200, 20);
        
        lbl.setFont(new Font(17));
        lbl.setTextFill(Color.GRAY);
        lbl.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        
        fld.setOnAction(event->{
        	String string = fld.getText();
        	if(string.equals(STRING)) {
        		lbl.setTextFill(Color.GRAY);
                lbl.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        	}else if(!matches(comboBox.getValue(), string)) {
        		lbl.setTextFill(Color.RED);
                lbl.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }else {
            	lbl.setTextFill(Color.GREEN);
                lbl.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        FlowPane root = new FlowPane(10, 10, comboBox,fld, lbl);
         
        Scene scene = new Scene(root, 800, 600);
          
        stage.setScene(scene);
        stage.setTitle("ComboBox in JavaFX");
        stage.show();
    }
    
    private boolean matches(String identifier,String text) {
    	if(identifier.equals(UINT))
    		return Pattern.compile("^\\d+$").matcher(text).matches();
    	if(identifier.equals(INT))
    		return Pattern.compile("^(\\+|-)?\\d+$").matcher(text).matches();
    	if(identifier.equals(FLOAT))
    		return Pattern.compile("^([-+]?(\\d+\\.?\\d*|\\d*\\.?\\d+)([Ee][-+]?[0-2]?\\d{1,2})?)$").matcher(text).matches() &&!Pattern.compile("^(\\+|-)?\\d+$").matcher(text).matches(); //^([-+]?(\d+\.?\d*|\d*\.?\d+)([Ee][-+]?[0-2]?\d{1,2})?)$
    	if(identifier.equals(DATE))
        	return Pattern.compile("^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$").matcher(text).matches();
    	if(identifier.equals(EMAIL))
            return Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matcher(text).matches();
    	if(identifier.equals(TIME))
            return Pattern.compile("([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]:([0-5][0-9]|[6][0])").matcher(text).matches();
    	
    	return false;
    }
}