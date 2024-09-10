package inventariotienda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class MainTest {
    Object[][] productos = Main.inicializarInventario();
    @BeforeEach
    void inicializarInventario() {
        productos = Main.inicializarInventario();
    }

    @Test
    void testAgregarProductos() {
        productos = Main.agregarProductos(productos, 1, "Tomate", 10);
        productos = Main.agregarProductos(productos, 2, "Leche", 5);
        assertEquals("Tomate", productos[0][1]);
        assertEquals("Leche", productos[1][1]);
        assertEquals(5, productos[1][2]);
    }

    @Test
    void testRestarProductos() {
        productos = Main.agregarProductos(productos, 1, "Tomate", 10);
        productos = Main.agregarProductos(productos, 2, "Leche", 5);

        Main.restarProductos(productos, 1, 5);
        Main.restarProductos(productos, 2, 3);

        assertEquals(5, productos[0][2]);
        assertEquals(2, productos[1][2]);
    }

    @Test
    void restarCantidadMayorQueStock() {
        productos = Main.agregarProductos(productos, 1, "Leche", 5);
        Scanner mockito = mock(Scanner.class);

        when(mockito.nextLine()).thenReturn("SI");
        Main.setScanner(mockito);

        productos = Main.restarProductos(productos, 1, 6);

        assertEquals(null, productos[0][0]);
    }

    @Test
    void testValidarInventario() {

    }

    @Test
    void testEliminarProductos() {
        
    }
}
