package excelConverter;

public class GLFormat {
	
	int refNo;//
	String fisKategori;//
	String muhTarihi;//
	String dovizCinsi;//
	String subeKodu;//
	String karsiSube;//
	String borcHesabi;//
	String borcHesRiskGrString;//
	String alacakHesabi;//
	String alacakHesRiskGr;//	
	double fisTutari;//
	String dovizTutari;//
	String aciklama;//
	String fisKesen;
	String kesilmeDurumu;
	
	public GLFormat() {
		
	}
	
	public String getFisKesen() {
		return fisKesen;
	}

	public void setFisKesen(String fisKesen) {
		this.fisKesen = fisKesen;
	}

	public String getKesilmeDurumu() {
		return kesilmeDurumu;
	}

	public void setKesilmeDurumu(String kesilmeDurumu) {
		this.kesilmeDurumu = kesilmeDurumu;
	}

	public int getRefNo() {
		return refNo;
	}
	public void setRefNo(int refNo) {
		this.refNo = refNo;
	}
	public String getFisKategori() {
		return fisKategori;
	}
	public void setFisKategori(String fisKategori) {
		this.fisKategori = fisKategori;
	}
	public String getMuhTarihi() {
		return muhTarihi;
	}
	public void setMuhTarihi(String muhTarihi) {
		this.muhTarihi = muhTarihi;
	}
	public String getDovizCinsi() {
		return dovizCinsi;
	}
	public void setDovizCinsi(String dovizCinsi) {
		this.dovizCinsi = dovizCinsi;
	}
	public String getSubeKodu() {
		return subeKodu;
	}
	public void setSubeKodu(String subeKodu) {
		this.subeKodu = subeKodu;
	}
	public String getKarsiSube() {
		return karsiSube;
	}
	public void setKarsiSube(String karsiSube) {
		this.karsiSube = karsiSube;
	}
	public String getBorcHesabi() {
		return borcHesabi;
	}
	public void setBorcHesabi(String borcHesabi) {
		this.borcHesabi = borcHesabi;
	}
	public String getBorcHesRiskGrString() {
		return borcHesRiskGrString;
	}
	public void setBorcHesRiskGrString(String borcHesRiskGrString) {
		this.borcHesRiskGrString = borcHesRiskGrString;
	}
	public String getAlacakHesabi() {
		return alacakHesabi;
	}
	public void setAlacakHesabi(String alacakHesabi) {
		this.alacakHesabi = alacakHesabi;
	}
	public String getAlacakHesRiskGr() {
		return alacakHesRiskGr;
	}
	public void setAlacakHesRiskGr(String alacakHesRiskGr) {
		this.alacakHesRiskGr = alacakHesRiskGr;
	}
	public double getFisTutari() {
		return fisTutari;
	}
	public void setFisTutari(double fisTutari) {
		this.fisTutari = fisTutari;
	}
	public String getDovizTutari() {
		return dovizTutari;
	}
	public void setDovizTutari(String dovizTutari) {
		this.dovizTutari = dovizTutari;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
