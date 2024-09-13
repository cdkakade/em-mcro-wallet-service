package expense.manager.wallet.controller;

import expense.manager.common.dto.wallet.request.WalletRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import expense.manager.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
@Slf4j
public class WalletController {

    @Autowired
    private WalletService service;

    @Operation(summary = "Get All Wallets")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<WalletResponse> findAll() {
        log.info("/wallets GET API called");
        return service.findAll();
    }

    @Operation(summary = "Get Wallet By ID")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WalletResponse> findById(@PathVariable(value = "id") String walletId, @AuthenticationPrincipal Jwt jwt) throws Exception {
        log.info("/wallets/{} API called", walletId);
        WalletResponse returnValue = service.findById(walletId, jwt);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @Operation(summary = "Add New Wallet")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public WalletResponse addWallet(@Valid @RequestBody WalletRequest wallet, @AuthenticationPrincipal Jwt jwt) throws Exception {
        log.info("/wallets POST API called");
        return service.addWallet(wallet, jwt);
    }

    /*
     * @Operation(summary = "Update Wallet By Id", security = @SecurityRequirement(name =
     * "bearerAuth"))
     *
     * @PutMapping(path = "/{walletId}", produces = {MediaType.APPLICATION_JSON_VALUE})
     * public ResponseEntity<WalletResponse> updateWallet(Principal
     * principal, @Valid @RequestBody WalletRequest wallet,
     *
     * @PathVariable(name = "walletId") String walletId ) throws Exception {
     * WalletResponse returnValue = service.update(principal.getName(), wallet, walletId);
     * return new ResponseEntity<>(returnValue, HttpStatus.OK); }
     *
     * @Operation(summary = "Delete Wallet By Id", security = @SecurityRequirement(name =
     * "bearerAuth"))
     *
     * @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
     * public ResponseEntity<OperationStatusDto> deleteWallet(Principal
     * principal, @PathVariable String id) throws Exception { OperationStatusDto
     * returnValue = new OperationStatusDto();
     * returnValue.setOperationName(RequestOperationName.DELETE.name());
     * service.delete(principal.getName(), id);
     * returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name()); return
     * ResponseEntity.status(HttpStatus.OK).body(returnValue); }
     */

}
