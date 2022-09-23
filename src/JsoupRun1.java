import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupRun1 {

	public String[] crawlData() throws IOException {
		int i = 0;
		Document doc = Jsoup.connect(
				"https://www0025.trucbachconcert.com/cach-doc-bang-gia-vang/?fbclid=IwAR32ev_bSAmkTuIXBudkz_kZKT8JxEC6pOGawCJOa1zUZbu0GuYYSBnrf5Y")
				.timeout(10000).get();
		Elements body = doc.select("tbody");
		int len = body.select("tr").size();
		String arrS[] = new String[len];
		String arrS1[] = new String[len];
		String arrS2[] = new String[len];
		String arrS3[] = new String[len];
		String arrS4[] = new String[len];
		String result[] = new String[len];
		for (Element e : body.select("tr")) {
			String khuVuc = null;
			String loaiVang = e.select("td:nth-of-type(1)").text().trim();
			if (loaiVang.equals("SJC")) {
				khuVuc = "SJC";
			}
			String giaMua = e.select("td:nth-of-type(2)").text().trim().replaceAll(",", "");
//			double giaMuacd=Double.parseDouble(giaMua);
			String giaBan = e.select("td:nth-of-type(3)").text().trim().replaceAll(",", "");
			String chenhLech = e.select("td:nth-of-type(4)").text().trim().replaceAll(",", "");
			arrS[i] = loaiVang;
			arrS1[i] = giaMua;
			arrS2[i] = giaBan;
			arrS3[i] = chenhLech;
			arrS4[i] = khuVuc;
			i++;
		}
		for (int x = 0; x < arrS.length - 1; x++) {
			if (x >= 13 && x < 40) {
				String s = (String) (arrS4[x] + "," + arrS[x] + "," + arrS1[x] + "," + arrS2[x] + "," + arrS3[x]);
				result[x] = s;

			}
		}
		System.out.println(result);
		return result;
	}

	public static void Write(String fileName, String data[]) {
		File f = new File("datawarehouse\\" + fileName);
		System.out.println(f.getAbsolutePath());
		try {
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter writerutf8 = new OutputStreamWriter(fos, "UTF-8");
			PrintWriter writer = new PrintWriter(writerutf8, true);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			writer.println(dtf.format(now));
			for (int i = 0; i < data.length - 1; i++) {
				if (i >= 13 && i < 40) {
					System.out.println(data[i]);
					writer.println(data[i]);
				}
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		JsoupRun1 js = new JsoupRun1();
		js.crawlData();
//		LocalDateTime now = LocalDateTime.now();

//		js.Write("java.csv", js.crawlData());
	}

}
