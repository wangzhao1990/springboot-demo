
/**
 * @Title: ComputeMoneyFactory.java
 * @Package com.pingan.haofang.goldcenter.common.money
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZOUHAIJUN575
 * @date 2017年7月21日 下午4:11:06
 * @version V1.0
 */
package com.zhao.demo.common.utils.money;

import java.math.BigDecimal;

/**
 * @ClassName: ComputeMoneyFactory
 * @Description: 金额计算
 * @author ZOUHAIJUN575
 * @date 2017年7月21日 下午4:11:06
 */
public class ComputeMoneyFactory {


    /**
     * @Title: getFangOrderPrincipal @Description: 计算费用 @param allMoney(分) @param 年化利率(xx%) @param
     * 非年化利率(xx%) @return @throws
     */
    public static BigDecimal getPrincipal(Long allMoney, String yearRate, String nYearRate,
            Integer periods) {
        BigDecimal bigDecimal = new BigDecimal(allMoney);
        bigDecimal = bigDecimal.subtract(getYearRateMoney(allMoney, yearRate, periods))
                .subtract(getNoYearRateMoney(allMoney, nYearRate));
        return bigDecimal;
    }

    /**
     * @Title: getYearRateMoney @Description: 年化利率 @param allMoney @param yearRate @param
     * periods @return @throws
     */
    public static BigDecimal getYearRateMoney(Long allMoney, String yearRate, Integer periods) {
        BigDecimal bigDecimal = new BigDecimal(allMoney);
        bigDecimal = bigDecimal.multiply(new BigDecimal(yearRate)).divide(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(periods))
                .divide(BigDecimal.valueOf(12), 0, BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }

    /**
     * @Title: getNoYearRateMoney @Description:非年化利率 @param allMoney @param
     * nYearRate @return @throws
     */
    public static BigDecimal getNoYearRateMoney(Long allMoney, String nYearRate) {
        BigDecimal bigDecimal = new BigDecimal(allMoney);
        bigDecimal = bigDecimal.multiply(new BigDecimal(nYearRate)).divide(BigDecimal.valueOf(100),
                0, BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }
}
