package org.alsoftware.calc;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("calc.fxml"));
			BorderPane root = (BorderPane)loader.load();
			calcController controller = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);			
			   scene.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
				   KeyCode c = key.getCode();					   
				   switch (c) {				   
				   case ENTER: 	controller.performTotale();
				   			   	break;
				   case DIGIT0:
				   case NUMPAD0:
					   			controller.performDigit("0");
				   				break;
				   case DIGIT1: 
				   case NUMPAD1: 
					   			controller.performDigit("1");
	   							break;
				   case DIGIT2:
				   case NUMPAD2:
					   			controller.performDigit("2");
	   							break;
				   case DIGIT3: 
				   case NUMPAD3: 
					   			controller.performDigit("3");
	   							break;
				   case DIGIT4:
				   case NUMPAD4: 
					   			controller.performDigit("4");
	   							break;
				   case DIGIT5: 
				   case NUMPAD5: 
					   			controller.performDigit("5");
	   							break;
				   case DIGIT6: 
				   case NUMPAD6: 
					   			controller.performDigit("6");
	   							break;
				   case DIGIT7: 
				   case NUMPAD7: 
					   			controller.performDigit("7");
	   							break;
				   case DIGIT8: 
				   case NUMPAD8:
					   			controller.performDigit("8");
	   							break;
				   case DIGIT9: 
				   case NUMPAD9:
					   			controller.performDigit("9");
	   							break;
				   case DECIMAL:
			   					controller.performDigit(".");
			   					break;		
				   case ADD:
					   			controller.performOperazione("+");
					   			break;
				   case SUBTRACT: controller.performOperazione("-");
				   				  break;
				   case DIVIDE: controller.performOperazione("/");
				   				break;
				   case MULTIPLY: controller.performOperazione("*");
				   				  break;
				   case DELETE:
				   case BACK_SPACE: controller.performDelDigit();
				   					break;
				default:
					break;				   				
				   }
				});			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
