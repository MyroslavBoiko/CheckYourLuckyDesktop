package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sample.backend.controller.RandomController;
import sample.backend.entity.*;
import sample.backend.service.FileService;
import sample.backend.service.impl.StatisticService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ObservableList<HistoryDto> data;

    private ObservableList<Statistic> dataStatistic;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private RandomController randomController;

    @Autowired
    private FileService fileService;

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

    @FXML
    private TableView<Statistic> statisticTable;

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

        List<Statistic> statistics = randomController.getStatistic(new RangeStringEntity("0-10"));
        dataStatistic = FXCollections.observableArrayList(statistics);

        TableColumn<Statistic, String> valueColumn = new TableColumn<>("value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Statistic, String> countColumn = new TableColumn<>("count");
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        TableColumn<Statistic, String> percentColumn = new TableColumn<>("chance to win");
        percentColumn.setCellValueFactory(new PropertyValueFactory<>("percent"));

        statisticTable.getColumns().addAll(valueColumn, countColumn, percentColumn);

        statisticTable.setItems(dataStatistic);
    }

    @FXML
    public void checkLuck() throws UnknownHostException {
        if (leftBoundary.getText().isEmpty() || rightBoundary.getText().isEmpty() || yourLeftBoundary.getText().isEmpty() || yourRightBoundary.getText().isEmpty())
            return;
        try{
            int leftBoundTest = Integer.parseInt(leftBoundary.getText());
            int rightBoundTest = Integer.parseInt(rightBoundary.getText());
            int yourLeftBoundaryTest = Integer.parseInt(yourLeftBoundary.getText());
            int yourRightBoundaryTest = Integer.parseInt(yourRightBoundary.getText());
            if (yourLeftBoundaryTest < leftBoundTest || yourRightBoundaryTest > rightBoundTest)
                throw new Exception("Bad boundary!");
        }catch (Exception e){
            return;
        }
        String leftBound = leftBoundary.getText();
        String rightBound = rightBoundary.getText();
        String yourLeftBound = yourLeftBoundary.getText();
        String yourRightBound = yourRightBoundary.getText();
        HistoryDto historyDto =
                randomController.getRangeNumber(new RangeLuckEntity(leftBound+ "-"+rightBound,
                        yourLeftBound+"-"+yourRightBound));

        data.add(historyDto);
        if (historyTable.getItems().size() == 4)
            data.remove(0);
        List<Statistic> statistics =
                randomController.getStatistic(new RangeStringEntity(historyDto.getRange()));
        dataStatistic.clear();
        dataStatistic.addAll(statistics);
        makePercent();
    }

    @FXML
    public void loadFile() throws IOException {
            File file = fileService.loadFile();
            FileData data = fileService.parseFile(file);
            List<Statistic> statistics = statisticService.getStatistic(data, InetAddress.getLocalHost().toString());
            dataStatistic.clear();
            dataStatistic.addAll(statistics);
            makePercent();
    }

    private void makePercent(){
        for (Statistic statistic:
             dataStatistic) {
            statistic.setPercent(statistic.getPercent() * 100.0);
        }
    }
}
