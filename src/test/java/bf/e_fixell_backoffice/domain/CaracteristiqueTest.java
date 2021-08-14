package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class CaracteristiqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Caracteristique.class);
        Caracteristique caracteristique1 = new Caracteristique();
        caracteristique1.setId(1L);
        Caracteristique caracteristique2 = new Caracteristique();
        caracteristique2.setId(caracteristique1.getId());
        assertThat(caracteristique1).isEqualTo(caracteristique2);
        caracteristique2.setId(2L);
        assertThat(caracteristique1).isNotEqualTo(caracteristique2);
        caracteristique1.setId(null);
        assertThat(caracteristique1).isNotEqualTo(caracteristique2);
    }
}
