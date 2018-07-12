package excelConverter;

import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;

public class FrameWork {

	public static JFrame main;
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		main = new JFrame("Ters Muhasebe");
		MainPanel mainPanel = new MainPanel(main);
		
		main.add(mainPanel);
		main.setSize(1500, 500);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main.setVisible(true);

	}

}
