package org.king;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SuperWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperWebManagementApplication.class, args);
        System.out.println("=========已启动===============================================");
    }
}
