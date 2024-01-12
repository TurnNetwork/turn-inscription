package com.turn.inscription.config;

import com.turn.inscription.enums.I18nEnum;
import com.turn.inscription.exception.BusinessException;
import com.turn.inscription.utils.HttpUtil;
import com.turn.inscription.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.TimeoutException;

/**
 * Unify static methods
 */
@Slf4j
@Service
public class CommonMethod {

	@Value("${recaptchaUrl:}")
	private String recaptchaUrl;

	@Value("${isRecaptcha:false}")
	private Boolean isRecaptcha;

	@Autowired
	private I18nUtil i18n;

	private CommonMethod() {
	}

	/**
	 * web asynchronous request timeout method call
	 *
	 * @param webAsyncTask
	 * @method onTimeOut
	 */
	public static <T> void onTimeOut(WebAsyncTask<T> webAsyncTask) {
		webAsyncTask.onTimeout(() -> {
			// When it times out, throw an exception directly and let the outer layer handle the timeout exception uniformly.
			throw new TimeoutException("System busy!");
		});
	}

	/**
	 * recaptcha authentication method
	 *
	 * @param token
	 * @method recaptchaAuth
	 */
	public void recaptchaAuth(String token) {
		try {
			if (!this.isRecaptcha) {
				log.debug("not recaptchaAuth ");
				return;
			}
			RecaptchaDto recaptchaDto = HttpUtil.get(String.format(this.recaptchaUrl, token), RecaptchaDto.class);
			log.debug("recaptchaAuth token:{}", token);
			if (!recaptchaDto.getSuccess().booleanValue()) {
				throw new BusinessException(this.i18n.i(I18nEnum.DOWNLOAD_EXCEPTION));
			}
		} catch (Exception e1) {
			log.error("google recaptchaAuth error.", e1);
			throw new BusinessException(this.i18n.i(I18nEnum.DOWNLOAD_EXCEPTION));
		}
	}
}
