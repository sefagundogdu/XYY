package excelConverter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseFilePanel extends JPanel{
	
	JFileChooser chooser;
	JFileChooser chooser2;
	File selected;
	JButton button;
	JPanel buttonPanel;
	JPanel choosersPanel;
	JLabel labelLeft;
	JLabel labelRight;
	JPanel leftChooser;
	JPanel rightChooser;
	
	public ChooseFilePanel()
	{
		
		labelLeft = new JLabel();
		labelRight = new JLabel();
		
		labelLeft.setText("TERS BAKIYE EXCEL");
		labelRight.setText("TEMINAT KODLARI EXCEL");
		labelLeft.setForeground(Color.RED);
		labelRight.setForeground(Color.RED);
		
		
		
		leftChooser = new JPanel();
		rightChooser = new JPanel();
		choosersPanel = new JPanel();
		

		
		buttonPanel = new JPanel();
		button = new JButton("CREATE FILES");
		buttonPanel.add(button);
		
	


		
		setLayout(new BorderLayout());
		
		chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
	
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
		chooser.addChoosableFileFilter(filter);
		chooser.setAutoscrolls(true);
		
		chooser2 = new JFileChooser();
		chooser2.setAcceptAllFileFilterUsed(false);
	
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
		chooser2.addChoosableFileFilter(filter);
		chooser2.setAutoscrolls(true);
		
		chooser.setControlButtonsAreShown(false);
		chooser2.setControlButtonsAreShown(false);
		
		
		leftChooser.setLayout(new BorderLayout());
		leftChooser.add(chooser,BorderLayout.CENTER);
		leftChooser.add(labelLeft,BorderLayout.NORTH);
		
		
		
		rightChooser.setLayout(new BorderLayout());
		rightChooser.add(chooser2,BorderLayout.CENTER);
		rightChooser.add(labelRight,BorderLayout.NORTH);
		
		
		choosersPanel.add(leftChooser);
		choosersPanel.add(rightChooser);
		
		add(choosersPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
		
		
		
	}

}
