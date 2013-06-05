package net.gobro.plauen.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gobro.plauen.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private ImageService imageService;

	@RequestMapping(method = RequestMethod.GET, params = { "id" })
	public void view(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") final Integer id) throws IOException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		OutputStream o = response.getOutputStream();

		if (id != null) {
			byte[] image = imageService.fetch(id);

			if (image != null) {
				// flush it in the response
				response.setContentType("image/jpeg");

				o.write(image);
			}
		}
		o.flush();
		o.close();
	}
}
