public class Person {
    private String id;
    private String nama;
    private int tahunLahir;
    private String kategori;
    private int kebugaran;

    // setter and getter for id
    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    // setter and getter for nama
    public void setNama(String nama) {this.nama = nama;}
    public String getNama() {return nama;}

    // setter and getter for tahunLahir
    public void setTahunLahir(int tahunLahir) {this.tahunLahir = tahunLahir;}
    public int getTahunLahir() {return tahunLahir;}

    // setter and getter for kategori
    public void getKategori(String kategori) {this.kategori = kategori;}
    public String getKategori() {return kategori;}

    // setter and getter kebugaran
    public void setKebugaran(int kebugaran) {this.kebugaran = kebugaran;}
    public int getKebugaran() {return kebugaran;}
};