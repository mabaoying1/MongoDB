package com.healthpay.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mabaoying
 * @ClassName: RandomUtils
 * @Description: TODO
 * @date: 2019/8/9 11:32
 * @最后修改人:
 * @最后修改时间:
 */
public class RandomUtils {

    /**
     * 生成流水号
     * @return
     */
    public static String getAlterNo() {
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String retStrFormatNowDate = sdf.format(nowTime);

        int randNum = new Random().nextInt(9999999) + 100000000;
        String alterNo = retStrFormatNowDate + randNum;
        return alterNo;

    }
}
