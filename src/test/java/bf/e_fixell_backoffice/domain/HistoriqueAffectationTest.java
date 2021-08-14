package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class HistoriqueAffectationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoriqueAffectation.class);
        HistoriqueAffectation historiqueAffectation1 = new HistoriqueAffectation();
        historiqueAffectation1.setId(1L);
        HistoriqueAffectation historiqueAffectation2 = new HistoriqueAffectation();
        historiqueAffectation2.setId(historiqueAffectation1.getId());
        assertThat(historiqueAffectation1).isEqualTo(historiqueAffectation2);
        historiqueAffectation2.setId(2L);
        assertThat(historiqueAffectation1).isNotEqualTo(historiqueAffectation2);
        historiqueAffectation1.setId(null);
        assertThat(historiqueAffectation1).isNotEqualTo(historiqueAffectation2);
    }
}
