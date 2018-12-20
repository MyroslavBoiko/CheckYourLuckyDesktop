package sample.backend.service;

import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.backend.entity.FileData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    StatisticService statisticService;
    @Override
    public File loadFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", ".txt",".doc",".docx"));
        File selectedFile = fc.showOpenDialog(null);
        return selectedFile;
    }

    @Override
    public FileData parseFile(File file) throws IOException {
        String range = null;
        String values = null;
        String data = new String(Files.readAllBytes(Paths.get(file.getPath())));
        String patternRange = "^(\\d+-\\d+)\\s+([\\d\\s+,]+$)";
        Pattern pattern = Pattern.compile(patternRange);
        Matcher matcher = pattern.matcher(data);

        if(matcher.find()){
            range = matcher.group(1);
            values = matcher.group(2);
        }
        List<Integer> intValues = new ArrayList<>();
        String[] stringValues = values.split("[\\s,]+");
        for (String s: stringValues) {
            intValues.add(Integer.valueOf(s));
        }
        return new FileData(range, intValues.toArray(new Integer[intValues.size()]));
    }

}
