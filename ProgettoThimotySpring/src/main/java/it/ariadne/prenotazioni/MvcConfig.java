package it.ariadne.prenotazioni;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@Import (SecurityConfiguration.class)
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Override
	   public void addViewControllers(ViewControllerRegistry registry) {
	      super.addViewControllers(registry);
	 
	      registry.addViewController("/login.html");
	   }

}
