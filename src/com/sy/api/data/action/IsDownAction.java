package com.sy.api.data.action;

import com.sy.basic.action.BaseAction;
import org.apache.struts2.convention.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: IsDownAction
 * @Description: 用来判断 客户端所有节目是否可以下载
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/26 1:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 1:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ParentPackage("xiif-default")
@Namespace("/api")
@Results({
        @Result(name = "json",type="json", params={"root","msgs"})
})
public class IsDownAction extends BaseAction {
    @Action(value="getDownFlag")
    public String getDownFlag() {
        msg = new HashMap<String, Object>();
        msg.put("download", 1);

        return  "json";
    }

    //==================================
    private Map<String, Object> msg;

    public Map<String, Object> getMsg() {
        return msg;
    }
}