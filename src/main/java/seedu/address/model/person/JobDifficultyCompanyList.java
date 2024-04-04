package seedu.address.model.person;

import java.util.HashMap;

/**
 * Represents a list of companies and their corresponding job difficulty.
 */
public class JobDifficultyCompanyList {
    /**
     * A list of companies and their corresponding job difficulty.
     */
    public static final HashMap<String, Integer> JOBDIFFCULTYLIST = new HashMap<String, Integer>() {
        {
            put("google", 70);
            put("googles", 70);
            put("facebook", 70);
            put("amazon", 70);
            put("apple", 70);
            put("apples", 70);
            put("microsoft", 70);
            put("netflix", 70);
            put("twitter", 70);
            put("linkedIn", 70);
            put("airbnb", 70);
            put("uber", 70);
            put("grab", 50);
            put("shopee", 50);
            put("lazada", 50);
            put("carousell", 50);
            put("tikTok", 60);
            put("byteDance", 50);
            put("tencent", 60);
            put("alibaba", 60);
            put("baidu", 60);
            put("jd", 60);
            put("meituan-dianping", 50);
            put("didichuxing", 50);
            put("xiaomi", 50);
            put("huawei", 50);
            put("oppo", 50);
            put("vivo", 50);
            put("oneplus", 50);
            put("realme", 50);
            put("lenovo", 55);
            put("asus", 55);
            put("acer", 55);
            put("dell", 55);
            put("hp", 55);
            put("sony", 65);
            put("samsung", 65);
            put("lg", 65);
            put("nokia", 55);
            put("motorola", 55);
            put("ibm", 70);
            put("oracle", 70);
            put("sap", 70);
            put("vmware", 70);
            put("cisco", 70);
            put("gm", 70);
            put("accenture", 70);
            put("tata consultancy services", 70);
            put("tata", 60);
            put("wipro", 60);
            put("sea", 60);
            put("singtel", 60);
            put("tiktok", 60);
            put("pg", 50);
            put("p & g", 50);
            put("p&g", 50);
            put("sp", 50);
            put("ministry of defence", 50);
            put("mindef", 60);
            put("changi airport group", 50);
            put("cag", 60);
            put("goto Group", 50);
            put("goto", 50);
            put("dbs", 65);
            put("dbs bank", 65);
            put("ocbc", 65);
            put("uob", 65);
            put("ncs", 55);
            put("jtc", 55);
            put("jtc corporation", 55);
            put("temasek", 70);
            put("temasek holdings", 70);
            put("st engineering", 55);
            put("st", 55);
            put("gic", 70);
            put("deloitte", 70);
            put("bnp paribas", 55);
            put("dsta", 60);
            put("smrt", 50);
            put("micron", 50);
            put("micron technology", 50);
            put("phillip", 55);
            put("phillip securities", 55);
            put("singhealth", 50);
            put("jp morgan", 70);
            put("bank of america", 65);
            put("cognizant", 60);
            put("pricewaterhouseCoopers", 70);
            put("pwc", 70);
            put("govtech", 60);
            put("meta", 70);
        }
    };

    public static HashMap<String, Integer> getJobDifficultyCompanyList() {
        return JOBDIFFCULTYLIST;
    }
}
