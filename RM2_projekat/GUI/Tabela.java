package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Code.SNMPclass;

public class Tabela extends Frame {//implements Runnable{

	
	private JTable tabela;
	private Panel pan;
	String imeColona[];
	SNMPclass ruter;
	JScrollPane sp;
	UpdateNit t;
	JProgressBar jpb;
	
		public Tabela(SNMPclass ruter) {
			setLayout(new FlowLayout());

		    this.ruter=ruter;
		   // ruter.dohvatiTabelu().addTableModelListener(this);
		     addWindowListener(new WindowAdapter() {
				@Override
				public void windowGainedFocus(WindowEvent e) {
					setVisible(true);
				}
				@Override
				public void windowClosing(WindowEvent arg0) {
					// TODO Auto-generated method stub
					setVisible(false);
					dispose();
					t.stop();
					ruter.zatvori();
					
				}
				
			
			});
		     pan=new Panel();
			imeColona= new String[]{"IpAdresa","Maska","Next hop","Porijeklo rute"};
			Object[][] podaci=matrica();
			tabela=new JTable(podaci,imeColona);
			tabela.setPreferredSize(new Dimension(5000,100));
			tabela.setFillsViewportHeight(true);
	
		
			jpb=new JProgressBar(0, 100);
		
			sp=new JScrollPane(tabela);
			pan.add(sp);
			add(pan,BorderLayout.CENTER);
			add(jpb,BorderLayout.NORTH);
			setSize(500, 500);
			setVisible(true);
			t=new UpdateNit(this);
			t.start();
		
		}
		public Object[][] matrica() {
			
			Object[][] mat=new Object[ruter.dohvatiBrojRedova()][4];
			for(int i=0;i<ruter.dohvatiBrojRedova();i++) {
				mat[i][0]=ruter.dohvatiIPAdresu(i);
				mat[i][1]=ruter.dohvatiMasku(i);
				mat[i][2]=ruter.dohvatiNextHopAddresu(i);
				mat[i][3]=ruter.dohvatiPorijekloAddrese(i);
			
			}
			
			return mat;
		}
		public void setjpb(int a) {
			jpb.setValue(a);
			jpb.repaint();
		}
		public void osvjezi_ruter() {
			ruter.osvjezi();
		}
		public void osvjezi_tabelu() {
			osvjezi_ruter();
			Object[][] podaci;
			podaci=matrica();
			pan.remove(sp);
			tabela=new JTable(podaci,imeColona);
			sp=new JScrollPane(tabela);
			

			pan.add(sp);
			
		//	if(super.hasFocus())
			setVisible(true);
		
		}
		
}
