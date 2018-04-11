package hyuki.design.strategy;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-11
 * Time: 14:56
 */
public interface Strategy {
    /**
     *
     * @return
     */
    Hand nextHand();

    /**
     *
     * @param win
     */
    void study(boolean win);
}