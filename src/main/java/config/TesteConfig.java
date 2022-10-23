package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import services.DBService;

@Configuration
@Profile("Teste")
public class TesteConfig {
    @Autowired
    private DBService dbService;

    @Bean
    public void intaciaBaseDedados(){
        this.dbService.instanciaBaseDeDados();
    }

}
