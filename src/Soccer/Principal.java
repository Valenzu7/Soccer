package Soccer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable ResultsTable;
	private JComboBox<String> CountryBox;
	private JComboBox<League> LeagueBox;
	private JSlider sliderRound;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		initialize();
		
	/**	EJEMPLOS
		//String xml=readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country=ES");
		//System.out.println(xml);
		
		//extractLeagues(xml);
		
		String xml=Extractor.readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country=ES");
		String xml2=Extractor.readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?key=857d690c2faae0679da7766573cb258d&format=xml&req=matchs&category=1&league=1&round=6");

		//Al instanciar un objeto padre sobre los hijos y usar un método sobreescrito en la clase del
		//hijo, usaremos el método propio
		Extractor e=new League();
		e.extract(xml);
		
		Extractor e2=new Score();
		e2.extract(xml2);
		
		League l=new League();
		l.extract(xml);
		
		ArrayList<Extractor> list=new ArrayList<Extractor>();
		list.add(e);
		list.add(e2);
		list.add(l);
		
		for(Extractor elem:list){
			elem.extract("");
		}
	**/
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel=new GridBagLayout();
		gbl_panel.columnWidths=new int[]{0, 0, 0};
		gbl_panel.rowHeights=new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights=new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights=new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCountry=new JLabel("Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.insets=new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx=0;
		gbc_lblCountry.gridy= 0;
		panel.add(lblCountry, gbc_lblCountry);
		
		CountryBox=new JComboBox<String>();
		CountryBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					onCountryChanged();
				}
			}
		});
		
		
		CountryBox.setModel(new DefaultComboBoxModel(new String[] {"IT", "ES", "EN"}));
		GridBagConstraints gbc_CountryBox = new GridBagConstraints();
		gbc_CountryBox.insets = new Insets(0, 0, 5, 0);
		gbc_CountryBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_CountryBox.gridx = 1;
		gbc_CountryBox.gridy = 0;
		panel.add(CountryBox, gbc_CountryBox);
		
		JLabel lblLeague = new JLabel("League");
		GridBagConstraints gbc_lblLeague = new GridBagConstraints();
		gbc_lblLeague.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeague.gridx = 0;
		gbc_lblLeague.gridy = 1;
		panel.add(lblLeague, gbc_lblLeague);
		
		LeagueBox = new JComboBox<League>();
		LeagueBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					onLeagueChanged();
				}
			}
		});
		GridBagConstraints gbc_LeagueBox = new GridBagConstraints();
		gbc_LeagueBox.insets = new Insets(0, 0, 5, 0);
		gbc_LeagueBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_LeagueBox.gridx = 1;
		gbc_LeagueBox.gridy = 1;
		panel.add(LeagueBox, gbc_LeagueBox);
		
		JLabel lblWeek = new JLabel("Game week");
		GridBagConstraints gbc_lblWeek = new GridBagConstraints();
		gbc_lblWeek.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeek.gridx = 0;
		gbc_lblWeek.gridy = 2;
		panel.add(lblWeek, gbc_lblWeek);
		
		sliderRound = new JSlider();
		sliderRound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JSlider slider=(JSlider) arg0.getSource();
				if(!slider.getValueIsAdjusting()){
					onRoundChanged();
				}
				
			}
		});
		
		GridBagConstraints gbc_sliderRound = new GridBagConstraints();
		gbc_sliderRound.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderRound.insets = new Insets(0, 0, 5, 0);
		gbc_sliderRound.gridx = 1;
		gbc_sliderRound.gridy = 2;
		panel.add(sliderRound, gbc_sliderRound);
		
		JScrollPane resultsPanel = new JScrollPane();
		GridBagConstraints gbc_resultsPanel = new GridBagConstraints();
		gbc_resultsPanel.gridwidth = 2;
		gbc_resultsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_resultsPanel.fill = GridBagConstraints.BOTH;
		gbc_resultsPanel.gridx = 0;
		gbc_resultsPanel.gridy = 3;
		panel.add(resultsPanel, gbc_resultsPanel);
		
		ResultsTable = new JTable();
		resultsPanel.setViewportView(ResultsTable);
		
		DefaultTableModel dtm=(DefaultTableModel) ResultsTable.getModel();
		dtm.addColumn("Local");
		dtm.addColumn("Goles locales");
		dtm.addColumn("Goles visitante");
		dtm.addColumn("Visitante");
	}

	public JComboBox<String> getCountryBox(){return CountryBox;}
	public JComboBox<League> getLeagueBox(){return LeagueBox;}
	public JSlider getWeekSlider(){return sliderRound;}
	
	
	private void fillTableResults(){
		League l=(League) LeagueBox.getSelectedItem();
		int league=l.getId();
		int round=sliderRound.getValue();
		
		String path="http://apiclient.resultados-futbol.com/scripts/api/api.php?key=857d690c2faae0679da7766573cb258d&format=xml&req=matchs&category=1&league="+league+"&round="+round;
		String xml=Extractor.readUrl(path);
		ArrayList<Score> list=new Score().extract(xml);//Devuelve un arraylist de scores
		
		//Vacío la tabla
		DefaultTableModel dtm=(DefaultTableModel) ResultsTable.getModel();
		while(dtm.getRowCount()<0){dtm.removeRow(0);}
		
		//Relleno la abla con las puntuaciones
		for(Score s:list){
			Object[] row={s.getLocal(),s.getGoLocal(),s.getGoVisit(),s.getVisit()};
			dtm.addRow(row);
		}
	}
	
	private void onRoundChanged(){fillTableResults();}
	
	private void onCountryChanged(){
		String country=(String)CountryBox.getSelectedItem();
		String xml=Extractor.readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country=ES");
		System.out.println(xml);
		
		LeagueBox.removeAllItems();
		
		ArrayList<League> leagues=new League().extract(xml);
		for(League l:leagues){
			LeagueBox.addItem(l);//almaceno un objeto de tipo liga
		}
	}
	
	private void onLeagueChanged(){
		League league=(League) LeagueBox.getSelectedItem();
		//Ajusto el slider
		sliderRound.setMaximum(league.getTotalRounds());
		sliderRound.setValue(league.getCurrentRound());
		//
		fillTableResults();
	}

}
