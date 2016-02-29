import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;
import java.security.KeyStore;
import java.security.cert.*;
import java.util.jar.JarException;
import java.security.UnrecoverableKeyException;
import java.math.*;

/*
 * This example shows how to set up a key manager to perform client
 * authentication.
 *
 * This program assumes that the client is not inside a firewall.
 * The application can be modified to connect to a server outside
 * the firewall by following SSLSocketClientWithTunneling.java.
 */
public class client {
	public static void main(String[] args) throws Exception {
		String host = null;
		int port = -1;
		for (int i = 0; i < args.length; i++) {
			System.out.println("args[" + i + "] = " + args[i]);
		}
		if (args.length < 2) {
			System.out.println("USAGE: java client host port");
			System.exit(-1);
		}
		try { /* get input parameters */
			host = args[0];
			port = Integer.parseInt(args[1]);
		} catch (IllegalArgumentException e) {
			System.out.println("USAGE: java client host port");
			System.exit(-1);
		}

		try { /* set up a key manager for client authentication */
			SSLSocketFactory factory = null;
			try {
				System.out.print("PASSWORD: ");
				char[] password = System.console().readPassword();
				KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
				KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
				TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
				SSLContext ctx = SSLContext.getInstance("TLS");
				try {
					ks.load(new FileInputStream("clientkeystore"), password); // keystore
					// password
				}

				catch (Exception e) {
					System.out.println("wrong password");	
					return;
				}											// (storepass)
					ts.load(new FileInputStream("clienttruststore"), password); // truststore
					// password
					// (storepass);
					kmf.init(ks, password); // user password (keypass)
					tmf.init(ts); // keystore can be used as truststore here
					ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
					factory = ctx.getSocketFactory();

				} catch (Exception e) {
					throw new IOException(e.getMessage());
				}
				SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
				System.out.println("\nsocket before handshake:\n" + socket + "\n");

				/*
				 * send http request
				 *
				 * See SSLSocketClient.java for more information about why there is
				 * a forced handshake here when using PrintWriters.
				 */
				socket.startHandshake();

				SSLSession session = socket.getSession();
				X509Certificate cert = (X509Certificate) session.getPeerCertificateChain()[0];
				String subject = cert.getSubjectDN().getName();
				String issuer = cert.getIssuerDN().getName();
				BigInteger bsn = cert.getSerialNumber();
				System.out.println(
				    "certificate name (subject DN field) on certificate received from server:\n" + subject + "\n");
				System.out.println("issuer:\n" + issuer + "\n");
				System.out.println("serial number: " + bsn);
				System.out.println("socket after handshake:\n" + socket + "\n");
				System.out.println("secure connection established\n\n");

				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				for (;;) {
                    String rcv = "";
					while (!rcv.equals("null")) {
                        System.out.println(rcv);
                        rcv = in.readLine();
                    }
				    String msg = stdin.readLine();
                    System.out.println("> " + msg);
					out.println(msg);
					out.flush();
                    if (msg == "q") {
						break;
					}
				}
				in.close();
				out.close();
				stdin.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
