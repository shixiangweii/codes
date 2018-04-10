package hyuki.design.decorator;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-10
 * Time: 15:30
 * 角色：具体的被装饰着，concrete Component
 */
public class StringDisplay extends Display {
    private String string;

    public StringDisplay(String string) {
        this.string = string;
    }

    @Override
    public int getColumns() {
        return string.getBytes().length;
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public String getRowText(int row) {
        if(row==0){
            return string;
        }
        return null;
    }
}
