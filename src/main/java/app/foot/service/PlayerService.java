package app.foot.service;

import app.foot.exception.NotFoundException;
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
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> createPlayers(List<Player> toCreate) {
        return repository.saveAll(toCreate.stream()
                        .map(mapper::toEntity)
                        .collect(Collectors.toUnmodifiableList())).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> updatePlayers(List<Player> toSave){
        for (Player player: toSave) {
            repository.findById(player.getId()).orElseThrow(
                    () -> new NotFoundException("Player#"+player.getId()+" not found.")
            );
        }
        return repository.saveAll(toSave.stream().map(mapper::toEntity).toList())
                .stream()
                .map(mapper::toDomain).toList();
    }
}
