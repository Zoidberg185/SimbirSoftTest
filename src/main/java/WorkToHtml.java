import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class WorkToHtml {

    private String[] site;

    public WorkToHtml(String[] sites) {
        site = sites;
    }

    public void saveHTMLPages() {
        for (int i = 0; i < site.length; i++) {
            try {
                String pageName = "site_" + i + ".html";
                OutputStream out = new FileOutputStream(pageName);

                URL url = new URL(site[i]);
                URLConnection conn = url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();

                copy(is, out);
                System.out.println("File save");
                is.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copy(InputStream from, OutputStream to) {
        byte[] buffer = new byte[4096];
        try {
            while (true) {
                int numBytes = from.read(buffer);
                if (numBytes == -1) {
                    break;
                }
                to.write(buffer, 0, numBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
