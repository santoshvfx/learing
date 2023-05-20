//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package catalyst.messaging.transport;

import catalyst.config.Configuration;
import catalyst.messaging.discovery.Offer;
import catalyst.util.DocumentTransformer;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class HTTPSender extends Sender {
    private static final String CLASSNAME = HTTPSender.class.getName();
    private static final Logger logger;
    private static String DEFAULT_TIMEOUT;
    private static int SOCKET_CONN_TIMEOUT;
    private static final String CRLF = "\r\n";

    public HTTPSender() {
    }

    public Document sendAndReceive(HashMap ctx, Document msg, Offer offer) throws Exception {
        logger.entering(CLASSNAME, "sendAndReceive");
        Socket socket = null;

        try {
            String timeoutStr = (String)ctx.get("catalyst.messaging.transport.timeout");
            if (timeoutStr == null) {
                timeoutStr = DEFAULT_TIMEOUT;
            }

            int timeout = Integer.parseInt(timeoutStr);
            URL url = new URL(offer.getEndpoint());
            socket = new Socket();
            socket.setKeepAlive(true);
            socket.setReuseAddress(true);
            socket.setSoLinger(false, 0);
            socket.setSoTimeout(timeout);
            SocketAddress address = new InetSocketAddress(url.getHost(), url.getPort());
            System.out.println("HTTPSender address: " + address);
            socket.connect(address, SOCKET_CONN_TIMEOUT);
            OutputStream out = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            String path = url.getPath();
            if (path == null || path.trim().length() == 0) {
                path = "/";
            }

            out.write(("POST " + path + " HTTP/1.0\r\n").getBytes());
            out.write("Content-Type: text/xml; charset=utf-8\r\n".getBytes());
            out.write(("Host: " + url.getHost() + "\r\n").getBytes());
            DocumentTransformer transform = new DocumentTransformer(msg);
            byte[] bytes = transform.transformToString().getBytes();
            out.write(("Content-Length: " + bytes.length + "\r\n").getBytes());
            out.write("\r\n".getBytes());
            out.write(bytes);
            out.flush();
            Properties props = new Properties();
            String actionLine = this.readLine(is);
            String[] actionToks = actionLine.split(" ");
            String returnCode = null;
            String returnMsg = "";
            if (actionToks.length < 3) {
                throw new IOException("Response from server has an invalid HTTP header: " + actionLine);
            } else {
                returnCode = actionToks[1];

                for(int i = 2; i < actionToks.length; ++i) {
                    returnMsg = returnMsg + " " + actionToks[i];
                }

                while(true) {
                    String contentLengthStr = this.readLine(is);
                    int length;
                    if (contentLengthStr.trim().equals("\r\n") || contentLengthStr.trim().equals("")) {
                        contentLengthStr = props.getProperty("Content-Length");
                        if (!returnCode.trim().equals("200") && contentLengthStr == null) {
                            throw new IOException("HTTP " + returnCode + " Returned with No Fault.  Server Error: " + returnMsg);
                        }

                        length = 0;
                        if (contentLengthStr != null) {
                            length = Integer.parseInt(contentLengthStr);
                        }

                        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = builderFactory.newDocumentBuilder();
                        logger.exiting(CLASSNAME, "sendAndReceive");
                        Document doc = null;
                        if (length == 0) {
                            doc = builder.parse(is);
                        } else {
                            doc = builder.parse(new ContentLengthInputStream(is, length));
                        }

                        Document var25 = doc;
                        return var25;
                    }

                    length = contentLengthStr.indexOf(":");
                    String key = contentLengthStr.substring(0, length);
                    String value = contentLengthStr.substring(length + 1).trim();
                    props.setProperty(key, value);
                }
            }
        } catch (Exception var29) {
            var29.printStackTrace();
            throw var29;
        } finally {
            if (socket != null) {
                socket.close();
            }

        }
    }

    private String readLine(InputStream input) throws IOException {
        StringBuffer buffer;
        int i;
        for(buffer = new StringBuffer(); (i = input.read()) != -1; buffer.append((char)i)) {
            if (i == 13) {
                int i2 = input.read();
                if (i2 == 10) {
                    return buffer.toString();
                }
            }
        }

        return buffer.toString();
    }

    static {
        logger = Logger.getLogger(CLASSNAME);
        DEFAULT_TIMEOUT = "30000";
        SOCKET_CONN_TIMEOUT = 1000;
        Configuration config = Configuration.getDefaultConfiguration();
        if (config == null) {
            throw new RuntimeException("HTTPSender could not initialize because Configuration is null (UNEXPECTED!!)");
        } else {
            String socketConnTimeoutStr = config.getProperty("catalyst.messaging.transport.HTTPSender.socketConnTimeoutMs", "1000");
            SOCKET_CONN_TIMEOUT = Integer.parseInt(socketConnTimeoutStr);
            String defaultReadTimeout = config.getProperty("catalyst.messaging.transport.HTTPSender.defaultReadTimeoutMs", "30000");
            DEFAULT_TIMEOUT = defaultReadTimeout;
        }
    }
}
