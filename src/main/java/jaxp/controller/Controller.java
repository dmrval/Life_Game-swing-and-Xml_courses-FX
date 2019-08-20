package jaxp.controller;

import jaxp.model.Model;

public class Controller {
    Model model;

    public Controller() {
        model = new Model();
    }


    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.model.startModel();
        System.out.println(controller.model.getUniver().getStudentList());
    }
}
