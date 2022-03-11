package com.dc.hexagonal.hexagonalarchitecture.account.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;

import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.SendMoneyUseCase;
import com.dc.hexagonal.hexagonalarchitecture.account.application.port.in.dto.SendMoneyDto;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Account.AccountId;
import com.dc.hexagonal.hexagonalarchitecture.account.domain.Money;

@WebMvcTest(controllers = SendMoneyController.class)
class SendMoneyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SendMoneyUseCase sendMoneyUseCase;

	@Test
	void testSendMoney() throws Exception {

		mockMvc.perform(post("/accounts/sendMoney/{sourceAccountId}/{targetAccountId}/{amount}",
				41L, 42L, 500)
				.header("Content-Type", "application/json"))
				.andExpect(status().isOk());

		/*then(sendMoneyUseCase).should()
				.sendMoney(eq(new SendMoneyDto(
						new AccountId(41L),
						new AccountId(42L),
						Money.of(500L))));*/
	}

}