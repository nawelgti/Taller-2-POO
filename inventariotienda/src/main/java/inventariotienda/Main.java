package inventariotienda;

public class Main {
    public static void main(String[] args) {

    }

    public static Object[][] inicializarInventario() {
        Object[][] productos = new Object[10][3];
        return productos;
    }

    public static Object[][] agregarProductos(Object[][] productos, int idProducto, String nombre, int cantidad) {
        if (validarInventario(productos) == true) {
            for (int i=0; i<10;) {
                if (productos[i][0] == null) {
                    productos[i][0] = idProducto;
                    productos[i][1] = nombre;
                }
                int stock = (int) productos[i][2];
                productos[i][2] = stock + cantidad;
                break;
            }
        }
        return productos;
    }

    public static Object[][] eliminarProductos(Object[][] productos, int idProducto) {
        for (int i=0; i<10; i++) {
            if ((productos[i][0]).equals(idProducto)) {
                productos[i][0] = null;
                productos[i][0] = null;
                productos[i][2] = null;
            }
            else {}
        }
        return productos;
    }

    public static boolean validarInventario(Object[][] productos) {
        boolean validado = false;
        if (productos[9][0] != null) {
            System.out.println("Error: El inventario está lleno.");
        }
        else {
            validado = true;
        }
        return validado;
    }
}