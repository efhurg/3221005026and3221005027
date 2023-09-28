package constant;

/**
 * 数字类型标识
 */
public enum NumType {
    /** 普通的数，例如1, 2, 3.... **/
    ORDINARY(1),
    /** 真分数, 1/2, 1/3, 1/4, 1'1/2 **/
    FRACTION(2);

    int code;

    NumType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
