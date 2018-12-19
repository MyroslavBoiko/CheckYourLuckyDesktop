package sample.backend.service;

import java.io.File;

public interface FileService {
    File loadFile();
    void parseFile(File file);
    void saveParsedData();
}
