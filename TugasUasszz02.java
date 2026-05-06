// Produk.java

public class Produk {

    // ================================================================
    // FIELDS — menyimpan semua data satu produk
    // ================================================================

    public int     id;
    public String  nama;
    public String  kategori;
    public int     stok;
    public double  harga;
    public boolean aktif; // false = soft deleted

    // ================================================================
    // CONSTRUCTOR
    // ================================================================

    public Produk(int id, String nama, String kategori, int stok, double harga) {
        this.id       = id;
        this.nama     = nama;
        this.kategori = kategori;
        this.stok     = stok;
        this.harga    = harga;
        this.aktif    = true; // default aktif saat dibuat
    }

    // ================================================================
    // HELPER — format rupiah tanpa library
    // ================================================================

    public String hargaFormatted() {
        String s = Long.toString((long) harga);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) sb.insert(0, '.');
            sb.insert(0, s.charAt(i));
            count++;
        }
        return "Rp" + sb.toString();
    }

    // ================================================================
    // HELPER — potong string jika terlalu panjang
    // ================================================================

    public static String truncate(String s, int max) {
        if (s.length() <= max) return s;
        return s.substring(0, max - 2) + "..";
    }
}
