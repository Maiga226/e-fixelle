package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Commande;
import bf.e_fixell_backoffice.repository.CommandeRepository;
import bf.e_fixell_backoffice.service.dto.CommandeDTO;
import bf.e_fixell_backoffice.service.mapper.CommandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Commande}.
 */
@Service
@Transactional
public class CommandeService {

    private final Logger log = LoggerFactory.getLogger(CommandeService.class);

    private final CommandeRepository commandeRepository;

    private final CommandeMapper commandeMapper;

    public CommandeService(CommandeRepository commandeRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    /**
     * Save a commande.
     *
     * @param commandeDTO the entity to save.
     * @return the persisted entity.
     */
    public CommandeDTO save(CommandeDTO commandeDTO) {
        log.debug("Request to save Commande : {}", commandeDTO);
        Commande commande = commandeMapper.toEntity(commandeDTO);
        commande = commandeRepository.save(commande);
        return commandeMapper.toDto(commande);
    }

    /**
     * Get all the commandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Commandes");
        return commandeRepository.findAll(pageable)
            .map(commandeMapper::toDto);
    }


    /**
     * Get one commande by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommandeDTO> findOne(Long id) {
        log.debug("Request to get Commande : {}", id);
        return commandeRepository.findById(id)
            .map(commandeMapper::toDto);
    }

    /**
     * Delete the commande by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Commande : {}", id);
        commandeRepository.deleteById(id);
    }
}
