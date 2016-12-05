package br.com.abevieiramota.memechat.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.abevieiramota.memechat.classifier.MemeClassifier;
import br.com.abevieiramota.memechat.controller.MemeController;
import br.com.abevieiramota.memechat.dao.MemeDao;

@EnableWebMvc
@ComponentScan(basePackageClasses = { MemeController.class, MemeClassifier.class, MemeDao.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	// viewName é transformado em <prefix> + viewName + <sufix>
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		resolver.setExposedContextBeanNames("carrinhoCompras");

		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		// TODO: 1s para ambiente de desenvolvimento
		messageSource.setCacheSeconds(1);

		return messageSource;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
