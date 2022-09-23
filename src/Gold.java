public class Gold {
    private String id;
    private String khuVuc;
    private String heThong;
    private int giaMua;
    private int giaBan;
    private int chenhLech;
    private String upDatePage;
    private String timeCrawlData;

    public Gold(String id, String khuVuc, String heThong, int giaMua, int giaBan, int chenhLech, String upDatePage, String timeCrawlData) {
        this.id = id;
        this.khuVuc = khuVuc;
        this.heThong = heThong;
        this.giaMua = giaMua;
        this.giaBan = giaBan;
        this.chenhLech = chenhLech;
        this.upDatePage = upDatePage;
        this.timeCrawlData = timeCrawlData;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public String getHeThong() {
        return heThong;
    }

    public int getGiaMua() {
        return giaMua;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public int getChenhLech() {
        return chenhLech;
    }

    public String getId() {
        return id;
    }

    public String getUpDatePage() {
        return upDatePage;
    }

    public String getTimeCrawlData() {
        return timeCrawlData;
    }

    @Override
    public String toString() {
        return "Gold{" +
                "id=" + id +
                ", khuVuc='" + khuVuc + '\'' +
                ", heThong='" + heThong + '\'' +
                ", giaMua=" + giaMua +
                ", giaBan=" + giaBan +
                ", chenhLech=" + chenhLech +
                ", upDatePage='" + upDatePage + '\'' +
                ", timeCrawlData='" + timeCrawlData + '\'' +
                '}';
    }

}
