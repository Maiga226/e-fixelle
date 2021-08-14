package bf.e_fixell_backoffice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.e_fixell_backoffice.web.rest.TestUtil;

public class SessionCaisseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionCaisse.class);
        SessionCaisse sessionCaisse1 = new SessionCaisse();
        sessionCaisse1.setId(1L);
        SessionCaisse sessionCaisse2 = new SessionCaisse();
        sessionCaisse2.setId(sessionCaisse1.getId());
        assertThat(sessionCaisse1).isEqualTo(sessionCaisse2);
        sessionCaisse2.setId(2L);
        assertThat(sessionCaisse1).isNotEqualTo(sessionCaisse2);
        sessionCaisse1.setId(null);
        assertThat(sessionCaisse1).isNotEqualTo(sessionCaisse2);
    }
}
