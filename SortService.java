// SortService.java

public class SortService {

    // bubble sort urutkan ID Ascending

    public static void sortById() {
        System.out.println("\n URUTKAN BERDASARKAN ID ASCENDING (Bubble Sort)");

        int n = StokManager.jumlahData;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Jika ID kiri lebih besar dari ID kanan, tukar posisinya
                if (StokManager.data[j].id > StokManager.data[j + 1].id) {
                    StokManager.swap(j, j + 1);
                }
            }
        }

        System.out.println("  [v] Data berhasil diurutkan berdasarkan ID (Ascending).");
        StokManager.tampilkanSemua();
    }

    // selection short urutkan nama (A-Z)

    public static void sortByNama() {
        System.out.println("\n URUTKAN BERDASARKAN NAMA A-Z (Selection Sort)");

        int n = StokManager.jumlahData;

        for (int i = 0; i < n - 1; i++) {

            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (StokManager.data[j].nama.compareToIgnoreCase(StokManager.data[minIdx].nama) < 0) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                StokManager.swap(i, minIdx);
            }
        }

        System.out.println("  [v] Data berhasil diurutkan berdasarkan Nama (A-Z).");
        StokManager.tampilkanSemua();
    }

    // insertion short urutkan stok terbanyak (Descending)

    public static void sortByStok() {
        System.out.println("\nURUTKAN BERDASARKAN STOK TERBANYAK (Insertion Sort)");

        int n = StokManager.jumlahData;

        for (int i = 1; i < n; i++) {
            // Simpan elemen ke-i sebagai "key" yang akan disisipkan
            Produk key = StokManager.data[i];
            int j = i - 1;

            // Geser elemen yang stoknya lebih kecil dari key ke kanan
            while (j >= 0 && StokManager.data[j].stok < key.stok) {
                StokManager.data[j + 1] = StokManager.data[j];
                j--;
            }

            StokManager.data[j + 1] = key;
        }

        System.out.println("  [v] Data berhasil diurutkan berdasarkan Stok Terbanyak (Descending).");
        StokManager.tampilkanSemua();
    }
}