package hyuki.design.strategy;

import java.util.Random;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-11
 * Time: 16:20
 */
public class WinnerStrategy implements Strategy {

    private Random random;
    private boolean won = false;
    private Hand prevHand;

    public WinnerStrategy(int seed) {
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        if (!won) {
            prevHand = Hand.getHand(random.nextInt(3));
        }
        return prevHand;
    }

    @Override
    public void study(boolean win) {
        won = win;
    }
}
