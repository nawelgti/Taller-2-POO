package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Registro {
    public static void main(String[] args) {
        menu();
    }

    public static int obtenerUltimoEspacio(String[][] registro) {
        String espacioPosicion;
        for (int i = 0; i < registro.length; i++) {
            if (registro[i][0].equals("")) {
                espacioPosicion = registro[i][1];
            }else {
                System.out.println("No hay espacios.");
            }
        }
        return registro.length - largoMatriz(registro);
    }

    public static boolean hayCupo(String[][] registro) {
        return largoMatriz(registro) != 0;
    }

    public static int largoMatriz(String[][] registro) {
        for (int i = 0; i < registro.length; i++) {
            if (registro[i][0]==null) {
                registro[i][0]="";
                return registro.length - i;
            }
        }

        return 0;
    }

    public static void menu() {
        String[][] registro = new String[50][3];
        int opcion = -1;
        boolean programaFuncionando = true;
        System.out.println("""
                Menú
                1) Agregar persona.
                2) Mostrar la cantidad de personas mayores de edad.
                3) Mostrar la cantidad de personas menores de edad.
                4) Mostrar la cantidad de personas de tercera edad.
                5) Mostrar la cantidad de personas según estado civil (Soltero/a - Casado/a).
                6) Salir.
                """);
        do {
                try {
                    opcion = new Scanner(System.in).nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Opción inválida");
                    continue;
                }

        while (opcion > 1 || opcion < 6) {

                if (opcion == 1) {
                    if (hayCupo(registro)) {
                        int indiceDisponible = obtenerUltimoEspacio(registro);
                        String nombre;
                        String Estadocivil;
                        String edad;
                        System.out.println("Ingresa el nombre de la persona: ");
                        nombre = new Scanner(System.in).nextLine();
                        System.out.println("Ingresa el estado civil de la persona: ");
                        Estadocivil = new Scanner(System.in).nextLine();
                        System.out.println("Ingresa la edad de la persona: ");
                        edad = new Scanner(System.in).nextLine();

                        registro[indiceDisponible][0] = nombre;
                        registro[indiceDisponible][1] = Estadocivil;
                        registro[indiceDisponible][2] = edad;
                        System.out.println("Persona agregada.");
                    } else {
                        System.out.println("No hay cupo.");
                    }
                } else if (opcion == 2) {
                    int mayoresDeEdad = 0;
                    for (String[] persona : registro) {
                        int edadPersona = Integer.parseInt(persona[2]);
                        if (edadPersona >= 18)
                            mayoresDeEdad++;
                    }
                    System.out.println("Hay " + mayoresDeEdad + " mayores de edad.");

                } else if (opcion == 3) {
                    int menoresDeEdad = 0;
                    int edadRegistro = 0;
                    int queSera = obtenerUltimoEspacio(registro);

                    for (int i = 0; i < queSera; i++) {
                        edadRegistro = Integer.parseInt(registro[i][2]);
                        if (edadRegistro < 18) {
                            menoresDeEdad++;
                        }
                    }
                    System.out.println("Hay " + menoresDeEdad + " menores de edad.");

                } else if (opcion == 4) {
                    int personasTerceraEdad = 0;
                    for (String[] persona : registro) {
                        int edadPersona = Integer.parseInt(persona[2]);
                        if (edadPersona >= 60)
                            personasTerceraEdad++;
                    }
                    System.out.println("Hay " + personasTerceraEdad + " personas de tercera edad");
                } else if (opcion == 5) {
                    int casados = 0;
                    int solteros = 0;
                    for (String[] persona : registro) {
                        if (persona[1].equals("casado/a")) {
                            casados++;
                        } else if (persona[1].equals("soltero/a")) {
                            solteros++;
                        }
                    }

                    System.out.println("Hay " + casados + " casados/as.");
                    System.out.println("Hay " + solteros + " solteros/as.");
                } else if (opcion == 6) {
                    System.out.println("Finalizando programa...");
                    break;
                } else {
                    System.out.println("Opcion no valida.");
                }
            }
        } while(programaFuncionando);
    }
}


