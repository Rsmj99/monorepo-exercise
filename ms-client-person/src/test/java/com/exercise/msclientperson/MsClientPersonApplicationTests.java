package com.exercise.msclientperson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsClientPersonApplicationTests {

	// Esta prueba asegura que el contexto de la aplicación se cargue sin errores.
	@Test
	public void contextLoads() {
		// Simula la ejecución del método main con una lista de argumentos vacía.
		MsClientPersonApplication.main(new String[] {});
	}

}
