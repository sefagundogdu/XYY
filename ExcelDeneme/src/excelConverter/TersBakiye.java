package excelConverter;

public class TersBakiye {
	
	String subeKodu;
	String hesapKodu;
	String hesapKarakteri;
	String dovizKodu;
	double ypNetBakiye;
	double tpNetBakiye;
	String grupBilgisi;

	public TersBakiye(String subeKodu, String hesapKodu, String hesapKarakteri, String dovizKodu, double ypNetBakiye,
			double tpNetBakiye, String grupBilgisi) {
		super();
		this.subeKodu = subeKodu;
		this.hesapKodu = hesapKodu;
		this.hesapKarakteri = hesapKarakteri;
		this.dovizKodu = dovizKodu;
		this.ypNetBakiye = ypNetBakiye;
		this.tpNetBakiye = tpNetBakiye;
		this.grupBilgisi = grupBilgisi;
	}
	public TersBakiye(){
		
	}
	public String getSubeKodu() {
		return subeKodu;
	}
	public void setSubeKodu(String subeKodu) {
		this.subeKodu = subeKodu;
	}
	public String getHesapKodu() {
		return hesapKodu;
	}
	public void setHesapKodu(String hesapKodu) {
		this.hesapKodu = hesapKodu;
	}
	public String getHesapKarakteri() {
		return hesapKarakteri;
	}
	public void setHesapKarakteri(String hesapKarakteri) {
		this.hesapKarakteri = hesapKarakteri;
	}
	public String getDovizKodu() {
		return dovizKodu;
	}
	public void setDovizKodu(String dovizKodu) {
		this.dovizKodu = dovizKodu;
	}
	public double getYpNetBakiye() {
		return ypNetBakiye;
	}
	public void setYpNetBakiye(double ypNetBakiye) {
		this.ypNetBakiye = ypNetBakiye;
	}
	public double getTpNetBakiye() {
		return tpNetBakiye;
	}
	public void setTpNetBakiye(double tpNetBakiye) {
		this.tpNetBakiye = tpNetBakiye;
	}
	public String getGrupBilgisi() {
		return grupBilgisi;
	}
	public void setGrupBilgisi(String grupBilgisi) {
		this.grupBilgisi = grupBilgisi;
	}
	
	
	
	

}
