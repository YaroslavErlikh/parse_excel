package hackaton.crazy_devs.animals_without_owners;

import hackaton.crazy_devs.animals_without_owners.configuration.initalizer.TestDataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean(initMethod = "init")
	@PostConstruct
	public TestDataInit initTestData() {
		return new TestDataInit();
	}
	// текущие настройки для моей машины, которые выдают
	// наименьшее количество ошибок
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(3);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("SEARCH SERVICE-");
		executor.initialize();
		return executor;
	}
}
