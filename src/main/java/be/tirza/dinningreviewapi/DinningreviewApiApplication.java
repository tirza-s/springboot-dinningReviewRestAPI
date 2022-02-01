package be.tirza.dinningreviewapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Dinning Review Application using Spring boot and Rest Api
 *
 * @author Tirza Samosir
 *
 */
@SpringBootApplication
public class DinningreviewApiApplication  {

    /**
     * Using third-party library ModelMapper
     * This method is to map one object model to another
     * ModelMapper internally using toString method to print the result for the mapping,
     * And there was infinite loop while mapping between Restaurant entity to Restaurant DTO
     * In order to fix it, remove toString method from restaurant entity.
     * @Data lombok annotation internally provide toString method. remove @Data annotation
     * and add only @Getter and @Setter annotation. It will fix the issues
     */

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DinningreviewApiApplication.class, args);
    }

}
