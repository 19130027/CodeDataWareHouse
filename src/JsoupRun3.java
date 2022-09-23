import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupRun3 {
    public String tgCapNhat = null;

    public ArrayList<Gold> crawlData() throws IOException {
        int i = 0;
        String khuVuc = null;
        String loaiVang = null;
        String naturalKey = null;
        Document doc = Jsoup.connect("https://giavang.org/").timeout(8000).get();// Link gốc đây nha ae =)))
        Elements body = doc.select("tbody");
        int len = body.select("tr").size();
        String arrS1[] = new String[len];
        String arrS2[] = new String[len];
        int arrS3[] = new int[len];
        int arrS4[] = new int[len];
        int arrS5[] = new int[len];
        ArrayList<Gold> listGoldData = new ArrayList<Gold>();
        Gold gold = new Gold(null, null, null, 0, 0, 0, null, null);
        for (Element e : body.select("tr")) {
            khuVuc = e.select("th.text-left a").text().trim();
            loaiVang = e.select("td.text-left").text().trim();
            String giaMua = e.select("td:nth-of-type(2)").text().trim().concat(".000");
            int giaMuaConvert = convertToNumber(giaMua);
            String giaBan = e.select("td:nth-of-type(3)").text().trim().concat(".000");
            int giaBanConvert = convertToNumber(giaBan);
            int chenhLech = giaBanConvert - giaMuaConvert;
            tgCapNhat = e.select("td i").text().trim().replaceAll("https://giavang.org/", "");
            tgCapNhat = tgCapNhat.replaceAll("Cập nhật lúc ", "");
            arrS1[i] = khuVuc;
            arrS2[i] = loaiVang;
            arrS3[i] = giaMuaConvert;
            arrS4[i] = giaBanConvert;
            arrS5[i] = chenhLech;
            i++;
        }
        i = 1;
        for (int x = 0; x < arrS1.length - 1; x++) {
            if (arrS1[x].equalsIgnoreCase("") && (x != 0)) {
                arrS1[x] = arrS1[x - 1];

            }
            if (x == arrS1.length - 2) {
                arrS1[x + 1] = tgCapNhat;
            }
            naturalKey = arrS1[x] + "_" + arrS2[x];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            gold = new Gold(naturalKey, arrS1[x], arrS2[x], arrS3[x], arrS4[x], arrS5[x], tgCapNhat, dtf.format(now));
            listGoldData.add(gold);
//			System.out.println(gold.toString());
        }
        return listGoldData;
    }

    public static int convertToNumber(String data) {
        int num = 0;
        int result = 0;
        StringBuffer sb = new StringBuffer();
        sb.append(data);
        String arrStr[] = sb.toString().split("");
        for (int x = 0; x < arrStr.length; x++) {
            if (arrStr[x].equalsIgnoreCase(".")) {
            } else {
                num = Integer.parseInt(arrStr[x]);
                result = (int) ((result * 10) + num);
            }
        }
        return result;
    }

    public static String fileExcelPath() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy_HH-mm");
        LocalDateTime now = LocalDateTime.now();
        String fileName = "DataWareHouse_" + dtf.format(now).replaceAll("-", "h");
        String changePath = "D:\\DataWareHouse\\datawarehouse\\";//Nhớ đổi đường dẫn
        String excelFilePath = changePath + fileName + ".xlsx";
        return excelFilePath;
    }

    public static void main(String[] args) throws IOException {
        JsoupRun3 js = new JsoupRun3();
        ArrayList<Gold> data = js.crawlData();
        String filePath = fileExcelPath();
        WriteExcel.writeExcel(data, filePath);
    }
}
