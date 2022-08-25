package com.leovegas.digital.wallet.controller;

import com.leovegas.digital.wallet.data.dto.transaction.CreateTransactionDto;
import com.leovegas.digital.wallet.data.dto.transaction.TransactionDto;
import com.leovegas.digital.wallet.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping()
    public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody CreateTransactionDto createTransactionDTO) {
        TransactionDto transactionDto = service.create(createTransactionDTO);
        return ResponseEntity.ok().body(transactionDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Long id) {
        TransactionDto transactionDto = service.findOne(id);
        return ResponseEntity.ok().body(transactionDto);
    }


    @GetMapping()
    public ResponseEntity<Page<TransactionDto>> getAllTransaction(@RequestParam(required = false) Optional<Long> playerId, Pageable pageable) {
        Page<TransactionDto> transactionDtoPage = service.getAllTransaction(pageable, playerId);
        return ResponseEntity.ok()
                .body(transactionDtoPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}