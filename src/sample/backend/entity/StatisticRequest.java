package sample.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"range", "ip_address_id"}, name = "uniqueRangeIpConstraint")}, name = "statistic_range")
public class StatisticRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy="statisticRequest")
    @JsonIgnore
    private List<Statistic> values;

    @Column(name="range")
    private String range;

    @Column(name="count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "ip_address_id")
    @JsonIgnore
    private IdIpEntity ip;

    public StatisticRequest() {
    }

    public StatisticRequest(List<Statistic> values, String range, Integer number) {
        this.values = values;
        this.range = range;
        this.count = number;
    }

    public List<Statistic> getValues() {
        return values;
    }

    public void setValues(List<Statistic> values) {
        this.values = values;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public void updateStatistic(int number){
        count++;
        for (Statistic stat:
             values) {
            if (stat.getValue().compareTo(number) == 0)
                stat.setNumber(stat.getNumber() + 1);
            stat.calculatePercent(count);
        }
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", values='" + values + '\'' +
                ", range='" + range + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

}
