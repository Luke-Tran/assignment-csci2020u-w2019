package question3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class DraggingPointsOnCircle extends Application {

    // Variables to set the dimensions of the shapes.
    double centerX = 150;
    double centerY = 137.5;
    double radius = 100;
    double redRadius = 10;

    // Variables to be used for calculations.
    double triangleCenterX;
    double triangleCenterY;
    double theta;

    // Circles to be drawn on the perimeter of the main circle.
    Circle c1 = new Circle(redRadius, Color.RED);
    Circle c2 = new Circle(redRadius, Color.RED);
    Circle c3 = new Circle(redRadius, Color.RED);

    // Text to label the angles of the triangle.
    Text angleText1 = new Text();
    Text angleText2 = new Text();
    Text angleText3 = new Text();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Question 3");

        Pane pane = new Pane();

        // Draws main circle.
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        pane.getChildren().add(circle);

        // Sets the x & y coordinates for 3 circles.
        Random r = new Random();
        //double randomAngle1 = 2*Math.PI * r.nextDouble();
        double randomAngle = 2*Math.PI * r.nextDouble();
        double offsetX1 = radius * Math.cos(randomAngle);
        double offsetY1 = radius * Math.sin(randomAngle);
        double x1 = centerX + offsetX1;
        double y1 = centerY + offsetY1;

        //double randomAngle2 = 2*Math.PI * r.nextDouble();
        randomAngle = 2*Math.PI * r.nextDouble();
        double offsetX2 = radius * Math.cos(randomAngle);
        double offsetY2 = radius * Math.sin(randomAngle);
        double x2 = centerX + offsetX2;
        double y2 = centerY + offsetY2;

        //double randomAngle3 = 2*Math.PI * r.nextDouble();
        randomAngle = 2*Math.PI * r.nextDouble();
        double offsetX3 = radius * Math.cos(randomAngle);
        double offsetY3 = radius * Math.sin(randomAngle);
        double x3 = centerX + offsetX3;
        double y3 = centerY + offsetY3;

        // Draws the triangle's edges
        Line edge1_2 = new Line();
        pane.getChildren().add(edge1_2);
        Line edge2_3 = new Line();
        pane.getChildren().add(edge2_3);
        Line edge3_1 = new Line();
        pane.getChildren().add(edge3_1);

        // Draws the circles and make the edges follow them
        c1.setStroke(Color.BLACK);
        c1.setCenterX(x1);
        c1.setCenterY(y1);
        edge1_2.startXProperty().bind(c1.centerXProperty());
        edge1_2.startYProperty().bind(c1.centerYProperty());
        edge3_1.endXProperty().bind(c1.centerXProperty());
        edge3_1.endYProperty().bind(c1.centerYProperty());
        pane.getChildren().add(c1);

        c2.setStroke(Color.BLACK);
        c2.setCenterX(x2);
        c2.setCenterY(y2);
        edge2_3.startXProperty().bind(c2.centerXProperty());
        edge2_3.startYProperty().bind(c2.centerYProperty());
        edge1_2.endXProperty().bind(c2.centerXProperty());
        edge1_2.endYProperty().bind(c2.centerYProperty());
        pane.getChildren().add(c2);

        c3.setStroke(Color.BLACK);
        c3.setCenterX(x3);
        c3.setCenterY(y3);
        edge3_1.startXProperty().bind(c3.centerXProperty());
        edge3_1.startYProperty().bind(c3.centerYProperty());
        edge2_3.endXProperty().bind(c3.centerXProperty());
        edge2_3.endYProperty().bind(c3.centerYProperty());
        pane.getChildren().add(c3);

        // Calculate the triangle's center.
        triangleCenterX = (x1+x2+x3)/3;
        triangleCenterY = (y1+y2+y3)/3;

        // Draws the text telling the angle of the triangle's corner.
        theta = Math.atan2(triangleCenterY - y1, triangleCenterX - x1);
        double x1Text = x1 + 25*Math.cos(theta);
        double y1Text = y1 + 25*Math.sin(theta);
        angleText1.setX(x1Text);
        angleText1.setY(y1Text);
        pane.getChildren().add(angleText1);

        theta = Math.atan2(triangleCenterY - y2, triangleCenterX - x2);
        double x2Text = x2 + 25*Math.cos(theta);
        double y2Text = y2 + 25*Math.sin(theta);
        angleText2.setX(x2Text);
        angleText2.setY(y2Text);
        pane.getChildren().add(angleText2);

        theta = Math.atan2(triangleCenterY - y3, triangleCenterX - x3);
        double x3Text = x3 + 25*Math.cos(theta);
        double y3Text = y3 + 25*Math.sin(theta);
        angleText3.setX(x3Text);
        angleText3.setY(y3Text);
        pane.getChildren().add(angleText3);

        setAngleValues();

        // Allows user to drag the circles using their mouse.
        c1.setOnMouseDragged(e -> {
            dragCircle(e, c1);
        });
        c2.setOnMouseDragged(e -> {
            dragCircle(e, c2);
        });
        c3.setOnMouseDragged(e -> {
            dragCircle(e, c3);
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);

        primaryStage.setScene(new Scene(borderPane, 300, 275));
        primaryStage.show();
    }

    // This function recalculates a circle's position, and the angles of the triangle.
    // It gets called whenever the user drags a circle with their mouse.
    public void dragCircle(MouseEvent e, Circle circle) {
        Circle cA = new Circle();
        Circle cB = new Circle();
        if (circle == c1) {
            cA = c2;
            cB = c3;
        }
        else if (circle == c2) {
            cA = c1;
            cB = c3;
        }
        else if (circle == c3) {
            cA = c1;
            cB = c2;
        }

        // Repositions the circle based on the mouse's location.
        double angle = Math.atan2(e.getY() - centerY, e.getX() - centerX);
        double newOffsetX = radius * Math.cos(angle);
        double newOffsetY = radius * Math.sin(angle);
        circle.setCenterX(newOffsetX + centerX);
        circle.setCenterY(newOffsetY + centerY);

        // Recalculate the triangle's center based on the circle's new position.
        triangleCenterX = (circle.getCenterX() + cA.getCenterX() + cB.getCenterX())/3;
        triangleCenterY = (circle.getCenterY() + cA.getCenterY() + cB.getCenterY())/3;

        // Repositions where the angles of the triangle are displayed.
        double newX;
        double newY;

        theta = Math.atan2(triangleCenterY - c1.getCenterY(), triangleCenterX - c1.getCenterX());
        newX = c1.getCenterX() + 25*Math.cos(theta);
        newY = c1.getCenterY() + 25*Math.sin(theta);
        angleText1.setX(newX);
        angleText1.setY(newY);

        theta = Math.atan2(triangleCenterY - c2.getCenterY(), triangleCenterX - c2.getCenterX());
        newX = c2.getCenterX() + 25*Math.cos(theta);
        newY = c2.getCenterY() + 25*Math.sin(theta);
        angleText2.setX(newX);
        angleText2.setY(newY);

        theta = Math.atan2(triangleCenterY - c3.getCenterY(), triangleCenterX - c3.getCenterX());
        newX = c3.getCenterX() + 25*Math.cos(theta);
        newY = c3.getCenterY() + 25*Math.sin(theta);
        angleText3.setX(newX);
        angleText3.setY(newY);
        
        setAngleValues();
    }

    // Calculates the angles of the triangle's corners and displays them in degrees to the nearest tenths.
    public void setAngleValues() {
        double c = Math.sqrt(Math.pow(c2.getCenterX() - c1.getCenterX(), 2) + Math.pow(c2.getCenterY() - c1.getCenterY(), 2));
        double a = Math.sqrt(Math.pow(c3.getCenterX() - c2.getCenterX(), 2) + Math.pow(c3.getCenterY() - c2.getCenterY(), 2));
        double b = Math.sqrt(Math.pow(c1.getCenterX() - c3.getCenterX(), 2) + Math.pow(c1.getCenterY() - c3.getCenterY(), 2));
        double A = round(Math.acos((a*a - b*b - c*c)/(-2*b*c))*180/Math.PI, 1);
        double B = round(Math.acos((b*b - a*a - c*c)/(-2*a*c))*180/Math.PI, 1);
        double C = round(Math.acos((c*c - b*b - a*a)/(-2*a*b))*180/Math.PI, 1);
        angleText1.setText(Double.toString(A));
        angleText2.setText(Double.toString(B));
        angleText3.setText(Double.toString(C));
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
