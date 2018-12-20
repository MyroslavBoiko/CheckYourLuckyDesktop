package sample.backend.service;

import sample.backend.entity.FileData;

import java.io.File;
import java.io.IOException;

public interface FileService {
    File loadFile();
    FileData parseFile(File file) throws IOException;
}
