package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Approvisionnement;
import bf.e_fixell_backoffice.domain.Caracteristique;
import bf.e_fixell_backoffice.domain.FicheTechnique;
import bf.e_fixell_backoffice.domain.enumeration.Etat;
import bf.e_fixell_backoffice.domain.enumeration.Statut;
import bf.e_fixell_backoffice.domain.enumeration.TypeTransaction;
import bf.e_fixell_backoffice.repository.ApprovisionnementRepository;
import bf.e_fixell_backoffice.repository.CaracteristiqueRepository;
import bf.e_fixell_backoffice.service.dto.ApprovisionnementDTO;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueDTO;
import bf.e_fixell_backoffice.service.dto.PrixProduitDTO;
import bf.e_fixell_backoffice.service.dto.TransactionDTO;
import bf.e_fixell_backoffice.service.mapper.ApprovisionnementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Approvisionnement}.
 */
@Service
@Transactional
public class ApprovisionnementService {

    private final Logger log = LoggerFactory.getLogger(ApprovisionnementService.class);

    private final ApprovisionnementRepository approvisionnementRepository;

    private final ApprovisionnementMapper approvisionnementMapper;

    private final FicheTechniqueService ficheTechniqueService;

    private final CaracteristiqueService caracteristiqueService;

    private final CaracteristiqueRepository caracteristiqueRepository;

    private final PrixProduitService prixProduitService;

    private final TransactionService transactionService;

    public ApprovisionnementService(ApprovisionnementRepository approvisionnementRepository, ApprovisionnementMapper approvisionnementMapper, FicheTechniqueService ficheTechniqueService, CaracteristiqueService caracteristiqueService, CaracteristiqueRepository caracteristiqueRepository, PrixProduitService prixProduitService, TransactionService transactionService) {
        this.approvisionnementRepository = approvisionnementRepository;
        this.approvisionnementMapper = approvisionnementMapper;
        this.ficheTechniqueService = ficheTechniqueService;
        this.caracteristiqueService = caracteristiqueService;
        this.caracteristiqueRepository = caracteristiqueRepository;
        this.prixProduitService = prixProduitService;
        this.transactionService = transactionService;
    }

    /**
     * Save a approvisionnement.
     *
     * @param approvisionnementDTO the entity to save.
     * @return the persisted entity.
     */
    public ApprovisionnementDTO save(ApprovisionnementDTO approvisionnementDTO) {
        log.debug("Request to save Approvisionnement : {}", approvisionnementDTO);
       List<Caracteristique>  newListeCaracteristique = new ArrayList<>();
        // GESTION APPRO

        Approvisionnement finalApprovisionnement;
        Approvisionnement approvisionnement = approvisionnementMapper.toEntity(approvisionnementDTO);
        finalApprovisionnement = approvisionnementRepository.save(approvisionnement);

        approvisionnementDTO.getTransactions().forEach(transaction -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            PrixProduitDTO prixProduitDTO = new PrixProduitDTO();
            FicheTechniqueDTO ficheTechniqueDTO = new FicheTechniqueDTO();

            // GESTION FICHETECHNIQUE

            ficheTechniqueDTO.setLibelle(approvisionnementDTO.getLibelle());
            FicheTechnique newFiche = ficheTechniqueService.save(ficheTechniqueDTO);

            // GESTION PRIX PRODUIT

            prixProduitDTO.setDateDebut(Instant.now());
            prixProduitDTO.setDateFin(Instant.now());
            prixProduitDTO.setPrix(transaction.getPrixProduit().getPrix());
            prixProduitDTO.setStatut(Statut.ACTIF);
            prixProduitDTO.setProduitId(transaction.getProduitId());
            prixProduitService.save(prixProduitDTO);

            // GESTION CARRACTERISTIQUE

            transaction.getFicheTechnique().getCaracteristiques().forEach(caracteristique -> {
                Caracteristique  newCaracteristique = new Caracteristique();
                newCaracteristique.setLibelle(caracteristique.getLibelle());
                newCaracteristique.setValeur(caracteristique.getValeur());
                newCaracteristique.setFicheTechnique(newFiche);
                newListeCaracteristique.add(newCaracteristique);
            });
            caracteristiqueRepository.saveAll(newListeCaracteristique);

            // GESTION TRANSACTION

            transactionDTO.setApprovisionnementId(finalApprovisionnement.getId());
            transactionDTO.setDate(Instant.now());
            transactionDTO.setFicheTechniqueId(newFiche.getId());
            transactionDTO.setPrixUnitaire(transaction.getPrixUnitaire());
            transaction.setQuantite(transaction.getQuantite());
            transaction.setTypeTransaction(TypeTransaction.APPROVISIONNEMENT);
            transactionDTO.setEtat(Etat.VALIDER);
            transactionDTO.setProduitId(transaction.getProduitId());
            transactionDTO.setMotif(null);
            transactionService.save(transactionDTO);
        });
        return approvisionnementMapper.toDto(approvisionnement);
    }

    /**
     * Get all the approvisionnements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ApprovisionnementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Approvisionnements");
        return approvisionnementRepository.findAll(pageable)
            .map(approvisionnementMapper::toDto);
    }


    /**
     * Get one approvisionnement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ApprovisionnementDTO> findOne(Long id) {
        log.debug("Request to get Approvisionnement : {}", id);
        return approvisionnementRepository.findById(id)
            .map(approvisionnementMapper::toDto);
    }

    /**
     * Delete the approvisionnement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Approvisionnement : {}", id);
        approvisionnementRepository.deleteById(id);
    }
}
