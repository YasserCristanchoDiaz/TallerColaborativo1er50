package model;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase Features: En esta clase estan definidos los métodos que permiten dar la funcionalidad del programa.
 * @TallerColavorativo1er50
 */
public class Features {
    /**
     * ms: Permite almacenar la cadena por defecto, la cual es la referencia para la mayoria de los metodos.
     */
    private String ms;
    /**
     * key: Permite almacenar una llave de tipo String para el metodo createPassword().
     */
    private static String key;

    /**
     * Metodo constructor: Define las variables de tipo String ms y key.
     */
    public Features() {
        ms = "Sogamoso ciudad del sol y del acero";
        key = "UPTC-2021";
    }

    public String getMs() {
        return ms;
    }

    /**
     * convertProperame: Convierte las palabras de la cadena ms en nombres priopios.
     * @return Cadena con nombres propios.
     */
    public String convertProperName() {
        Character c = ' ';
        char[] chars = new char[ms.length()];

        for (int i = 0; i < ms.length(); i++) {
            c = ms.charAt(i);
            if ( (i == 0) || (ms.charAt(i-1) == ' ') ) {
                c = Character.toUpperCase(c);
            }

            chars[i] = c;
        }

        return new String(chars);
    }

    /**
     * findString: Permite buscar una cadena en la cadena ms, y cuenta el numero de veces que se encuentra.
     * @param input Recibe la cadena que se va a buscar dentro de la cadema ms.
     * @return int Devuelve el numero de veces que esta input dentro de ms.
     */
    public int findString(String input) {
        int cont = 0;
        String[] c = ms.toLowerCase().split(" ");
        for (int i = 0; i < c.length; i++) {
            if ( c[i].equals(input.toLowerCase()) ) {
                cont++;
            }
        }

        return cont;
    }

    /**
     * createPassword: Permite generar un SecretKeySpec para los metodos encrypt y decrypt.
     * @param key Toma la cadena key de referencia que pasa por parametro.
     * @return Retorna un SecretKeySpec.
     */
    private static SecretKeySpec createPassword(String key) {
        try {
            byte[] string = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            string = messageDigest.digest(string);
            string = Arrays.copyOf(string, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(string, "AES");
            return secretKeySpec;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * encrypt: Permite encriptar una cadena de entrada.
     * @param encrypt Recibe por parametro la cadena que se va a encriptar.
     * @return Devuelve la cadena encriptada.
     */
    public static String encrypt(String encrypt) {
        try {
            SecretKeySpec secretKeySpec = createPassword(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] string = encrypt.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(string);
            String encryptedString = Base64.getEncoder().encodeToString(encrypted);

            return encryptedString;

        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * decrypt: Permite desenciptar una cadena de entrada
     * @param decrypt Recibe por parametro la cadena que se desea desencriptar.
     * @return Devuelve la cadena original desencriptada.
     */
    public static String decrypt(String decrypt) {
        try {
            SecretKeySpec secretKeySpec = createPassword(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] string = Base64.getDecoder().decode(decrypt);
            byte[] decrypted = cipher.doFinal(string);
            String decryptedString = new String(decrypted);

            return decryptedString;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * fillChars: Permite concatenar la cadena ms con la direccion selecionada
     * y el numero de veces del caracter ingresado, entrados por parametro.
     * @param character Recibe por parametro el caracter que se desea concatenar.
     * @param number Recibe el numero de veces que se quiere repetir el caracter que se recibe por parametro.
     * @param direction Recibe la direccion (Derecha o Izquierda) a la que se va a concatenar.
     * @return Devuelve la cadena concatenada según los parametros de entrada.
     */
    public String fillChars(char character, int number, String direction){
        String chars = "", aux = "";
        for (int i = 0; i < number; i++) {
            chars += character;
        }
        if ( direction.equals("Derecha") ) {
            aux = ms + chars;
        } else {
            aux = chars + ms;
        }

        return aux;
    }

    /**
     * cleanChar: Elimina el caracter de entrada por parametro en la cadena ms.
     * @param character Recibe por parametro de el caracter que se desea eliminar de la cadena ms.
     * @return Devuelve la cadena borrando el caracter ingresado por parametro, si existe.
     */
    public String cleanChar(char character) {
        String aux = "";
        String c = "" + character;
        aux = ms.toLowerCase().replaceAll(c, "").replaceAll(c.toLowerCase(), "");
        return aux;
    }

    /**
     * intersection: Permite buscar los caracteres en comun entre la cadena de entrada por parametro y la cadena ms.
     * @param data Recibe por parametro la cadena que se va a comparar con la cadena ms.
     * @return Devuelve la cadena con la intersección de caracteres entre la cadena ingresada por parametro y la cadena ms.
     */
    public String intersection(String data) {
        String repeated = "";
        char[] dataChars = data.toCharArray();
        char[] msChars = ms.toCharArray();
        for (int i = 0; i < data.length(); i++) {
            for (int j = 0; j < ms.length(); j++) {
                String aux;
                aux = "" + dataChars[i];
                if (Character.toLowerCase(dataChars[i]) == Character.toLowerCase(msChars[j])) {
                    if (repeated.contains(aux)) {

                    } else {
                        repeated = repeated + dataChars[i];
                    }
                }
            }
        }
        return repeated;
    }

    /**
     * diference: permite encontrar los caracteres diferentes entre la cadena ms
     * y la cadena de entrada por parametro, y los elimina.
     * @param data Recibe por parametro la cadena que se va a comparar con la cadena ms.
     * @return Devuelve la cadena con la diferencia de caracteres entre la cadena ingresada por parametro y la cadena ms.
     */
    public String difference(String data) {
        ArrayList<Character> arr = new ArrayList<>();
        String aux = "";
        for (int i = 0; i < ms.length(); i++) {
            arr.add(i, ms.charAt(i));
        }
        for (int i = 0; i < data.length(); i++) {
            for (int j = 0; j < arr.size(); j++) {
                if (String.valueOf(data.charAt(i)).equalsIgnoreCase(String.valueOf(arr.get(j)))) {
                    arr.remove(j);
                }
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            aux += arr.get(i);
        }
        return aux;
    }

    /**
     * deleteCharsDir: Permite borrar por Derecha o por izquierda los caracteres en comun
     * hasta que no exista un caracter entre la cadena ms y la cadena de entrada por parametro.
     * @param data Recibe por parametro la cadena que se va a comparar con la cadena ms.
     * @param dir Recibe la direccion (Derecha o Izquierda) por donde se va a realizar la comparacion.
     * @return Devuelve ArrayList<String> con el listado de letras que no se eliminaron.
     */
    public ArrayList<String> deleteCharsDir(String data, String dir) {
        int con = 0;
        boolean direccion;
        String[] word = data.split("");
        con = word.length;
        String sentenceOne[] = ms.split("");
        String sentenceTwo[] = ms.split("");
        ArrayList<String> arr = new ArrayList<>();
        if ( dir.equals("Derecha") ) {
            direccion=false;
        }else {
            direccion=true;
        }
        if (direccion) {
            for (int i = 0; i < sentenceOne.length; i++) {
                for (int j = 0; j < word.length; j++) {
                    con = word.length;
                    if (sentenceOne[i].toLowerCase().contains(word[j])) {
                        sentenceOne[i]="/";
                    }
                }
                if (i>0) {
                    if (sentenceOne[i-1].contains("/")) {
                    }
                }
            }
            for (int k = 0; k < sentenceOne.length; k++) {
                if (!sentenceOne[k].contains("/")) {
                    con = k;
                    break;
                }
            }
            for (int j = con; j < sentenceTwo.length; j++) {
                arr.add(sentenceTwo[j]);
            }
            return arr;
        } else {
            for (int i = 0; i < sentenceOne.length; i++) {
                for (int j = 0; j < word.length; j++) {
                    con = word.length;
                    if (sentenceOne[i].toLowerCase().contains(word[j])|| sentenceOne[i].contains(" ")) {
                        sentenceOne[i]="/";
                    }
                }
                if (i>0) {
                    if (sentenceOne[i-1].contains("/")) {
                    }
                }
            }
            for (int k = sentenceOne.length-1; k >=0; k--) {
                if (!sentenceOne[k].contains("/")) {
                    con = k;
                    break;
                }
            }
            for (int j = 0; j < con + 1; j++) {
                arr.add(sentenceTwo[j]);
            }
            return arr;
        }
    }

    /**
     * checkEmail: permite validar una direccion de correo por medio de la cadena de entrada por parametro.
     * @param email Recibe por parametro la direccion de correo.
     * @return Devuelve un mesaje de tipo String indiando.
     */
    public boolean checkEmail(String email) {
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(email);
        return mat.matches();
    }
}
