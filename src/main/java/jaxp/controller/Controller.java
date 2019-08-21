package jaxp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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

    public void closeApplication(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void openFileDialog(ActionEvent actionEvent) {
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

    public void setTextOnTextArea(MouseEvent mouseEvent) {
        if (university != null) {
            textArea.setText(String.valueOf(((StringForTreeView) treeView.getFocusModel().getFocusedItem().getValue()).treeViewtoString()));
        }
    }

    private void getBadxmlScreen() {
        Stage stage = new Stage();
        stage.setTitle("Bad XML...........");
        stage.setMaxWidth(300);
        stage.setMaxHeight(300);
        VBox vBox = new VBox();
        Scene tmp = new Scene(vBox);
        stage.setScene(tmp);
        stage.setResizable(false);
        stage.show();
    }
}
