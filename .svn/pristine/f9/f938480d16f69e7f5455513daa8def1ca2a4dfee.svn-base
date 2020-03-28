/**
 * Copyright &copy; 2015-2020 <a href="http://www.healthpay.com/">HealthPay</a> All rights reserved.
 */



package com.healthpay.common.utils.excel.fieldtype;

import com.healthpay.common.utils.StringUtils;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.utils.UserUtils;

/**
 * Class AreaType
 *
 *
 * @version        1.0, 16/06/28
 * @author         gyp
 */
public class AreaType {

    /**
     * Method description
     *
     *
     * @param val
     *
     * @return
     */
    public static Object getValue(String val) {
        for (Area e : UserUtils.getAreaList(null)) {
            if (StringUtils.trimToEmpty(val).equals(e.getName())) {
                return e;
            }
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @param val
     *
     * @return
     */
    public static String setValue(Object val) {
        if ((val != null) && ((Area) val).getName() != null) {
            return ((Area) val).getName();
        }

        return "";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
