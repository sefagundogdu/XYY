package excelConverter;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.codec.Charsets;



public class MainPanel extends JPanel{
	
	String fisKategoriDefault = "DUZELTME FISLERI";
	String aciklamaDefault = "MAYIS 2018 TERS BAKIYE DUZELTME";
	String tarihDefault = "31.05.2018";
	String fisKesenDefault = "96306";
	String kesilmeDurumuDefault ="E";
	ChooseFilePanel a;
	TextPanel b;
	public MainPanel(final JFrame main) {
		
		setLayout(new BorderLayout());
		
		
		a = new ChooseFilePanel();
		
		b = new TextPanel();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setSize(1500, 100);
		
		b.aciklamaField.setText(aciklamaDefault);
		b.tarihField.setText(tarihDefault);
		b.fisKategoriField.setText(fisKategoriDefault);
		b.fisKesenField.setText(fisKesenDefault);
		//b.kesilmeDurumuField.setText(kesilmeDurumuDefault);
		
		add(a,BorderLayout.CENTER);
		add(b,BorderLayout.NORTH);
		
		a.button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				a.selected = a.chooser.getSelectedFile();
				
				String fisKategori = b.fisKategoriField.getText();
		    	String aciklama = b.aciklamaField.getText();
		    	String tarih = b.tarihField.getText();
		    	String fisKesen = b.fisKesenField.getText();
		    	//String kesilmeDurumu =b.kesilmeDurumuField.getText();
		    	//String	selectedDirectory= getDirectory(new String(a.selected.getPath()));
		    	//
		    	//
		    	main.setVisible(false);
		    	
		    	
		    	if(fisKategori.equals(""))
		    		fisKategori = fisKategoriDefault;
		    	
		    	if(aciklama.equals(""))
		    		aciklama = aciklamaDefault;
		    	
		    	if(tarih.equals(""))
		    		tarih = tarihDefault;
		    	
		    	if(fisKesen.equals(""))
		    		fisKesen = fisKesenDefault;
		    	
//		    	if(kesilmeDurumu.equals(""))
//		    		kesilmeDurumu = kesilmeDurumuDefault;
		    	
		    	System.out.println(a.chooser.getSelectedFile());
		    	System.out.println(a.chooser2.getSelectedFile());
		    	
		    	if(a.chooser.getSelectedFile()==null||a.chooser2.getSelectedFile()==null)
		    	{
		    		main.setVisible(true);
		    		JOptionPane.showMessageDialog(main, "Ters Bakiye ve Teminat Kodları excel dosyalarını seçiniz!");
		    		
		    	
		    	}
		    	else
		    	{
		    		Converter createFiles = new Converter(fisKategori, aciklama, tarih, fisKesen, kesilmeDurumuDefault, a.chooser.getSelectedFile().getPath(), a.selected, a.chooser2.getSelectedFile());
		    		System.exit(0);
		    	
		    	}
		    	
		    	
		    	/*JFileChooser teminatKodları = new JFileChooser();
		    	
		    	teminatKodları.setAcceptAllFileFilterUsed(false);
		    	
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
				
				teminatKodları.addChoosableFileFilter(filter);
				teminatKodları.setDialogTitle("Teminat Kodlarını içeren EXCEL dosyasını seçiniz...");
				int returnValue = teminatKodları.showOpenDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(teminatKodları.getSelectedFile().getPath());
					Converter createFiles = new Converter(fisKategori, aciklama, tarih, fisKesen, kesilmeDurumuDefault, selectedDirectory, a.selected, teminatKodları.getSelectedFile());
				}
				*/
				
		    	
		    	
		    	
		    	
				
			}

			
		});
		
		
		
	}
	public String getDirectory(String string) {
		
		int i = string.lastIndexOf("\\");
		
		return string.substring(0, i+1);
	}

}
