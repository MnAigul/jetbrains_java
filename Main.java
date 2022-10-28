package tracker;

import java.util.*;

import static tracker.Student.*;

public class Main {
    public static void start() {
        System.out.println("Learning Progress Tracker");
        Scanner scanner = new Scanner (System.in);
        String a = scanner.nextLine();
        HashSet<String> emails_set = new HashSet<>();
        LinkedHashMap<Integer, Integer[]> map = new LinkedHashMap<>();
        List<Student> students = new LinkedList<>();
        int[] otpravki = new int[4];
        while (!a.equals("exit")) {
            if (a.isBlank()) {
                System.out.println("No input.");
            } else {
                if (a.equals("add students")) {
                    System.out.println("Enter student credentials or 'back' to return:");
                    a = scanner.nextLine();
                    while (!a.equals("back")) {
                        add(a, map, emails_set, students);
                        a = scanner.nextLine();
                    }
                    System.out.println("Total " + getCount() + " students have been added.");
                } else {
                    if (a.equals("list")) {
                        list(map);
                    } else {
                        if (a.equals("back")) {
                            System.out.println("Enter 'exit' to exit the program.");
                        } else {
                            if (a.equals("add points")) {
                                addPoints(map, otpravki);
                            } else {
                                if (a.equals("find")) {
                                    find(map);
                                } else {
                                    if (a.equals("statistics")) {
                                        statistics(map, otpravki);
                                    } else {
                                        if (a.equals("notify")) {
                                            notify(map, students);
                                        } else {
                                            System.out.println("Unknown command!");
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
            a = scanner.nextLine();
        }
        System.out.println("Bye!");
    }

    private static void notify(LinkedHashMap<Integer, Integer[]> map, List<Student> students) {
        Student.notify(map, students);
    }

    public static void main(String[] args) {
        start();
    }

}
