package xyz.slienceme.project_shop.common;

/**
 * @Author slience_me
 * @Time : 2021/7/12  14:26
 * 响应码
 * 该类为枚举类，可以定义常量
 */
public enum ResponseCode {

    //设置常量
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    ERROR1(2, "登录失败"),
    ERROR2(3, "需要登录");


    private int code;//响应码
    private String desc;//描述description、内容

    //枚举的构造方法。
    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //遍历所有常量，返回与index对应的常量
    public static ResponseCode stateOf(int index){
        for(ResponseCode state : values()){
            if (state.getCode() == index){
                return state;
            }
        }
        return null;
    }
}
