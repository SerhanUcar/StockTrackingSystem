package MuhTasarim;
//İnsan sinifindan inheritance alir ve inheritance maddesi sağlanmış olur
public class Calisan implements İnsan {

    String yetki;
    String sifre;
    String isim;
    int id;
    int maas;
    
    Calisan(){
        yetkiVer();
        maasVer();
    }
    //Admin sınıfında da bulunan yetkiVer() ve maasVer() metodlarında farklı işlemler yapıldığı için polimorfizm maddesi burada kullanılmıştır.
    @Override
    public void yetkiVer() {
        this.yetki = "Çalışan";
    }
    
    public void maasVer(){
        this.maas=7500;
    }

}
