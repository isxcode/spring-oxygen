package com.isxcode.oxygen.flysql.response;

import com.isxcode.oxygen.flysql.common.BaseResponse;
import lombok.Getter;
import lombok.Setter;

public class SuccessException extends RuntimeException {

	@Setter @Getter private BaseResponse<Object> baseResponse;

	public SuccessException(BaseResponse<Object> baseResponse) {

		this.baseResponse = baseResponse;
	}
}
