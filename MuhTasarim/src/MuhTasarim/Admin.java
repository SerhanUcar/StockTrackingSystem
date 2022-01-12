package MuhTasarim;
//Admin de bir çalışan olduğu için calisan dan inheritance almıştır.
public class Admin extends Calisan {

    KullaniciVeriTabani kvt = new KullaniciVeriTabani();

    İnsan lambda;
//lambda fonksiyonu kullanılmıştır
    Admin(int id, String isim, String sifre) {
        this.lambda = () -> {
            this.yetki = "Admin";
        };
        lambda.yetkiVer();
        this.id = id;
        this.isim = isim;
        this.sifre = sifre;
        kvt.Ekle(id, isim, yetki, maas, sifre);
    }
    //Polimorfizm kullanıldı.
    @Override
    public void maasVer(){
       this.maas=10000; 
    }

}
