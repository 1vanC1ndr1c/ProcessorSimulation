package project.model.memory;

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

        //create empty memory
        //fill the first 100(16) locations with "00000000"
        String hexKey = "000000";
        String maxLimit = "000100";
        Integer intMaxLimit = Integer.parseInt(maxLimit, 16);
        for (int i = 0; i < intMaxLimit; i++) {
            locationAndContent.put(hexKey, "00000000");
            Integer hexKeyIntValue = Integer.parseInt(hexKey, 16);
            hexKeyIntValue++;
            hexKey = Integer.toHexString(hexKeyIntValue);
            //when incrementing a number "0000x", the saved value will be "x + 1"
            //without the zeroes,
            //so put zeros in front of the number
            for (int j = 0; hexKey.length() < 6; j++) {
                hexKey = "0" + hexKey;
            }
        }

        //Load into memory =================================================
        locationAndContent.put("000021", "10000023");// *****adda $000003***
        locationAndContent.put("000023", "00000023");// adda 23
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000025", "20000027");// *****lda $000203***
        locationAndContent.put("000027", "00000044");// lda 44
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000029", "3000002b");// *****anda $000303***
        locationAndContent.put("00002b", "12345678");// anda 42
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("00002d", "4000002f");// *****sta $000403****
        locationAndContent.put("00002f", "AAAAAAAA");// sta
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000032", "50000000");// *****shr ***********
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000034", "60000036");// *****jmpz $000662 **
        locationAndContent.put("000036", "11111111");
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000038", "7000003a");// *****jmp $000662 ***
        locationAndContent.put("00003a", "42424242");
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("00003c", "80000000");// *****coma **********
        //==================================================================

        Memory.getInstance().setLocationAndContent(locationAndContent);
    }
}
