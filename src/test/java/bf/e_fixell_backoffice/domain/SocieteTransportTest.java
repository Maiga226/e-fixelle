package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class SocieteTransportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocieteTransport.class);
        SocieteTransport societeTransport1 = new SocieteTransport();
        societeTransport1.setId(1L);
        SocieteTransport societeTransport2 = new SocieteTransport();
        societeTransport2.setId(societeTransport1.getId());
        assertThat(societeTransport1).isEqualTo(societeTransport2);
        societeTransport2.setId(2L);
        assertThat(societeTransport1).isNotEqualTo(societeTransport2);
        societeTransport1.setId(null);
        assertThat(societeTransport1).isNotEqualTo(societeTransport2);
    }
}
