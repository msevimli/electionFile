package copyFile;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;

@SuppressWarnings("serial")
public class copy extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtSource;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					copy frame = new copy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	public copy() {
		setResizable(false);
		setTitle("Tiles R Us ");
		setBounds(100, 100, 447, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JList list = new JList();
		list.setBounds(10, 62, 424, 178);
        contentPane.add(list);
        
        JLabel lblNewLabel = new JLabel(" stand by");
		lblNewLabel.setBounds(10, 250, 81, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Proccess :");
		lblNewLabel_1.setBounds(118, 251, 149, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setBounds(360, 334, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 354, 424, 160);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				FileInputStream fstream;
				fstream=null;
				try {
					fstream = new FileInputStream("source.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String strLine;
				//Read File Line By Line		
				DefaultListModel ccc = new DefaultListModel();
				try {
					while ((strLine = br.readLine()) != null)   {
					  // Print the content on the console
						ccc.addElement(strLine.trim());
					  //System.out.println (strLine);
					}
					list.setModel(ccc);
					int size=list.getModel().getSize();
					lblNewLabel.setText("loaded :" + String.valueOf(size));
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		btnNewButton.setBounds(72, 288, 89, 23);
		contentPane.add(btnNewButton);
		JButton btnCopyFiles = new JButton("copy files");
		btnCopyFiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int size=list.getModel().getSize();
				int count=0;
				int exist_file=0;
				while(count<size) {
					//JOptionPane.showMessageDialog(null, list.getModel().getElementAt(count).toString());
					File kaynak = new File(list.getModel().getElementAt(count).toString());
					File target = new File(list.getModel().getElementAt(count).toString().replaceAll("source", "destination"));
					//String folder=list.getModel().getElementAt(count).toString().replaceAll("source", "destination");
					//String[] str=folder.split("/");
					//JOptionPane.showMessageDialog(null, str.length);
					
				//	if (!folder.exists()) {
		        //        folder.mkdirs();
		        //    }
					if(target.exists()) {
						exist_file++;
						lblNewLabel_2.setText(" "+String.valueOf(exist_file));
						textArea.append(target+"\n");
					}
					InputStream input = null;
					OutputStream output = null;
					try {
						input = new FileInputStream(kaynak);
						output = new FileOutputStream(target);
						byte[] buf = new byte[1024];
						int bytesRead;
						while ((bytesRead = input.read(buf)) > 0) {
							output.write(buf, 0, bytesRead);
						}
						lblNewLabel_1.setText("Copied: "+String.valueOf(count));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						//return;
						e.printStackTrace();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							input.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							output.close();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				count++;
				}
			}
		});
		btnCopyFiles.setBounds(246, 288, 89, 23);
		contentPane.add(btnCopyFiles);
		
		textField = new JTextField();
		textField.setBounds(10, 31, 193, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtSource = new JTextField();
		txtSource.setText("source");
		txtSource.setBounds(246, 31, 89, 20);
		contentPane.add(txtSource);
		txtSource.setColumns(10);
		
		JLabel lblrecommand = new JLabel("(recommend)");
		lblrecommand.setBounds(345, 34, 81, 14);
		contentPane.add(lblrecommand);
		
		JLabel lblReplace = new JLabel("Replace :");
		lblReplace.setBounds(10, 11, 81, 14);
		contentPane.add(lblReplace);
		
		JLabel lblToThis = new JLabel("to this:");
		lblToThis.setBounds(246, 11, 46, 14);
		contentPane.add(lblToThis);
		
		JLabel lblExistingFileTotal = new JLabel("Existing File Total: ");
		lblExistingFileTotal.setBounds(246, 334, 105, 14);
		contentPane.add(lblExistingFileTotal);
			
	}
}
