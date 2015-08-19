package com.bogle.frame.sms.defines;

/**
 * Created by Administrator on 2015/7/2.
 */
public enum CodeMsg {
    SUCCESS(0, "服务器端", "成功"),
    ERROR_CODE(-100000, "未知", "未知"),
    ERROR_CODE_$1(-1, "服务器端", "系统异常"),
    ERROR_CODE_$2(-2, "客服端", "客户端异常"),
    ERROR_CODE_$101(-101, "服务器端", "命令不被支持"),
    ERROR_CODE_$102(-102, "服务器端", "RegistryTransInfo删除信息失败"),
    ERROR_CODE_$103(-103, "服务器端", "RegistryTransInfo更新信息失败，"),
    ERROR_CODE_$104(-104, "服务器端", "请求超过限制"),
    ERROR_CODE_$110(-110, "服务器端", "号码注册激活失败"),
    ERROR_CODE_$111(-111, "服务器端", "企业注册失败"),
    ERROR_CODE_$113(-113, "服务器端", "充值失败"),
    ERROR_CODE_$117(-117, "服务器端", "发送短信失败"),
    ERROR_CODE_$118(-118, "服务器端", "接收MO失败"),
    ERROR_CODE_$119(-119, "服务器端", "接收Report失败"),
    ERROR_CODE_$120(-120, "服务器端", "修改密码失败"),
    ERROR_CODE_$122(-122, "服务器端", "号码注销失败"),
    ERROR_CODE_$123(-123, "服务器端", "查询单价失败"),
    ERROR_CODE_$124(-124, "服务器端", "查询余额失败"),
    ERROR_CODE_$125(-125, "服务器端", "设置MO转发失败"),
    ERROR_CODE_$126(-126, "服务器端", "路由信息失败"),
    ERROR_CODE_$127(-127, "服务器端", "没有余额"),
    ERROR_CODE_$128(-128, "服务器端", "余额不足"),
    ERROR_CODE_$190(-190, "服务器端", "数据操作失败"),
    ERROR_CODE_$1100(-1100, "服务器端", "序列号错误，序列号不存在内存中或尝试攻击的用户"),
    ERROR_CODE_$1102(-1102, "服务器端", "序列号密码错误"),
    ERROR_CODE_$1103(-1103, "服务器端", "序列号key错误"),
    ERROR_CODE_$1104(-1104, "服务器端", "路由失败，请联系系统管理员"),
    ERROR_CODE_$1105(-1105, "服务器端", "注册号状态异常，未用1"),
    ERROR_CODE_$1107(-1107, "服务器端", "注册号状态异常，停用3"),
    ERROR_CODE_$1108(-1108, "服务器端", "注册号状态异常，停止5"),
    ERROR_CODE_$1131(-1131, "服务器端", "充值卡无效"),
    ERROR_CODE_$1132(-1132, "服务器端", "充值卡密码无效"),
    ERROR_CODE_$1133(-1133, "服务器端", "充值卡绑定异常"),
    ERROR_CODE_$1134(-1134, "服务器端", "充值状态无效"),
    ERROR_CODE_$1135(-1135, "服务器端", "充值金额无效"),
    ERROR_CODE_$1901(-1901, "服务器端", "数据库插入操作失败"),
    ERROR_CODE_$1902(-1902, "服务器端", "数据库更新操作失败"),
    ERROR_CODE_$1903(-1903, "服务器端", "数据库删除操作失败"),
    ERROR_CODE_$9000(-9000, "服务器端", "数据格式错误,数据超出数据库允许的范围"),
    ERROR_CODE_$9001(-9001, "客户端 ,所有业务", "序列号格式错误"),
    ERROR_CODE_$9002(-9002, "客户端 ,所有业务", "密码格式错误"),
    ERROR_CODE_$9003(-9003, "客户端 ,所有业务", "客户端key格式错误"),
    ERROR_CODE_$9004(-9004, "客户端 ,转发业务", "设置转发格式错误"),
    ERROR_CODE_$9005(-9005, "客服端，企业注册业务", "公司地址格式错误"),
    ERROR_CODE_$9006(-9006, "客服端，企业注册业务", "企业中文名格式错误"),
    ERROR_CODE_$9007(-9007, "客服端，企业注册业务", "企业中文名简称格式错误"),
    ERROR_CODE_$9008(-9008, "客服端，企业注册业务", "邮件地址格式错误"),
    ERROR_CODE_$9009(-9009, "客服端，企业注册业务", "企业英文名格式错误"),
    ERROR_CODE_$9010(-9010, "客服端，企业注册业务", "企业英文名简称格式错误"),
    ERROR_CODE_$9011(-9011, "客服端，企业注册业务", "传真格式错误"),
    ERROR_CODE_$9012(-9012, "客服端，企业注册业务", "联系人格式错误"),
    ERROR_CODE_$9013(-9013, "客服端，企业注册业务", "联系电话格式错误"),
    ERROR_CODE_$9014(-9014, "客服端，企业注册业务", "邮编格式错误"),
    ERROR_CODE_$9015(-9015, "客户端 ,密码修改业务", "新密码格式错误"),
    ERROR_CODE_$9016(-9016, "客服端，发送业务", "发送短信包大小超出范围"),
    ERROR_CODE_$9017(-9017, "客服端，发送业务", "发送短信内容格式错误"),
    ERROR_CODE_$9018(-9018, "客服端，发送业务", "发送短信扩展号格式错误"),
    ERROR_CODE_$9019(-9019, "客服端，发送业务", "发送短信优先级格式错误"),
    ERROR_CODE_$9020(-9020, "客服端，发送业务", "发送短信手机号格式错误"),
    ERROR_CODE_$9021(-9021, "客服端，发送业务", "发送短信定时时间格式错误"),
    ERROR_CODE_$9022(-9022, "客服端，发送业务", "发送短信唯一序列值错误"),
    ERROR_CODE_$9023(-9023, "客服端，充值业务", "充值卡号格式错误"),
    ERROR_CODE_$9024(-9024, "客服端，充值业务", "充值密码格式错误"),
    ERROR_CODE_$9025(-9025, "客服端", "客户端请求sdk5超时"),
    ERROR_CODE_101(101, "客服端网络", "客户端网络故障"),
    ERROR_CODE_303(303, "客服端网络", "客户端网络超时或网络故障"),
    ERROR_CODE_305(305, "服务器端", "服务器端返回错误，错误的返回值（返回值不是数字字符串）"),
    ERROR_CODE_307(307, "客服端", "目标电话号码不符合规则，电话号码必须是以0、1开头"),
    ERROR_CODE_308(308, "服务器端", "新密码不是数字，必须是数字"),
    ERROR_CODE_999(999, "客服端", "操作频繁");

    private int errorcode;
    private String errmsg;
    private String source;

    CodeMsg(int errorcode, String source, String errmsg) {
        this.errorcode = errorcode;
        this.errmsg = errmsg;
        this.source = source;
    }

    public static CodeMsg handCode(Object code) {
        if(code instanceof Integer) {
            int returnCode = Integer.parseInt(code.toString());
            for(CodeMsg codeMsg : values()) {
                if(returnCode == codeMsg.getErrorcode()) {
                    return codeMsg;
                }
            }
        }
        return ERROR_CODE;
    }

    public int getErrorcode() {
        return this.errorcode;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public String getSource() {
        return source;
    }
}
