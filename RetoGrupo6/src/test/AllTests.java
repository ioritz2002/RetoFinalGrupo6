package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ImplementacionAdministradorBDTest.class,
	ImplementacionAmbosUsuariosTest.class,
	ImplementacionClienteBDTest.class
})
public class AllTests {

}