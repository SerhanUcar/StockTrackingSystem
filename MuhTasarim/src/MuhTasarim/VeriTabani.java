package MuhTasarim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class VeriTabani {

    static Connection con,con1,con2;
    static Statement stmt,stmt1,stmt2;
    String vtAd;
//Veri tabanı oluşturuldu ve Generic Collections'dan HashSet kullanıldı.    
    Set<Urun> urunSet;
    
    VeriTabani() {
        vtAd = "StokKontroll.db";
        urunSet=new HashSet<>();
        /*veritabanı yoksa oluşturur varsa bağlanır*/
        try {
            yeniVtOlustur(vtAd);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
//Veri tabanı oluşturur ve java ile bağlantısını sağlar
    public void yeniVtOlustur(String dosyaadi) throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            
            con1 = DriverManager.getConnection("jdbc:sqlite:EDevlet.db");
            stmt1 = con1.createStatement();
            String sql1 = ("CREATE TABLE if not exists EDevlet "
                    + "(VTİsim CHAR(50) PRIMARY KEY NOT NULL) ");
            stmt1.executeUpdate(sql1);
            System.out.println("E-Devlet Veritabanı Oluşturma Başarılı");
            String sorgu = String.format("insert into EDevlet values(%s)", "StokKontroll");
            
           
            
            con = DriverManager.getConnection("jdbc:sqlite:StokKontroll.db");
            stmt = con.createStatement();
            String sql = ("CREATE TABLE if not exists StokKontroll "
                    + "(UrunBarkodNo INT PRIMARY KEY NOT NULL, "
                    + "UrunAd CHAR(50) NOT NULL, "
                    + "UrunAdedi INT NOT NULL,"
                    + "UrunFiyati INT NOT NULL)");
            stmt.executeUpdate(sql);
            System.out.println("Veritabanı Oluşturma Başarılı");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//Menü oluşturur
    public void islemYap() {

        Scanner scan = new Scanner(System.in, "iso-8859-9");
        int secim;
        
        while(true){
            System.out.println("E-Devlet Veri Tabanından Seçmek İstediğiniz Mağazayı Seçiniz: ");
            System.out.println("1)Şok Market");
            secim=scan.nextInt();
            if (secim==1) {
                System.out.println("Şok Market Seçenekleri Listeleniyor");
                break;
            } else System.out.println("Lütfen Geçerli Bir Seçim Yapınız.");
            
        }
        
        while (true) {
            System.out.println("*************");
            System.out.println("1.Listele");
            System.out.println("2.Ekle");
            System.out.println("3.Güncelle");
            System.out.println("4.Sil");
            System.out.println("5.Çıkış");
            System.out.print("Seçiminiz:");
            secim = scan.nextInt();

            System.out.println("*************");

            if (secim == 1) {
                Listele();
            }
            if (secim == 2) {
                Ekle();
            }
            if (secim == 3) {
                Guncelle();
            }
            if (secim == 4) {
                Sil();
            }
            if (secim == 5) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
            }
        }
    }
//Veri tabanındakileri listeler
    public void Listele() {
        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from StokKontroll");
            while (rs.next()) {
                System.out.println("Barkod No:" + rs.getInt(1) + "\t Ürün Adı: " + rs.getString(2) + "\t Ürün Adedi: " + rs.getInt(3) + "\t Ürün Fiyatı: " + rs.getInt(4) + "TL");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
//Veri tabanına ekler
    public void Ekle() {
        
        Urun u=new Urun();
        
        Scanner scan = new Scanner(System.in, "iso-8859-9");
        System.out.print("Ürün Barkod No: ");
        int UrunBarkodNo = scan.nextInt();
        u.BarkodNo=UrunBarkodNo;
        System.out.print("Ürün Adı: ");
        String UrunAd = scan.next();
        u.UrunAd=UrunAd;
        System.out.print("Ürün Adeti: ");
        int UrunAdet = scan.nextInt();
        u.Adet=UrunAdet;
        System.out.print("Ürün Fiyatı:");
        int UrunFiyat = scan.nextInt();
        u.Fiyat=UrunFiyat;
        urunSet.add(u);
        
        try {
            Statement stmt = con.createStatement();
            String sorgu = String.format("insert into StokKontroll values( %d, '%s','%d', '%d')", UrunBarkodNo, UrunAd, UrunAdet, UrunFiyat);
            int ekleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıt Eklendi");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
//Veri tabanındaki bir malzemeyi günceller
    public void Guncelle() {
        Scanner scan = new Scanner(System.in, "iso-8859-9");
        try {
            Listele();
            System.out.print("Barkod Numarasını Girin: ");
            int EskiBarkodNo = scan.nextInt();
            System.out.print("Yeni Barkod Numarası: ");
            int YeniBarkodNo = scan.nextInt();
            System.out.print("Yeni Ürün Adı: ");
            String YeniUrunAd = scan.next();
            System.out.print("Yeni Ürün Adedi: ");
            int YeniUrunAdet = scan.nextInt();
            System.out.print("Yeni Fiyat: ");
            int YeniUrunFiyat = scan.nextInt();

            String sorgu = String.format("update StokKontroll set UrunBarkodNo='" + YeniBarkodNo + "', UrunAd='" + YeniUrunAd + "', UrunAdedi='" + YeniUrunAdet
                    + "'" + ", UrunFiyati='" + YeniUrunFiyat + "'"
                    + " WHERE UrunBarkodNo='%d'", EskiBarkodNo);

            Statement stmt = con.createStatement();
            int guncelleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıtlar Güncellendi");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//Veri tabanından bir malzeme siler
    public void Sil() {
        Scanner scan = new Scanner(System.in, "iso-8859-9");
        try {
            Listele();
            System.out.print("Barkod Numarası Girin: ");
            int SilinecekBarkodNo = scan.nextInt();

            String sorgu = "delete from StokKontroll where UrunBarkodNo=" + SilinecekBarkodNo;
            Statement stmt1 = con.createStatement();
            stmt1.executeUpdate(sorgu);
            System.out.println("Kayıtlar Silindi");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
