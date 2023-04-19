package club.fuwenhao.exception;

import lombok.Getter;

public class AppBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String code;
    @Getter
    private final Object data;

    public AppBusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.data = null;
    }

    public AppBusinessException(AppBusinessCode code, String message) {
        super(message);
        this.code = code.getCode();
        this.data = null;
    }

    public AppBusinessException(AppBusinessCode code,Object data) {
        super(code.getMsg());
        this.code = code.getCode();
        this.data = data;
    }

    public AppBusinessException(AppBusinessCode code) {
        this(code.getCode(), code.getMsg());
    }

}

