package com.sy.basic.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.basic.util
 * @ClassName: Test
 * @Description: java类作用描述
 * @Author: hingbox@163.com
 * @CreateDate: 2019/5/15 9:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/15 9:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Test {
    public static void main(String[]arg){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        System.out.println(dateName);
    }
}