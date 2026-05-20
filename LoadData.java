import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class LoadData {

    public static void loadDariFile() {
        System.out.println("\n LOAD DATA DARI FILE");
        System.out.print("  Masukkan nama file (contoh: produk.txt): ");
        String namaFile = StokManager.sc.nextLine();

        int berhasil = 0;
        int gagal = 0;

        try {
            Scanner sc = new Scanner(new File(namaFile));

            while (sc.hasNextLine()) {
                String baris = sc.nextLine().trim();
                if (baris.isEmpty()) continue;

                String[] bagian = baris.split(",");

                if (bagian.length != 4) {
                    gagal++;
                    continue;
                }

                String nama     = bagian[0].trim();
                String kategori = bagian[1].trim();
                int stok        = Integer.parseInt(bagian[2].trim());
                double harga    = Double.parseDouble(bagian[3].trim());

                if (StokManager.jumlahData >= StokManager.MAX) {
                    System.out.println("  [!] Kapasitas penuh, load dihentikan.");
                    break;
                }

                StokManager.data[StokManager.jumlahData++] = new Produk(
                    StokManager.nextId++,
                    nama, kategori, stok, harga
                );
                berhasil++;
            }

            sc.close();
            System.out.println("  [v] Load selesai. Berhasil: " + berhasil + ", Gagal: " + gagal);

        } catch (Exception e) {
            System.out.println("  [!] File tidak ditemukan atau gagal dibaca.");
        }
    }
