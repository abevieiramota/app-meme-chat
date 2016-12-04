package br.com.abevieiramota.memechat.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.abevieiramota.memechat.dao.MemeDao;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class MemeController {

	@Autowired
	private MemeDao memeDao;

	@Autowired
	private ServletContext ctx;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {

		return new ModelAndView("home");
	}

	// TODO: content can be null?
	@RequestMapping(value = "/meme/{id}/render", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void memerate(HttpServletResponse response, @PathVariable("id") long id, String content)
			throws IOException, SQLException {

		InputStream imgAsStream = this.memeDao.find(id);
		final BufferedImage image = ImageIO.read(imgAsStream);

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString(content, 100, 100);
		g.dispose();

		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	@RequestMapping("/cadastra")
	public void cadastra() throws IOException {
		try (InputStream imgAsStream = this.ctx.getResourceAsStream("/images/lememe.jpg")) {
			this.memeDao.add(imgAsStream, 1l);
		}
	}

}
