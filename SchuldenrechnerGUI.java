package klausur_2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.*;

public class SchuldenrechnerGUI extends JFrame implements ActionListener {
	
	JPanel mainPanel;
	JPanel linkesPanel;
	JScrollPane rechterScrollPane;
	JLabel label1;
	JTextField textfield1;
	JSeparator seperator1, seperator2;
	private JLabel label2;
	private JComboBox combo1;
	private JComboBox combo2;
	private JButton calculate;
	private JPanel buttonPanel;
	private JButton exit;
	private JTextArea textArea;

	/**
	 * Constructor for the GUI
	 */
	public SchuldenrechnerGUI() 
	{
		super();
		mainPanel = initMainPanel();
		this.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(5,5));
		
		linkesPanel = initLinkesPanel();
		mainPanel.add(linkesPanel);
		
		rechterScrollPane = initRechterScrollPane();
		mainPanel.add(rechterScrollPane, BorderLayout.EAST);
		
		initialisiereFenster();
		this.setVisible(true);
	}

	void initialisiereFenster() {
		this.setTitle("Hallo Schuldenrechner!");
		this.setSize(500, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	JPanel initMainPanel() {
		JPanel panel = new JPanel();
		return panel;
	}
	
	JPanel initLinkesPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,1));
		
		label1 = new JLabel("Geliehene Summe");
		panel.add(label1);
		
		textfield1 = new JTextField();
		panel.add(textfield1);
		
		seperator1 = new JSeparator();
		panel.add(seperator1);
		
		label2 = new JLabel("Laufzeit");
		panel.add(label2);
		
		String[] laufzeiten = { "6", "12", "18", "24" };
		combo1 = new JComboBox(laufzeiten);
		panel.add(combo1);
		
		seperator2 = new JSeparator();
		panel.add(seperator2);
		
		label2 = new JLabel("Startmonat");
		panel.add(label2);
		
		String[] monate = { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember" };
		combo2 = new JComboBox(monate);
		panel.add(combo2);
		
		buttonPanel = initButtonPanel();
		panel.add(buttonPanel);
		
		return panel;
	}
	
	private JPanel initButtonPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		calculate = new JButton("Berechnen");
		calculate.addActionListener(this);
		panel.add(calculate);
		
		exit = new JButton("Beenden");
		exit.addActionListener(this);
		panel.add(exit);
		
		return panel;
	}
	
	private JScrollPane initRechterScrollPane() {
		textArea = new JTextArea(12,20);
		JScrollPane pane = new JScrollPane(textArea);
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Beenden"){
			int n = JOptionPane.showConfirmDialog(this,"Willst du abbrechen?","Abbrechen", JOptionPane.YES_NO_OPTION);
			
			if(n == JOptionPane.YES_OPTION){
				this.setVisible(false);
				this.dispose();
				System.exit(0);	
			}
		}
		
		if (e.getActionCommand() == "Berechnen"){
			textArea.setText(null);
			float summe = 0;

			try {
				summe = Float.parseFloat(textfield1.getText());
				int laufzeit = Integer.parseInt(combo1.getSelectedItem().toString());

				Schuldbetrag s1 = new Schuldbetrag(summe, laufzeit);

				ListIterator<Float> iterator = s1.getRaten().listIterator();
			    while(iterator.hasNext()){
			    	textArea.append(combo2.getSelectedItem().toString() + " " + iterator.next().toString() + "\n");
			    }

			}
			catch (NumberFormatException num) {
				System.out.println(num);
				int n = JOptionPane.showConfirmDialog(this,"Du musst eine Zahl eingeben!", (textfield1.getText() + " ist keine Zahl"), JOptionPane.CLOSED_OPTION);
				textfield1.setText(null);
			}
		}
		
	}
}
