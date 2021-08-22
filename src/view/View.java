package view;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase View: En esta clase están definidos los metodos que estaran a la vista el usuario.
 * @TallerColavorativo1er50
 */
public class View {
    private Scanner sc = new Scanner(System.in);

    /**
     * Imprime las opciones del menú y lee la opcion seleccionada.
     * @return Devuelve un entero segun la opción selecionada.
     */
    public int readOptionMenu()  {
        showMessage("Bienvenido! \n" +
                "¿Qué desea hacer con la cadena? Selecione una opción. \n"+
                "[1] Convertirla a nombre propio.\n"+
                "[2] Buscar cadena.\n"+
                "[3] Encriptar.\n"+
                "[4] Desencriptar.\n"+
                "[5] Llenar caracteres.\n"+
                "[6] Borrar caracteres.\n"+
                "[7] Intersección.\n"+
                "[8] Diferencia.\n"+
                "[9] Borrar caracteres Izquierda o Derecha\r\n" +
                "[10] Validar direccion de correo.\n"+
                "[11] Salir.");
        int option = Integer.parseInt( sc.nextLine() );
        if ( option < 1 || option > 11 ) {
            showMessageErr("Digito invalido");
        }
        return option;
    }

    /**
     * Metodo que permite mostrar un mensaje.
     * @param message Recibe por parametro una cadena como mensaje.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Metodo que permite mostar mensaje de error.
     * @param message Recibe por parametro una cadena como mensaje.
     */
    public void showMessageErr(String message) {
        System.err.println(message);
    }

    /**
     * Metodo que permite leer un entero.
     * @param message Recibe por parametro una cadena como mensaje.
     * @return Devuelve un entero ingresado por Scanner.
     */
    public int readInt(String message) {
        System.out.println(message);
        return Integer.parseInt((sc.nextLine()));
    }

    /**
     * Metodo que permite leer una cadena.
     * @param message Recibe por parametro una cadena como mensaje.
     * @return Devuelve una cadena ingresada por Scanner.
     */
    public String readString(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    /**
     * Metodo que permite leer un caracter.
     * @param message Recibe por parametro una cadena como mensaje.
     * @return Devuelve Character ingresado por Scanner.
     */
    public Character readChar(String message) {
        System.out.println(message);
        return sc.nextLine().charAt(0);
    }

    /**
     * Metodo que permite selccionar una dirección (Derecha o Izquierda).
     * @param message Recibe por parametro una cadena como mensaje.
     * @return Devuelve una cadena indecando la dirección selecionada (Derecha o Izquierda).
     */
    public String selecDirection(String message) {
        int option = 0;
        System.out.println(message);
        System.out.println("[1] Derecha");
        System.out.println("[2] Izquierda");
        option = Integer.parseInt( sc.nextLine() );
        if (option < 1 || option > 2) {
            showMessageErr("Opción invalida");
        }

        return option == 1 ? "Derecha" : "Izquierda";
    }

    /**
     * Metodo que permite leer los elementos de un ArrayList de tipo String.
     * @param sentence Recibe por parametro un ArrayList<String>.
     */
    public void readArr(ArrayList<String> sentence) {
        sentence.forEach(phrase -> System.out.print(phrase));
        System.out.println();
    }

    /**
     * Metodo que permite mostar el mensaje de validación de la direccion de correo.
     * @param isTrue Recibe por parametro un boolean.
     */
    public void showCheckEmail(boolean isTrue) {
        System.out.println( isTrue ? "Dirección de correo VALIDA" : "Dirección de correo INVALIDA");
    }
}
