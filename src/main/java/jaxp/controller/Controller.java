package jaxp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jaxp.entity.*;
import jaxp.model.Model;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    University university;
    @FXML
    TreeView<?> treeView;

    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeApplication(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    private void openFileDialog(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File openfile = fileChooser.showOpenDialog(new Stage());
        Model model = new Model();
        if (model.startModel(openfile.getPath()).getStudentList().size() == 0) {
            getBadxmlScreen();
        } else {
            university = model.startModel(openfile.getPath());
            initTree(university);
        }
    }

    private void initTree(University university) {
        TreeItem univer = new TreeItem(university);
        treeView.setRoot(univer);
        univer.setExpanded(true);
        for (Student tmp : university.getStudentList()) {
            TreeItem studentItem = new TreeItem<Student>(tmp);
            univer.getChildren().add(studentItem);
            TreeItem programmItem = new TreeItem<Program>(tmp.getProgram());
            studentItem.getChildren().add(programmItem);
            for (int i = 0; i < tmp.getProgram().getCourseList().size(); i++) {
                TreeItem courseItem = new TreeItem<Course>(tmp.getProgram().getCourseList().get(i));
                programmItem.getChildren().add(courseItem);
                for (int j = 0; j < tmp.getProgram().getCourseList().get(i).getTaskList().size(); j++) {
                    TreeItem taskItem = new TreeItem<Task>(tmp.getProgram().getCourseList().get(i).getTaskList().get(j));
                    courseItem.getChildren().add(taskItem);
                }
            }
        }
    }

    @FXML
    private void setTextOnTextArea(MouseEvent mouseEvent) {
        if (university != null) {
            String current = String.valueOf(
                    ((StringForTreeView) treeView.getFocusModel().getFocusedItem().getValue()).treeViewtoString()
            );
            textArea.setText(current);
        }
    }

    private void getBadxmlScreen() {
        Stage stage = new Stage();
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        Image image = new Image("/bad.jpg");
        stage.getIcons().add(image);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Bad XML...........");
        stage.setScene(scene);
        stage.show();
    }
}
