package POM;

public class OrderHistoryPOM extends BasePOM {
    public double StringToDouble(String str){
        return Double.parseDouble(str.replace("$",""));
    }
}
