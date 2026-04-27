import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "";

        while (true) {
            System.out.println("\n****************** MENU ******************");
            System.out.println("1. Nhập chuỗi");
            System.out.println("2. Đếm số ký tự thường, hoa, số, đặc biệt");
            System.out.println("3. Đảo ngược chuỗi");
            System.out.println("4. Kiểm tra Palindrome");
            System.out.println("5. Chuẩn hóa chuỗi");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập chuỗi bất kỳ: ");
                    str = sc.nextLine();
                    break;

                case 2:
                    if (str.isEmpty()) {
                        System.out.println("Vui lòng nhập chuỗi trước (chọn 1).");
                        break;
                    }
                    demKyTu(str);
                    break;

                case 3:
                    String reverse = new StringBuilder(str).reverse().toString();
                    System.out.println("Chuỗi đảo ngược: " + reverse);
                    break;

                case 4:
                    if (isPalindrome(str)) {
                        System.out.println("Đây là chuỗi Palindrome.");
                    } else {
                        System.out.println("Đây không phải chuỗi Palindrome.");
                    }
                    break;

                case 5:
                    str = chuanHoa(str);
                    System.out.println("Chuỗi sau khi chuẩn hóa: " + str);
                    break;

                case 6:
                    System.out.println("Tạm biệt HoangPK!");
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // Chức năng 2: Phân loại ký tự
    public static void demKyTu(String s) {
        int lower = 0, upper = 0, digit = 0, special = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) lower++;
            else if (Character.isUpperCase(c)) upper++;
            else if (Character.isDigit(c)) digit++;
            else special++;
        }
        System.out.println("Kết quả đếm:");
        System.out.println("- Chữ thường: " + lower);
        System.out.println("- Chữ hoa: " + upper);
        System.out.println("- Số: " + digit);
        System.out.println("- Ký tự đặc biệt: " + special);
    }

    // Chức năng 4: Kiểm tra đối xứng
    public static boolean isPalindrome(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        return s.equalsIgnoreCase(reverse);
    }

    // Chức năng 5: Chuẩn hóa chuỗi
    public static String chuanHoa(String s) {
        if (s == null || s.isEmpty()) return s;
        // Xóa khoảng trắng dư thừa ở đầu, cuối và giữa
        s = s.trim().replaceAll("\\s+", " ");
        // Viết hoa chữ cái đầu, các ký tự khác giữ nguyên
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}