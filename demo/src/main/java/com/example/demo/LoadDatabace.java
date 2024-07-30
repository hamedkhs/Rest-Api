package com.example.demo;

/*برای مقدار دهی اولیه ی پایگاه داده از این کلاس استفاده میشود**/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(StudentRepository repository) {
    return args -> {
      log.info("Preloading "
          + repository.save(new Student(null, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102)));

      log.info("Preloading "
          + repository.save(new Student(null, "hamid", "467035497", 21, "student", "09958563567", "computer", 102)));
    };
  }
}