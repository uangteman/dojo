/**
 * 
 */
package com.fanjavaid.service;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;


/**
 * @author fanjavaid
 *
 */
public class ISO8583Service {
	private ISOPackager packager;

	public ISO8583Service(ISOPackager packager) {
		super();
		this.packager = packager;
	}
	
	/**
	 * @param message
	 * @return
	 * @throws ISOException
	 * @throws Exception
	 */
	public ISOMsg unpackRequest(String message) throws ISOException {  
        ISOMsg isoMsg = new ISOMsg();  
        isoMsg.setPackager(packager);  
        
        isoMsg.unpack(message.getBytes());
                
        return isoMsg;
    }
	
	public ISOMsg responseRequest(ISOMsg msg) throws ISOException {
		ISOMsg reply = (ISOMsg) msg.clone();
		reply.setPackager(packager);
		
		reply.setMTI("0210");
		reply.set(39, "00");
		
		return reply;
	}
	
	/**
	 * @param msg
	 * @return
	 * Untuk validasi message ISO8583
	 */
	public boolean testISOMsg(String msg) {
		boolean isValid = false;
		
		try {
			unpackRequest(msg);
			isValid = true;
		} catch (ISOException e) {
			isValid = false;
		} catch (Exception e) {
			isValid = false;
		}
		
		return isValid;
	}
}
