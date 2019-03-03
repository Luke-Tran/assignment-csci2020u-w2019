// Luke Tran
// https://github.com/Luke-Tran/assignment-csci2020u-w2019
package question4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Histogram extends Application {

    // This array keeps track of which character appears how many times.
	int[] charCountArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	// This list stores the rectangles to draw the histogram.
	List<Rectangle> rectList = FXCollections.observableArrayList();

	// Spacing between bars and/or labels.
	double spacing = 0.035;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 4");

        Pane pane = new Pane();
        pane.setPadding(new Insets(5, 5, 5, 5));

        List<Label> labelList = makeLabelList();
        placeLabelList(pane, labelList);

        // Draws the horizontal axis of the histogram and binds its width to the pane width.
        Line horizontalAxis = new Line(15, 200, 0, 200);
        pane.getChildren().add(horizontalAxis);
        horizontalAxis.endXProperty().bind(pane.widthProperty().subtract(10));
        horizontalAxis.startYProperty().bind(pane.heightProperty().subtract(20));
        horizontalAxis.endYProperty().bind(pane.heightProperty().subtract(20));

        VBox vbox = new VBox();
        vbox.getChildren().add(pane);

        HBox hbox = new HBox();
        vbox.getChildren().add(hbox);

        // A label that tells the user what the text field does.
        Label filenameLabel = new Label("Filename");
        hbox.getChildren().add(filenameLabel);

        // Adds a text field where the user can enter a file path.
        TextField filenameField = new TextField();
        filenameField.setPrefWidth(260);
        hbox.getChildren().add(filenameField);

        // Pressing the Enter key on the text field calls a function that draws the histogram.
        filenameField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    readAndDraw(pane, filenameField);
                }
            }
        });

        // Adds a button where pressing it calls a function that draws the histogram.
        Button viewButton = new Button("View");
        hbox.getChildren().add(viewButton);
        viewButton.setOnAction(e -> {
            readAndDraw(pane, filenameField);
        });

        primaryStage.setScene(new Scene(vbox, 350, 275));
        primaryStage.show();
    }

    // Returns a list of labels that the histogram will use to draw its horizontal axis.
    public List<Label> makeLabelList() {
        List<Label> labelList = FXCollections.observableArrayList();
        Label ALabel = new Label("A");
        Label BLabel = new Label("B");
        Label CLabel = new Label("C");
        Label DLabel = new Label("D");
        Label ELabel = new Label("E");
        Label FLabel = new Label("F");
        Label GLabel = new Label("G");
        Label HLabel = new Label("H");
        Label ILabel = new Label("I");
        Label JLabel = new Label("J");
        Label KLabel = new Label("K");
        Label LLabel = new Label("L");
        Label MLabel = new Label("M");
        Label NLabel = new Label("N");
        Label OLabel = new Label("O");
        Label PLabel = new Label("P");
        Label QLabel = new Label("Q");
        Label RLabel = new Label("R");
        Label SLabel = new Label("S");
        Label TLabel = new Label("T");
        Label ULabel = new Label("U");
        Label VLabel = new Label("V");
        Label WLabel = new Label("W");
        Label XLabel = new Label("X");
        Label YLabel = new Label("Y");
        Label ZLabel = new Label("Z");
        labelList.add(ALabel);
        labelList.add(BLabel);
        labelList.add(CLabel);
        labelList.add(DLabel);
        labelList.add(ELabel);
        labelList.add(FLabel);
        labelList.add(GLabel);
        labelList.add(HLabel);
        labelList.add(ILabel);
        labelList.add(JLabel);
        labelList.add(KLabel);
        labelList.add(LLabel);
        labelList.add(MLabel);
        labelList.add(NLabel);
        labelList.add(OLabel);
        labelList.add(PLabel);
        labelList.add(QLabel);
        labelList.add(RLabel);
        labelList.add(SLabel);
        labelList.add(TLabel);
        labelList.add(ULabel);
        labelList.add(VLabel);
        labelList.add(WLabel);
        labelList.add(XLabel);
        labelList.add(YLabel);
        labelList.add(ZLabel);
        return labelList;
    }

    // Labels the horizontal axis of the histogram.
    public void placeLabelList(Pane pane, List<Label> labelList) {
        int x = 20;
        int i = 1;
        for (Label label : labelList) {
            label.layoutYProperty().bind(pane.heightProperty().subtract(20));
            label.layoutXProperty().bind(pane.widthProperty().multiply(spacing*i));
            i++;
            pane.getChildren().add(label);
        }
    }

    // Reads the file specified and draws the histogram according to the file's contents.
    public void readAndDraw(Pane pane, TextField filenameField) {
        // Resets the character counters and clears any graphics from previous readings.
        for (int i = 0; i < charCountArray.length; i++) {
            charCountArray[i] = 0;
        }
        drawHistogram(pane);

        String filePathString = filenameField.getText();
        File filePath = new File(filePathString);
        if (!filePath.exists()) {
            System.out.println("Error: File at " + filePathString + " does not exist");
        }

        try {
            Scanner scanner = new Scanner(filePath);

            while (scanner.hasNext()) {
                String word = scanner.next();
                for (int i = 0; i < word.length(); i++) {
                    int chrToInt = (int)word.charAt(i);
                    if (65 <= chrToInt && chrToInt <= 90) {
                        int charIndex = chrToInt - 65;
                        charCountArray[charIndex]++;
                    }
                    else if (97 <= chrToInt && chrToInt <= 122) {
                        int charIndex = chrToInt - 97;
                        charCountArray[charIndex]++;
                    }
                }
            }
            drawHistogram(pane);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	public void drawHistogram(Pane pane) {
        // Clears graphics from previous readings.
        for (Rectangle rect : rectList) {
            pane.getChildren().remove(rect);
        }
        rectList.clear();

        double width = 10;
        double height = 220;
		int maxValueIndex = maxValueOf(charCountArray);

		// If the maximum number of appearances for any character is 0, then nothing is drawn.
        if (0 == charCountArray[maxValueIndex]) {
            return;
        }

        // First creates the tallest bar and have its height predefined.
        Rectangle biggestBar = new Rectangle(0, 10, width, height);

        // Loops through the character counter array and draws bars
        // with heights determined by the value of the array element.
		for (int i = 0; i < charCountArray.length; i++) {
            if (i != maxValueIndex && 0 != charCountArray[i]) {
                // The remaining bars are drawn with heights relative to the biggest bar's height.
                double heightRatio = (double)charCountArray[i]/charCountArray[maxValueIndex];
                height = biggestBar.getHeight()*heightRatio;
                double y = biggestBar.getY() + biggestBar.getHeight() - height;
                Rectangle r = new Rectangle(0, y, width, height);
                r.layoutXProperty().bind(pane.widthProperty().multiply(spacing*(i+1)));
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().add(r);
                rectList.add(r);
            }
            else if (i == maxValueIndex) {
                // When the array reaches the index of the most frequent character,
                // the biggest bar is placed on the histogram.
                biggestBar.layoutXProperty().bind(pane.widthProperty().multiply(spacing*(i+1)));
                biggestBar.setFill(Color.WHITE);
                biggestBar.setStroke(Color.BLACK);
                pane.getChildren().add(biggestBar);
                rectList.add(biggestBar);
            }
		}
	}

	// Returns the index of the maximum value in the array.
	public int maxValueOf(int[] array) {
        int index = 0;
        double maxVal = 0;
        for (int i = 0; i < array.length; i++) {
            if (maxVal < array[i]) {
                maxVal = array[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        launch(args);
    }
}