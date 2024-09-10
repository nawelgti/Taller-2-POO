package inventariotienda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Object[][]productos = inicializarInventario();
        System.out.println("Bienvenido al gestor de Inventario");
        while (true) {menu(productos);}
    }

    public static void opcionesMenu() {
        System.out.println("1. Agregar productos");
        System.out.println("2. Restar productos");
        System.out.println("3. Eliminar productos");
        System.out.println("4. Consultar STOCK de un producto");
        System.out.println("5. Ver listado de productos");
    }

    public static void menu(Object[][] productos) {
        opcionesMenu();

        int resp = leerRespuestaINT();
        if (resp == 1) {
            String nombre = leerRespuestaSTRING();
            int id = leerRespuestaINT();
            int cant = leerRespuestaINT();
            agregarProductos(productos, id, nombre, cant);
        }
        if (resp == 2) {
            System.out.println("Ingrese el ID del producto:");
            String id = leerRespuestaSTRING();
        }
    }

    public static Object[][] inicializarInventario() {
        Object[][] productos = new Object[10][3];
        return productos;
    }

    public static Object[][] agregarProductos(Object[][] productos, int idProducto, String nombre, int cantidad) {
        if (validarInventario(productos) == true) {
            for (int i=0; i<10; i++) {
                if (productos[i][0] == null) {
                    productos[i][0] = idProducto;
                    productos[i][1] = nombre;
                    productos[i][2] = cantidad;
                    break;
                }
                else if ((int)productos[i][0] == idProducto) {
                    int stock = (int) productos[i][2];
                    productos[i][2] = stock + cantidad; break;
                }
            }
        }
        else {System.out.println("Error: El inventario está lleno");}
        return productos;
    }

    public static Object[][] restarProductos(Object[][] productos, int idProducto, int cantidad) {
        for (int i=0; i<10; i++) {
            if (productos[i][0] != null && (productos[i][0]).equals(idProducto)) {
                if ((int) productos[i][2] >= cantidad) {productos[i][2] = (int)productos[i][2] - cantidad;}
                else {
                    System.out.println("¿Desea eliminar el producto? (SI/NO)"); String resp = leerSIoNO();
                    if (resp.equals("SI")) {eliminarProductos(productos, idProducto);}
                    else {System.out.print("No se han realizado cambios.");}
                }
                break;
            }
        }
        return productos;
    }

    public static Object[][] eliminarProductos(Object[][] productos, int idProducto) {
        for (int i=0; i<10; i++) {
            if (productos[i][0] != null && (productos[i][0]).equals(idProducto)) {
                productos[i][0] = null;
                productos[i][1] = null;
                productos[i][2] = null;
            }
            else {
                System.out.println("No se ha encontrado el producto.");
            }
        }
        return productos;
    }

    public static boolean validarInventario(Object[][] productos) {
        boolean validado = false;
        if (productos[9][0] == null) {
            validado = true;
        }
        return validado;
    }

    public static String leerSIoNO() {
        while (true) {
            String respuesta = scanner.nextLine();
            if (respuesta.equals("SI") || respuesta.equals("NO")) {
                return respuesta;
            } else {
                System.out.println("Ingrese una respuesta válida (SI/NO)");
            }
        }
    }    

    public static int leerRespuestaINT() {
        @SuppressWarnings("resource")
        Scanner resp = new Scanner(System.in);
        return Integer.parseInt(resp.nextLine());
    }

    public static String leerRespuestaSTRING() {
        @SuppressWarnings("resource")
        Scanner resp = new Scanner(System.in);
        return resp.nextLine();
    }

    private static Scanner scanner = new Scanner(System.in);
    public static void setScanner(Scanner scanner) { Main.scanner = scanner;}
}