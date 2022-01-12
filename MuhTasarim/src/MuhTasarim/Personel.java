package MuhTasarim;

public class Personel extends Calisan{


    KullaniciVeriTabani kvt = new KullaniciVeriTabani();
    
    İnsan lambda;
    //lambda fonksiyonu maddesi burada kullanıldı.
    //Personel nesnesi oluşturuldu.
    Personel(int id, String isim,String sifre) {
        this.lambda = () -> {
            this.yetki = "Personel";
        };
        lambda.yetkiVer();
        this.id = id;
        this.isim = isim;
        this.sifre=sifre;
        kvt.Ekle(id, isim, yetki, maas, sifre);
    }
//Admin'den farklı değer döndüren fakat aynı method olan maasVer() ile polimorfizm kullanıldı
    @Override
    public void maasVer(){
       this.maas=5000; 
    }
   


}
