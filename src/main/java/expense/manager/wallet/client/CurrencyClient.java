package expense.manager.wallet.client;

import expense.manager.common.constants.LoggingConstants;
import expense.manager.common.dto.currency.response.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "currency", decode404 = true)
public interface CurrencyClient {

	@RequestMapping(method = RequestMethod.GET, value = "currencies", consumes = "application/json")
	List<CurrencyResponse> findAll(@RequestHeader(LoggingConstants.CORRELATION_ID) String correlationId);

	@RequestMapping(method = RequestMethod.GET, value = "currencies/{id}", consumes = "application/json")
	ResponseEntity<CurrencyResponse> findById(@RequestHeader(LoggingConstants.CORRELATION_ID) String correlationId,
			@PathVariable("id") Long id);

}
