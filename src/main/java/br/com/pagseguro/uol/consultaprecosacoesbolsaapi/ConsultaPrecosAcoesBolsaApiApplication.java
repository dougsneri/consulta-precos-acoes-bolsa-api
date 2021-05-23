package br.com.pagseguro.uol.consultaprecosacoesbolsaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsultaPrecosAcoesBolsaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaPrecosAcoesBolsaApiApplication.class, args);
	}

}
