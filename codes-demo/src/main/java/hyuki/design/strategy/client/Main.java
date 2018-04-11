package hyuki.design.strategy.client;

import hyuki.design.strategy.Hand;
import hyuki.design.strategy.Player;
import hyuki.design.strategy.ProbStrategy;
import hyuki.design.strategy.WinnerStrategy;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-11
 * Time: 8:29
 */
public class Main {

    public static void main(String[] args) {
        int seed1 = 23534;
        int seed2 = 9340934;
        Player player1 = new Player("Taro", new WinnerStrategy(seed1));
        Player player2 = new Player("Hana", new ProbStrategy(seed2));
        for (int i = 0; i < 10000; i++) {
            Hand nextHand1 = player1.nextHand();
            Hand nextHand2 = player2.nextHand();
            if (nextHand1.isStrongerThan(nextHand2)) {
                System.out.println("Winner:" + player1);
                player1.win();
                player2.lose();
            } else if (nextHand2.isStrongerThan(nextHand1)) {
                System.out.println("Winner:" + player2);
                player2.win();
                player1.lose();
            } else {
                System.out.println("Even...");
                player1.even();
                player2.even();
            }
        }
        System.out.println("Total result:");
        System.out.println(player1.toString());
        System.out.println(player2.toString());
    }
}
