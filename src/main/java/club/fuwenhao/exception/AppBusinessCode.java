package club.fuwenhao.exception;

import lombok.Getter;

/**
* @author caowenjun1
* @date 2022/6/6 18:37
* @param 
* @return
* @Version 4.3
**/
public enum AppBusinessCode {
    LOGIN_FAIL("1001", "登录失败"),
    INVALID_TOKEN("1002", "无效token"),

    USER_EXIST("1003", "用户已存在"),
    GUEST_COUNT_LIMIT("1004", "访客访问次数超限"),
    /**
     * 81001 - 请勿重复操作
     */
    DUPLICATE_REQUEST("81001", "请勿重复操作"),
    UNEXPECTED_ERROR("81002", "数据校验失败"),
    UNEXPECTED_DATA_OPERATION("81003", "数据查询操作失败"),
    APP_CODE_NOT_EXISTS("82001", "业务编码不存在"),
    BUSINESS_STATUS_OFFLINE("82002", "业务已下线"),
    BUSINESS_OPERATION_NOT_ALLOWED("82003", "该业务无操作权限"),
    BUSINESS_ACCOUNT_NOT_EXISTS("82004", "业务积分账户不存在"),
    BUSINESS_ACCOUNT_NOT_AVAILABLE("82005", "业务积分账户不可用"),
    BUSINESS_ACCOUNT_POINTS_NOT_ENOUGH("83006", "业务账户积分不足"),
    SCENE_NOT_EXISTS("83001", "场景不存在"),
    SCENE_POINTS_NOT_ENOUGH("83002", "场景积分不足"),
    SCENE_NOT_AVAILABLE("83003", "场景不可用"),
    USER_POINTS_NOT_ENOUGH("84001", "用户积分不足"),
    POINTS_RULE_NOT_EXISTS("84002", "积分规则编码不存在"),
    USER_POINTS_CONSUME_ERROR("84003", "用户积分扣减异常"),
    REPEAT_CREATE_POINTS_ACCOUNT("84010", "重复创建积分账户"),
    USER_POINTS_ACCOUNT_NOT_EXISTS("84011", "用户积分账户不存在"),
    USER_POINTS_ACCOUNT_NOT_AVAILABLE("84012", "用户账户不可用"),
    CREATE_ACCOUNT_ERROR("84013", "积分账户创建异常"),
    ACCOUNT_OPERATION_ERROR("84014", "积分账户操作异常"),
    USER_SCENE_POINTS_LIMIT("84015", "用户领取场景积分达到上限"),
    USER_POINTS_INCREASE_ERROR("84016", "用户积分增发异常"),
    SEARCH_SUPER_ID_ERROR("84017", "查询用户中心superId失败"),
    EXCHANGE_SUB_ID_ERROR("84018", "用户中心换取subId失败"),
    SEARCH_USER_CENTER_ID_BY_MOBILE_ERROR("84019", "手机号获取用户中心ID失败"),
    SEARCH_SUB_ID_BY_MOBILE_ERROR("84020", "用户中心手机号查询subId异常"),
    EXCHANGE_MOBILE_BY_SUB_ID_ERROR("84021", "用户中心subId获取手机号异常"),
    SEARCH_USER_INFO_ERROR("84022", "用户中心查询用户信息异常"),
    INIT_CODE_ERROR("84023", "初始化失败"),
    CITY_POINTS_FREEZE_ERROR("84101", "城市积分冻结失败"),
    CITY_POINTS_UNFREEZE_ERROR("84102", "城市积分解冻失败"),
    CITY_POINTS_EXCHANGE_ERROR("84103", "城市积分兑换失败"),
    CITY_POINTS_DEDUCT_ERROR("84104", "城市积分扣减失败"),
    PROPS_NOT_EXISTS("85001", "道具不存在"),
    PROPS_NOT_AVAILABLE("85002", "道具已下线"),
    PROPS_NOT_ENOUGH("85003", "道具数量不足"),
    PROPS_USAGE_ERROR("85004", "道具使用异常"),
    POINTS_REFUND_RECORD_NOT_EXISTS("85005", "积分退款记录不存在"),
    GOODS_NOT_EXISTS("85101", "商品不存在"),
    GOODS_NOT_AVAILABLE("85102", "商品不存在或已下线"),
    GOODS_TOO_MANY("85103", "最多支持20种商品"),
    STOCK_NOT_ENOUGH("86001", "商品库存不足"),
    CARD_KEY_STOCK_NOT_ENOUGH("86002", "商品库存不足~"),
    CREATE_ORDER_ERROR("86003", "订单创建失败"),
    REPEAT_CREATE_ORDER_ERROR("86004", "重复创建订单"),
    ORDER_NOT_EXISTS("86005", "订单不存在"),
    ORDER_STATUS_ERROR("86006", "订单状态校验失败"),
    ORDER_INFO_CHECK_ERROR("86007", "订单信息校验失败"),
    EXCHANGE_LIMIT_ERROR("86008", "兑换超出限制"),
    INTEREST_SERVICE_ERROR("86009", "权益服务调用失败"),
    ORDER_CANCELING("86010", "订单取消中"),
    ORDER_CANCEL_FAILED("86011", "订单取消失败"),
    ORDER_CANCEL_REJECT("86012", "暂不支持该商品取消"),
    ORDER_FREIGHT_CHECK_ERROR("86013", "订单运费信息校验失败"),
    PAYMENT_PASSWORD_EXCEPTION("87000", "支付密码管理异常"),
    PAYMENT_NOT_SET_PASSWORD("87001", "您当前无虚拟资产支付密码，为了您的资产安全，需要先进行密码设定"),
    PAYMENT_REPEAT_SET_PASSWORD("87002", "重复设置积分支付密码"),
    PAYMENT_UPDATE_SAME_PASSWORD("87003", "修改密码不能与旧密码相同"),
    PAYMENT_SMS_SEND_ERROR("87004", "短信验证码发送失败"),
    PAYMENT_SMS_PERIOD_LIMIT("87005", "短信发送过于频繁"),
    PAYMENT_VALIDATION_LOCK("87006", "多次验证失败，账号锁定"),
    PAYMENT_VALIDATION_NEED_PASSWORD("88001", "需要积分支付密码"),
    PAYMENT_VALIDATION_NEED_VERIFY_CODE("88002", "需要积分支付短信验证码"),
    PAYMENT_VERIFY_CODE_ERROR("88003", "验证码错误"),
    PAYMENT_PASSWORD_ERROR("88004", "支付密码错误"),
    BUSINESS_CALLBACK_ERROR("89001", "业务回调通知异常"),
    SHOPPING_CART_GOODS_OFFLINE("550001", "商品已经下架，再看看其他商品吧~"),
    SETTLEMENT_GOODS_NO_STOCK_ERROR("550007", "以下商品收货地址所在地库存不足，是否继续下单其他商品？"),
    SETTLEMENT_GOODS_SKU_ERROR("550006", "一次最多下单20种商品，请重新选择"),
    ;
    @Getter
    private final String code;
    @Getter
    private final String msg;

    AppBusinessCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean codeEqual(String cmpCode) {
        return this.code.equals(cmpCode);
    }

    public boolean codeNotEqual(String cmpCode) {
        return !this.codeEqual(cmpCode);
    }
}
