package com.colak.springtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class SpringTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTutorialApplication.class, args);
	}

}
