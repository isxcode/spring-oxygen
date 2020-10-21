package com.ispong.oxygen.flysql.success;

import com.ispong.oxygen.flysql.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

public class SuccessException extends RuntimeException {

    @Setter
    @Getter
    private BaseResponse<Object> baseResponse;

    public SuccessException(BaseResponse<Object> baseResponse){

        this.baseResponse = baseResponse;
    }


}
