package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.db.Datasource;
import sample.model.Artist;


public class Controller {

    @FXML
    private TableView<Artist> artistTable;

    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

}

class GetAllArtistsTask extends Task {

    @Override
    public ObservableList<Artist> call() throws Exception {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}
