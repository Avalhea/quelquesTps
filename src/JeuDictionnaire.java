import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.sql.rowset.spi.SyncResolver;

public class JeuDictionnaire {
    static int NOMBREMOT = 22506;
    static char[] tableauDef;
    static char[] motMelange;
    static char[] t;
    static Scanner scan = new Scanner(System.in);
    static Scanner scan2 = new Scanner(System.in);

    public static void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static char[] motRandom() throws Exception {

        BufferedReader in = new BufferedReader(new FileReader("dictionnaire.txt"));

        Random random = new Random();
        int numMotChoisit = random.nextInt(NOMBREMOT);
        String poubelle;

        for (int i = 0; i < NOMBREMOT; i++) {
            if (numMotChoisit == i) {
                t = in.readLine().toUpperCase().toCharArray();

            } else {
                poubelle = in.readLine();
            }

        }
        return t;
    }

    public static char[] melanger(char[] melange) {

        for (int mot = 0; mot < melange.length; mot++) {
            for (int i = 1; i < melange.length; i++) {
                int random1 = (int) (Math.random() * (melange.length));
                int random2 = (int) (Math.random() * (melange.length));
                char lettreRandom = melange[random1];
                melange[random1] = melange[random2];
                melange[random2] = lettreRandom;
            }
        }
        return melange;
    }

    public static boolean compare(char[] tableauComparatif, String essai) {

        char[] tEssai = essai.toCharArray();

        char[] copie = Arrays.copyOf(tableauDef, tableauDef.length);
        int length = 0;
        System.out.println(copie);
        boolean valides = false;

        for (int i = 0; i < tEssai.length; i++) {
            for (int j = 0; j < tableauComparatif.length; j++) {

                if (tEssai[i] == copie[j]) {
                    length = length + 1;
                    copie[j] = '#';
                    ;
                    break;
                }

            }
        }

        if (tEssai.length == length) {
            System.out.println("Les lettres utilisées sont valides.");
            valides = true;
        } else {
            System.out.println("N'utilisez que les lettres affichées !!");
        }

        return valides;
    }

    public static boolean dansLeDico(String essai) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader("dictionnaire.txt"));
        String line;
        boolean dansLeDico = false;

        while ((line = in.readLine()) != null) {

            if (line.equals(essai.toLowerCase())) {
                System.out.println("...");
                pause();
                System.out.println("Le mot est bien dans le dico!");
                dansLeDico = true;
                break;
            }
        }
        if (dansLeDico == false) {
            pause();
            System.out.println("...");
            pause();
            System.out.println("Ce mot n'existe pas... Réessaye !");
        }
        return dansLeDico;
    }

    public static boolean dico;
    static int STOP = 1;
    public static int cStop;
    public static int coucou = 0;
    public static int Nbtentative;

    public static void main(String[] args) throws Exception {

        do {
            Nbtentative = 1;
            boolean lettresOk = false;

            String tentative;
            if (coucou == 0) {
                System.out.println("Coucou!");
            } else {
                System.out.println("C'est repartit!!!!");
            }
            pause();
            System.out.println(

                    "Essaye de former un mot avec les lettres suivantes;  (n'utilise bien que les lettres proposées!)");
            pause();
            tableauDef = motRandom();
            char[] tableauComparatif = Arrays.copyOf(tableauDef, tableauDef.length);
            // System.out.println(tableauDef);
            String motATrouver = new String(tableauDef);
            motMelange = melanger(tableauDef);
            System.out.println(tableauDef);
            do {
                System.out.println("Tentative n°" + Nbtentative + ":");
                tentative = scan.nextLine().toUpperCase();
                lettresOk = compare(tableauComparatif, tentative);

                if (lettresOk == true) {
                    dico = dansLeDico(tentative);

                    if (dico == true) {
                        if (motATrouver.equals(tentative)) {
                            pause();
                            System.out.println("Bingo !!!!! C'était le plus long mot!");
                        } else {
                            pause();
                            System.out.println("Bravo ! Mais ce n'était pas le mot à trouver!!!! Le mot à trouver était : " + motATrouver);
                        }
                    }
                }
                Nbtentative = Nbtentative + 1;
            } while ((lettresOk != true || dico != true) && Nbtentative <= 5);

            if (lettresOk == false || dico == false){
                System.out.println("Dommage. Le mot à trouver était : " + motATrouver);
            }
            pause();
            System.out.println("Voulez vous rejouer ?");
            pause();
            System.out.println("1. Oui");
            System.out.println("2. Non");
            cStop = scan2.nextInt();
            coucou = coucou + 1;
        } while (cStop == STOP);
        System.out.println("Merci d'avoir joué!");
    }
}
