package xyz.slienceme.project_shop.common;

/**
 * 业务处理失败异常
 */
public class BusinessException extends Exception {

    /**
     * 异常信息
     * @param message
     */
    public BusinessException(String message){
        super(message);
    }

}
