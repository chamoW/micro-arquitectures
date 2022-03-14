package com.dc.hexagonal.hexagonalarchitecture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWebTestClient
public class SendMoneySystemTest {

	@Autowired
	 private TestRestTemplate client;

	@Test
	void testSendMoney() {

		AccountId sourceAccountId = new AccountId(1L);
		AccountId targetAccountId = new AccountId(2L);
		Money amount = Money.of(500L);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Void> request = new HttpEntity<>(null, headers);

		ResponseEntity  response = client.exchange("/accounts/sendMoney/{sourceAccountId}/{targetAccountId}/{amount}",
				HttpMethod.POST,
				request,
				Object.class,
				sourceAccountId.getValue(), targetAccountId.getValue(), amount.getAmount())
		;
		
		//assertEquals(response.getStatusCode(), HttpStatus.OK);
		//.expectStatus().isOk();
	}

}
