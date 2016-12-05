package br.com.abevieiramota.memechat.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.abevieiramota.memechat.dao.MemeDao;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/meme")
public class MemeController {

	@Autowired
	private MemeDao memeDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("memes", this.memeDao.all());
		
		return modelAndView;
	}

	// TODO: content can be null?
	// TODO: imagem sendo salva com filename "render"
	@RequestMapping(value = "/render", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void memerate(HttpServletResponse response, long id, String content)
			throws IOException, SQLException {

		InputStream imgAsStream = this.memeDao.find(id);
		final BufferedImage image = ImageIO.read(imgAsStream);

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString(content, 100, 100);
		g.dispose();

		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView cadastra() throws IOException {
		return new ModelAndView("form");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes)
			throws IOException {

		long memeId = this.memeDao.add(image.getInputStream(), image.getOriginalFilename());
		redirectAttributes.addFlashAttribute("message", "Meme id:" + memeId);

		return "redirect:/meme/form";
	}

}
