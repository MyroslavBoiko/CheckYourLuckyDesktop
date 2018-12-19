package sample.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "statistic")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @Column(name="value")
    private Integer value;

    @Column(name="count")
    private Integer count;

    @Column(name="percent")
    private Double percent;

    @ManyToOne
    @JoinColumn(name = "statistic_range_id")
    @JsonIgnore
    private StatisticRequest statisticRequest;

    public Statistic() {
    }

    public Statistic(Integer value, Integer count, Double number) {
        this.value = value;
        this.count = count;
        this.percent = number;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getNumber() {
        return percent;
    }

    public void setNumber(Double number) {
        this.percent = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public StatisticRequest getStatisticRequest() {
        return statisticRequest;
    }

    public void setStatisticRequest(StatisticRequest statisticRequest) {
        this.statisticRequest = statisticRequest;
    }

    public void calculatePercent(Integer generalNumber){
        percent = count.doubleValue() / generalNumber.doubleValue();
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", count='" + count + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }

}
