package inventariotienda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MainTest {
    Object[][] productos = Main.inicializarInventario();
    @BeforeEach
    void inicializarInventario() {
        productos = Main.inicializarInventario();
    }

    void agregarTomate() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("Tomate");
        Main.setScanner(mockScanner);
        productos = Main.agregarProductos(productos, 1, 10);
    }
    @Test
    void testAgregarProductos() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("Tomate");
        Main.setScanner(mockScanner);

        productos = Main.agregarProductos(productos, 1, 10);
        assertEquals("Tomate", productos[0][1]);
        assertEquals(10, productos[0][2]);
    }

    @Test
    void testRestarProductos() {
        agregarTomate();

        Main.restarProductos(productos, 1, 5);

        assertEquals(5, productos[0][2]);
    }

    @Test
    void restarCantidadMayorQueStock() { //tambien valido como testEliminarProducto
        agregarTomate();

        Scanner mockito = mock(Scanner.class);
        when(mockito.nextLine()).thenReturn("SI");
        Main.setScanner(mockito);

        productos = Main.restarProductos(productos, 1, 11);
        assertEquals(null, productos[0][0]);
    }

    @Test
    void testConsultarDisponibilidad() {
        agregarTomate();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.consultarDisponibilidad(productos, 1);
        String actual = outContent.toString().replace("\r\n","\n");
        assertEquals("Hay 10 unidades de 'Tomate'\n", actual);
    }

    @Test
    void testListarProductos() {
        agregarTomate();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.listarProductos(productos);
        String actual = outContent.toString().replace("\r\n","\n");
        String expected = ("Nombre: Tomate\nID: 1\nStock: 10 unidades\n\n");
        assertEquals(expected, actual);
    }
}
