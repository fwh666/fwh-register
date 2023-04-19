package club.fuwenhao.exception;


import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * @author yuchangcun
 * @desc
 * @date 2020/02/05
 */
public class Asserts {

    public static void notNull(Object object, AppBusinessCode code) {
        if (null == object) {
            throw new AppBusinessException(code);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "");
    }

    public static void notNull(Object object, AppBusinessCode code, String msg) {
        if (null == object) {
            throwException(code, msg);
        }
    }

    public static void notNull(Object object, String msg) {
        if (object == null) {
            throwException(AppBusinessCode.UNEXPECTED_ERROR, msg);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "");
    }

    public static void isTrue(boolean expression, AppBusinessCode code) {
        if (!expression) {
            throw new AppBusinessException(code);
        }
    }

    public static void isTrue(boolean expression, AppBusinessCode code, String msg) {
        if (!expression) {
            throwException(code, msg);
        }
    }

    public static void isTrue(boolean expression, String code, String msg) {
        if (!expression) {
            throw new AppBusinessException(code, msg);
        }
    }

    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throwException(AppBusinessCode.UNEXPECTED_ERROR, msg);
        }
    }
    public static void isTrue(boolean expression, AppBusinessCode code, Object data) {
        if (!expression) {
            throw new AppBusinessException(code,data);
        }
    }
    public static void notEmpty(String data) {
        notEmpty(data, "");
    }

    public static void notEmpty(String data, String msg) {
        if (data == null || data.length() == 0) {
            throwException(AppBusinessCode.UNEXPECTED_ERROR, msg);
        }
    }

    public static void notEmpty(String data, AppBusinessCode code) {
        if (data == null || data.length() == 0) {
            throw new AppBusinessException(code);
        }
    }

    public static void notEmpty(String data, String code, String msg) {
        if (data == null || data.length() == 0) {
            throw new AppBusinessException(code, msg);
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, String msg) {
        if (collection == null || collection.isEmpty()) {
            throw new AppBusinessException(AppBusinessCode.UNEXPECTED_ERROR, msg);
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, AppBusinessCode code) {
        if (collection == null || collection.isEmpty()) {
            throw new AppBusinessException(code);
        }
    }


    private static void throwException(AppBusinessCode code, String msg) {
        if (msg == null) {
            throw new AppBusinessException(code);
        } else {
            throw new AppBusinessException(code.getCode(), msg);
        }
    }
}
