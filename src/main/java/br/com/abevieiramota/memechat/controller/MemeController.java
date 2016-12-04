package br.com.abevieiramota.memechat.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemeController {

	@Autowired
	private ServletContext ctx;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/memerate", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void memerate(HttpServletResponse response, String content) throws IOException {
		final BufferedImage image = ImageIO.read(this.ctx.getResourceAsStream("/images/lememe.jpg"));

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString(content, 100, 100);
		g.dispose();

		ImageIO.write(image, "jpg", response.getOutputStream());
	}

}
