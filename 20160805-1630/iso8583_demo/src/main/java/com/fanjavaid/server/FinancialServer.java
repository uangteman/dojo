/**
 * 
 */
package com.fanjavaid.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.fanjavaid.handler.FinancialServerHandler;

/**
 * @author fanjavaid
 *
 */
public class FinancialServer {
	private static final int PORT = 1992;
	
	public static void main(String [] args) throws IOException {
		// Class yang disediakan oleh Apache Mina untuk menghandle TCP/IP Connections (Socket Based)
		IoAcceptor acceptor = new NioSocketAcceptor();
		
		// Tambahkan beberapa filter
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		
		// Set handler class
		// Handler digunakan untuk menangani request yang datang
		acceptor.setHandler(new FinancialServerHandler());
		
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
		// Binding acceptor ke PORT yang sudah dideklarasikan
		acceptor.bind(new InetSocketAddress(PORT));
	}
}
