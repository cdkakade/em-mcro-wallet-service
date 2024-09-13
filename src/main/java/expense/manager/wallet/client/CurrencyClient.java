package expense.manager.wallet.client;

import expense.manager.common.dto.currency.response.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "currency-service", dismiss404 = true, url = "http://localhost:8072/")
public interface CurrencyClient {

    @RequestMapping(method = RequestMethod.GET, value = "currencies", consumes = "application/json")
    List<CurrencyResponse> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "currencies/{id}", consumes = "application/json")
    ResponseEntity<CurrencyResponse> findById(@PathVariable("id") Long id);

}
