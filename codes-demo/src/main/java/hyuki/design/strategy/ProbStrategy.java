package hyuki.design.strategy;

import java.util.Random;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-11
 * Time: 16:25
 */
public class ProbStrategy implements Strategy {

    private Random random;
    private int prevHandValue = 0;
    private int currentHandValue = 0;

    private int[][] history = {
        {1,1,1},
        {1,1,1},
        {1,1,1}
    };

    public ProbStrategy(int seed) {
        random = new Random(seed);
    }


    @Override
    public Hand nextHand() {
        int bet = random.nextInt(getSum(currentHandValue));
        int handvalue = 0;
        if (bet < history[currentHandValue][0]) {
            handvalue = 0;
        } else if (bet < history[currentHandValue][0] + history[currentHandValue][1]) {
            handvalue = 1;
        } else {
            handvalue = 2;
        }
        prevHandValue = currentHandValue;
        currentHandValue = handvalue;
        return Hand.getHand(handvalue);
    }

    @Override
    public void study(boolean win) {
        if (win) {
            history[prevHandValue][currentHandValue]++;
            return;
        }
        history[prevHandValue][(currentHandValue + 1) % 3]++;
        history[prevHandValue][(currentHandValue + 2) % 3]++;
    }

    private int getSum(int hv) {
        int sum = 0;
        for (int i = 0; i < history[hv].length; i++) {
            sum += history[hv][i];
        }
        return sum;
    }
}
