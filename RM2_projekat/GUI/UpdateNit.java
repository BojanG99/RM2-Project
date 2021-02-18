package Gui;

import javax.swing.JProgressBar;

public class UpdateNit  implements Runnable{
private Thread nit=new Thread(this);	

Tabela tab;
Object last[][];
private boolean radi=true;
public void run() {
	
	try {
	while(radi) {	
		if(desilaSePromjena())
	tab.osvjezi_tabelu();
	Thread.sleep(1000);
	tab.setjpb(0);
	Thread.sleep(1000);
	tab.setjpb(12);
	Thread.sleep(1000);
	tab.setjpb(24);
	Thread.sleep(1000);
	tab.setjpb(36);
	Thread.sleep(1000);
	tab.setjpb(48);
	Thread.sleep(1000);
	tab.setjpb(60);
	Thread.sleep(1000);
	tab.setjpb(72);
	Thread.sleep(1000);
	tab.setjpb(84);
	Thread.sleep(1000);
	tab.setjpb(99);
	Thread.sleep(1000);
	
	}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

}

public void  start() {
	nit.start();
}
public void stop() {
	radi=false;
}
boolean desilaSePromjena() {
	tab.osvjezi_ruter();
	if(last==null) {
		last=tab.matrica();
		return true;
	}
	else {
		Object[][] pomobj=tab.matrica();
		if(pomobj.length!=last.length) {
			last=pomobj;
			return true;
		}
		for(int i=0;i<last.length;i++) {
			for(int j=0;j<4;j++) {
				if(((String)last[i][j]).equalsIgnoreCase((String)pomobj[i][j]))continue;
				else {
					last=pomobj;
					return true;
					
				}
				
			}
			
		}
		
	}
	
	return false;
}
	public UpdateNit(Tabela tab) {
	
		this.tab=tab;
		last=null;
	}
}
