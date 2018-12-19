package sample.backend.utils;


import sample.backend.algorithm.Color;

public class NumberClass {

    private int number;

    private double startAngle;

    private double endAngle;

    private Color color;

    public Color getColor() {return color;}

    public void setColor(Color color) {this.color = color;}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }

    public NumberClass(int number, Color color, double startAngle, double endAngle) {
        this.number = number;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.color = color;
    }
}
