import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;

public class Artikel implements Searchable{
    private String ime;
    private BigDecimal cena;
    private String drzava;

    //za list
    private BarCode EAN;
    private int kolicina;

    public static BigDecimal davncaStopnja = new BigDecimal("1.22");

    public Artikel(String ime, BigDecimal cena) {
        this.ime = ime;
        this.cena = cena;
        this.kolicina = 1;
        this.setEAN();


    }

    public boolean search(String text)
    {
        if(ime.contains(text))
        {
            return true;
        }

        if(cena.toString().contains(text))
        {
            return true;
        }

        if(getEAN().contains(text))
        {
            return true;
        }

        if(Integer.toString(kolicina).contains(text))
        {
            return true;
        }

        return false;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getIme() {
        return ime;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getEAN() {
        return EAN.getCodeToEncode();
    }

    public void setEAN(){
        EAN = new BarCode();
        EAN.setCodeToEncode(GenerateRandomEAN());
        EAN.setSymbology(IBarCode.EAN13);
    }

    public void setEAN(String EAN13){
        EAN = new BarCode();
        EAN.setCodeToEncode(EAN13);
        EAN.setSymbology(IBarCode.EAN13);
    }

    public static boolean checkDigit(String EANCode){

        if(EANCode.length() != 13)
        {
            return false;
        }

        if (!EANCode.matches("[0-9]+"))
        {
            return false;
        }

        String idWithoutCheckdigit = new String(EANCode.substring(0, EANCode.length() - 1));

        int currCheckDigit = Integer.parseInt(EANCode.substring(EANCode.length() - 1));

        int sum = 0;

        int first = 3;

        if(idWithoutCheckdigit.length() % 2 == 0){
            first = 1;
        }

        for (int i = 0; i < idWithoutCheckdigit.length(); i++) {

            char ch = idWithoutCheckdigit.charAt(i);

            int digit = ch - 48;

            sum += digit * first;

            if(first == 1) {
                first = 3;
                continue;
            }

            if(first == 3){
                first = 1;
            }
        }

        sum = Math.abs(sum) + 10;

        int realCheckDigit = (10 - (sum % 10)) % 10;

        return (currCheckDigit == realCheckDigit);
    }

    public String getDrzavaFromEAN(String EANCode)
    {
        return "";
    }

    public String getDrzavaByIndex(int i)
    {
        return "";
    }

    public static String GenerateRandomEAN() {

        String randomTwelve = "383"; //Slovenia

        randomTwelve += Integer.toString(ThreadLocalRandom.current().nextInt(10000, 100000));
        randomTwelve += Integer.toString(ThreadLocalRandom.current().nextInt(1000, 10000));

        String idWithoutCheckdigit = new String(randomTwelve);
        idWithoutCheckdigit = idWithoutCheckdigit.trim().toUpperCase();

        int sum = 0;

        int first = 3;

        if(idWithoutCheckdigit.length() % 2 == 0){
            first = 1;
        }

        for (int i = 0; i < idWithoutCheckdigit.length(); i++) {

            char ch = idWithoutCheckdigit.charAt(i);

            int digit = ch - 48;

            sum += digit * first;

            if(first == 1) {
                first = 3;
                continue;
            }

            if(first == 3){
                first = 1;
            }
        }

        sum = Math.abs(sum) + 10;

        int checkDigit = (10 - (sum % 10)) % 10;
        randomTwelve += Integer.toString(checkDigit);

        BigInteger EAN = new BigInteger(randomTwelve);

        return EAN.toString();
    }
}
