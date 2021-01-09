package club.lightingsummer.movie.userbiz;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "club.lightingsummer.movie")
@MapperScan("club.lightingsummer.movie.userdal.dao")
@EnableDubboConfiguration
public class UserBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBizApplication.class, args);
    }

}
