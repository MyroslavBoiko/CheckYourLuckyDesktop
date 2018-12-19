package sample.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import static sample.backend.utils.ParseRange.parseRange;

public class RangeLuckEntity {

    private String rangeInput;
    private String betInput;

    @JsonIgnore
    private  List<Integer> range;

    @JsonIgnore
    private  List<Integer>  bet;

    public RangeLuckEntity() {}

    public RangeLuckEntity(String rangeInput, String betInput) {
        this.rangeInput = rangeInput;
        this.betInput = betInput;
    }

    public  List<Integer> getRange() {
        return range;
    }

    public  List<Integer>  getBet() {
        return bet;
    }

    public String getRangeInput() {
        return rangeInput;
    }

    public void setRangeInput(String rangeInput) {
        this.rangeInput = rangeInput;
    }

    public String getBetInput() {
        return betInput;
    }

    public void setBetInput(String betInput) {
        this.betInput = betInput;
    }

    public void setRange() {
        this.range = parseRange(rangeInput);
    }

    public void setBet() {
        this.bet = parseRange(betInput);
    }

}
