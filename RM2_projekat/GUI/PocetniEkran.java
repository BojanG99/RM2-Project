package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Code.SNMPclass;

public class PocetniEkran extends Frame  {
	ImageIcon icon;
	Panel p;
	TextField tf;
	JProgressBar jpb;
	public PocetniEkran() {
		icon=new ImageIcon("/home/korisnik/Downloads/pic1.gif");
	
		tf=new TextField();
		tf.setPreferredSize(new Dimension(100, 20));
		super.addWindowListener(new WindowAdapter()  {
					
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
			}
		
		}	);
		//setBackground(Color.CYAN);
	setSize(2000, 1000);
	setLayout(new FlowLayout());
   p=new Panel();
	p.setLayout(new FlowLayout());
	
	
	JButton dodaj=new JButton();
	dodaj.setText("Dodaj");
	dodaj.setPreferredSize(new Dimension(100, 50));
	dodaj.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dodajDugme(tf.getText());// TODO Auto-generated method stub
			
		}
	});
	JButton obrisi=new JButton();
	obrisi.setText("Obrisi");
	obrisi.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			obrisiDugme(tf.getText());// TODO Auto-generated method stub
			p.repaint();
		}
	});
	//dodajDugme("192.168.10.1");
	Panel panel=new Panel(new GridLayout(1,2));
	Panel panel1=new Panel(new GridLayout(2,1));
	panel.add(tf);
	
	panel.add(panel1);//
	panel1.add(dodaj);
	panel1.add(obrisi);
	add(panel,BorderLayout.CENTER);
	add(p,BorderLayout.SOUTH);
	
		setVisible(true);
	}
	
	public void dodajDugme(String s){
		if(postojiDugme( s))return;
		JButton button=new JButton(icon);
		button.setSize(30,30);
		
		button.setText(s);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton but=(JButton)(arg0.getSource());
				SNMPclass nova=new SNMPclass(but.getText(),161);
				Tabela tab=new Tabela(nova);
			   //tab.start();
			}
		});
		p.add(button);
		setVisible(true);
	}

	private boolean postojiDugme(String s) {
		// TODO Auto-generated method stub
		Component comps[]=p.getComponents();
		for (Component component : comps) {
			if(((JButton)component).getText().equalsIgnoreCase(s))
			return true;
		}
		return false;
	}
    private void obrisiDugme(String s) {
    	Component comps[]=p.getComponents();
    	for (Component component : comps) {
			if(((JButton)component).getText().equalsIgnoreCase(s))
			{
				p.remove(component);
				return;
			}
		}
    }
	public static void main(String args[]) {
		
		new PocetniEkran();
	 
	}


}
