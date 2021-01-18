package com.sy.api.data.action;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: Test
 * @Description: java类作用描述
 * @Author: hingbox@163.com
 * @CreateDate: 2019/1/4 21:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/4 21:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Test {
    public static String ClearBracket(String company){
        String bracket = company.substring(company.indexOf("("),company.indexOf(")")+1);
        company = company.replace(bracket, "");
        return company;
    }

    public static void main(String []str){
        String strs ="({\"code\":\"200\",\"message\":\"SUCCESS\",\"timeStamp\":1546610372381,\"body\":[{\"urlType\":null,\"url\":\"http://gslbmgspdl.miguvideo.com/depository_sjq/asset/zhengshi/5102/147/442/5102147442/media/5102147442_5003932026_91.mp4?msisdn=15702120638&mdspid=&spid=699007&netType=4&sid=5500652226&pid=2028597139&timestamp=20190104215932&Channel_ID=&ProgramID=648855538&ParentNodeID=-99&assertID=5500652226&client_ip=47.104.220.221&SecurityKey=20190104215932&promotionID=&mvid=5102147442&mcid=1001&mpid=130000105933&encrypt=0feafe74674ddcbfd1602f7b0d69115f\",\"mediaType\":\"10\",\"usageCode\":\"91\",\"codeRate\":\"160\",\"mediaSize\":614617796,\"rateDesc\":null,\"contId\":\"648855538\",\"rateType\":null,\"needClothHat\":false,\"resultCode\":null,\"code\":\"200\",\"desc\":\"SUCCESS\"},{\"urlType\":null,\"url\":\"http://gslbmgspdl.miguvideo.com/depository_sjq/asset/zhengshi/5102/147/400/5102147400/media/5102147400_5003932021_91.mp4?msisdn=15702120638&mdspid=&spid=699007&netType=4&sid=5500652228&pid=2028597139&timestamp=20190104215932&Channel_ID=&ProgramID=648855511&ParentNodeID=-99&assertID=5500652228&client_ip=47.104.220.221&SecurityKey=20190104215932&promotionID=&mvid=5102147400&mcid=1001&mpid=130000105933&encrypt=f0834f48d59b661990bdb9aff3553271\",\"mediaType\":\"10\",\"usageCode\":\"91\",\"codeRate\":\"160\",\"mediaSize\":614872850,\"rateDesc\":null,\"contId\":\"648855511\",\"rateType\":null,\"needClothHat\":false,\"resultCode\":null,\"code\":\"200\",\"desc\":\"SUCCESS\"},{\"urlType\":null,\"url\":\"http://gslbmgspdl.miguvideo.com/depository_sjq/asset/zhengshi/5102/147/398/5102147398/media/5102147398_5003931809_91.mp4?msisdn=15702120638&mdspid=&spid=699007&netType=4&sid=5500652225&pid=2028597139&timestamp=20190104215932&Channel_ID=&ProgramID=648855493&ParentNodeID=-99&assertID=5500652225&client_ip=47.104.220.221&SecurityKey=20190104215932&promotionID=&mvid=5102147398&mcid=1001&mpid=130000105933&encrypt=2f968d96944ba1826378c3bc8ccbfaed\",\"mediaType\":\"10\",\"usageCode\":\"91\",\"codeRate\":\"160\",\"mediaSize\":613876614,\"rateDesc\":null,\"contId\":\"648855493\",\"rateType\":null,\"needClothHat\":false,\"resultCode\":null,\"code\":\"200\",\"desc\":\"SUCCESS\"}]}\n" +
                ")";
        String company = "华厦世纪(厦门)地产";
        System.out.println(ClearBracket(company));
    }

}