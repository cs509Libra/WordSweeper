package client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import client.ServerAccess;
import client.controller.CreateGameController;
import client.controller.JoinGameController;
import client.controller.PracticeController;
import client.model.Model;
import client.view.MultiGame;
import javax.swing.JLabel;


public class Application extends JFrame {

	public final Model model;
	
	JPanel contentPane;
	ServerAccess serverAccess;

	JTextArea requestArea;
	JTextArea responseArea;
	
	JScrollPane clientRequests;
	JScrollPane serverOutput;
	
	String playerName;
	String password;
	String gameNumber;
	
	

	public String getPassword() {
		return password;
	}

	public String getGameNumber() {
		return gameNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public Application(Model model) {
		this.model = model;
		initiate();
	}
	
	private void initiate(){		
		this.setTitle("WordSweeper Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		this.setBounds(100, 100, 473, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel gameNumberLabel = new JLabel("Game Number: ");
		gameNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameNumberLabel.setBounds(45, 60, 133, 30);
		contentPane.add(gameNumberLabel);
		
		JTextField gameNumberField =  new JTextField(10);
		gameNumberField.setBounds(200, 60, 164, 30);
		contentPane.add(gameNumberField);
		gameNumberField.setColumns(10);
		
		JLabel playerNameLabel = new JLabel("Player Name: ");
		gameNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		playerNameLabel.setBounds(95, 110, 133, 30);
		contentPane.add(playerNameLabel);
		
		JTextField playerNameField =  new JTextField(10);
		playerNameField.setBounds(200, 110, 164, 30);
		contentPane.add(playerNameField);
		playerNameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password: ");
		gameNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setBounds(113, 160, 133, 30);
		contentPane.add(passwordLabel);
		
		JPasswordField passWordField = new JPasswordField();
		passWordField.setBounds(200, 160, 164, 30);
		contentPane.add(passWordField);
		passWordField.setColumns(10);
		
		JButton btnPractice = new JButton("Practice");
		btnPractice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Practice pg = new Practice(model, Application.this);			
				pg.setVisible(true);
				Application.this.setVisible(false);
			}
		});
		btnPractice.setBounds(70, 250, 120, 40);
		contentPane.add(btnPractice);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.this.playerName = playerNameField.getText();
				if(playerName.length() != 0){			
					MultiGame mg = new MultiGame(model, Application.this);
					mg.setVisible(true);
					new CreateGameController(Application.this, model).process();
					Application.this.setVisible(false);
				}
			}
		});
		btnCreateGame.setBounds(195, 250, 120, 40);
		contentPane.add(btnCreateGame);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.this.playerName = playerNameField.getText();
				Application.this.gameNumber = gameNumberField.getText();
				if(playerName.length()!=0 && gameNumber.length()!=0){                     //player.jframe
					new JoinGameController(Application.this, model).process();
					Application.this.setVisible(false);
				}	
			}
		});
		
		btnJoinGame.setBounds(320, 250, 120, 40);
		contentPane.add(btnJoinGame);
	
		clientRequests = new JScrollPane();
		serverOutput = new JScrollPane();
		
		requestArea = new JTextArea();
		clientRequests.setViewportView(requestArea);
		
		responseArea = new JTextArea();
		serverOutput.setViewportView(responseArea);

	}

	/** Record the means to communicate with server. */
	public void setServerAccess(ServerAccess access) {
		this.serverAccess = access;
	}
	
	/** Get the server access object. */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}
	
	/** Navigation access to actionable elements in the GUI. */
	public JTextArea getRequestArea() {
		return requestArea;
	}
	
	/** Navigation access to actionable elements in the GUI. */
	public JTextArea getResponseArea() {
		return responseArea;
	}
}
