package expense.manager.wallet.controller;

import expense.manager.common.dto.wallet.request.WalletRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import expense.manager.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService service;

    @Operation(summary = "Get All Wallets", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<WalletResponse> findAll() {
        return service.findAll();
    }

    /*@Operation(summary = "Get Wallet By ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WalletResponse> findById(Principal principal, @PathVariable(value = "id") String walletId)
            throws Exception {
        WalletResponse returnValue = service.findById(walletId);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @Operation(summary = "Add New Wallet")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WalletResponse> addWallet(@Valid @RequestBody WalletRequest wallet
    ) throws Exception {
        WalletResponse returnValue = service.save(wallet);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }*/

    /*@Operation(summary = "Update Wallet By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping(path = "/{walletId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WalletResponse> updateWallet(Principal principal, @Valid @RequestBody WalletRequest wallet,
                                                  @PathVariable(name = "walletId") String walletId
    ) throws Exception {
        WalletResponse returnValue = service.update(principal.getName(), wallet, walletId);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @Operation(summary = "Delete Wallet By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OperationStatusDto> deleteWallet(Principal principal, @PathVariable String id) throws Exception {
        OperationStatusDto returnValue = new OperationStatusDto();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        service.delete(principal.getName(), id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }*/

}
