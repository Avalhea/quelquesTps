import java.sql.Date;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Scanner;
import javax.print.event.PrintJobAdapter;
import javax.rmi.ssl.SslRMIClientSocketFactory;

public class gregorianCalendar {
    static Scanner scan = new Scanner(System.in);
    static String[] calendrierJours;
    static int[][] calendrierNombres;

    public static void afficherMois(int moisChoisie, int anneeChoisie) {

        switch (moisChoisie) {
        case 0:
            System.out.println("    * Janvier " + anneeChoisie + " *" + " (4)");
            break;
        case 1:
            System.out.println("   * Février " + anneeChoisie + " *" + " (4)");
            break;
        case 2:
            System.out.println("   * Mars " + anneeChoisie + " *" + " (4)");
            break;
        case 3:
            System.out.println("   * Avril " + anneeChoisie + " *" + " (4)");
            break;
        case 4:
            System.out.println("   * Mai " + anneeChoisie + " *" + " (4)");
            break;
        case 5:
            System.out.println("   * Juin " + anneeChoisie + " *" + " (4)");
            break;
        case 6:
            System.out.println("   * Juillet " + anneeChoisie + " *" + " (4)");
            break;
        case 7:
            System.out.println("   * Août " + anneeChoisie + " *" + " (4)");
            break;
        case 8:
            System.out.println("   * Septembre " + anneeChoisie + " *" + " (4)");
            break;
        case 9:
            System.out.println("   * Octobre " + anneeChoisie + " *" + " (4)");
            break;
        case 10:
            System.out.println("   * Novembre " + anneeChoisie + " *" + " (4)");
            break;
        case 11:
            System.out.println("   * Décembre " + anneeChoisie + " *" + " (4)");
            break;
        }

    }

    public static String[] afficherJoursCal() { // affichage des jours en lettres
        calendrierJours = new String[7];

        for (int i = 0; i < 7; i++) {
            {
                switch (i) {
                case 0:
                    calendrierJours[i] = "L   ";
                    break;
                case 1:
                    calendrierJours[i] = "Ma  ";
                    break;
                case 2:
                    calendrierJours[i] = "Me  ";
                    break;
                case 3:
                    calendrierJours[i] = "J   ";
                    break;
                case 4:
                    calendrierJours[i] = "V   ";
                    break;
                case 5:
                    calendrierJours[i] = "S   ";
                    break;
                case 6:
                    calendrierJours[i] = "D   ";
                    break;
                }
                System.out.print(calendrierJours[i]);

            }
        }
        return calendrierJours;

    }

    public static int[][] afficherNombresCal(int moisChoisie, int anneeChoisie) { // répartition des jours selon jour de // la semaine
        calendrierNombres = new int[6][7];
        int jour = 1;

        for (int i = calendar.get(Calendar.DAY_OF_WEEK)-1; i < 7; i++) {

            calendrierNombres[0][i] = jour;
            jour = jour + 1;

        }

        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (jour <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    calendrierNombres[i][j] = jour;
                    jour = jour + 1;
                }
            }
        }
        for (int ii = 0; ii < 6; ii++) {
            for (int jj = 0; jj < 7; jj++) {
                if (calendrierNombres[ii][jj] < 10 && calendrierNombres[ii][jj] > 0) {
                    System.out.print(calendrierNombres[ii][jj] + "   ");
                } else if (calendrierNombres[ii][jj] == 0) {
                    System.out.print("    ");
                } else {
                    System.out.print(calendrierNombres[ii][jj] + "  ");
                }
            }
            System.out.println();
        }

        return calendrierNombres;
    }

    public static void choixMois(int moisChoisie, int commande) {
        int year = 4000;
        int mois = 100;
        if (commande == 1) {
            if (moisChoisie == 0) {
                calendar.add(Calendar.YEAR, -1);
                calendar.set(Calendar.MONTH, 11);
            } else {
                calendar.add(Calendar.MONTH, -1);
            }
        }
        if (commande == 3) {
            if (moisChoisie == 11) {
                calendar.add(Calendar.YEAR, +1);
                calendar.set(Calendar.MONTH, 0);
            } else {
                calendar.add(Calendar.MONTH, +1);
            }
        }
        if (commande == 4) {
            while (year < 1853 || year > 2100) {
                System.out.println("Entrez l'année :");
                year = scan.nextInt();
            }
            calendar.set(Calendar.YEAR, year);
            while (mois < 0 || mois > 11) {
                System.out.println("Entrez le mois :");
                mois = scan.nextInt();
            }
            calendar.set(Calendar.MONTH, mois - 1);

        }
    }

    public static Calendar calendar = GregorianCalendar.getInstance();

    static public int commandeUser;

    public static void main(String[] args) {
        do {
            int chosenMonth = calendar.get(Calendar.MONTH);
            int chosenYear = calendar.get(Calendar.YEAR);

            commandeUser = 10;
            afficherMois(chosenMonth, chosenYear);
            System.out.println("");
            afficherJoursCal();
            System.out.println("");
            afficherNombresCal(chosenMonth, chosenYear);
            System.out.println("");

            while (commandeUser != 1 && commandeUser != 2 && commandeUser != 3 && commandeUser != 4) {
                System.out.println("<- (1)   STOP (2)   (3) ->");

                commandeUser = scan.nextInt();
            }
            if (commandeUser == 2) {
                ;
                break;
            }
            choixMois(chosenMonth, commandeUser);

        } while (commandeUser != 2);
    }
}