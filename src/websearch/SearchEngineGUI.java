package websearch;

import jsjf.ArrayStack;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jsjf.ArrayUnorderedList;


public class SearchEngineGUI extends Application {

	String CurrentText;
	
	@Override
    public void start(Stage primaryStage) {
		
		
		//create two stack for the forward and back button
		ArrayStack<String> back = new ArrayStack<String>();
		ArrayStack<String> forward = new ArrayStack<String>();
		
		//Make search button
        Button searchbtn = new Button();
        searchbtn.setText("Go!");
        TextField tf = new TextField(); //website to load
        CurrentText = tf.getText();
        
      //Make search button2 for search bar
        Button searchbtn2 = new Button();
        searchbtn.setText("Go!");
        
        //Make button to go back
        Button backbtn = new Button();
        backbtn.setText("<--");
        
        //Make button to go forward
        Button forwardbtn = new Button();
        forwardbtn.setText("-->");
        
        //Make website view area
        WebView webview = new WebView();
        WebEngine webEngine = webview.getEngine();
        webview.setMaxSize(1000, 1000);
        
        //Size output
        Text tSearch = new Text("Search:");
        TextField searchBar = new TextField();

        //area to display string input and compressed output
        Text tResult = new Text("Relevent Sites: ");
        TextArea searchResult = new TextArea();
        
        //Go button event handler (web display)
        searchbtn.setOnAction((ActionEvent event) -> {
                //load and read website
        		back.push(CurrentText); //add the website to the back button stack
                CurrentText = tf.getText();
                webEngine.load(CurrentText);
                while(!forward.isEmpty()) { //empty the forward stack
                	forward.pop();
                }

        });
        
      //Go button2 event handler (search engine)
        searchbtn2.setOnAction((ActionEvent event) -> {
                //load and read website
        	try {
                ArrayUnorderedList<URLWords> crawlResult = WebCrawlResultReader.readFromFile("crawlResultRWU.txt");

                SearchEngine se = new SearchEngine(crawlResult);

                String result = se.search(searchBar.getText().toLowerCase());
                searchResult.setText(result);
                
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found");
            }
        });
        
        //Back button event handler
        backbtn.setOnAction((ActionEvent event) -> {
        	try {
        		
        		forward.push(tf.getText()); //put current text on the forward pile before loading the back button result
	        	String temp = back.pop();
	        	webEngine.load(temp);
	        	tf.setText(temp);
        	} catch (java.util.EmptyStackException ex) {
    			System.out.println("Empty Stack");
        	}
        });
        
      //forward button implementation
        forwardbtn.setOnAction((ActionEvent event) -> {
        	try {
        		back.push(tf.getText()); //put current text on back pile again
	        	String temp = forward.pop();
	        	webEngine.load(temp);
	        	tf.setText(temp);
        	} catch (java.util.EmptyStackException ex) {
    			System.out.println("Empty Stack");
        	}
        });

        //Layout components
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.add(searchbtn, 0, 0);
        root.add(tf, 1, 0);
        root.add(backbtn, 2, 0);
        root.add(forwardbtn, 3, 0);
        root.add(webview, 0, 1, 2, 1);
        root.add(tSearch, 0, 2);
        root.add(searchBar, 1, 3, 2, 1);
        root.add(searchbtn2, 0, 3);
        root.add(tResult, 0, 4);
        root.add(searchResult, 1, 4);


        Scene scene = new Scene(root, 1000, 1000);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
