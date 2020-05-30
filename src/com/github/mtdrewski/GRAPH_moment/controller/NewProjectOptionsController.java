package com.github.mtdrewski.GRAPH_moment.controller;

import com.github.mtdrewski.GRAPH_moment.model.graphs.Graph;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewProjectOptionsController {

    private static GraphDrawerController graphDrawerController;

    @FXML
    private BorderPane root;

    public void undirected() {
        graphDrawerController.setUnsaved(true);
        graphDrawerController.drawNewGraph(new Graph()); //TODO: modify for directed
        ((Stage) root.getScene().getWindow()).close();
    }

    public void directed() {
        graphDrawerController.setUnsaved(true);
        graphDrawerController.drawNewGraph(new Graph()); //TODO: modify for undirected
        ((Stage) root.getScene().getWindow()).close();
    }

    public static void setGraphDrawerController(GraphDrawerController graphDrawer) {
        graphDrawerController = graphDrawer;
    }
}
