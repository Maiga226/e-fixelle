package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class TypeFraisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeFrais.class);
        TypeFrais typeFrais1 = new TypeFrais();
        typeFrais1.setId(1L);
        TypeFrais typeFrais2 = new TypeFrais();
        typeFrais2.setId(typeFrais1.getId());
        assertThat(typeFrais1).isEqualTo(typeFrais2);
        typeFrais2.setId(2L);
        assertThat(typeFrais1).isNotEqualTo(typeFrais2);
        typeFrais1.setId(null);
        assertThat(typeFrais1).isNotEqualTo(typeFrais2);
    }
}
