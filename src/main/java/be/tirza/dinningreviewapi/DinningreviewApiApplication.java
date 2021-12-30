package be.tirza.dinningreviewapi;

import be.tirza.dinningreviewapi.entity.Role;
import be.tirza.dinningreviewapi.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DinningreviewApiApplication  {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DinningreviewApiApplication.class, args);
    }

//    @Autowired // implements CommandLineRunner
//    private RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        roleRepository.save(userRole);

}
