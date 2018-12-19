package sample.backend.entity;

public class HistoryDto {

    private String bet;
    private String range;
    private int choice;
    private boolean game;

    public HistoryDto(){}

    public HistoryDto(String bet, String range, int choice, boolean game) {
        this.bet = bet;
        this.range = range;
        this.choice = choice;
        this.game = game;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getRange() { return range; }

    public void setRange(String range) { this.range = range; }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }
}
