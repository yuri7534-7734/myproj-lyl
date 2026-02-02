class Korean {
    String nation = "대한민국";
    String name;
    String ssn;

    public Korean(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }
}

public class yr01 {
    public static void main(String[] args) {
        Korean k1 = new Korean("이유리","990610");
        Korean k2 = new Korean("윤채영","990321");
        System.out.println(k1.ssn);
        System.out.println(k2.name);
    }
}
