package br.com.abevieiramota.memechat.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.abevieiramota.memechat.classifier.MemeClassifier;
import br.com.abevieiramota.memechat.dao.MemeDao;
import br.com.abevieiramota.memechat.model.Meme;

@Controller
public class MemeController {

	@Autowired
	private MemeDao memeDao;

	@Autowired
	private MemeClassifier memeClassifier;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView generate(String content) {
		ModelAndView mav = new ModelAndView("lememe");

		Meme contentMeme = this.memeClassifier.classify(content);

		contentMeme.generateImg();

		this.memeDao.save(contentMeme);

		mav.addObject("meme", contentMeme);

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/{id}/img", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void img(HttpServletResponse response, @PathVariable("id") long id) {
		InputStream imgStream = this.memeDao.getImg(id);
		try {
			IOUtils.copy(imgStream, response.getOutputStream());
		} catch (IOException e) {
			try {
				response.getOutputStream().println("deu ruim");
			} catch (IOException e1) {
				try {
					response.getOutputStream().println("deu muito ruim");
				} catch (IOException e2) {
					try {
						response.getOutputStream().println("não para de dar ruim...");
					} catch (IOException e3) {
						try {
							response.getOutputStream().println("já deu");
						} catch (IOException e4) {
							e4.printStackTrace();
						}
					}
				}
			}
		}
	}
}
