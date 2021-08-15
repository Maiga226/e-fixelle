package bf.e_fixell_backoffice.service.mapper;


import bf.e_fixell_backoffice.domain.*;
import bf.e_fixell_backoffice.service.dto.TransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {FicheTechniqueMapper.class, ProduitMapper.class, CommandeMapper.class, ApprovisionnementMapper.class, LivraisonMapper.class, VenteMapper.class})
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {

    @Mapping(source = "ficheTechnique.id", target = "ficheTechniqueId")
    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "approvisionnement.id", target = "approvisionnementId")
    @Mapping(source = "livraison.id", target = "livraisonId")
    @Mapping(source = "vente.id", target = "venteId")
    TransactionDTO toDto(Transaction transaction);

    @Mapping(source = "ficheTechniqueId", target = "ficheTechnique")
    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "commandeId", target = "commande")
    @Mapping(source = "approvisionnementId", target = "approvisionnement")
    @Mapping(source = "livraisonId", target = "livraison")
    @Mapping(source = "venteId", target = "vente")
    Transaction toEntity(TransactionDTO transactionDTO);

    default Transaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}
