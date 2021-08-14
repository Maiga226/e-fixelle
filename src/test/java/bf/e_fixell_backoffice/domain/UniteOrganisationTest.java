package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class UniteOrganisationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UniteOrganisation.class);
        UniteOrganisation uniteOrganisation1 = new UniteOrganisation();
        uniteOrganisation1.setId(1L);
        UniteOrganisation uniteOrganisation2 = new UniteOrganisation();
        uniteOrganisation2.setId(uniteOrganisation1.getId());
        assertThat(uniteOrganisation1).isEqualTo(uniteOrganisation2);
        uniteOrganisation2.setId(2L);
        assertThat(uniteOrganisation1).isNotEqualTo(uniteOrganisation2);
        uniteOrganisation1.setId(null);
        assertThat(uniteOrganisation1).isNotEqualTo(uniteOrganisation2);
    }
}
