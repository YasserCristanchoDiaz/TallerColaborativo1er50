package controller;

import model.Features;
import view.View;

/**
 * Clase Control: en esta clase se controlan las clases que intervienen en el programa.
 * @TallerColavorativo1er50
 */
public class Control {
    private Features features;
    private View view;

    /**
     * Metodo contructor: define las instancias de la clase Features y View,
     * y se ejecuta el método menu().
     */
    public Control() {
        features = new Features();
        view = new View();
    }

    /**
     * Metodo que permite dar una organizacion a la ejecucion del programa segun un menu de opciones.
     */
    public void menu() {
        boolean check = true;

        while ( check ) {
            int option = 0;
            try {
                view.showMessage("------ Cadena por defecto: " + features.getMs() + " ------");
                option = view.readOptionMenu();
            } catch (Exception e) {
                view.showMessageErr("Dato no valido");
            }
            switch ( option ) {
                case 1:
                    view.showMessage( features.convertProperName() );
                    break;
                case 2:
                    view.showMessage("La cadena esta " + features.findString( view.readString("Escriba la cadena a buscar")) + " veces");
                    break;
                case 3:
                    view.showMessage( features.encrypt( view.readString("Digite cadena a encriptar") ) );
                    break;
                case 4:
                    view.showMessage( features.decrypt( view.readString("Digite cadena a desencriptar") ) );
                    break;
                case 5:
                    char character = view.readChar("Digite el caracter que desea añadir");
                    int number = view.readInt("Digite el numero de veces que se va repetir el caracter");
                    String direction = view.selecDirection("Selecione la dirección que desea");
                    view.showMessage( features.fillChars(character, number, direction) );
                    break;
                case 6:
                    view.showMessage( features.cleanChar(view.readChar("Digite el caracter que desea borrar")));
                    break;
                case 7:
                    view.showMessage( features.intersection(view.readString("Digite la cadena")));
                    break;
                case 8:
                    view.showMessage( features.difference(view.readString("Digite la cadena")));
                    break;
                case 9:
                    view.readArr( features.deleteCharsDir(view.readString("Digite una cadena"), view.selecDirection("Seleccione una dirección")) );
                    break;
                case 10:
                    view.showCheckEmail( features.checkEmail(view.readString("Digite dirección de correo")));
                    break;
                case 11:
                    view.showMessage("Cerrando programa...");
                    check = false;
                    break;
            }
        }
    }

}
