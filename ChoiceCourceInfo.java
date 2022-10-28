package tracker;

import java.util.LinkedHashMap;

public interface ChoiceCourceInfo {
    default int switchCource(String a) {
        a = a.toLowerCase();
        if (a.equals("java")) {
            return 0;
        } else {
            if (a.equals("dsa")) {
                return 1;
            } else {
                if (a.equals("databases")) {
                    return 2;
                } else {
                    if (a.equals("spring"))
                        return 3;
                    else
                        return -1;
                }
            }
        }
    }
    void course(LinkedHashMap<Integer, Integer[]> map, int k);

    default String choice(Integer val) {
        switch (val) {
            case 0: return "Java";
            case 1: return "DSA";
            case 2: return "Databases";
            case 3: return "Spring";
        }
        return null;
    }
}
