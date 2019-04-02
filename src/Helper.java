import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {
    public static String [] drzave = new String[]{"USA or Canada", "USA", "USA or Canada",
            "USA", "Palestine", "France and Monaco", "Bulgaria", "Slovenia",
            "Croatia", "Bosnia and Herzegovina", "Montenegro",
            "Kosovo", "Germany", "Japan", "Russia",
            "Kyrgyzstan", "Taiwan", "Estonia", "Latvia",
            "Azerbaijan", "Lithuania",
            "Uzbekistan", "Sri Lanka", "Phillippines",
            "Belarus", "Ukraine", "Turkemistan", "Moldova",
            "Armenia", "Georgia", "Kazakhstan", "Tajikistan",
            "Hong Kong", "Japan", "United Kingdom", "Greece",
            "Lebanon", "Cyprus", "Albania", "Macedonia",
            "Malta", "Ireland", "Belgium and Luxembourg",
            "Portugal", "Iceland", "Denmark", "Poland",
            "Romania", "Hungary", "South Africa", "Ghana",
            "Senegal", "Bahrain", "Mauritius", "Morocco",
            "Algeria", "Nigeria", "Kenya", "Ivory Coast",
            "Tunisia", "Tanzania", "Syria", "Egypt", "Brunei",
            "Lybia", "Jordan", "Iran", "Kuwait", "Saudi Arabia",
            "United Arab Emirates", "Finland", "China",
            "Norway", "Israel", "Sweden", "Guatemala",
            "El Salvador", "Honduras", "Nicaragua",
            "Costa Rica", "Panama", "Dominican Republic",
            "Mexico", "Canada", "Venezuela", "Switzerland and Liechtenstein",
            "Colombia", "Uruguay", "Peru", "Bolivia", "Argentina",
            "Chile", "Paraguay", "Ecuador", "Brazil", "Italy, San Marino and Vatican City",
            "Spain and Andorra", "Cuba", "Slovakia", "Czech Republic", "Serbia",
            "Mongolia", "North Koreas", "Turkey", "Netherlands", "South Korea",
            "Cambodia", "Thailand", "Singapore", "India", "Vietnam", "Pakistan", "Indonesia",
            "Austria", "Australia", "New Zealand", "Malaysia", "Macau"};

    public static String [] kode = new String[]{"0-19", "30-39", "60-99", "100-139",
            "275", "300-379", "380", "383", "385", "387", "389", "390",
            "400-440", "450-459", "460-469", "470", "471",
            "474", "475", "476", "477", "478", "479", "480",
            "481", "482", "483", "484", "485", "486", "487",
            "488", "489", "490-499", "500-509", "520-521",
            "528", "529", "530", "531", "535", "539", "540-549",
            "560", "569", "570-579", "590", "594", "599", "600-601",
            "603", "604", "608", "609", "611", "613", "615",
            "616", "618", "619", "620", "621", "622", "623",
            "624", "625", "626", "627", "628",
            "629", "640-649", "690-699", "700-709", "729",
            "730-739", "740", "741", "742", "743", "744",
            "745", "746", "750", "754-755", "759", "760-769",
            "770-771", "773", "775", "777", "778-779",
            "780", "784", "786", "789-790", "800-839",
            "840-849", "850", "858", "859", "860", "865", "867",
            "868-869", "870-879", "880", "884", "885", "888",
            "890", "893", "896", "899", "900-919", "930-939",
            "940-949", "955", "958"};

    public static BigDecimal davncaStopnja = new BigDecimal("1.22");

    public static boolean checkDigit(String EANCode){

        if(EANCode.length() < 8)
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

    public static String getDrzavaFromEAN(String EANCode) {
        if(EANCode.length() != 13)
        {
            return "";
        }

        if (!EANCode.matches("[0-9]+"))
        {
            return "";
        }

        int countryCode = Integer.parseInt(EANCode.substring(0, 3));

        for(int i = 0; i < kode.length; i++)
        {
            String koda = kode[i];

            if(koda.contains("-"))
            {
                String [] koda_split = koda.split("-");

                int from = Integer.parseInt(koda_split[0]);
                int to = Integer.parseInt(koda_split[1]);

                for(int j = from; j <= to; j++)
                {
                    if(countryCode == j)
                    {
                        return drzave[i];
                    }
                }
            }
            else
            {
                int code = Integer.parseInt(koda);

                if(countryCode == code)
                {
                    return drzave[i];
                }
            }
        }

        return "";
    }

    public static String drzavaToCode(String drzava) {
        String out = "";

        for(int i = 0; i < drzave.length; i++) {
            if(drzave[i].toLowerCase().equals(drzava.toLowerCase()))
            {
                out = getDrzavaByIndex(i);
                break;
            }
        }

        if(out.length() == 1)
        {
            return "00" + out;
        }
        else if(out.length() == 2)
        {
            return "0" + out;
        }
        else
        {
            return out;
        }
    }

    public static String getDrzavaByIndex(int i) {
        String koda = kode[i];
        String out =  koda;

        if(koda.contains("-")) {
            String[] koda_split = koda.split("-");

            int from = Integer.parseInt(koda_split[0]);
            int to = Integer.parseInt(koda_split[1]);

            out = Integer.toString(ThreadLocalRandom.current().nextInt(from, to + 1));

        }

        if(out.length() == 1)
        {
            return "00" + out;
        }
        else if(out.length() == 2)
        {
            return "0" + out;
        }
        else
        {
            return out;
        }
    }

    public static String GenerateEANTezaCena(String oddelek, String idIzdelka, int tezaGrami) {
        String teza = Integer.toString(tezaGrami);

        if(oddelek.length() != 3 || idIzdelka.length() != 4 || teza.length() == 0 || teza.length() > 4){
            return "";
        }

        String randomTwelve = oddelek;

        randomTwelve += idIzdelka;

        if(teza.length() == 1)
        {
            teza = "000" + teza;
        }
        else if(teza.length() == 2)
        {
            teza = "00" + teza;
        }
        else if(teza.length() == 3)
        {
            teza = "0" + teza;
        }

        randomTwelve += teza;

        if(randomTwelve.length() != 11){
            return "";
        }

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

        return randomTwelve;
    }

    public static int GetTezaFromEAN(String EAN12){
        int teza = 0;

        if(EAN12.length() != 12){
            return teza;
        }

        String tezaString = EAN12.substring(7, 11);

        teza = Integer.parseInt(tezaString);

        return teza;
    }

    public static Timestamp GetCouponVeljavnost(String EAN12) throws ParseException {
        if(EAN12.length() != 12){
            return null;
        }

        String datumString = EAN12.substring(2, 8);

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        Date parsedDate = dateFormat.parse(datumString);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        return timestamp;
    }

    public static int GetCouponPopust(String EAN12) throws ParseException {
        if(EAN12.length() != 12){
            return 0;
        }

        String popustString = EAN12.substring(8, 11);

        int popust = Integer.parseInt(popustString);

        return popust;
    }

    public static String GenerateCouponEAN(int procent, String veljaDo){
        String proc = Integer.toString(procent);

        if(veljaDo.length() != 6 || proc.length() == 0 || proc.length() > 3){
            return "";
        }

        String randomTwelve = "99";

        randomTwelve += veljaDo;

        if(proc.length() == 1)
        {
            proc = "00" + proc;
        }
        else if(proc.length() == 2)
        {
            proc = "0" + proc;
        }

        randomTwelve += proc;

        if(randomTwelve.length() != 11){
            return "";
        }

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

        return randomTwelve;
    }

    public static String GenerateRandomEAN(String drzava) {

        String randomTwelve = "";

        if(drzava != null) {
            randomTwelve = drzavaToCode(drzava);

        }
        else {
            randomTwelve = getDrzavaByIndex(ThreadLocalRandom.current().nextInt(0, kode.length));
        }

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

        return randomTwelve;
    }

    public static void WriteToFile(String path, String data){
        try(FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(data);
        } catch (IOException e) {
        }
    }

    public static String ReadToString(String path){
        String out = "";

        try(FileReader fileReader = new FileReader(path)) {
            int ch = fileReader.read();
            while(ch != -1) {
                out += (char)ch;
                ch = fileReader.read();
            }
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }

        return out;
    }
}
