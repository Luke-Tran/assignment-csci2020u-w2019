package question1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;
import java.util.stream.IntStream;

public class DisplayThreeCards extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Question 1");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setHgap(10);
        layout.setVgap(10);

        int cardAmount = 3;

        // Cards are selected by generating a random integer between 1 and 52.
        int[] cardNums = new int[cardAmount];
        Random rand = new Random();
        int card0 = rand.nextInt(52) + 1;
        cardNums[0] = card0;

        int card1 = rand.nextInt(52) + 1;
        boolean hasNum = IntStream.of(cardNums).anyMatch(x -> x == card1);
        int i = 1;
        if (!hasNum) {
            cardNums[1] = card1;
            hasNum = true;
            i++;
        }
        while (hasNum) {
            int n = rand.nextInt(52) + 1;
            hasNum = IntStream.of(cardNums).anyMatch(x -> x == n);
            if (!hasNum) {
                cardNums[i] = n;
            }
        }

        String[] cardStringArray = new String[cardAmount];
        for (i = 0; i < cardAmount; i++) {
            cardStringArray[i] = "Cards/" + String.valueOf(cardNums[i]) + ".png";
        }

        // Loads and displays card images.
        Image card0Image = new Image(getClass().getResource(cardStringArray[0]).toString());
        ImageView card0View = new ImageView(card0Image);
        GridPane.setConstraints(card0View, 0, 0);
        layout.getChildren().add(card0View);

        Image card1Image = new Image(getClass().getResource(cardStringArray[1]).toString());
        ImageView card1View = new ImageView(card1Image);
        GridPane.setConstraints(card1View, 1, 0);
        layout.getChildren().add(card1View);

        Image card2Image = new Image(getClass().getResource(cardStringArray[2]).toString());
        ImageView card2View = new ImageView(card2Image);
        GridPane.setConstraints(card2View, 2, 0);
        layout.getChildren().add(card2View);

        primaryStage.setScene(new Scene(layout, 260, 110));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
