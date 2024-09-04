package inventariotienda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    Object[][] productos = Main.inicializarInventario();
    @BeforeEach
    void inicializarInventario() {
        productos = Main.inicializarInventario();
    }

    @Test
    void testAñadirProductos() {
        productos = Main.agregarProductos(productos, 1, "Tomate", 10);
        productos = Main.agregarProductos(productos, 2, "Leche", 5);
        System.out.println(productos);
        assertEquals("Tomate", productos[0][1]);
        assertEquals("Leche", productos[1][1]);
        assertEquals(5, productos[1][2]);
    }

    @Test
    void testRestarProductos() {

    }

    @Test
    void testValidarInventario() {

    }
}
