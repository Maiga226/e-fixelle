package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class PerteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Perte.class);
        Perte perte1 = new Perte();
        perte1.setId(1L);
        Perte perte2 = new Perte();
        perte2.setId(perte1.getId());
        assertThat(perte1).isEqualTo(perte2);
        perte2.setId(2L);
        assertThat(perte1).isNotEqualTo(perte2);
        perte1.setId(null);
        assertThat(perte1).isNotEqualTo(perte2);
    }
}
