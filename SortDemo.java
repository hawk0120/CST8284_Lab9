package cst8284.collections;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.scene.control.ListView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class SortDemo extends Application {

	private static Stage pStage;

	// Note: Only of use if you are attempting Bonus B:
	//private static final char[] scrabbleValues = {
	//		1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10
	//};  // value of each letter in the game Scrabble, i.e. 'a'=1, 'b'=3, etc.

	@Override
	public void start(Stage primaryStage){

		pStage = primaryStage;
		ObservableList<String> obsList = FXCollections.observableList(getFileContents());
		ListView<String> outputList = new ListView<String>(obsList);
		outputList.setPrefWidth(200);

		BorderPane scenePane = new BorderPane();
		scenePane.setLeft(outputList);

		Button btnAlpha = new Button("Alphabetical Order");
		btnAlpha.setOnAction((ActionEvent e) ->{
			Collections.sort(obsList);
		}); // Sorts the observableList using Collections.sort()

		Button btnWordLength = new Button("     Word Length    ");
		btnWordLength.setOnAction((ActionEvent e) -> {
			SortListLength sort = new SortListLength();
			Collections.sort(obsList, sort); 
		}); //Sorts observableList by word Length

		Button btnReverse = new Button("   Reverse Order    "); 
		btnReverse.setOnAction((ActionEvent e) -> {
			Collections.reverse(obsList);
		}); //Sorts the observableList using collections.reserve()

		VBox vb = new VBox();
		vb.getChildren().addAll(btnAlpha, btnWordLength, btnReverse);
		scenePane.setRight(vb);

		Scene scene =  new Scene(scenePane, 640, 480);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Welcome to WordSort");
		primaryStage.show();	  

	}

	public static void main(String[] args){
		Application.launch(args);
	}

	public ArrayList<String> getFileContents() {
		ArrayList<String> list = new ArrayList<String>();
		FileChooser fc = new FileChooser();
		fc.setTitle("Open a word list file");
		File file = fc.showOpenDialog(pStage);
		if(file != null) {
			try {
				InputStream fis = new FileInputStream(file);
				BufferedReader bf = new BufferedReader(new InputStreamReader(fis));				
				String line;
				while((line = bf.readLine()) != null ) {
					list.add(line);
				}
				bf.close();
			} catch (IOException e) {
			}	
		} //Opens and adds the file to the ArrayList
		return list; 
	}

	
	public class SortListLength implements Comparator<String>{
		@Override
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}//Sorts by list
	}
	

}
