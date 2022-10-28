package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Student{
    String firstname;
    String lastname;
    String email;
    static int count;
    int id;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Student() {
    }

    public Student(String firstname, String lastname, String email, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.id = id;
    }

    public static void counting(LinkedHashMap<Integer, Integer[]> map, int[] count_of_stud, int[] sum_points) {
        for (var a: map.entrySet()) {
            Integer[] t = a.getValue();
            for (int k = 0; k < 4; k++) {
                if (t[k] != 0) {
                    count_of_stud[k]++;
                    sum_points[k] += t[k];
                }
            }
        }



    }
    public static void notify(LinkedHashMap<Integer, Integer[]> map, List<Student> students) {
        int[] defaultpoints = new int[4];
        defaultpoints[0] = 600; defaultpoints[1] = 400; defaultpoints[2] = 480; defaultpoints[3] = 550;
        int sum = 0;
        for (var a: map.entrySet()) {
            Integer[] points = a.getValue();
            List<Integer> subjects = new LinkedList<>();
            boolean f = false;
            for (int i = 0; i < points.length; i++) {
                if (points[i] >= defaultpoints[i]) {
                    subjects.add(i);
                    points[i] = 0;
                    f = true;
                }
            }
            if (f) {
                Speak speak = new Speak() {
                    @Override
                    public String choice(Integer val) {
                        return Speak.super.choice(val);
                    }
                };
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.println("To: " + students.get(a.getKey() - 10000).getEmail());
                    System.out.println("Re: Your Learning Progress");
                    System.out.println("Hello, " + students.get(a.getKey() - 10000).getFirstname() + " " +
                            students.get(a.getKey() - 10000).getLastname() + "! You have accomplished our " + speak.choice(i) + " course!");;
                }
                sum++;
            }
            map.put(a.getKey(), points);
        }
        System.out.println("Total " + sum + " students have been notified.");
    }
    public static void statistics(LinkedHashMap<Integer, Integer[]> map, int[] otpravki) {
        int[] count_of_stud = new int[4];
        int[] sum_points = new int[4];
        int[] sredn_points = new int[4];
        counting(map, count_of_stud, sum_points);
        for (int i = 0; i < sredn_points.length; i++) {
            if (count_of_stud[i] != 0)
                sredn_points[i] = sum_points[i] / count_of_stud[i];
        }
        Func<int[], List<Integer>> most_least = new Func<int[], List<Integer>>() {
            @Override
            public List<Integer> most(int[] a) {
                List<Integer> list = new LinkedList<>();
                int most = a[0];
                for (int i = 0; i < a.length; i++) {
                    if (a[i] > most) {
                        most = a[i];
                    }
                }
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == most) {
                        list.add(i);
                    }
                }
                return list;
            }
            @Override
            public List<Integer> least(int[] a) {
                List<Integer> list = new LinkedList<>();
                int least = a[0];
                for (int i = 0; i < a.length; i++) {
                    if (a[i] < least) {

                        least = a[i];
                    }
                }
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == least) {
                        list.add(i);
                    }
                }
                return list;
            }
        };
        boolean b = false;
        for (int i = 0; i < count_of_stud.length; i++)
            if (count_of_stud[i] != 0)
                b = true;
        Speak interfaceOutInfo = new Speak() {};
        List<String> error = new LinkedList<>();
        error.add("n/a");
        if (!b)
            interfaceOutInfo.say_error();
        else
            interfaceOutInfo.say_most(most_least.most(count_of_stud), most_least.least(count_of_stud),
                    most_least.most(otpravki),most_least.least(otpravki),
                    most_least.most(sredn_points), most_least.least(sredn_points));
        choiceCource(map);


    }
    public static void choiceCource(LinkedHashMap<Integer, Integer[]> map) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        int[] defaultpoints = new int[4];
        defaultpoints[0] = 600; defaultpoints[1] = 400; defaultpoints[2] = 480; defaultpoints[3] = 550;
        ChoiceCourceInfo choiceCourceInfo = new ChoiceCourceInfo() {
            @Override
            public void course(LinkedHashMap<Integer, Integer[]> map, int k) {
                System.out.println(choice(k));
                System.out.println("id    points    completed");
                LinkedHashMap<Integer, Integer> list = new LinkedHashMap<>();
                for (var a: map.entrySet()) {
                    Integer[] points = new Integer[4];
                    points = a.getValue();
                    if (points[k] != 0)
                        list.put(a.getKey(), points[k]);
                }
                ArrayList<Map.Entry<Integer, Integer>> l = new ArrayList<>(list.entrySet());
                Collections.sort(l, new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                        if (a.getValue().equals(b.getValue())) {
                            return a.getKey() - b.getKey();
                        } else {
                            return b.getValue() - a.getValue();
                        }
                    }
                });
                for (Map.Entry<Integer, Integer> a: l) {
                    String soon = (a.getValue() / 10 > 0) ? "" : " ";
                    BigDecimal f = BigDecimal.valueOf(a.getValue() * 100.0 / defaultpoints[k]);
                    f = f.setScale(1, RoundingMode.HALF_UP);
                    System.out.println(a.getKey() + " " + a.getValue() + "        " + soon + f + "%");
                }
            }
        };
        while (!a.equals("back")) {
            int k = choiceCourceInfo.switchCource(a);
            if (k == -1) {
                System.out.println("Unknown course.");
            } else {
                choiceCourceInfo.course(map, k);
            }
            a = sc.nextLine();
        }
    }
    public static void add(String input, LinkedHashMap<Integer, Integer[]> map, HashSet<String> email_set, List<Student> students) {
        String[] fioemail = input.split(" ");
        if (fioemail.length < 3) {
            System.out.println("Incorrect credentials.");
        } else {
            String firstname = fioemail[0];
            String lastname = fioemail[1];
            int i = 2;
            while (i != fioemail.length - 1) {
                lastname += " ";
                lastname += fioemail[i];
                i++;
            }
            String email = fioemail[fioemail.length - 1];
            if (!checkwordofname(firstname)) {
                System.out.println("Incorrect first name.");
            } else {
                if (!checklastname(lastname)) {
                    System.out.println("Incorrect last name.");
                } else {
                    if (!checkemail(email)) {
                        System.out.println("Incorrect email.");
                    } else {
                        if (!checkEmailAieadytaken(email, map, email_set)) {
                            System.out.println("The student has been added.");
                            Student student = new Student(firstname,lastname,email, 1000 + count);
                            count++;
                            students.add(student);
                        } else {
                            System.out.println("This email is already taken.");
                        }


                    }
                }
            }
        }


    }
    public static void addPoints(LinkedHashMap<Integer, Integer[]> map, int[] otpravki) {
        System.out.println("Enter an id and points or 'back' to return:");
        Scanner scanner = new Scanner(System.in);
        String h = scanner.nextLine();
        Integer a[];
        while (!h.equals("back")) {
            String str [] = h.split(" ");
            String regex = "[0-9]+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;
            matcher = pattern.matcher(str[0]);
            if (!matcher.matches()) {
                System.out.println("No student is found for id=" + str[0] + ".");
            } else {
                Integer key = Integer.parseInt(str[0]);
                if (map.containsKey(key)) {
                    a = map.get(key);
                    Integer c[] = new Integer[5];
                    if (!correctPoints(str, c)) {
                        System.out.println("Incorrect points format.");
                    } else {
                        for (int i = 0, k = 1; i < 4; i++, k++) {
                            a[i] += c[k];
                            if (c[k] != 0)
                                otpravki[k - 1] ++;
                        }
                        map.replace(key, a);
                        System.out.println("Points updated.");
                    }
                } else {
                    System.out.println("No student is found for id=" + key + ".");
                }
            }

            h = scanner.nextLine();
        }
    }

    public static void find(LinkedHashMap<Integer, Integer[]> map) {
        System.out.println("Enter an id or 'back' to return:");
        Scanner sc = new Scanner(System.in);
        String h = sc.nextLine();
        while (!h.equals("back")) {
            Integer b = Integer.parseInt(h);
            if (map.containsKey(b)) {
                Integer t[] = new Integer[4];
                t = map.get(b);
                System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d", b, t[0], t[1], t[2], t[3]);
                System.out.println();
            } else {
                System.out.println("No student is found for id=" + b + ".");
            }
            h = sc.nextLine();
        }
    }

    public static boolean correctPoints(String str[], Integer b[]) {
        if (str.length != 5)
            return false;
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        for (int i = 0; i < 5; i++) {
            matcher = pattern.matcher(str[i]);
            if (!matcher.matches())
                return false;
            b[i] = Integer.parseInt(str[i]);
        }
        return true;
    }


    public static void list(LinkedHashMap<Integer, Integer[]> map) {
        if (map.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            for(var a: map.entrySet()) {
                System.out.println(a.getKey());
            }
        }

    }

    public static boolean checkEmailAieadytaken(String new_email, LinkedHashMap<Integer, Integer[]> map, HashSet<String> email_set) {
        if (email_set.contains(new_email)) {
            return true;
        } else {
            email_set.add(new_email);
            map.put(10000 + count, new Integer[]{0,0,0,0});
            return false;
        }

    }
    public static boolean checkwordofname(String fio) {
        //String regex = "^[A-Za-z][A-Za-z('-){1}]*[A-Za-z]$";
        String regex = "^[A-Za-z]((['-]?[A-Za-z]+)*|([A-Za-z]+['-]?)*|(['-])?)[A-Za-z]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fio);
        return matcher.matches();
    }

    public static boolean checklastname(String fio) {
        String[] lastn = fio.split(" ");
        boolean b = true;
        for (int i = 0; i < lastn.length && b; i++) {
            if (!checkwordofname(lastn[i]))
                b = false;
        }
        return b;
    }

    public static boolean checkemail(String email) {
        //String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static int getCount() {
        Student student = new Student();
        return student.count;
    }

}
