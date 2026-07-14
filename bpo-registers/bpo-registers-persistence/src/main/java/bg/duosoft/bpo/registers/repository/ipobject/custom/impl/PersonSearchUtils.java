package bg.duosoft.bpo.registers.repository.ipobject.custom.impl;

/**
 * Created by Raya
 * 05.11.2020
 */
class PersonSearchUtils {

    public static String normalizeString(String toNormalize){
        String result = null;
        if(toNormalize != null){
            result = toNormalize.replaceAll("[\"'\\.„]", "");
            result = result.replaceAll("\\s{2,}", " ");
            result = result.toLowerCase();
        }
        return result;
    }

    public static String buildMatchingSequentialWords(String source){
        String[] normalizedNameEntries = normalizeString(source).split(" ");
        StringBuilder valueBuilder = new StringBuilder();
        for(int i=0; i<normalizedNameEntries.length; i++){
            valueBuilder.append("%").append(normalizedNameEntries[i].trim());
            if(i == normalizedNameEntries.length-1){
                valueBuilder.append("%");
            }
        }
        return valueBuilder.toString();
    }

    public static String getLongestWord(String[] sourceArray){
        int longest = -1, index = -1;
        for(int i=0; i< sourceArray.length; i++){
            if(sourceArray[i].length() > longest){
                longest = sourceArray[i].length();
                index = i;
            }
        }
        return sourceArray[index];
    }
}
