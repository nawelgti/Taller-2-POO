package inventariotienda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void opcionesMenu() {
        System.out.println("1. Agregar productos");
        System.out.println("2. Restar productos");
        System.out.println("3. Eliminar productos");
        System.out.println("4. Consultar STOCK de un producto");
        System.out.println("5. Ver listado de productos");
        System.out.println("6. Salir");
        System.out.println("Ingrese su opción:");
    }

    public static void menu() {
        Object[][] productos = inicializarInventario();
        System.out.println("Bienvenido al gestor de Inventario");
        boolean salir = false;
        while (!salir) {
            opcionesMenu();

            int resp = leerRespuestaINT();
            while (resp > 6) {System.out.println("Error: Ingrese un número válido."); resp = leerRespuestaINT();}
            if (resp == 1) {

                System.out.println("Ingrese el ID del producto:");
                int id = leerRespuestaINT();
                System.out.println("Ingrese la cantidad de unidades: ");
                int cant = leerRespuestaINT();
                agregarProductos(productos, id, cant);
            }
            else if (resp == 2) {
                System.out.println("Ingrese el ID del producto:");
                int id = leerRespuestaINT();
                System.out.println("Cantidad de unidades a restar: ");
                int cant = leerRespuestaINT();
                restarProductos(productos, id, cant);
            }
            else if (resp == 3) {
                System.out.println("Ingrese el ID del producto:");
                int id = leerRespuestaINT();
                eliminarProductos(productos, id);
            }
            else if (resp == 4) {
                System.out.println("Ingrese el ID del producto:");
                int id = leerRespuestaINT();
                consultarDisponibilidad(productos, id);
            }
            else if (resp == 5) {
                listarProductos(productos);
            }
            else if (resp == 6) {System.out.println("Saliendo...");salir = true;}
        }
    }

    public static Object[][] inicializarInventario() {
        Object[][] productos = new Object[10][3];
        return productos;
    }

    public static Object[][] agregarProductos(Object[][] productos, int idProducto, int cantidad) {
        if (validarInventario(productos) == true) {
            for (int i=0; i<10; i++) {
                if (productos[i][0] == null) {
                    System.out.println("Ingrese el nombre del producto:");
                    String nombre = leerRespuestaSTRING();
                    productos[i][0] = idProducto;
                    productos[i][1] = nombre;
                    productos[i][2] = cantidad;
                    System.out.println("El producto ha sido agregado.\n");
                    break;
                }
                else if ((int)productos[i][0] == idProducto) {
                    int stock = (int) productos[i][2];
                    productos[i][2] = stock + cantidad;
                    System.out.println("Se han agregado unidades.\n"); break;
                }
            }
        }
        else {System.out.println("Error: El inventario está lleno");}
        return productos;
    }

    public static Object[][] restarProductos(Object[][] productos, int idProducto, int cantidad) {
        for (int i=0; i<10; i++) {
            if (productos[i][0] != null && (productos[i][0]).equals(idProducto)) {
                if ((int) productos[i][2] > cantidad) {
                    System.out.println("Se han restado unidades.\n");
                    productos[i][2] = (int)productos[i][2] - cantidad;}
                else {
                    System.out.println("¿Desea eliminar el producto? (SI/NO)"); String resp = leerSIoNO();
                    if (resp.equals("SI")) { 
                        eliminarProductos(productos, idProducto);}
                    else {
                        if ((int)productos[i][2] == cantidad) {
                            productos[i][2] = (int)productos[i][2] - cantidad;
                            System.out.println("Se han restado unidades.\n");}
                        else {System.out.print("No se han realizado cambios.\n");}
                    }      
                }
                break;
            }
            else {System.out.println("No se ha encontrado el producto.\n");}
        }
        return productos;
    }

    public static Object[][] eliminarProductos(Object[][] productos, int idProducto) {
        for (int i=0; i<10; i++) {
            if (productos[i][0] != null && (productos[i][0]).equals(idProducto)) {
                productos[i][0] = null;
                productos[i][1] = null;
                productos[i][2] = null;
                System.out.println("Se ha eliminado el producto.\n");
                break;
            }
            else {
                System.out.println("No se ha encontrado el producto.\n");
                break;
            }
        }
        return productos;
    }

    public static void consultarDisponibilidad(Object[][] productos, int idProducto) {
        for (int i=0; i<10; i++) {
            if (productos[i][0] != null && (productos[i][0]).equals(idProducto)) {
                int stock = (int)productos[i][2];
                String nombre = (String)productos[i][1];
                System.out.println("Hay "+stock+" unidades de '"+nombre+"'");
            }
        }
    }

    public static void listarProductos(Object[][] productos) {
        for (int i=0; i<10; i++) {
            if (productos[i][0] != null) {
                System.out.println("Nombre: "+productos[i][1]);
                System.out.println("ID: "+productos[i][0]);
                System.out.println("Stock: "+productos[i][2]+" unidades");
                System.out.println("");
            }
        }
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
        int n = 0;
        boolean validado = false;
        while (!validado) {
            try {
                n = Integer.parseInt(resp.nextLine());
                validado = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
        return n;
    }

    public static String leerRespuestaSTRING() {
        return scanner.nextLine();
    }

    private static Scanner scanner = new Scanner(System.in);
    public static void setScanner(Scanner scanner) { Main.scanner = scanner;}
}