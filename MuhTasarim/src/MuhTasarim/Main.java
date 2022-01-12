package MuhTasarim;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner k = new Scanner(System.in);
        String kullanici;

        VeriTabani vt = new VeriTabani();
        KullaniciVeriTabani kvt = new KullaniciVeriTabani();

        Admin a1 = new Admin(1, "Ahmet", "123456");
        Admin a2 = new Admin(2, "Ayşe", "151515");

        Personel p1 = new Personel(3, "Ali", "353535");
        Personel p2 = new Personel(4, "Zeynep", "zeynep123");
        Personel p3 = new Personel(5, "Fatih", "fatih");
        Personel p4 = new Personel(6, "Barış", "barış123");
        Personel p5 = new Personel(7, "Gülce", "gülce");
        Personel p6 = new Personel(8, "Sercan", "istanbul");

        kvt.listElemanlari();
        kvt.Listele();

        System.out.println("Personel Girişi İçin İsim ve Şifre Girin");

        while (true) {
            System.out.print("İsim: \n");
            String isim = k.next();
            System.out.println("Şifre: ");
            String sifre = k.next();

            if (kvt.Onay(isim, sifre)) {
                kullanici = isim;
                break;
            }
        }

        vt.islemYap();
    }
}
