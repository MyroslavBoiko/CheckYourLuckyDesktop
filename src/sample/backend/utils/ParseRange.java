package sample.backend.utils;

import sample.backend.entity.Statistic;
import sample.backend.entity.StatisticRequest;

import java.util.ArrayList;
import java.util.List;

public class ParseRange {

    public static  List<Integer> parseRange(String rangeString){
        char[] rangeChars = rangeString.toCharArray();
        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < rangeChars.length; i++) {
            int first = 0;
            while(i < rangeChars.length && rangeChars[i] >= '0' && rangeChars[i] <= '9'){
                first = first * 10 + (rangeChars[i] - 48);
                i++;
            }
            if (i < rangeChars.length) {
                if (rangeChars[i] == ',')
                    range.add(first);
                else{
                    i++;
                    int second = 0;
                    while(i < rangeChars.length && rangeChars[i] >= '0' && rangeChars[i] <= '9'){
                        second = second * 10 + (rangeChars[i] - 48);
                        i++;
                    }
                    while (first <= second){
                        range.add(first);
                        first++;
                    }
                }
            }

        }
        return range;
    }

    public static List<Statistic> fillStatistic(List<Integer> range, StatisticRequest statisticRequest){
        List<Statistic> values = new ArrayList<>();
        for (int i = 0; i < range.size(); i++) {
            values.add(new Statistic(range.get(i), 0, 0.0));
            values.get(i).setStatisticRequest(statisticRequest);
        }
        return values;
    }

}
