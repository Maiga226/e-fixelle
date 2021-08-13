package bf.e_fixell_backoffice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("bf.e_fixell_backoffice");

        noClasses()
            .that()
                .resideInAnyPackage("bf.e_fixell_backoffice.service..")
            .or()
                .resideInAnyPackage("bf.e_fixell_backoffice.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..bf.e_fixell_backoffice.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
