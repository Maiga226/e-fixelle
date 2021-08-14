package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class ApprovisionnementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Approvisionnement.class);
        Approvisionnement approvisionnement1 = new Approvisionnement();
        approvisionnement1.setId(1L);
        Approvisionnement approvisionnement2 = new Approvisionnement();
        approvisionnement2.setId(approvisionnement1.getId());
        assertThat(approvisionnement1).isEqualTo(approvisionnement2);
        approvisionnement2.setId(2L);
        assertThat(approvisionnement1).isNotEqualTo(approvisionnement2);
        approvisionnement1.setId(null);
        assertThat(approvisionnement1).isNotEqualTo(approvisionnement2);
    }
}
