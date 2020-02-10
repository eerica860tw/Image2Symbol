import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class mainframe extends JFrame {

	private JPanel contentPane;
	private JButton btnSelectImg;
	private JSlider sliderHue;
	private JTextArea textAreaOutput;
	private File imageFile;
	private static JFileChooser chooser;
	private BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initial();
					mainframe frame = new mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void initial() {
		chooser = new JFileChooser();
	}

	/**
	 * Create the frame.
	 */
	public mainframe() {
		setTitle("Image2Symbol By EricShen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSelectImg = new JButton("Select Image");
		btnSelectImg.setBounds(10, 10, 123, 23);
		contentPane.add(btnSelectImg);
		btnSelectImg.addMouseListener(MListener);
		
		sliderHue = new JSlider(0,360);
		sliderHue.setBounds(143, 10, 281, 26);
		contentPane.add(sliderHue);
		sliderHue.addChangeListener(ChListener);
		
		textAreaOutput = new JTextArea();
		textAreaOutput.setBounds(10, 47, 414, 204);
		contentPane.add(textAreaOutput);
	}
	private ChangeListener ChListener = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == sliderHue) {
				textAreaOutput.setText("");
				float Hue = (float) (sliderHue.getValue()/360.0);
				for(int y =0;y<image.getHeight();y++) {
					String line = "";
					for(int x=0;x<image.getWidth();x++) {
						Color origpixel = new Color(image.getRGB(x, y));
						float[] HSBpixel = Color.RGBtoHSB(origpixel.getRed(), origpixel.getGreen(), origpixel.getBlue(), null);
						if(HSBpixel[0]>Hue-0.05 && HSBpixel[0]<Hue+0.05) {
							line+="#";
						}else {
							line+="_";
						}
					}
					textAreaOutput.setText(textAreaOutput.getText()+line+"\n");
				}
			}
		}
		
	};
	private MouseListener MListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnSelectImg) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpeg","jpg");
				chooser.setFileFilter(filter);
				if(chooser.showOpenDialog(contentPane)==JFileChooser.APPROVE_OPTION) {
					imageFile = chooser.getSelectedFile();
					try {
						image = Scalr.resizeof(ImageIO.read(imageFile)).size;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
}
