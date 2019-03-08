package project.data;

import lombok.Data;
import project.model.memory.Memory;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class MemoryLoader {

    public static void loadData() {
        Map<String, String> locationAndContent = new LinkedHashMap<>();

        //everything is in hex!
        //opcode is first 4 bits = 1st digit

        //Load into memory =================================================
        locationAndContent.put("000100", "10000103");// *****adda $000103***
        locationAndContent.put("000101", "00");
        locationAndContent.put("000102", "00");
        locationAndContent.put("000103", "00000023");// adda 23
        //==================================================================


        //Load into memory =================================================
        locationAndContent.put("000200", "20000203");// *****lda $000203***
        locationAndContent.put("000201", "00");
        locationAndContent.put("000202", "00");
        locationAndContent.put("000203", "00000044");// lda 44
        //==================================================================


        //Load into memory =================================================
        locationAndContent.put("000300", "30000303");// *****anda $000303***
        locationAndContent.put("000301", "00");
        locationAndContent.put("000302", "00");
        locationAndContent.put("000303", "12345678");// anda 42
        //==================================================================


        //Load into memory =================================================
        locationAndContent.put("000400", "40000403");// *****sta $000403****
        locationAndContent.put("000401", "00");
        locationAndContent.put("000402", "00");
        locationAndContent.put("000403", "AAAAAAAA");// sta
        //==================================================================


        //Load into memory =================================================
        locationAndContent.put("000500", "50000000");// *****shr ***********
        locationAndContent.put("000501", "00");
        locationAndContent.put("000502", "00");
        locationAndContent.put("000503", "00");// sta
        //==================================================================

        //Load into memory =================================================
        locationAndContent.put("000600", "60000662");// *****jmpz $000662 **
        locationAndContent.put("000601", "00");
        locationAndContent.put("000602", "00");
        locationAndContent.put("000603", "00");
        locationAndContent.put("000662", "11111111");
        //==================================================================


        //Load into memory =================================================
        locationAndContent.put("000700", "70000762");// *****jmp $000662 ***
        locationAndContent.put("000701", "00");
        locationAndContent.put("000702", "00");
        locationAndContent.put("000703", "00");
        locationAndContent.put("000762", "42424242");
        //==================================================================


        //Load into memory =================================================
        locationAndContent.put("000800", "80000000");// *****coma **********
        locationAndContent.put("000801", "00");
        locationAndContent.put("000802", "00");
        locationAndContent.put("000803", "00");
        locationAndContent.put("000862", "11211212");
        //==================================================================


        Memory.getInstance().setLocationAndContent(locationAndContent);
    }
}
