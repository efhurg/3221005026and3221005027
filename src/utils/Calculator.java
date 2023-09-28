package utils;

import entity.Fraction;
import entity.Ordinary;
import entity.Type;

/**
 * 计算类
 */
public class Calculator {
    /**
     * 求a和b的最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int f(int a,int b){
        if(a < b){
            int c = a;
            a = b;
            b = c;
        }
        int r = a % b;
        while(r != 0){
            a = b;
            b = r;;
            r = a % b;
        }
        return b;
    }

    /** 求两个分数运算后的分子 **/
    public static int CalculateNumer(Fraction fra1, Fraction fra2, int op){
        if (op == 1){
            if (fra1.getDenomin() == fra2.getDenomin()){
                return fra1.getNumer() + fra2.getNumer();
            }
            return fra1.getNumer() * fra2.getDenomin() + fra2.getNumer() * fra1.getDenomin();
        }
        if (op == 2){
            if (fra1.getDenomin() == fra2.getDenomin()){
                return fra1.getNumer() - fra2.getNumer();
            }
            return fra1.getNumer() * fra2.getDenomin() - fra2.getNumer() * fra1.getDenomin();
        }
        return 0;
    }

    /** 求两个分数运算后的分母 **/
    public static int CalculateDenomin(Fraction fra1, Fraction fra2){
        if (fra1.getDenomin() == fra2.getDenomin()){
            return fra1.getDenomin();
        }
        return fra1.getDenomin() * fra2.getDenomin();
    }

    /** 求带分数化假分数的分子 **/
    public static int CalculateNumer(Fraction fra){
        return fra.getHeadNum() * fra.getDenomin() + fra.getNumer();
    }

    /** 两个数都是 Ordinary 类型 **/
    public static Type calculate(Ordinary ord1, Ordinary ord2, int op){
        if (op == 1){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(ord1.getValue() + ord2.getValue());
            return ordinary;
        }
        if (op == 2){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(ord1.getValue() - ord2.getValue());
            return ordinary;
        }
        if (op == 3){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(ord1.getValue() * ord2.getValue());
            return ordinary;
        }
        if (op == 4){
            // 两个普通数相除为分数则返回 Fraction 类型
            if ((ord1.getValue() % ord2.getValue()) != 0){
                Fraction fraction = new Fraction();
                fraction.setHeadNum(ord1.getValue() / ord2.getValue());
                fraction.setNumer(ord1.getValue() % ord2.getValue());
                fraction.setDenomin(ord2.getValue());
                return getType(fraction);
            } else{
                Ordinary ordinary = new Ordinary();
                ordinary.setValue(ord1.getValue() / ord2.getValue());
                return ordinary;
            }
        }
        return null;
    }

    /** 第一个数是 Fraction 类型，第二个数是 Ordinary 类型 **/
    public static Type calculate(Fraction fra1, Ordinary ord2, int op){
        if (op == 1){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() + ord2.getValue());
            fraction.setNumer(fra1.getNumer());
            fraction.setDenomin(fra1.getDenomin());
            return getType(fraction);
        }
        if (op == 2){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() - ord2.getValue());
            if (fraction.getHeadNum() < 0){
                fraction.setNumer( - fra1.getNumer());
            }else{
                fraction.setNumer(fra1.getNumer());
            }
            fraction.setDenomin(fra1.getDenomin());
            return getType(fraction);
        }
        if (op == 3){
            Fraction fraction = new Fraction();
            // 新 HeadNum 等于旧 HeadNum 乘整数加上旧分子除以分母
            fraction.setHeadNum((fra1.getHeadNum() * fra1.getDenomin() + fra1.getNumer()) * ord2.getValue() / fra1.getDenomin());
            // 新分子等于整数与旧分母取模
            fraction.setNumer((fra1.getHeadNum() * fra1.getDenomin() + fra1.getNumer()) * ord2.getValue() % fra1.getDenomin());
            fraction.setDenomin(fra1.getDenomin());

            return getType(fraction);
        }
        if (op == 4){
            Fraction fraction = new Fraction();
            fraction.setHeadNum((fra1.getHeadNum() * fra1.getDenomin() + fra1.getNumer()) / (fra1.getDenomin() * ord2.getValue()));
            fraction.setNumer((fra1.getHeadNum() * fra1.getDenomin() + fra1.getNumer()) % (fra1.getDenomin() * ord2.getValue()));
            fraction.setDenomin(fra1.getDenomin() * ord2.getValue());

            return getType(fraction);
        }
        return null;
    }

    /** 第一个数是 Ordinary 类型，第二个数是 Fraction 类型 **/
    public static Type calculate(Ordinary ord1, Fraction fra2, int op){
        if (op == 1){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra2.getHeadNum() + ord1.getValue());
            fraction.setNumer(fra2.getNumer());
            fraction.setDenomin(fra2.getDenomin());

            return getType(fraction);
        }
        if (op == 2){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(ord1.getValue() - fra2.getHeadNum() - 1);
            fraction.setNumer(fra2.getDenomin() - fra2.getNumer());
            fraction.setDenomin(fra2.getDenomin());

            return getType(fraction);
        }
        if (op == 3){
            Fraction fraction = new Fraction();
            fraction.setHeadNum((fra2.getHeadNum() * fra2.getDenomin() + fra2.getNumer()) * ord1.getValue() / fra2.getDenomin());
            fraction.setNumer((fra2.getHeadNum() * fra2.getDenomin() + fra2.getNumer()) * ord1.getValue() % fra2.getDenomin());
            fraction.setDenomin(fra2.getDenomin());

            return getType(fraction);
        }
        if (op == 4){
            int commonDiviser;
            Fraction fraction = new Fraction();
            // 新 headNum 等于整数除以旧 HeadNum 乘分母加分子
            fraction.setHeadNum((ord1.getValue() * fra2.getDenomin()) / (fra2.getHeadNum() * fra2.getDenomin() + fra2.getNumer()));
            // 新分子等于整数乘分母与旧 HeadNum 乘分母加分子取模
            fraction.setNumer((ord1.getValue() * fra2.getDenomin()) % (fra2.getHeadNum() * fra2.getDenomin() + fra2.getNumer()));
            // 新分母等于旧 HeadNum 乘分母加分子
            fraction.setDenomin(fra2.getHeadNum() * fra2.getDenomin() + fra2.getNumer());

            return getType(fraction);
        }
        return null;
    }

    /** 两个数都是 Fraction 类型 **/
    public static Type calculate(Fraction fra1, Fraction fra2, int op){
        if (op == 1){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() + fra2.getHeadNum());
            fraction.setNumer(CalculateNumer(fra1, fra2, 1));
            fraction.setDenomin(CalculateDenomin(fra1, fra2));
            // 分数相加大于1时进1
            if (fraction.getNumer() > fraction.getDenomin()){
                fraction.setHeadNum(fraction.getHeadNum() + 1);
                fraction.setNumer(fraction.getNumer() - fraction.getDenomin());
            }

            return getType(fraction);
        }
        if (op == 2){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(fra1.getHeadNum() - fra2.getHeadNum());
            fraction.setNumer(CalculateNumer(fra1, fra2, 2));
            fraction.setDenomin(CalculateDenomin(fra1, fra2));

            // 此处若整数部分相减为负数时存在bug，但不考虑这种情况发生
            // 如果分子为负数则向 headNum 借1进行计算
            if (fraction.getHeadNum() + fraction.getNumer()== 0){
                Ordinary ordinary = new Ordinary();
                ordinary.setValue(fraction.getHeadNum());
                return ordinary;
            } else if (fraction.getNumer() < 0){
                fraction.setHeadNum(fraction.getHeadNum() - 1);
                fraction.setNumer(fraction.getDenomin() + fraction.getNumer());
            }
            return getType(fraction);
        }
        if (op == 3){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(CalculateNumer(fra1) * CalculateNumer(fra2) / (fra1.getDenomin() * fra2.getDenomin()));
            fraction.setNumer(CalculateNumer(fra1) * CalculateNumer(fra2) % (fra1.getDenomin() * fra2.getDenomin()));
            fraction.setDenomin(fra1.getDenomin() * fra2.getDenomin());

            return getType(fraction);
        }
        if (op == 4){
            Fraction fraction = new Fraction();
            fraction.setHeadNum(CalculateNumer(fra1) * fra2.getDenomin() / (fra1.getDenomin() * CalculateNumer(fra2)));
            fraction.setNumer(CalculateNumer(fra1) * fra2.getDenomin() % (fra1.getDenomin() * CalculateNumer(fra2)));
            fraction.setDenomin(fra1.getDenomin() * CalculateNumer(fra2));

            return getType(fraction);
        }
        return null;
    }

    /** 对带分数进行约分 **/
    private static Type getType(Fraction fraction) {
        // 当分子是 0 时，转化为 ordinary 类型
        if (fraction.getNumer() == 0){
            Ordinary ordinary = new Ordinary();
            ordinary.setValue(fraction.getHeadNum());
            return ordinary;
        }
        // 获取分子分母的最大公因数并约分
        int commonDiviser;
        commonDiviser = f(fraction.getNumer(), fraction.getDenomin());
        if (commonDiviser != 1){
            fraction.setNumer(fraction.getNumer() / commonDiviser);
            fraction.setDenomin(fraction.getDenomin() / commonDiviser);
            // 可化为整数则将其转化为 Ordinary 类型返回
            if (fraction.getDenomin() == fraction.getNumer()){
                Ordinary ordinary = new Ordinary();
                ordinary.setValue(fraction.getHeadNum());
                return ordinary;
            }
            return fraction;
        }
        return fraction;
    }
}