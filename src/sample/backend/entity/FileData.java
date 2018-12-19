package sample.backend.entity;

public class FileData {
    private String range;
    private Integer[] values;

    public FileData(String range, Integer[] values) {
        this.range = range;
        this.values = values;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer[] getValues() {
        return values;
    }

    public void setValues(Integer[] values) {
        this.values = values;
    }
}
