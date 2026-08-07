package com.abc.abcbank;

import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.enums.NotificationType;
import com.abc.abcbank.notification.dto.NotificationDTO;
import com.abc.abcbank.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class AbcbankApplication {

//	private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(AbcbankApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			NotificationDTO notificationDTO = NotificationDTO.builder()
//					.recipient("minh4921.workingemail@gmail.com")
//					.subject("Testing email")
//					.body("This is a test email, do not reply!")
//					.type(NotificationType.EMAIL)
//					.build();
//
//			notificationService.sendEmail(notificationDTO, new User());
//		};
//	}

}
