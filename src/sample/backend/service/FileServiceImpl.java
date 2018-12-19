package sample.backend.service;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public class FileServiceImpl implements FileService {

    @Override
    public File loadFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", ".txt",".docx"));
        File selectedFile = fc.showOpenDialog(null);


        if (selectedFile != null) {

        }
        return selectedFile;
    }

    @Override
    public void parseFile(File file){

    }

    @Override
    public void saveParsedData() {

    }

}
