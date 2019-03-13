package project.data;

import lombok.Data;
import project.model.memory.Memory;

import java.util.LinkedHashMap;
import java.util.Map;


@Data
public class MemoryLoader {

    //everything is in hex!
    //opcode is first 4 bits = 1st digit

    public static void loadData() {

        Map<String, String> locationAndContent = new LinkedHashMap<>();

        //first, fill the first 1000(16) locations with "00000000"
        String hexKey = "000000";
        String maxLimit = "000100";
        Integer intMaxLimit = Integer.parseInt(maxLimit, 16);
        for (int i = 0; i < intMaxLimit; i++) {
            locationAndContent.put(hexKey, "00000000");
            Integer hexKeyIntValue = Integer.parseInt(hexKey, 16);
            hexKeyIntValue++;
            hexKey = Integer.toHexString(hexKeyIntValue);
            for (int j = 0; hexKey.length() < 6; j++) {
                hexKey = "0" + hexKey;
            }
        }

        //Load into memory =================================================
        locationAndContent.put("000001", "10000003");// *****adda $000003***
        locationAndContent.put("000003", "00000023");// adda 23
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000005", "20000007");// *****lda $000203***
        locationAndContent.put("000007", "00000044");// lda 44
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000009", "3000000b");// *****anda $000303***
        locationAndContent.put("00000b", "12345678");// anda 42
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("00000d", "4000000f");// *****sta $000403****
        locationAndContent.put("00000f", "AAAAAAAA");// sta
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000012", "50000000");// *****shr ***********
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000014", "60000016");// *****jmpz $000662 **
        locationAndContent.put("000016", "11111111");
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000018", "7000001a");// *****jmp $000662 ***
        locationAndContent.put("00001a", "42424242");
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("00001c", "80000000");// *****coma **********
        //==================================================================

        Memory.getInstance().setLocationAndContent(locationAndContent);
    }
}
