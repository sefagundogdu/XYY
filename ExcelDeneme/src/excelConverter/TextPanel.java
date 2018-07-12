package excelConverter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TextPanel extends JPanel{
	
	JLabel fisKategoriLabel;
	JTextField fisKategoriField;
	
	JLabel aciklamaLabel;
	JTextField aciklamaField;
	
	JLabel tarihLabel;
	JTextField tarihField;
	
	JLabel fisKesenLabel;
	JTextField fisKesenField;
	
	JLabel kesilmeDurumuLabel;
	JTextField kesilmeDurumuField;
	
	JLabel defaultRadioButton;
	JRadioButton button;
	
	
	public TextPanel() {
		
		setLayout(new BorderLayout());
		
		JPanel up = new JPanel();
		final JPanel down = new JPanel();
		
		add(up,BorderLayout.NORTH);
		add(down, BorderLayout.CENTER);
		
		
		/*defaultRadioButton = new JLabel("Default DeÄŸerleri Kullan:");
		button = new JRadioButton();
		
		up.add(defaultRadioButton);
		up.add(button);*/
		
		fisKategoriLabel = new JLabel("(GL)Fis Kategori:");
		fisKategoriField = new JTextField(12);
		
		aciklamaLabel = new JLabel("(GL&RunMuh)Acıklama:");
		aciklamaField = new JTextField(20);
		
		tarihLabel = new JLabel("(GL&RunMuh)Tarih(DD.MM.YYYY):");
		tarihField = new JTextField(10);
		
		fisKesenLabel = new JLabel("(GL)Fis Kesen:");
		fisKesenField = new JTextField(10);
		
		/*kesilmeDurumuLabel = new JLabel("(GL)Fis Kesilme Durumu:");
		kesilmeDurumuField = new JTextField(3);*/
		
		down.add(aciklamaLabel);
		down.add(aciklamaField);
		
		down.add(tarihLabel);
		down.add(tarihField);
		
		
		down.add(fisKategoriLabel);
		down.add(fisKategoriField);
		

		
		down.add(fisKesenLabel);
		down.add(fisKesenField);
		
		/*down.add(kesilmeDurumuLabel);
		down.add(kesilmeDurumuField);*/
		
		
		/*button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(button.isSelected())
					down.setVisible(false);
				else
					down.setVisible(true);
				
			}
		});*/
	}

}
