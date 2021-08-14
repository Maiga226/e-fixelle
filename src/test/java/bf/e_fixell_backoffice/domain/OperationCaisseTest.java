package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class OperationCaisseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperationCaisse.class);
        OperationCaisse operationCaisse1 = new OperationCaisse();
        operationCaisse1.setId(1L);
        OperationCaisse operationCaisse2 = new OperationCaisse();
        operationCaisse2.setId(operationCaisse1.getId());
        assertThat(operationCaisse1).isEqualTo(operationCaisse2);
        operationCaisse2.setId(2L);
        assertThat(operationCaisse1).isNotEqualTo(operationCaisse2);
        operationCaisse1.setId(null);
        assertThat(operationCaisse1).isNotEqualTo(operationCaisse2);
    }
}
