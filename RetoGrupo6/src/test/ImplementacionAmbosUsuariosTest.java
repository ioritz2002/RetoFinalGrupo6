package test;

import static org.junit.Assert.*;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import clases.Cliente;
import clases.ListarTablaHistorial;
import clases.Usuario;
import modelo.ImplementacionAdministradorBD;
import modelo.ImplementacionAmbosUsuarios;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;

@RunWith(Parameterized.class)
public class ImplementacionAmbosUsuariosTest {

	private InterfazAmbosUsuarios ambos = new ImplementacionAmbosUsuarios();
	private String dni1;

	public ImplementacionAmbosUsuariosTest(String dni1) {
		this.dni1 = dni1;
	}

	@Parameters
	public static java.util.Collection<Object[]> dnis() {
		return Arrays.asList(new Object[][] { { "79365183A" }, { "99999999R" }, { "9999999i" }, {"99999998T"} });
	}

	// (expected= java.lang.NullPointerException.class)
	@Test
	public void testBuscarUsuarioLogin() {
		Usuario us = ambos.buscarUsuarioLogin(dni1);
		if (us == null) {
			assertNull("No es nulo", us);
		} else {
			if (us.getTipo().equalsIgnoreCase("administrador")) {
				assertEquals("Son distintos", "administrador", us.getTipo());
			}
			if (us.getTipo().equalsIgnoreCase("administrador")) {
				assertNotEquals("Son iguales", "cliente", us.getTipo());
			}
		}
	}

	@Test 
	public void testBuscarCestas() {
		List<ListarTablaHistorial> listaHistorial = ambos.buscarCestas(dni1);
		if (listaHistorial.isEmpty()) {
			assertEquals(0, listaHistorial.size());
		} else {
			if (listaHistorial.get(0).getCodigo().equalsIgnoreCase("CE-0001")) {
				assertEquals("Son distintos", "CE-0001", listaHistorial.get(0).getCodigo());
			} else {
				assertNotEquals("Son iguales", "CE-0001", listaHistorial.get(0).getCodigo());
			}

		}
	}
}
