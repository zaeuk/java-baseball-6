package baseball;

import java.util.List;
import java.util.ArrayList;

import camp.nextstep.edu.missionutils.Console;

import baseball.utils.ValidationUtils;

import baseball.utils.NumberGenerator;

public class Application {

    public static void main(String[] args) {
        boolean isGameActive = true;
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (isGameActive) {
            newGame();
            isGameActive = askRestartOrExit();
        }
        System.out.println("게임이 종료됩니다.");
    }

    private static boolean askRestartOrExit() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = Console.readLine();
        ValidationUtils.validateOneOrTwo(input);
        return input.equals("1");
    }

    private static void newGame() {
        List<Integer> computer = NumberGenerator.generateUnique3DigitNum();
        playGame(computer);
    }

    private static void playGame(List<Integer> computer) {
        int ball;
        int strike = 0;
        while (strike != 3) {
            List<Integer> user = getUserNumber();
            ball = countBall(computer, user);
            strike = countStrike(computer, user);
            printGameResult(ball, strike);
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    private static void printGameResult(int ball, int strike) {
        if (ball == 0 && strike == 0) {
            System.out.println("낫싱");
        } else if (ball == 0) {
            System.out.printf("%d스트라이크\n", strike);
        } else if (strike == 0) {
            System.out.printf("%d볼\n", ball);
        } else {
            System.out.printf("%d볼 %d스트라이크\n", ball, strike);
        }
    }

    private static int countStrike(List<Integer> computer, List<Integer> user) {
        int count = 0;
        for (int i = 0; i < user.size(); i++) {
            if (computer.get(i).equals(user.get(i))) {
                count++;
            }
        }
        return count;
    }

    private static int countBall(List<Integer> computer, List<Integer> user) {
        int count = 0;
        for (int i = 0; i < computer.size(); i++) {
            for (int j = 0; j < user.size(); j++) {
                if (i != j && computer.get(i).equals(user.get(j))) {
                    count++;
                }
            }
        }
        return count;
    }

    private static List<Integer> getUserNumber() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();
        ValidationUtils.validateUnique3DigitNum(input);
        List<Integer> list = new ArrayList<>();
        for (String s : input.split("")) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

}
