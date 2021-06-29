package sg.edu.iss.caps.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
			Path profilePicUploadDir = Paths.get("./profile-pic");
			String profilePicUploadPath = profilePicUploadDir.toFile().getAbsolutePath();
			
			registry.addResourceHandler("/profile-pic/**").addResourceLocations("file:/"+profilePicUploadPath
					+ "/");
		}
	

}
