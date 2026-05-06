// StokManager.java

import java.util.Scanner;

public class StokManager {

    // ================================================================
    // STRUKTUR DATA — array paralel manual
    // ================================================================

    static final int MAX  = 100;
    static Produk[]  data = new Produk[MAX];
    static int       jumlahData = 0;
    static int       nextId     = 1;

    // Log soft delete
    static String[]  logHapus   = new String[MAX];
    static int       jumlahLog  = 0;

    static Scanner sc = new Scanner(System.in);

    // ================================================================
    // INIT DATA AWAL (sample data)
    // ================================================================

    public static void initDataAwal() {
        String[][] sample = {
            {"Laptop ASUS VivoBook",     "Laptop",     "45",  "7500000"},
            {"Mouse Logitech M235",      "Aksesori",   "120", "250000"},
            {"Samsung Galaxy A54",       "Smartphone", "60",  "4200000"},
            {"Keyboard Mechanical RGB",  "Aksesori",   "35",  "850000"},
            {"Monitor LG 24 inch",       "Monitor",    "20",  "2300000"},
            {"Headphone Sony WH-1000",   "Audio",      "15",  "3500000"},
            {"Flash Drive SanDisk 64GB", "Storage",    "200", "85000"},
            {"Laptop Lenovo IdeaPad",    "Laptop",     "30",  "6800000"},
            {"Charger USB-C 65W",        "Aksesori",   "90",  "175000"},
            {"SSD Samsung 500GB",        "Storage",    "55",  "950000"},
        };

        for (String[] d : sample) {
            data[jumlahData++] = new Produk(
                nextId++,
                d[0], d[1],
                Integer.parseInt(d[2]),
                Double.parseDouble(d[3])
            );
        }
    }

    // ================================================================
    // TAMBAH PRODUK
    // ================================================================

    public static void tambah() {
        if (jumlahData >= MAX) {
            System.out.println("\n  [!] Kapasitas penuh!");
            return;
        }

        System.out.println("\n─── TAMBAH PRODUK BARU ───");
        System.out.print("  Nama Produk  : ");
        String nm = sc.nextLine();
        System.out.print("  Kategori     : ");
        String kat = sc.nextLine();
        System.out.print("  Stok         : ");
        int stk = sc.nextInt();
        System.out.print("  Harga (Rp)   : ");
        double hrg = sc.nextDouble();
        sc.nextLine();

        data[jumlahData++] = new Produk(nextId++, nm, kat, stk, hrg);
        System.out.println("\n  [✓] Produk berhasil ditambahkan! ID: " + (nextId - 1));
    }

    // ================================================================
    // TAMPILKAN SEMUA
    // ================================================================

    public static void tampilkanSemua() {
        System.out.println("\n─── DAFTAR SEMUA PRODUK ───");
        printHeader();

        int count = 0;
        for (int i = 0; i < jumlahData; i++) {
            if (data[i].aktif) {
                printRow(data[i]);
                count++;
            }
        }

        if (count == 0) System.out.println("  (Tidak ada data produk aktif)");
        System.out.println("  Total: " + count + " produk aktif.");
    }

    // ================================================================
    // EDIT PRODUK
    // ================================================================

    public static void edit() {
        System.out.println("\n─── EDIT PRODUK ───");
        System.out.print("  Masukkan ID produk yang akan diedit: ");
        int targetId = sc.nextInt();
        sc.nextLine();

        int idx = cariIndexById(targetId);
        if (idx == -1 || !data[idx].aktif) {
            System.out.println("\n  [!] Produk ID " + targetId + " tidak ditemukan.");
            return;
        }

        Produk p = data[idx];
        System.out.println("\n  Data saat ini:");
        printHeader();
        printRow(p);

        System.out.println("\n  Masukkan data baru (Enter = tidak berubah):");

        System.out.print("  Nama [" + p.nama + "]: ");
        String nm = sc.nextLine();
        if (!nm.isEmpty()) p.nama = nm;

        System.out.print("  Kategori [" + p.kategori + "]: ");
        String kat = sc.nextLine();
        if (!kat.isEmpty()) p.kategori = kat;

        System.out.print("  Stok [" + p.stok + "]: ");
        String stokInput = sc.nextLine();
        if (!stokInput.isEmpty()) p.stok = Integer.parseInt(stokInput);

        System.out.print("  Harga [" + p.harga + "]: ");
        String hargaInput = sc.nextLine();
        if (!hargaInput.isEmpty()) p.harga = Double.parseDouble(hargaInput);

        System.out.println("\n  [✓] Data produk ID " + targetId + " berhasil diperbarui.");
    }

    // ================================================================
    // HAPUS (SOFT DELETE)
    // ================================================================

    public static void hapus() {
        System.out.println("\n─── HAPUS PRODUK (SOFT DELETE) ───");
        System.out.print("  Masukkan ID produk yang akan dihapus: ");
        int targetId = sc.nextInt();
        sc.nextLine();

        int idx = cariIndexById(targetId);
        if (idx == -1 || !data[idx].aktif) {
            System.out.println("\n  [!] Produk ID " + targetId + " tidak ditemukan atau sudah dihapus.");
            return;
        }

        Produk p = data[idx];
        System.out.println("\n  Produk yang akan dihapus:");
        printHeader();
        printRow(p);
        System.out.print("\n  Yakin ingin menghapus? (y/n): ");
        String konfirmasi = sc.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            logHapus[jumlahLog++] = "[LOG] ID=" + p.id
                + " | Nama=" + p.nama
                + " | Kategori=" + p.kategori
                + " | Stok=" + p.stok
                + " | Harga=" + p.hargaFormatted()
                + " | STATUS: DIHAPUS";
            p.aktif = false;
            System.out.println("\n  [✓] Produk ID " + targetId + " berhasil dihapus (soft delete).");
        } else {
            System.out.println("\n  [!] Penghapusan dibatalkan.");
        }
    }

    // ================================================================
    // LIHAT LOG
    // ================================================================

    public static void lihatLog() {
        System.out.println("\n─── LOG PENGHAPUSAN PRODUK ───");
        if (jumlahLog == 0) {
            System.out.println("  (Belum ada produk yang dihapus)");
            return;
        }
        for (int i = 0; i < jumlahLog; i++) {
            System.out.println("  " + (i + 1) + ". " + logHapus[i]);
        }
    }

    // ================================================================
    // HELPERS — dipakai juga oleh Search & Sort
    // ================================================================

    public static int cariIndexById(int targetId) {
        for (int i = 0; i < jumlahData; i++) {
            if (data[i].id == targetId) return i;
        }
        return -1;
    }

    public static void printHeader() {
        System.out.println("  ┌──────┬──────────────────────────────┬──────────────┬───────┬────────────────┐");
        System.out.println("  │  ID  │ Nama Produk                  │ Kategori     │ Stok  │ Harga          │");
        System.out.println("  ├──────┼──────────────────────────────┼──────────────┼───────┼────────────────┤");
    }

    public static void printRow(Produk p) {
        System.out.printf("  │ %-4d │ %-28s │ %-12s │ %-5d │ %-14s │%n",
            p.id,
            Produk.truncate(p.nama, 28),
            Produk.truncate(p.kategori, 12),
            p.stok,
            p.hargaFormatted()
        );
    }

    // Swap dua elemen di array data[]
    public static void swap(int i, int j) {
        Produk tmp = data[i];
        data[i]    = data[j];
        data[j]    = tmp;
    }
}
