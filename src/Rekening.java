public abstract class Rekening {
    // nomor dan pemilik tetap private: anak harus lewat getter
    private String nomor;
    private String pemilik;

    // protected: boleh dipakai langsung oleh class turunan (Tabungan, Giro)
    protected int saldo;

    public Rekening(String nomor, String pemilik, int saldo) {
        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldo;
    }

    public String getNomor() {
        return nomor;
    }

    public String getPemilik() {
        return pemilik;
    }

    public int getSaldo() {
        return saldo;
    }

    // Method abstract: setiap jenis rekening WAJIB menentukan biaya adminnya sendiri
    public abstract int biayaAdminBulanan();

    // Method konkret: sama untuk semua jenis rekening
    public int potongBiayaAdmin() {
        int biaya = biayaAdminBulanan();
        saldo -= biaya;
        return biaya;
    }

    public boolean setor(int jumlah) {
        if (jumlah <= 0) {
            return false;
        }
        saldo += jumlah;
        return true;
    }

    public boolean tarik(int jumlah) {
        if (jumlah <= 0 || jumlah > saldo) {
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    // Contoh keluaran: 101 | Ani    |  Rp1.000.000
    public void cetakInfo() {
        System.out.println(nomor + " | "
                + String.format("%-6s", pemilik) + " | "
                + String.format("%12s", formatRupiah(saldo)));
    }

    // 120000 -> "Rp120.000", -15000 -> "-Rp15.000"
    protected static String formatRupiah(int angka) {
        if (angka < 0) {
            return "-" + formatRupiah(-angka);
        }
        String digit = String.valueOf(angka);
        StringBuilder hasil = new StringBuilder();
        int hitung = 0;
        for (int i = digit.length() - 1; i >= 0; i--) {
            hasil.insert(0, digit.charAt(i));
            hitung++;
            if (hitung % 3 == 0 && i > 0) {
                hasil.insert(0, '.');
            }
        }
        return "Rp" + hasil;
    }
}