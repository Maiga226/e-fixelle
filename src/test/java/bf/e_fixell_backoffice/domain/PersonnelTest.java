package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class PersonnelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Personnel.class);
        Personnel personnel1 = new Personnel();
        personnel1.setId(1L);
        Personnel personnel2 = new Personnel();
        personnel2.setId(personnel1.getId());
        assertThat(personnel1).isEqualTo(personnel2);
        personnel2.setId(2L);
        assertThat(personnel1).isNotEqualTo(personnel2);
        personnel1.setId(null);
        assertThat(personnel1).isNotEqualTo(personnel2);
    }
}
