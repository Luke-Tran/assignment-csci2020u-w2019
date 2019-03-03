package question2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.lang.Math.pow;

public class InvestmentValueCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Question 2");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setHgap(10);
        layout.setVgap(10);

        // Adds a label for the investment amount text field.
        Label investmentAmountLabel = new Label("Investment Amount");
        GridPane.setConstraints(investmentAmountLabel, 0,0);
        layout.getChildren().add(investmentAmountLabel);

        // Adds a text field where the user enters the investment amount.
        TextField investmentAmountField = new TextField();
        GridPane.setConstraints(investmentAmountField, 1,0);
        layout.getChildren().add(investmentAmountField);

        // Adds a label for the years text field.
        Label yearsLable = new Label("Years");
        GridPane.setConstraints(yearsLable, 0,1);
        layout.getChildren().add(yearsLable);

        // Adds a text field where the user enters the number of years to calculate for.
        TextField yearsField = new TextField();
        GridPane.setConstraints(yearsField, 1,1);
        layout.getChildren().add(yearsField);

        // Adds a label for the monthy interest rate text field.
        Label annualRateLabel = new Label("Monthly Interest Rate");
        GridPane.setConstraints(annualRateLabel, 0,2);
        layout.getChildren().add(annualRateLabel);

        // Adds a text field where the user enters the monthy interest rate.
        TextField monthlyRateField = new TextField();
        GridPane.setConstraints(monthlyRateField, 1,2);
        layout.getChildren().add(monthlyRateField);

        // Adds a label to indicate where the calculated value will appear.
        Label futureValueLabel = new Label("Future Value");
        GridPane.setConstraints(futureValueLabel, 0,3);
        layout.getChildren().add(futureValueLabel);

        // Adds a label to display the calculated value.
        Label futureValueDisplay = new Label();
        GridPane.setConstraints(futureValueDisplay, 1,3);
        layout.getChildren().add(futureValueDisplay);

        // Adds a button where when pressed, will calculate the future investment value
        // based on information entered in the text fields.
        // It also checks and rejects invalid or empty entries.
        Button calcuateButton = new Button("Calculate");
        GridPane.setConstraints(calcuateButton, 1, 4);
        layout.getChildren().add(calcuateButton);
        calcuateButton.setOnAction(e -> {
            // Obtains the values from the text fields.
            String investmentAmountString = investmentAmountField.getText();
            String yearsString = yearsField.getText();
            String monthlyRateString = monthlyRateField.getText();

            // If any field is empty, displays an error and does not make a calculation.
            if (investmentAmountString.equals("") || yearsString.equals("") || monthlyRateString.equals("")) {
                futureValueDisplay.setText("ERROR: empty fields");
                return;
            }
            // If the text field is not a real number, displays an error and does not make a calculation.
            if (!investmentAmountString.matches("^[+]?\\d+([.]\\d+)?$")) {
                futureValueDisplay.setText("ERROR: fields must be numbers");
                return;
            }
            if (!yearsString.matches("^[+]?\\d+([.]\\d+)?$")) {
                futureValueDisplay.setText("ERROR: fields must be numbers");
                return;
            }
            if (!monthlyRateString.matches("^[+]?\\d+([.]\\d+)?$")) {
                futureValueDisplay.setText("ERROR: fields must be numbers");
                return;
            }

            // Converts the text field entries to doubles and makes the calculation.
            double investmentAmount = round(Float.valueOf(investmentAmountString), 2);
            double years = Double.valueOf(yearsString);
            double monthlyRate = Double.valueOf(monthlyRateString)/100;
            double futureValue = round(investmentAmount * pow((1 + monthlyRate), years * 12), 2);
            futureValueDisplay.setText(String.valueOf(futureValue));
        });

        primaryStage.setScene(new Scene(layout, 330, 275));
        primaryStage.show();
    }

    // Rounds a double's value to the specified number of decimal places.
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
