package com.dmxiaoshen.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 大数工具类
 *
 */
public class BigDecimalUtil {

    private static final int DEFAULT_SCALE = 2;
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final Random rnd = new Random();

    /**
     * 计算和
     *
     * @param args
     * @return args[0] + args[1] + ...
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double add(double... args) {
        checkNotEmpty(args);
        return sum(makeStream(args)).doubleValue();
    }

    /**
     * 计算和
     *
     * @param args
     * @return args[0] + args[1] + ...
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double add(String... args) {
        checkNotEmpty(args);
        return sum(makeStream(args)).doubleValue();
    }

    /**
     * 计算差
     *
     * @param d
     * @param args
     * @return d - args[0] - args[1] - ...
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double sub(double d, double... args) {
        checkNotEmpty(args);
        return toBigDecimal(d).subtract(sum(makeStream(args))).doubleValue();
    }

    /**
     * 计算差
     *
     * @param s
     * @param args
     * @return s - args[0] - args[1] - ...
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double sub(String s, String... args) {
        checkNotEmpty(args);
        return toBigDecimal(s).subtract(sum(makeStream(args))).doubleValue();
    }

    /**
     * 计算乘积
     *
     * @param args
     * @return args[0] * args[1] * ...
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double mul(double... args) {
        checkNotEmpty(args);
        return product(makeStream(args)).doubleValue();
    }

    /**
     * 计算乘积
     *
     * @param args
     * @return args[0] * args[1] * ...
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double mul(String... args) {
        checkNotEmpty(args);
        return product(makeStream(args)).doubleValue();
    }

    /**
     * 计算两个数的商，结果保留2位小数
     *
     * @param dividend
     * @param divisor
     * @return dividend / divisor
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     * @throws ArithmeticException      除数为0
     */
    public static double div(double dividend, double divisor) {
        return div(dividend, divisor, DEFAULT_SCALE);
    }

    /**
     * 计算两个数的商，结果保留2位小数
     *
     * @param dividend
     * @param divisor
     * @return dividend / divisor
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     * @throws ArithmeticException      除数为0
     */
    public static double div(String dividend, String divisor) {
        return div(dividend, divisor, DEFAULT_SCALE);
    }

    /**
     * 计算两个数的商，结果保留指定的小数位数
     *
     * @param dividend
     * @param divisor
     * @param scale    小数位数
     * @return dividend / divisor
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     * @throws ArithmeticException      除数为0
     */
    public static double div(double dividend, double divisor, int scale) {
        return toBigDecimal(dividend).divide(toBigDecimal(divisor), scale, DEFAULT_ROUNDING_MODE).doubleValue();
    }

    /**
     * 计算两个数的商，结果保留指定的小数位数
     *
     * @param dividend
     * @param divisor
     * @param scale    小数位数
     * @return dividend / divisor
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     * @throws ArithmeticException      除数为0
     */
    public static double div(String dividend, String divisor, int scale) {
        return toBigDecimal(dividend).divide(toBigDecimal(divisor), scale, DEFAULT_ROUNDING_MODE).doubleValue();
    }

    /**
     * 计算平均数，结果保留2位小数
     *
     * @param args
     * @return (args[0] + args[1] + ... args[n]) / n
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double average(String... args) {
        return average(DEFAULT_SCALE, args);
    }

    /**
     * 计算平均数，结果保留2位小数
     *
     * @param args
     * @return (args[0] + args[1] + ... args[n]) / n
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double average(double... args) {
        return average(DEFAULT_SCALE, args);
    }

    /**
     * 计算平均数，结果保留指定的小数位数
     *
     * @param scale
     * @param args
     * @return (args[0] + args[1] + ... args[n]) / n
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double average(int scale, String... args) {
        checkNotEmpty(args);
        return average(makeStream(args), args.length, scale).doubleValue();
    }

    /**
     * 计算平均数，结果保留指定的小数位数
     *
     * @param scale
     * @param args
     * @return (args[0] + args[1] + ... args[n]) / n
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double average(int scale, double... args) {
        checkNotEmpty(args);
        return average(makeStream(args), args.length, scale).doubleValue();
    }

    /**
     * 计算中位数
     *
     * @param args
     * @return
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double median(double... args) {
        checkNotEmpty(args);
        return median(makeStream(args)).doubleValue();
    }

    /**
     * 计算中位数
     *
     * @param args
     * @return
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double median(String... args) {
        checkNotEmpty(args);
        return median(makeStream(args)).doubleValue();
    }

    /**
     * 四舍五入，保留指定小数位数
     *
     * @param value
     * @param scale
     * @return
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double round(double value, int scale) {
        return toBigDecimal(value).setScale(scale, DEFAULT_ROUNDING_MODE).doubleValue();
    }

    /**
     * 四舍五入，保留指定小数位数
     *
     * @param value
     * @param scale
     * @return
     * @throws IllegalArgumentException 参数为空，或不能转化为BigDecimal
     */
    public static double round(String value, int scale) {
        return toBigDecimal(value).setScale(scale, DEFAULT_ROUNDING_MODE).doubleValue();
    }

    private static Stream<BigDecimal> makeStream(double[] args) {
        return Arrays.stream(args).mapToObj(BigDecimalUtil::toBigDecimal);
    }

    private static Stream<BigDecimal> makeStream(String[] args) {
        return Arrays.stream(args).map(BigDecimalUtil::toBigDecimal);
    }

    private static BigDecimal sum(Stream<BigDecimal> s) {
        return s.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal product(Stream<BigDecimal> s) {
        return s.reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    private static BigDecimal average(Stream<BigDecimal> s, int n, int scale) {
        return sum(s).divide(BigDecimal.valueOf(n), scale, DEFAULT_ROUNDING_MODE);
    }

    private static BigDecimal median(Stream<BigDecimal> s) {
        BigDecimal[] array = s.toArray(BigDecimal[]::new);
        int len = array.length, half = len >> 1;
        nth_element(array, 0, half, len);
        if ((len & 1) == 0) {
            BigDecimal tmp = array[0];
            for (int i = 1; i < half; ++i) {
                if (array[i].compareTo(tmp) > 0) {
                    tmp = array[i];
                }
            }
            return tmp.add(array[half]).divide(BigDecimal.valueOf(2));
        }
        return array[half];
    }

    private static <T extends Comparable<? super T>> void nth_element(T[] a, int low, int pos, int high) {
        if (low == high || pos == high) {
            return;
        }
        for (int i = low, j = high; i < j - 1; ) {
            int q = randomizedPartition(a, i, j);
            if (pos < q) {
                j = q;
            } else if (pos > q) {
                i = q + 1;
            } else {
                break;
            }
        }
    }

    private static <T extends Comparable<? super T>> int randomizedPartition(T[] a, int low, int high) {
        int i = low + rnd.nextInt(high - low);
        T x = a[i];
        a[i] = a[low];
        i = low;
        int j = high - 1;
        while (i < j) {
            while (i < j && x.compareTo(a[j]) <= 0) {
                --j;
            }
            a[i] = a[j];
            while (i < j && a[i].compareTo(x) <= 0) {
                ++i;
            }
            a[j] = a[i];
        }
        a[i] = x;
        return i;
    }

    private static BigDecimal toBigDecimal(String s) {
        return new BigDecimal(s);
    }

    private static BigDecimal toBigDecimal(double v) {
        return BigDecimal.valueOf(v);
    }

    private static void checkNotEmpty(double[] args) {
        if (args == null) {
            throw new IllegalArgumentException("param can't be null");
        }
        if (args.length == 0) {
            throw new IllegalArgumentException("param can't be empty");
        }
    }

    private static <T> void checkNotEmpty(T[] args) {
        if (args == null) {
            throw new IllegalArgumentException("param can't be null");
        }
        if (args.length == 0) {
            throw new IllegalArgumentException("param can't be empty");
        }
    }

    private BigDecimalUtil() {
        throw new AssertionError();
    }

    public static void main(String[] args) {
        Integer a = 20;
        Integer b = 100;

        BigDecimal c = BigDecimal.valueOf(div(a.intValue(),b.intValue(),4));

        System.out.println(c);
    }
}
