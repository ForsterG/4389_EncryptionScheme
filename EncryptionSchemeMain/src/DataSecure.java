package dataSecurity;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class DataSecure {

	private String password=null;
	private JFrame frame;
	private JTextField filePaths;
	private JPasswordField passwordField;

	// encrypt_main object


EncryptionMain main_obj= new EncryptionMain();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataSecure window = new DataSecure();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public DataSecure() {
		initialize();
	}

	public String handlePass() {



		return null;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JRadioButton rdbtnEncrypt = new JRadioButton("encrypt");

		JRadioButton rdbtnDecrypt = new JRadioButton("decrypt");

		filePaths = new JTextField();
		passwordField = new JPasswordField();
		filePaths.setColumns(10);

		JButton btnSelect = new JButton("select file");
		JTextArea dispayText = new JTextArea();

		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser select= new JFileChooser();
				select.showOpenDialog(select);

				File file= select.getSelectedFile();
				String filePath= file.getAbsolutePath();

				filePaths.setText(filePath);

		password = passwordField.getPassword().toString(); // user entered passwoed


/////////////////////////////////////////////////////////////////////////////////////////
//\\		Display file contents
/////////////////////////////////////////////////////////////////////////////////////////
			mainObj.encrypt_main(filePath,password);

				try {
					FileReader read= new FileReader(filePath);
					BufferedReader bread= new BufferedReader(read);
					dispayText.read(bread, null);
					bread.close();

					dispayText.requestFocus();

				} catch(Exception err) {

					JOptionPane.showMessageDialog(null, err);

				}
///////////////////////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////////////////////


			}
		});

		JLabel lblPassword = new JLabel("Password:");





		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(filePaths, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSelect))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnEncrypt)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnDecrypt)
							.addGap(18)
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(53, Short.MAX_VALUE))
				.addComponent(dispayText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnEncrypt)
						.addComponent(rdbtnDecrypt)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(filePaths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelect))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dispayText, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(18))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
