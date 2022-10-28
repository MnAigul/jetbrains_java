package tracker;

import java.util.List;

public interface Speak {
    default String choice(Integer val) {
        switch (val) {
            case 0: return "Java";
            case 1: return "DSA";
            case 2: return "Databases";
            case 3: return "Spring";
        }
        return null;
    }
    default void say_most(List<Integer> a, List<Integer> b, List<Integer> c, List<Integer> d, List<Integer> e, List<Integer> f) {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.print("Most popular: ");
        for (int i = 0; i < a.size(); i++) {
            System.out.print(choice(a.get(i)));
            if (i != a.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
        System.out.print("Least popular: ");
        if (!a.equals(b)) {
            while (b.size() != 0) {
                System.out.print(choice(b.get(0)));
                if (b.size() > 1)
                    System.out.print(", ");
                b.remove(0);
            }
            System.out.println();
        } else {
            System.out.println("n/a");
        }
        System.out.print("Highest activity: ");
        for (int i = 0; i < c.size(); i++) {
            System.out.print(choice(c.get(i)));
            if (i != c.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
        System.out.print("Lowest activity: ");
        if (!c.equals(d)) {
            while (d.size() != 0) {
                System.out.print(choice(d.get(0)));
                if (d.size() != 1)
                    System.out.print(", ");
                d.remove(0);
            }
            System.out.println();
        } else {
            System.out.println("n/a");
        }
        System.out.print("Easiest course: ");
        for (int i = 0; i < e.size(); i++) {
            System.out.print(choice(e.get(i)));
            if (i != e.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
        System.out.print("Hardest course: ");
        if (!e.equals(f)) {
            while (f.size() != 0) {
                System.out.print(choice(f.get(0)));
                if (f.size() != 1)
                    System.out.print(", ");
                f.remove(0);
            }
            System.out.println();
        } else {
            System.out.println("n/a");
        }
    }
    default void say_error() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println("Most popular: " + "n/a");
        System.out.println("Least popular: " + "n/a");
        System.out.println("Highest activity: " + "n/a");
        System.out.println("Lowest activity: " + "n/a");
        System.out.println("Easiest course: " + "n/a");
        System.out.println("Hardest course: " + "n/a");
    }
}
