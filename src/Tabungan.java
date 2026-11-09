public class Tabungan extends Rekening implements BerBunga {
    private static final int BIAYA_ADMIN = 5000;
    private static final int SALDO_BEBAS_ADMIN = 1000000;

    private double bungaPersen;

    public Tabungan(String nomor, String pemilik, int saldo, double bungaPersen) {
        super(nomor, pemilik, saldo);
        this.bungaPersen = bungaPersen;
    }

    // Gratis jika saldo minimal Rp1.000.000, selain itu Rp5.000
    @Override
    public int biayaAdminBulanan() {
        if (saldo >= SALDO_BEBAS_ADMIN) {
            return 0;
        }
        return BIAYA_ADMIN;
    }

    // Implementasi BerBunga: bunga masuk lewat setor, bukan saldo +=
    @Override
    public int tambahBunga() {
        int bunga = (int) Math.round(saldo * bungaPersen / 100);
        setor(bunga);
        return bunga;
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println("      Tabungan, bunga " + bungaPersen + "% per bulan");
    }
}