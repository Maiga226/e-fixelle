package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class FicheTechniqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheTechnique.class);
        FicheTechnique ficheTechnique1 = new FicheTechnique();
        ficheTechnique1.setId(1L);
        FicheTechnique ficheTechnique2 = new FicheTechnique();
        ficheTechnique2.setId(ficheTechnique1.getId());
        assertThat(ficheTechnique1).isEqualTo(ficheTechnique2);
        ficheTechnique2.setId(2L);
        assertThat(ficheTechnique1).isNotEqualTo(ficheTechnique2);
        ficheTechnique1.setId(null);
        assertThat(ficheTechnique1).isNotEqualTo(ficheTechnique2);
    }
}
