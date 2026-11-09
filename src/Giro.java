public class Giro extends Rekening {
    private static final int BIAYA_ADMIN = 15000;

    private int batasOverdraft;

    public Giro(String nomor, String pemilik, int saldo, int batasOverdraft) {
        super(nomor, pemilik, saldo);
        this.batasOverdraft = batasOverdraft;
    }

    // Giro selalu Rp15.000
    @Override
    public int biayaAdminBulanan() {
        return BIAYA_ADMIN;
    }

    // Giro boleh minus, asal tidak lebih rendah dari -batasOverdraft
    @Override
    public boolean tarik(int jumlah) {
        if (jumlah <= 0 || saldo - jumlah < -batasOverdraft) {
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println("      Giro, batas overdraft " + formatRupiah(batasOverdraft));
    }
}