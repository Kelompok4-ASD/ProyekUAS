public class StatistikService {

    public static void tampilkanStatistik() {
        System.out.println("\n STATISTIK DATA PRODUK");
        System.out.println("================================================");

        int totalProduk = 0;
        int totalStok = 0;
        int stokTertinggi = -1;
        int stokTerendah = Integer.MAX_VALUE;
        double hargaTertinggi = -1;
        double hargaTerendah = Double.MAX_VALUE;
        String namaStokTertinggi = "";
        String namaStokTerendah = "";
        String namaHargaTertinggi = "";
        String namaHargaTerendah = "";

        for (int i = 0; i < StokManager.jumlahData; i++) {
            Produk p = StokManager.data[i];
            if (!p.aktif) continue;

            totalProduk++;
            totalStok += p.stok;

            if (p.stok > stokTertinggi) {
                stokTertinggi = p.stok;
                namaStokTertinggi = p.nama;
            }
            if (p.stok < stokTerendah) {
                stokTerendah = p.stok;
                namaStokTerendah = p.nama;
            }
            if (p.harga > hargaTertinggi) {
                hargaTertinggi = p.harga;
                namaHargaTertinggi = p.nama;
            }
            if (p.harga < hargaTerendah) {
                hargaTerendah = p.harga;
                namaHargaTerendah = p.nama;
            }
        }

        if (totalProduk == 0) {
            System.out.println("  (Tidak ada data produk aktif)");
            return;
        }

        double rataStok = (double) totalStok / totalProduk;

        System.out.println("  Total Produk Aktif  : " + totalProduk);
        System.out.println("  Total Stok          : " + totalStok);
        System.out.printf( "  Rata-rata Stok      : %.2f%n", rataStok);
        System.out.println("------------------------------------------------");
        System.out.println("  Stok Terbanyak      : " + namaStokTertinggi + " (" + stokTertinggi + ")");
        System.out.println("  Stok Tersedikit     : " + namaStokTerendah + " (" + stokTerendah + ")");
        System.out.println("------------------------------------------------");
        System.out.println("  Harga Tertinggi     : " + namaHargaTertinggi + " (Rp" + (long) hargaTertinggi + ")");
        System.out.println("  Harga Terendah      : " + namaHargaTerendah + " (Rp" + (long) hargaTerendah + ")");
        System.out.println("================================================");
    }
}
