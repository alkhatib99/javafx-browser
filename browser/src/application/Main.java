package application;
	

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




import java.nio.file.Files;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;
public class Main extends Application {

    private LinkedStack<String> files;

	private LinkedStack<String> equations;
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();

        // Add a load button
        Button btnLoad = new Button("Load");
        root.add(btnLoad, 0, 0);

        // Add a label to display the name of the selected file
        Label lblFileName = new Label("No file selected");
        root.add(lblFileName, 1, 0);

        // Add a text area to display the contents of the file
        TextArea taFileContents = new TextArea();
        root.add(taFileContents, 0, 1, 2, 1);

        // Set the layout and spacing for the grid pane
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(10));
        btnLoad.setOnAction(event -> {
            // Create a file chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a file");
            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Equation Files", "*.242")
            );

            // Show the file chooser and get the selected file
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                // Read the contents of the file and parse it to extract the equations and files sections
                try {
                    // Read the file using the Files class
                    String contents = new String(Files.readAllBytes(file.toPath()));

                    // Parse the contents to extract the equations and files sections
                    parseFileContents(contents);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}
	private void parseFileContents(String contents) {
	    // Use a stack to keep track of the tags and ensure that they are balanced
		CursorArrayStack<String> tagStack = new CursorArrayStack<String>(50);

	    // Split the contents into lines
	    String[] lines = contents.split("\n");

	    // Loop through the lines and parse the equations and files sections
	    for (String line : lines) {
	        // Check if the line is a start tag
	        if (line.startsWith("<")) {
	            // Extract the tag name
	            String tagName = line.substring(1, line.length() - 1);

	            // Push the tag onto the stack
	            tagStack.push(tagName);

	            // Check if the tag is the start of the equations or files section
	            if (tagName.equals("equations") || tagName.equals("files")) {
	                // Start a new section
	                startSection(tagName);
	            }
	        }
	        // Check if the line is an end tag
	        else if (line.startsWith("</")) {
	            // Extract the tag name
	            String tagName = line.substring(2, line.length() - 1);

	            // Pop the top tag from the stack
	            String topTag = tagStack.pop();

	            // Check if the tag is the end of the equations or files section
	            if (tagName.equals("equations") || tagName.equals("files")) {
	                // End the current section
	                endSection(tagName);
	            }

	            // Check if the tag names match
	            if (!tagName.equals(topTag)) {
	                // The tags are not balanced, show an error message
	                showError("The tags are not balanced");
	                return;
	            }
	        }
	        // The line is not a tag, it must be a file or equation
	        else {
	            // Check the current section
	            String currentSection = tagStack.peek();
	            if (currentSection.equals("equations")) {
	                // Parse and evaluate the equation
	                parseAndEvaluateEquation(line);
	            } else if (currentSection.equals("files")) {
	                // Check if the file is valid
	                if (!isValidFile(line)) {
	                    // The file is not valid, show an error message
	                    showError("The file is not valid");
	                    return;
	                }
	            }
	        }
	    }

	    // Check if the stack is empty
	    if (!tagStack.isEmpty()) {
	        // The tags are not balanced, show an error message
	        showError("The tags are not balanced");
	        return;
	    }
	}
	private void startSection(String sectionName) {
	    if (sectionName.equals("equations")) {
	        // Initialize the list of equations
	    	        equations = new LinkedStack<String>();
	    } else if (sectionName.equals("files")) {
	        // Initialize the list of files
	    	   files = new LinkedStack<String>();
	    }
	}
	private void endSection(String sectionName) {
	    if (sectionName.equals("equations")) {
	        // Evaluate all the equations in the list
	        for (String equation : equations) {
	            parseAndEvaluateEquation(equation);
	        }
	    } else if (sectionName.equals("files")) {
	        // Load all the files in the list
	        for (String fileName : files) {
	            loadFile(fileName);
	        }
	    }
	}
	private void showError(String message) {
	    // Create an alert box with an error message
	    Alert alert = new Alert(Alert.AlertType.ERROR, message);
	    alert.showAndWait();
	}
	private void parseAndEvaluateEquation(String equation) {
	    // Check if the equation is valid and balanced
	    if (!isValidEquation(equation)) {
	        // The equation is not valid, show an error message
	        showError("The equation is not valid");
	        return;
	    }

	    // Convert the equation to postfix notation
	    String postfix = infixToPostfix(equation);

	    // Evaluate the postfix equation
	    double result = evaluatePostfix(postfix);

	    // Print the result
	    System.out.println("Result: " + result);
	}
	private boolean isValidFile(String fileName) {
	    // Check if the file has the .242 extension
	    return fileName.endsWith(".242");
	}

	private boolean isValidEquation(String equation) {
	    // Use a stack to check if the equation is balanced
		CursorArrayStack<Character> stack = new CursorArrayStack<Character>(50);

	    // Loop through all the characters in the equation
	    for (char c : equation.toCharArray()) {
	        // If the character is an opening delimiter, push it onto the stack
	        if (c == '(' || c == '[' || c == '{') {
	            stack.push(c);
	        }

	        // If the character is a closing delimiter, check if it matches the top of the stack
	        if (c == ')' || c == ']' || c == '}') {
	            if (stack.isEmpty()) {
	                // The equation is not balanced, return false
	                return false;
	            }
	            char top = stack.pop();
	            if (!((c == ')' && top == '(') || (c == ']' && top == '[') || (c == '}' && top == '{'))) {
	                // The equation is not balanced, return false
	                return false;
	            }
	        }
	    }

	    // If the stack is empty, the equation is balanced, return true
	    return stack.isEmpty();
	}
	private String infixToPostfix(String infix) {
	    // Use a stack to store the operators
		CursorArrayStack<Character> stack = new CursorArrayStack<Character>(50);

	    // Use a StringBuilder to construct the postfix expression
	    StringBuilder postfix = new StringBuilder();

	    // Loop through all the characters in the infix expression
	    for (char c : infix.toCharArray()) {
	        // If the character is a digit, append it to the postfix expression
	        if (Character.isDigit(c)) {
	            postfix.append(c);
	        }

	        // If the character is an operator, process it
	        else if (c == '+' || c == '-' || c == '*' || c == '/') {
	            // While there is an operator at the top of the stack with greater precedence,
	            // pop it and append it to the postfix expression
	            while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(c)) {
	                postfix.append(stack.pop());
	            }
	            // Push the current operator onto the stack
	            stack.push(c);
	        }

	        // If the character is an opening delimiter, push it onto the stack
	        else if (c == '(' || c == '[' || c == '{') {
	            stack.push(c);
	        }

	        // If the character is a closing delimiter, pop operators from the stack
	        // and append them to the postfix expression until the matching opening delimiter is found
	        else if (c == ')' || c == ']' || c == '}') {
	            while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != '[' && stack.peek() != '{') {
	                postfix.append(stack.pop());
	            }
	            if (!stack.isEmpty()) {
	                stack.pop();
	            }
	        }
	    }

	    // While there are still operators on the stack, pop them and append them to the postfix expression
	    while (!stack.isEmpty()) {
	        postfix.append(stack.pop());
	    }

	    // Return the postfix expression
	    return postfix.toString();
	}
	
	private void loadFile(String fileName) {
	    // Use a try-catch block to handle any exceptions that may occur while reading the file
	    try {
	        // Use a FileReader and BufferedReader to read the contents of the file
	        FileReader reader = new FileReader(fileName);
	        BufferedReader br = new BufferedReader(reader);

	        // Read each line of the file and append it to a StringBuilder
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }

	        // Close the reader
	        br.close();

	        // Get the contents of the file as a string
	        String fileContents = sb.toString();

	        // Do something with the file contents here
	    } catch (IOException e) {
	        // Handle the exception here
	    }
	}

	private double evaluatePostfix(String postfix) {
	    // Use a stack to store the operands
		CursorArrayStack<Double> stack = new CursorArrayStack<Double>(50);

	    // Loop through all the characters in the postfix expression
	    for (char c : postfix.toCharArray()) {
	        // If the character is a digit, push it onto the stack
	        if (Character.isDigit(c)) {
	            stack.push((double) (c - '0'));
	        }

	        // If the character is an operator, pop two operands from the stack,
	        // perform the operation, and push the result back onto the stack
	        else if (c == '+' || c == '-' || c == '*' || c == '/') {
	            double op2 = stack.pop();
	            double op1 = stack.pop();
	            double result = 0;
	            switch (c) {
	                case '+':
	                    result = op1 + op2;
	                    break;
	                case '-':
	                    result = op1 - op2;
	                    break;
	                case '*':
	                    result = op1 * op2;
	                    break;
	                case '/':
	                    result = op1 / op2;
	                    break;
	            }
	            stack.push(result);
	        }
	    }

	    // The result is the top of the stack
	    return stack.pop();
	}
	
	
	private int getPrecedence(char c) {
	    // Return the precedence of the operator
	    switch (c) {
	        case '+':
	        case '-':
	            return 1;
	        case '*':
	        case '/':
	            return 2;
	        default:
	            return 0;
	    }
	}

	


}
