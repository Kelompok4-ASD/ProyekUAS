// SearchService.java

import java.util.Scanner;

public class SearchService {

    static Scanner sc = new Scanner(System.in);

    // cari berdasarkan nama

    public static void cariNama() {
        System.out.println("\n CARI PRODUK BERDASARKAN NAMA (Linear Search)");
        System.out.print("  Masukkan kata kunci nama: ");
        String keyword = sc.nextLine().toLowerCase();

        System.out.println("\n  Hasil pencarian untuk \"" + keyword + "\":");
        StokManager.printHeader();

        int count = 0;

        for (int i = 0; i < StokManager.jumlahData; i++) {
            Produk p = StokManager.data[i];
            if (p.aktif && p.nama.toLowerCase().contains(keyword)) {
                StokManager.printRow(p);
                count++;
            }
        }

        if (count == 0) System.out.println("  (Tidak ada produk yang cocok)");
        else System.out.println("  Ditemukan: " + count + " produk.");
    }

    // cari berdasarkan ID

    public static void cariId() {
        System.out.println("\n CARI PRODUK BERDASARKAN ID (Binary Search)");
        System.out.print("  Masukkan ID yang dicari: ");
        int targetId = sc.nextInt();
        sc.nextLine();

        // index produk yang aktif
        int[] idx = new int[StokManager.jumlahData];
        int n = 0;
        for (int i = 0; i < StokManager.jumlahData; i++) {
            if (StokManager.data[i].aktif) idx[n++] = i;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (StokManager.data[idx[j]].id > StokManager.data[idx[j + 1]].id) {
                    int tmp  = idx[j];
                    idx[j]   = idx[j + 1];
                    idx[j + 1] = tmp;
                }
            }
        }

        // Binary Search
        int kiri  = 0;
        int kanan = n - 1;
        int hasil = -1;

        while (kiri <= kanan) {
            int tengah = (kiri + kanan) / 2;
            int idTengah = StokManager.data[idx[tengah]].id;

            if (idTengah == targetId) {
                hasil = idx[tengah];
                break;
            } else if (idTengah < targetId) {
                kiri = tengah + 1;   // cari di separuh kanan
            } else {
                kanan = tengah - 1;  // cari di separuh kiri
            }
        }

        if (hasil == -1) {
            System.out.println("\n  [!] Produk dengan ID " + targetId + " tidak ditemukan.");
        } else {
            System.out.println("\n  Produk ditemukan:");
            StokManager.printHeader();
            StokManager.printRow(StokManager.data[hasil]);
        }
    }

    // cari berdasarkan kategori

    public static void cariKategori() {
        System.out.println("\n CARI PRODUK BERDASARKAN KATEGORI");
        System.out.print("  Masukkan nama kategori: ");
        String keyword = sc.nextLine().toLowerCase();

        System.out.println("\n  Produk dalam kategori \"" + keyword + "\":");
        StokManager.printHeader();

        int count = 0;
        for (int i = 0; i < StokManager.jumlahData; i++) {
            Produk p = StokManager.data[i];
            if (p.aktif && p.kategori.toLowerCase().contains(keyword)) {
                StokManager.printRow(p);
                count++;
            }
        }

        if (count == 0) System.out.println("  (Tidak ada produk dalam kategori tersebut)");
        else System.out.println("  Ditemukan: " + count + " produk.");
    }
}