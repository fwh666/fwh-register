package club.fuwenhao.enums;

/**
 * @version V4.5.3
 * @Title: 美景
 * @ClassName: club.fuwenhao.enums.ResponseCodeEnum.java
 * @Description:
 * @author: fuwenhao
 * @date: 2023-04-16 4:00 PM
 */
public enum ResponseCodeEnum {
    SUCCESS("0000", "成功"),
    LOGIN_FAIL("10001", "登录失败"),
    INVALID_TOKEN("1002", "无效token");

    private String code;
    private String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
