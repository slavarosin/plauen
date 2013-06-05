package net.gobro.plauen.web.security;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("/captcha")
public final class CaptchaController {

	@Autowired
	private ImageCaptchaService captchaService;

	@RequestMapping(method = RequestMethod.GET)
	public void generateCaptcha(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		final BufferedImage challenge = captchaService.getImageChallengeForID(
				WebUtils.getSessionId(request), request.getLocale());

		final ServletOutputStream out = response.getOutputStream();

		ImageIO.setUseCache(false);
		ImageIO.write(challenge, "jpeg", out);

		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		out.flush();
		out.close();
	}
}
