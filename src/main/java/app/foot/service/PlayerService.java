package app.foot.service;

import app.foot.model.Player;
import app.foot.repository.PlayerRepository;
import app.foot.repository.mapper.PlayerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper mapper;

    public List<Player> getPlayers() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Player> createPlayers(List<Player> toCreate) {
        return repository.saveAll(toCreate.stream()
                        .map(mapper::toEntity)
                        .toList()).stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Player> putPlayer(List<Player> toChange){
        return repository.saveAll(toChange.stream()
                        .map(mapper::toRest)
                        .toList()).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
