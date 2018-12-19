package sample.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @Column(name="type")
    private String type;

    @Column(name="range")
    private String range;

    @Column(name="bet")
    private String bet;

    @Column(name="result")
    private String result;

    @Column(name="win")
    private boolean win;

    @ManyToOne
    @JoinColumn(name = "ip_address_id")
    @JsonIgnore
    private IdIpEntity ip;

    public HistoryDbEntity() {
    }

    public HistoryDbEntity(String type, String range, String bet, String result, boolean win) {
        this.type = type;
        this.range = range;
        this.bet = bet;
        this.result = result;
        this.win = win;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdIpEntity getIp() {
        return ip;
    }

    public void setIp(IdIpEntity ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBet() {
        return bet;
    }

    public String getRange() { return range; }

    public void setRange(String range) { this.range = range; }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "HistoryDbEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", range='" + range + '\'' +
                ", bet='" + bet + '\'' +
                ", result='" + result + '\'' +
                ", win=" + win +
                '}';
    }
}
