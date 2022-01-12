package MuhTasarim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KullaniciVeriTabani {

    static Connection con;
    static Statement stmt;
    String vtAd;
//Kullanıcı veri tabanı oluşturuldu ve ArrayList kullanıldı, ArrayList ve HashSet ile toplamda 2 Generic Collection da kullanılmış oldu ve madde sağlandı.
    List<Calisan> list;

    KullaniciVeriTabani() {
        this.list = new ArrayList<>();
        vtAd = "Personeller.db";
        /*veritabanı yoksa oluşturur varsa bağlanır*/
        try {
            yeniVtOlustur(vtAd);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
//Kullanıcıların tutulduğu veri tabanını oluşturur.
    public void yeniVtOlustur(String dosyaadi) throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:Personeller.db");
            stmt = con.createStatement();
            String sql = ("CREATE TABLE if not exists Personeller "
                    + "(PersonelID INT PRIMARY KEY NOT NULL, "
                    + "Personelİsim CHAR(50) NOT NULL, "
                    + "PersonelYetki CHAR(50) NOT NULL,"
                    + "PersonelMaas INT NOT NULL,"
                    + "PersonelSifre CHAR(50) NOT NULL)");
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//Kullanıcıları listeler
    public void Listele() {
        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Personeller");

            while (rs.next()) {
                System.out.println("PersonelNo:" + rs.getInt(1) + "\t Personel Adı: " + rs.getString(2) + "\t PersonelYetki: " + rs.getString(3) + "\t\t Personel Maaş: " + rs.getInt(4));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
//Kullanıcı veri tabanına personel ekler
    public void Ekle(int PersonelNo, String PersonelAd, String PersonelYetki, int PersonelMaas, String PersonelSifre) {

        try {
            Calisan c = new Calisan();
            c.id = PersonelNo;
            c.isim = PersonelAd;
            c.yetki = PersonelYetki;
            c.maas = PersonelMaas;
            c.sifre = PersonelSifre;
            list.add(c);

            Statement stmt = con.createStatement();
            String sorgu = String.format("insert into Personeller values( %d, '%s','%s','%d','%s')", PersonelNo, PersonelAd, PersonelYetki, PersonelMaas, PersonelSifre);
            int ekleme = stmt.executeUpdate(sorgu);

        } catch (Exception e) {
        }

    }
//Kullanıcı veritabanından personel siler
    public void Sil(int PersonelNo) {
        try {

            String sorgu = "delete from Personeller where PersonelNo=" + PersonelNo;
            Statement stmt1 = con.createStatement();
            stmt1.executeUpdate(sorgu);
            System.out.println("Kayıtlar Silindi");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
//Kullanıcı veri tabanında bir personel günceller
    public void Guncelle() {
        Scanner scan = new Scanner(System.in, "iso-8859-9");
        try {
            System.out.print("Personel No Girin: ");
            int no = scan.nextInt();
            System.out.print("Yeni Personel No: ");
            int YeniPersonelNo = scan.nextInt();
            System.out.print("Yeni Personel Adı: ");
            String YeniPersonelAd = scan.next();
            System.out.print("Yeni Personel Şifresi: ");
            int YeniSifre = scan.nextInt();

            String sorgu = String.format("update Personeller set PersonelID='" + YeniPersonelNo + "', Personelİsim='" + YeniPersonelAd + "', PersonelSifre='" + YeniSifre
                    + " WHERE PersonelID='%d'", no + ";");

            Statement stmt = con.createStatement();
            int guncelleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıtlar Güncellendi");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//Kullanıcının giriş yapmasını sağlar, şifre ve id eşleşiyorsa giriş yapılır
    public boolean Onay(String isim, String sifre) throws SQLException {
        Boolean onay = false;

        Statement stmt = con.createStatement();
        String sorgu = "SELECT Personelİsim,PersonelSifre FROM Personeller WHERE Personelİsim='" + isim + "' AND PersonelSifre='" + sifre + "' ;";
        ResultSet rs = stmt.executeQuery(sorgu);

        try {
            if (rs.getString(1).equals(isim) && rs.getString(2).equals(sifre)) {
                onay = true;
                System.out.println("Giriş Yapıldı.");
                return onay;
            }
        } catch (Exception e) {
            System.out.println("Giriş Yapılamadı.");
        }
        return onay;
    }

    public void listElemanlari() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).isim);
        }
    }
}
