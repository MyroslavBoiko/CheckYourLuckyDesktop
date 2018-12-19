package sample.backend.service;

import java.io.File;
import java.io.IOException;

public interface FileService {
    File loadFile();
    String parseFile(File file) throws IOException;
    void saveParsedData();
}
