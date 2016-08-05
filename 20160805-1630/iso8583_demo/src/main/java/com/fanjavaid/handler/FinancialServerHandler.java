/**
 * 
 */
package com.fanjavaid.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;

import com.fanjavaid.service.ISO8583Service;

/**
 * @author fanjavaid
 *
 */
public class FinancialServerHandler extends IoHandlerAdapter {
	private ISO8583Service isoService;
	
	public FinancialServerHandler() {
		// TODO Auto-generated constructor stub
		ISOPackager packager = null;
		
		try {
			packager = new GenericPackager("packager/iso8583_rules.xml");
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isoService = new ISO8583Service(packager);
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();
		if (str.trim().equalsIgnoreCase("quit")) {
			session.close();
			return;
		}
		
		if (isoService.testISOMsg(str)) {
			ISOMsg returnValue = isoService.unpackRequest(str);
			ISOMsg response = isoService.responseRequest(returnValue);
						
//			String data = logISOMsg(returnValue);
			String data = new String(response.pack());		
			session.write(data);
			
			
			// Sample Unpack Response
			ISOMsg responseLog = isoService.unpackRequest(data);
			System.out.println(logISOMsg(responseLog));
		} else {
			session.write("Invalid ISO Message!");
		}
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("IDLE : " + session.getIdleCount(status));
	}
	
	private String logISOMsg(ISOMsg msg) {
		StringBuilder builder = new StringBuilder();
		
		try {
			builder.append("MTI : ").append(msg.getMTI()).append("\n");
			for (int i=1; i<= msg.getMaxField(); i++) {
				if (msg.hasField(i)) {
					builder.append("DE-").append(i).append(" : ").append(msg.getString(i)).append("\n");
				}
			}
		} catch (ISOException e) {
			e.printStackTrace();
		} finally {
			// Nothing todo
		}
		
		return builder.toString();
	}

}
