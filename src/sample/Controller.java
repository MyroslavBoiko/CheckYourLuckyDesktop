package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import sample.backend.controller.RandomController;
import sample.backend.entity.HistoryDbEntity;
import sample.backend.entity.HistoryDto;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.backend.entity.RangeLuckEntity;

import javax.annotation.PostConstruct;
import java.beans.XMLDecoder;
import java.io.*;
import java.net.UnknownHostException;
import java.util.List;

public class Controller {

    private ObservableList<HistoryDto> data;

    @Autowired
    private RandomController randomController;

    @FXML
    private TextField leftBoundary;

    @FXML
    private TextField rightBoundary;

    @FXML
    private TextField yourLeftBoundary;

    @FXML
    private TextField yourRightBoundary;

    @FXML
    private TableView<HistoryDto> historyTable;

    @PostConstruct
    public void init() throws UnknownHostException {
        List<HistoryDto> history = randomController.getHistory();
        data = FXCollections.observableArrayList(history);

        TableColumn<HistoryDto, String> betColumn = new TableColumn<>("bet");
        betColumn.setCellValueFactory(new PropertyValueFactory<>("bet"));

        TableColumn<HistoryDto, String> rangeColumn = new TableColumn<>("range");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        TableColumn<HistoryDto, String> choiceColumn = new TableColumn<>("choice");
        choiceColumn.setCellValueFactory(new PropertyValueFactory<>("choice"));

        TableColumn<HistoryDto, String> gameColumn = new TableColumn<>("game");
        gameColumn.setCellValueFactory(new PropertyValueFactory<>("game"));

        historyTable.getColumns().addAll(betColumn, rangeColumn,choiceColumn,gameColumn);

        historyTable.setItems(data);
    }

    @FXML
    public void checkLuck() throws UnknownHostException {
        String leftBound = leftBoundary.getText();
        String rightBound = rightBoundary.getText();
        String yourLeftBound = yourLeftBoundary.getText();
        String yourRightBound = yourRightBoundary.getText();
        HistoryDto historyDto =
                randomController.getRangeNumber(new RangeLuckEntity(leftBound+ "-"+rightBound,
                        yourLeftBound+"-"+yourRightBound));

        data.add(historyDto);
        data.remove(0);
    }

    @FXML
    public void loadFile(){

    }
    public void setDataFlights(String path) throws FileNotFoundException {
        if (!path.contains(".txt"))
            throw new FileNotFoundException("Error while opening this file.");
        try {
            BufferedReader bd = new BufferedReader(new FileReader(path));
            XMLDecoder decoder =
                    new XMLDecoder(new BufferedInputStream(
                            new FileInputStream(path)));
            decoder.close();
        } catch (Exception e) {
            throw new FileNotFoundException("Error while reading from file.");
        }
    }
}
