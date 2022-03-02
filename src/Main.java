import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXNodesList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends Application {
    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";
ArrayList<Line> lines = new ArrayList<>();
    HashMap<String,Circle> circles = new HashMap<String,Circle>();

String currentStart = "";
String currentEnd = "";
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane ScreenPane = new AnchorPane();
        AnchorPane ControlPane = new AnchorPane();
        ScrollPane MapPane = new ScrollPane();
  //      MapPane.getStylesheets().add(this.getClass().getResource("st.css").toExternalForm());
        ControlPane.setPrefWidth(382);
        ControlPane.setPrefHeight(613);
        ControlPane.setLayoutX(471);
        ControlPane.setLayoutY(0);
        MapPane.setPrefWidth(471);
        MapPane.setPrefHeight(620);
        MapPane.setLayoutY(0);
        MapPane.setLayoutX(0);
        ControlPane.setStyle("-fx-background-color:  White;");
        MapPane.setStyle("-fx-background-color: Blue;");
        ScreenPane.getStylesheets().add("dijkstl.css");
        ComboBox<String> Start = new ComboBox<String>();
        ComboBox<String> End = new ComboBox<String>();

        Start.setPromptText("نقطة البداية");
        End.setPromptText("نقطة النهاية");
        Start.getItems().addAll("عكا", "حيفا");
        End.getItems().addAll("عكا", "حيفا");

        Start.getStyleClass().add("combo-box");
        End.getStyleClass().add("combo-box");

        Start.setPrefWidth(323);
        Start.setPrefHeight(26);
        Start.setLayoutX(29);
        Start.setLayoutY(159);

        End.setPrefWidth(323);
        End.setPrefHeight(26);
        End.setLayoutX(29);
        End.setLayoutY(213);




        /******* Nodes List *******/

        JFXButton ssbutton1 = new JFXButton();
        Label sslabel = new Label("R1");
        sslabel.setStyle(FX_TEXT_FILL_WHITE);
        ssbutton1.setGraphic(sslabel);
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        JFXButton ssbutton2 = new JFXButton("R2");
        ssbutton2.setTooltip(new Tooltip("Button R2"));
        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        JFXButton ssbutton3 = new JFXButton("R3");
        ssbutton3.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton3.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);


        JFXNodesList nodesList3 = new JFXNodesList();
        nodesList3.setSpacing(10);
        // init nodes
        nodesList3.addAnimatedNode(ssbutton1);
        nodesList3.addAnimatedNode(ssbutton2);
        nodesList3.addAnimatedNode(ssbutton3);


        JFXButton sbutton1 = new JFXButton();
        Label slabel = new Label("B1");
        slabel.setStyle(FX_TEXT_FILL_WHITE);
        sbutton1.setGraphic(slabel);
        sbutton1.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton1.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON);

        JFXButton sbutton2 = new JFXButton("B2");
        sbutton2.setTooltip(new Tooltip("Button B2"));
        sbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON);

        JFXButton sbutton3 = new JFXButton("B3");
        sbutton3.setButtonType(JFXButton.ButtonType.RAISED);
        sbutton3.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON);


        JFXNodesList nodesList2 = new JFXNodesList();
        nodesList2.setSpacing(10);
        // init nodes
        nodesList2.addAnimatedNode(sbutton1);
        nodesList2.addAnimatedNode(nodesList3);
        nodesList2.addAnimatedNode(sbutton2);
        nodesList2.addAnimatedNode(sbutton3);
        nodesList2.setRotate(90);


        JFXButton button1 = new JFXButton();
        Label label = new Label("احسب");
        button1.setGraphic(label);
        label.setStyle(FX_TEXT_FILL_WHITE);
        button1.setButtonType(JFXButton.ButtonType.RAISED);
        button1.getStyleClass().add(ANIMATED_OPTION_BUTTON);
        JFXButton button2 = new JFXButton("G2");
        button2.setTooltip(new Tooltip("Button G2"));
        button2.setButtonType(JFXButton.ButtonType.RAISED);
        button2.getStyleClass().add(ANIMATED_OPTION_BUTTON);

        JFXButton button3 = new JFXButton("G3");
        button3.setButtonType(JFXButton.ButtonType.RAISED);
        button3.getStyleClass().add(ANIMATED_OPTION_BUTTON);

        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setSpacing(10);
        nodesList.addAnimatedNode(button1);
        final HBox container = new HBox(new JFXButton("INFO"), button2);
        container.setAlignment(Pos.CENTER_RIGHT);
        container.setSpacing(12);
        nodesList.addAnimatedNode(container, (expanded, duration) -> {
                    List<KeyFrame> frames = new ArrayList<>();
                    frames.add(new KeyFrame(duration,
                            new KeyValue(container.getChildren().get(1).scaleXProperty(), expanded ? 1 : 0, Interpolator.EASE_BOTH),
                            new KeyValue(container.getChildren().get(1).scaleYProperty(), expanded ? 1 : 0, Interpolator.EASE_BOTH)
                    ));
                    frames.add(new KeyFrame(Duration.millis(duration.toMillis()),
                            new KeyValue(container.getChildren().get(0).opacityProperty(), expanded ? 0 : 1, Interpolator.EASE_BOTH),
                            new KeyValue(container.getChildren().get(0).translateXProperty(), expanded ? 20 : 0, Interpolator.EASE_BOTH)
                    ));
                    frames.add(new KeyFrame(Duration.millis(duration.toMillis() + 40),
                            new KeyValue(container.getChildren().get(0).opacityProperty(), expanded ? 1 : 0, Interpolator.EASE_BOTH),
                            new KeyValue(container.getChildren().get(0).translateXProperty(), expanded ? 0 : 20, Interpolator.EASE_BOTH)
                    ));
                    return frames;
                }
        );

        JFXNodesList.alignNodeToChild(container, button2);
        container.getChildren().get(0).setOpacity(0);
        container.setScaleX(1);
        container.setScaleY(1);

        nodesList.addAnimatedNode(nodesList2);
        nodesList.addAnimatedNode(button3);
        nodesList.setRotate(180);








        /******* Nodes List *******/










        Button Calculate = new Button("احسب");
        Calculate.setFont(Font.font("Dubai",20));
        Calculate.getStyleClass().add("calbtn");
        Calculate.setTextFill(Color.WHITE);
        Calculate.setLayoutX(131);
        Calculate.setLayoutY(277);
        Calculate.setPrefWidth(118);
        Calculate.setPrefHeight(43);

        TextField Disatnce = new TextField("المسافة: ");
        TextArea Path = new TextArea("المسار: ");
        Disatnce.setPrefWidth(358);
        Disatnce.setPrefHeight(50);
        Disatnce.setLayoutX(12);
        Disatnce.setLayoutY(338);
        Disatnce.getStyleClass().add("tf");
        Path.getStyleClass().add("tf");
        Path.setPrefWidth(358);
        Path.setPrefHeight(200);
        Path.setLayoutX(11);
        Path.setLayoutY(400);
        Disatnce.setEditable(false);
        Path.setEditable(false);
        ImageView logo = new ImageView(new Image(new FileInputStream("pallogo.png")));
        logo.setFitWidth(132);
        logo.setFitHeight(112);
        logo.setLayoutX(124);
        logo.setLayoutY(26);

        ControlPane.getChildren().addAll(Start,End,Calculate,Disatnce,Path,logo);

        ImageView imageView = new ImageView(new Image(new FileInputStream("palestine.png")));
        Group group = new Group(imageView);

        StackPane Map = new StackPane(new Group(group));
        Map.setOnScroll(
                e -> {
                    if (e.isShortcutDown() && e.getDeltaY() != 0) {
                        if (e.getDeltaY() < 0) {
                            group.setScaleX(Math.max(group.getScaleX() - 0.1, 0.5));
                        } else {
                            group.setScaleX(Math.min(group.getScaleX() + 0.1, 5.0));
                        }
                        group.setScaleY(group.getScaleX());
                        e.consume(); // prevents ScrollEvent from reaching ScrollPane
                    }
                });


        MapPane = new ScrollPane(Map);
        MapPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        MapPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        MapPane.setPannable(true);
        MapPane.setMaxWidth(471);
        MapPane.setMaxHeight(620);
        MapPane.setMinWidth(471);
        MapPane.setMinHeight(620);

        ScreenPane.getChildren().addAll(MapPane, ControlPane);

        Graph graph = new Graph();
        ReadGraph(graph);
        for (String i : graph.Vertices.keySet()) {
            Start.getItems().add(i);
            End.getItems().add(i);
            Circle circle = new Circle(6);

            circle.setStrokeWidth(0);
            circle.setFill(Color.BLACK);
            circle.setCenterX(graph.Vertices.get(i).getCity().getX());
            circle.setCenterY(graph.Vertices.get(i).getCity().getY());

            circle.setOnMouseClicked(event -> {
                if (Start.getValue()==null || Start.getValue().isEmpty() || Start.getValue()=="") {
                    Start.setValue(i);
                    circle.setFill(Color.MEDIUMVIOLETRED);
                    currentStart = i;
                }

                else if (End.getValue()==null || End.getValue().isEmpty()) {
                    End.setValue(i);
                    circle.setFill(Color.MEDIUMVIOLETRED);
                    currentEnd = i;
                }
                else {
                    circles.get(Start.getValue()).setFill(Color.BLACK);
                    circles.get(i).setFill(Color.MEDIUMVIOLETRED);
                    String temp = End.getValue();
                    End.setValue(i);
                    Start.setValue(temp);
                    currentStart = temp;
                    currentEnd = i;
                }

            });

            group.getChildren().add(circle);
            circles.put(i,circle);
        }

        Start.setOnAction(event -> {
            if (Start.getValue()!=null) {
                if (!currentStart.equals(""))
                    circles.get(currentStart).setFill(Color.BLACK);
                    circles.get(Start.getValue()).setFill(Color.MEDIUMVIOLETRED);
                    currentStart = Start.getValue();

            }
        });
        End.setOnAction(event -> {
            if (End.getValue()!=null) {
                if (!End.equals(""))
                    circles.get(End.getValue()).setFill(Color.BLACK);
                circles.get(End.getValue()).setFill(Color.MEDIUMVIOLETRED);
                currentEnd = End.getValue();
            }
        });
        Scene Dijkstra = new Scene(ScreenPane, 847, 613);
        primaryStage.setScene(Dijkstra);
        primaryStage.setResizable(false);
        Calculate.setOnAction(event -> {
            if (Start.getValue()==null || End.getValue()==null || Start.getValue().isEmpty() || End.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("fill the combox boxes!");
                alert.showAndWait();
            }
            else {
                AnchorPane chooseSearch = new AnchorPane();
                Label Choose = new Label("اختر خوارزمية البحث");
                Choose.setFont(Font.font("Dubai",27));
                Choose.setLayoutX(193);
                Choose.setLayoutY(14);
                Choose.setTextFill(Paint.valueOf("#0d2159"));

                JFXComboBox<String> Searches = new JFXComboBox<>();
                Searches.getItems().addAll(
                        "Uniform Cost (Uninformed)",
                                 "A* (Informed)",
                                 "Greedy Best First (Informed)",
                                 "Bidirectional (Uninformed)",
                                 "Breadth First (Uninformed)",
                                 "Depth First (Uninformed)",
                                 "Iterative Deepning (Uninformed)",
                                 "Depth Limited (Uninformed)"
                );
                Searches.setPrefWidth(390);
                Searches.setPrefHeight(26);
                Searches.setLayoutX(98);
                Searches.setLayoutY(61);

                JFXButton Go = new JFXButton("انطلق");
                Go.getStyleClass().add("button-raised");
                Go.setLayoutX(222);
                Go.setLayoutY(99);
                Go.setPrefWidth(142);
                Go.setPrefHeight(38);
                Go.setRipplerFill(Paint.valueOf("#0e286f"));

                chooseSearch.getChildren().addAll(Choose,Searches,Go);
                Scene tempScene = new Scene(chooseSearch,587,149);
                Stage tempStage = new Stage();
                tempStage.setScene(tempScene);
                tempStage.setResizable(false);
                tempStage.show();

                Go.setOnAction(event1 -> {
                    Report report = new Report();
                    if (Searches.getValue().equals("Uniform Cost (Uninformed)"))
                        SearchAlgorithms.UniformCostSearch(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("A* (Informed)"))
                    SearchAlgorithms.A_Star(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("Greedy Best First (Informed)"))
                    SearchAlgorithms.BestFirstSearch(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("Bidirectional (Uninformed)"))
                    SearchAlgorithms.BidirectionalSearch(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("Breadth First (Uninformed)"))
                    SearchAlgorithms.BreadthFirstSearch(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("Depth First (Uninformed)"))
                    SearchAlgorithms.DepthFirstSearch(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("Iterative Deepning (Uninformed)"))
                    SearchAlgorithms.IterativeDeepingSearch(graph,Start.getValue(),End.getValue(),report);
                    else if (Searches.getValue().equals("Depth Limited (Uninformed)"))
                    SearchAlgorithms.DepthLimitedSearch(graph,Start.getValue(),End.getValue(),graph.Vertices.size(),report);

                    graph.fill(Disatnce,Path,End.getValue());
                    AddLines(group, graph, End.getValue());
                    tempStage.close();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Time (Loob executed): "+report.time_counter+"\n"+"Space (queue or list size): "+report.space_counter);
                    alert.showAndWait();
                });
          //      graph.findShortestPath(Start.getValue(), End.getValue());
               // graph.fill(Disatnce, Path, End.getValue());
                //AddLines(group, graph, End.getValue());
            }

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void ReadGraph(Graph graph) throws FileNotFoundException {

            Scanner scanner = new Scanner(new File("Vertices.txt"));
            String tokens[];
        while (scanner.hasNextLine()) {
            tokens = scanner.nextLine().split("-");
            graph.insertVertex(new Vertex(new City(tokens[0],Double.parseDouble(tokens[1]),Double.parseDouble(tokens[2]))));
        }
        scanner = new Scanner(new File("Edges.txt"));
        while (scanner.hasNextLine()) {

            tokens = scanner.nextLine().split("-");
            graph.addAdjacent(tokens[0],tokens[1],Double.parseDouble(tokens[2]));
            graph.addAdjacent(tokens[1],tokens[0],Double.parseDouble(tokens[2]));
        }
        }
    public void AddLines(Group group, Graph graph, String Distance) {

        for (int i = 0; i < lines.size(); i++) {
            group.getChildren().remove(lines.get(i));
        }


        addLines(group,graph.Vertices.get(Distance));


    }

    private void addLines(Group group, Vertex vertex) {
        if (vertex.getPrev()==null)
            return;
        addLines(group,vertex.getPrev());
        Line line = new Line();
        line.setStrokeWidth(4);
        line.setStroke(Color.WHITE);
      //  circles.get(vertex.getCity().getName()).setFill(Color.MEDIUMVIOLETRED);
        line.setStyle("-fx-stroke-dash-array: 4;");
        lines.add(line);
        group.getChildren().add(line);

    }
    }