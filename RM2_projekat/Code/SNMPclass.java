package Code;

import java.io.IOException;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;
import com.ireasoning.protocol.snmp.SnmpTarget;
import com.ireasoning.protocol.snmp.SnmpVarBind;

public class SNMPclass {
	private SnmpTarget target;
	private SnmpSession sesija;
	private SnmpTableModel table;
	public SNMPclass(String host,int port) {
	
	try {
		
		sesija=new SnmpSession(host,port,"si2019","si2019",SnmpConst.SNMPV2);
		 SnmpSession.loadMib2();//load MIB-II
		table = sesija.snmpGetTable("ipRouteTable");
	
	}catch(Exception e){
		System.out.println("greska");
	}
	}
	public void osvjezi() {
		
		 try {
		 table = sesija.snmpGetTable("ipRouteTable");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public int dohvatiBrojRedova() {
		return table.getRowCount();
	}
	public String dohvatiNextHopAddresu(int row) {
		return table.getRow(row)[6].getValue().toString();
	}
	public String dohvatiPorijekloAddrese(int row) {
		int a=Integer.parseInt(table.getRow(row)[8].getValue().toString());
		switch(a) {
		case 1:
			return "other(1)";
		case 2:
			return "local(2)";
		case 3:
			return  "netmgmt(3)";
		case 4:
			return  "icmp(4)";
		case 5:
			return 	"egp(5)";
		case 6:
			return	"ggp(6)";
		case 7:
			return	"hello(7)";
		case 8:
			return	"rip(8)";
		case 9:
			return	"is-is(9)";
		case 10:
			return	"es-is(10)";
		case 11:
			return	"ciscoIgrp(11)";
		case 12:
			return	"bbnSpfIgp(12)";
		case 13:
			return	"ospf(13)";
		case 14:
			return	"bgp(14)";
		}
		return null;
	}
	
	public String dohvatiMasku(int row) {
		return table.getRow(row)[10].getValue().toString();
	}
	public String dohvatiIPAdresu(int row) {
		return table.getRow(row)[0].getValue().toString();
	}
	public void zatvori() {
		sesija.close();
	}
public static  void main(String [] args) {
	SNMPclass sc=new SNMPclass("192.168.10.1", 161);

	sc.table.addRow(new SnmpVarBind[sc.table.getColumnCount()]);

}


public SnmpTableModel dohvatiTabelu() {
	return table;
}
}