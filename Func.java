package tracker;

public interface Func<T, R> {
    R most(T val);
    R least(T val);

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
