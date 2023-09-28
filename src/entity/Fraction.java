package entity;

import constant.NumType;

import java.util.Objects;
import java.util.Random;

/**
 * 真分数
 */
public class Fraction extends Type {
    /** 前面带的数 **/
    private int headNum;
    /** 分子 不能是0 要比分母小 **/
    private int numer;
    /** 分母 不能是0 **/
    private int denomin;
    /** 真实数值 */
    private double value;

    public Fraction() {
        this.numType = NumType.FRACTION;
    }

    public String generateStr() {
        StringBuilder sb = new StringBuilder();
        if(headNum > 0) {
            sb.append(headNum).append("'").append(numer).append("/").append(denomin);
            return sb.toString();
        } else {
            sb.append(numer).append("/").append(denomin);
            return sb.toString();
        }
    }

    public int getHeadNum() {
        return headNum;
    }

    public void setHeadNum(int headNum) {
        this.headNum = headNum;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public int getDenomin() {
        return denomin;
    }

    public void setDenomin(int denomin) {
        this.denomin = denomin;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /** 获取随机值 */
    @Override
    public void getRandom(int range) {
        // 随机对象
        Random random = new Random();
        // 在指定范围内分别生成分数的各个部分的数字
        this.headNum = random.nextInt(range) + 1;
        // 分子不能为n
        this.numer = random.nextInt(range-1) + 1;
        // 分母不为0 要比分子大
        int temp = random.nextInt(range) + 1;
        while(temp <= 0 || temp <= this.numer) {
            temp = random.nextInt(range) + 1;
        }
        this.denomin = temp;
        this.value = this.headNum + (double)this.numer / this.denomin;
        this.realVal = this.value;
    }

    /** 判断相等 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return headNum == fraction.headNum &&
                numer == fraction.numer &&
                denomin == fraction.denomin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(headNum, numer, denomin);
    }
}
