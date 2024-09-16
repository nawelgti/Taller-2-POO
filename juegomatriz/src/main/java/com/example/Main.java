package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        iniciarJuego();
    }
    
    public static void iniciarJuego() {
        char[][] mapa = generarMapa();
        Object[] player = inicializarEntidad("player");
        Object[] mob1 = inicializarEntidad("mob");
        generarJuego(mapa, player, mob1);

        printMapa(mapa);

        while (true) {
            Scanner resp = new Scanner(System.in);
            char mov = resp.nextLine().charAt(0);
            moverPersonaje(mapa, player, mob1, mov);
            printMapa(mapa);
        }
    }

    public static char[][] generarMapa() {
        char[][] mapa = generarObstaculos();
        llenarVacio(mapa);
        return mapa;
    }
    public static char[][] generarObstaculos() {
        char[][] mapa = new char[10][10];
        for (int i=0; i<10; i++) {
            //Generar bordes:
            mapa[0][i] = '#';
            mapa[9][i] = '#';
            mapa[i][0] = '#';
            mapa[i][9] = '#';
            //Generar obstaculos aleatorios:
            int posX = (int)(Math.random()*8)+1;
            int posY = (int)(Math.random()*8)+1;
            mapa[posX][posY] = '#';
        }
        return mapa;
    }
    public static char[][] llenarVacio(char[][] mapa) {
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++) {
                if (mapa[i][j] == '\u0000') {
                    mapa[i][j] = '.';
                }
            }
        }
        return mapa;
    }

    public static void generarJuego(char[][] mapa, Object[] player, Object[] mob1) {
        mapa = spawnearEntidad(mapa, player);
        mapa = spawnearEntidad(mapa, mob1);
        mapa = spawnearInteractuables(mapa, "cofre");
        mapa = spawnearInteractuables(mapa, "meta");
    }
    public static void printMapa(char[][] mapa) {
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println("");
        }
    }

    public static boolean movPermitido(char[][] mapa, int posX, int posY) { //Para no generar sobre obstaculos
        boolean permitido = false;
        if (mapa[posY][posX] != '#') {
            permitido = true;        
        }
        return permitido;
    }

    public static Object[] inicializarEntidad(String tipo) {
        Object[] entidad = new Object[5];
        if (tipo == "player") {
            entidad[2] = 100; //vida
            entidad[3] = 15; //ataque
        }
        if (tipo == "mob") {
            entidad[2] = 45;
            entidad[3] = 10;
        }
        entidad[4] = tipo;
        return entidad;
    }
    public static char[][] spawnearEntidad(char[][] mapa, Object[] entidad) {
        boolean permitido = false; //para que no se genere sobre un obstaculo
        while (!permitido) {
            entidad[0] = (int)(Math.random()*7)+1;
            entidad[1] = (int)(Math.random()*7)+1;
            if (movPermitido(mapa,(int)entidad[0],(int)entidad[1])) {
                permitido = true;
            }
        }
        if (entidad[4] == "player") {
            mapa[(int)entidad[1]][(int)entidad[0]] = 'P';
        }
        else if (entidad[4] == "mob") {
            mapa[(int)entidad[1]][(int)entidad[0]] = 'E';
        }
        return mapa;
    }
    public static char[][] spawnearInteractuables(char[][] mapa, String tipo) {
        int posX = 0;
        int posY = 0;
        while (!movPermitido(mapa, posX, posY)) {
            posX = (int)(Math.random()*7)+1;
            posY = (int)(Math.random()*7)+1;
        }
        if (tipo == "cofre") {
            mapa[posY][posX] = 'C';
        }
        else if (tipo == "meta") {
            mapa[posY][posX] = 'X';
        }
        return mapa;
    }
    
    public static char scannerMovimiento() {
        Scanner mov = new Scanner(System.in);
        char direccion = (mov.nextLine()).charAt(0);
        mov.close();
        return direccion;
    }
    public static char[][] moverPersonaje(char[][] mapa, Object[] player, Object[] mob, char direccion) {
        int posX = (int)player[0];     int posY = (int)player[1];
        int NEOposX = posX;            int NEOposY = posY;

        if (direccion == 'a') {NEOposX = posX-1;}
        else if (direccion == 'w') {NEOposY = posY-1;}
        else if (direccion == 'd') {NEOposX = posX+1;}
        else if (direccion == 's') {NEOposY = posY+1;}

        String evento = evaluarEvento(mapa, NEOposX, NEOposY);
        if (movPermitido(mapa, NEOposX, NEOposY)) {
            iniciarEvento(evento, player, mob);
            mapa[posY][posX] = '.';
            mapa[NEOposY][NEOposX] = 'P';
            player[0] = NEOposX;
            player[1] = NEOposY;
            return mapa;
        }
        else {return mapa;}
    }
    public static String evaluarEvento(char[][] mapa, int posX, int posY) {
        char casilla = mapa[posY][posX];
        String evento;
        if (casilla == 'E') {evento = "enemigo";}
        else if (casilla == 'C') {evento = "cofre";}
        else if (casilla == 'X') {evento = "meta";}
        else {evento = "vacio";}

        return evento;
    }
    public static void iniciarEvento(String tipoEvento, Object[] player, Object[] mob) {
        if (tipoEvento == "cofre") {
            sorteoCofre(player);
        }
        if (tipoEvento == "enemigo") {
            iniciarCombate(player, mob);
        }
    }

    public static String iniciarCombate(Object[] player, Object[] mob) {
        boolean fin = false;
        String resultado = "";
        System.out.println("Te has encontrado con un enemigo.");
        System.out.println("¡Comienza el combate!");
        while (!fin) {
            System.out.println("¿Que harás?");
            System.out.println("1. Atacar");
            System.out.println("2. Huir");
            Scanner scanner = new Scanner(System.in);
            
            if (scanner.nextLine().equals("1")) {
                mob[2] = (int)mob[2]-(int)player[3];
                System.out.println("¡Atacaste al enemigo! -15 de vida");
                player[2] = (int)player[2]-(int)mob[3];
                System.out.println("¡El enemigo te ha atacado! -10 de vida");
            }
            else if (scanner.nextLine().equals("2")) {
                System.out.println("Huiste sin problemas");
                resultado = "huir";
                fin = true;
            }
            if ((int)mob[2] == 0) {
                System.out.println("¡Has derrotado al enemigo!");
                resultado = "victoria";
                fin = true;
            }
            else if ((int)player[2] == 0) {
                System.out.println("¡Te han derrotado!");
                resultado = "derrota";
                fin = true;
            }
        }
        return resultado;
    }
    public static void sorteoCofre(Object[] player) {
        int sorteo = (int)(Math.random()*10)+1;
        if (sorteo <5) {
            player[2] = (int)player[2]+10;
            System.out.println("Has encontrado una poción.\n+10 de vida");
        }
        else {
            player[2] = (int)player[2]-10;
            System.out.println("¡Es una trampa!\n-10 de vida");
        }
    }
    public static void victoria() {
        
    }
}