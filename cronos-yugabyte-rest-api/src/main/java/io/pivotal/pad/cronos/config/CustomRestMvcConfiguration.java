package io.pivotal.pad.cronos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import io.pivotal.pad.cronos.domain.ProductMetadata;
import io.pivotal.pad.cronos.domain.ProductRanking;
@Configuration
public class CustomRestMvcConfiguration {

	@Bean
	  public RepositoryRestConfigurer repositoryRestConfigurer() {

	    return new RepositoryRestConfigurerAdapter() {

	      @Override
	      public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	        config.setBasePath("/api/v1");
//	    	config.setBasePath("/");
	        config.exposeIdsFor(ProductMetadata.class, ProductRanking.class);
	      }
	    };
	  }

}
