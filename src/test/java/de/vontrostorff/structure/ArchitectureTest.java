package de.vontrostorff.structure;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.conditions.ArchConditions.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureTest {

    @Test
    public void domainClassesShouldOnlyDependOnDomainClasses() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("de.vontrostorff");
        ArchRule rule = classes()
            .that()
                .resideInAPackage("de.vontrostorff.domain..")
            .should()
                .onlyDependOnClassesThat(resideInAnyPackage("de.vontrostorff.domain..")
                        .or(JavaClass.Predicates.resideOutsideOfPackages("de.vontrostorff..")))
                .orShould(haveSimpleNameEndingWith("Test"));
        rule.check(importedClasses);
    }
}