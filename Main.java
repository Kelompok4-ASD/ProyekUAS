import java.util.Scanner;

public class Main {
   static Scanner sc = new Scanner(System.in);
   
   public static void main(String[] args) {

       StokManager.initDataAwal();
       int pilihan;
       do {
           System.out.println("\n|==============================================|");
           System.out.println("|     SISTEM MANAJEMEN STOK TOKO ELEKTRONIK    |");
           System.out.println("|==============================================|");
           System.out.println("|  [CRUD]                                      |");
           System.out.println("|  1. Tambah Produk Baru                       |");
           System.out.println("|  2. Tampilkan Semua Produk                   |");
           System.out.println("|  3. Edit Produk (berdasarkan ID)             |");
           System.out.println("|  4. Hapus Produk (Soft Delete)               |");
           System.out.println("|  5. Lihat Log Penghapusan                    |");
           System.out.println("|==============================================|");
           System.out.println("|  [SEARCHING]                                 |");
           System.out.println("|  6. Cari berdasarkan Nama (Linear Search)    |");
           System.out.println("|  7. Cari berdasarkan ID   (Binary Search)    |");
           System.out.println("|  8. Cari berdasarkan Kategori                |");
           System.out.println("|==============================================|");
           System.out.println("|  [SORTING]                                   |");
           System.out.println("|  9.  Urutkan ID Ascending  (Bubble Sort)     |");
           System.out.println("|  10. Urutkan Nama A-Z      (Selection Sort)  |");
           System.out.println("|  11. Urutkan Stok Terbanyak (Insertion Sort) |");
           System.out.println("|==============================================|");
           System.out.println("|  [STATISTIK]                                 |");
           System.out.println("|  12. Tampilkan Statistik Data                |");
           System.out.println("|==============================================|");
           System.out.println("|  [FILE]                                      |");
           System.out.println("|  13. Load Data dari File                     |");
           System.out.println("|  14. Simpan Data ke File                     |");
           System.out.println("|  0. Keluar                                   |");
           System.out.println("|==============================================|");
           System.out.print("  Pilih menu: ");
           pilihan = sc.nextInt();
           sc.nextLine();


           switch (pilihan) {
               case 1:  StokManager.tambah();  break;
               case 2:  StokManager.tampilkanSemua();  break;
               case 3:  StokManager.edit();  break;
               case 4:  StokManager.hapus(); break;
               case 5:  StokManager.lihatLog(); break;
               case 6:  SearchService.cariNama(); break;
               case 7:  SearchService.cariId(); break;
               case 8:  SearchService.cariKategori();  break;
               case 9:  SortService.sortById(); break;
               case 10: SortService.sortByNama(); break;
               case 11: SortService.sortByStok(); break;
               case 12: StatistikService.tampilkanStatistik(); break;
               case 13: LoadData.loadDariFile(); break;
               case 0:  System.out.println("\n  Terima kasih! Program selesai."); break;
               default: System.out.println("\n  [!] Pilihan tidak valid.");
           }
       } while (pilihan != 0);
   }
}


