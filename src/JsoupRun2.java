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

public class JsoupRun2 {

	public String[] crawlData() throws IOException {
		int i = 0;
		Document doc = Jsoup.connect("https://sjc.com.vn/giavang/textContent.php").timeout(6000).get();
		Elements body = doc.select("tbody");
		int len = body.select("tr").size();
		String arrS[] = new String[len];
		String arrS1[] = new String[len];
		String arrS2[] = new String[len];
		String arrS3[] = new String[len];
		String arrS4[] = new String[len];
		String result[] = new String[len];
		for (Element e : body.select("tr")) {
			String khuVuc = "HCM";
			String loaiVang = e.select("td.br.bb.ylo2_text.p12").text().trim();
//			if (loaiVang.equals("SJC")) {
//				khuVuc = "SJC";
//			}
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

	public static void Write(String data[]) {
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yy_HH-mm-ss");// yyyy/MM-dd.HH-mm-ss
		LocalDateTime now1 = LocalDateTime.now();
		String fileName = dtf1.format(now1);
		File f = new File("datawarehouse\\" + fileName + ".csv");
		System.out.println(f.getAbsolutePath());
		try {
			FileOutputStream fos = new FileOutputStream(f);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			OutputStreamWriter writerutf8 = new OutputStreamWriter(fos, "UTF-8");
			PrintWriter writer = new PrintWriter(writerutf8, true);
			LocalDateTime now = LocalDateTime.now();
			writer.println(dtf.format(now));
			for (int i = 0; i < data.length - 1; i++) {
				writer.println(data[i]);
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		JsoupRun2 js = new JsoupRun2();
		js.crawlData();
//		js.Write("java.csv", js.crawlData());
	}

}
