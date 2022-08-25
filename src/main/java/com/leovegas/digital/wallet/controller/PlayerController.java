package com.leovegas.digital.wallet.controller;

import com.leovegas.digital.wallet.data.dto.player.CreatePlayerDto;
import com.leovegas.digital.wallet.data.dto.player.PlayerDto;
import com.leovegas.digital.wallet.data.dto.player.UpdatePlayerDto;
import com.leovegas.digital.wallet.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/player")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService service;

    @PostMapping()
    public ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody CreatePlayerDto createPlayerDTO) {
        PlayerDto playerDto = service.create(createPlayerDTO);
        return ResponseEntity.ok().body(playerDto);
    }

    @PutMapping()
    public ResponseEntity<PlayerDto> updatePlayer(@RequestBody UpdatePlayerDto updatePlayerDTO) {
        PlayerDto playerDto = service.update(updatePlayerDTO);
        return ResponseEntity.ok().body(playerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable Long id) {
        PlayerDto playerDto = service.findOne(id);
        return ResponseEntity.ok().body(playerDto);
    }

    @GetMapping()
    public ResponseEntity<Page<PlayerDto>> getAllPlayers(Pageable pageable) {
        Page<PlayerDto> players = service.getAll(pageable);
        return ResponseEntity.ok()
                .body(players);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}