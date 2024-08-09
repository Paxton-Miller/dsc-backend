package com.ogms.dsc.common;

import com.ogms.dsc.domain.dto.common.BaseResultDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * @name: R
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 4/10/2024 9:14 PM
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {

    public static class Code {
        public static final int SUCCESS = 200;
        public static final int FAIL = 300;
    }

    private Integer code;
    private String message;
    private T data;

    public static <T> R<T> success() {
        return new R<>(Code.SUCCESS, "success", null);
    }

    public static <T> R<T> success(T data) {
        return new R<>(Code.SUCCESS, "success", data);
    }

    public static <T> R<T> success(T data, String message) {
        return new R<>(Code.SUCCESS, message, data);
    }

    public static <T> R<T> fail() {
        return new R<>(Code.FAIL, "fail", null);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(Code.FAIL, message, null);
    }

    public static <T> R<T> fail(Integer code) {
        return new R<>(code, "fail", null);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new R<>(code, message, null);
    }

    public static R handleService(Supplier<BaseResultDto> operation) {
        BaseResultDto resultDto = operation.get();
        if (resultDto.getStatus()) {
            return R.success(resultDto.getResult());
        } else {
            return R.fail(resultDto.getResult().toString().trim());
        }
    }
}
