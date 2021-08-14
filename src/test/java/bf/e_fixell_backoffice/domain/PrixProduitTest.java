package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class PrixProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrixProduit.class);
        PrixProduit prixProduit1 = new PrixProduit();
        prixProduit1.setId(1L);
        PrixProduit prixProduit2 = new PrixProduit();
        prixProduit2.setId(prixProduit1.getId());
        assertThat(prixProduit1).isEqualTo(prixProduit2);
        prixProduit2.setId(2L);
        assertThat(prixProduit1).isNotEqualTo(prixProduit2);
        prixProduit1.setId(null);
        assertThat(prixProduit1).isNotEqualTo(prixProduit2);
    }
}
