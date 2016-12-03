package br.com.abevieiramota.memechat.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.abevieiramota.memechat.dao.MessageDao;
import br.com.abevieiramota.memechat.model.Message;

@Controller
public class HomeController {

	// mapeia uma request num handler
	@RequestMapping("/teste")
	public ModelAndView index() {
		System.out.println("oi");
		List<Message> allMessages = new MessageDao().all();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("messages", allMessages);
		return modelAndView;
	}
}
