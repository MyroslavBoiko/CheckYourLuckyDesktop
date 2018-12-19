package sample.backend.algorithm;


import sample.backend.entity.HistoryDto;
import sample.backend.entity.RangeLuckEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Operator {

    public static HistoryDto checkWinReturnHistoryRange(RangeLuckEntity inputData) {
        HistoryDto history = new HistoryDto();
        history.setRange(inputData.getRangeInput());
        history.setBet(inputData.getBetInput());
        history.setChoice(makeRandom(inputData.getRange().get(0), inputData.getRange().get(inputData.getRange().size() - 1)));
        history.setGame(checkWin(history.getChoice(), inputData.getBet()));
        return history;
    }

    private static boolean checkWin(int choice, List<Integer> betRange){
        for (int i = 0; i < betRange.size(); i++) {
            if (betRange.get(i).compareTo(choice) == 0)
                return true;
        }
        return false;
    }

    private static int makeRandom(int first, int second){
        return ThreadLocalRandom.current().nextInt(first, second);
    }

}
