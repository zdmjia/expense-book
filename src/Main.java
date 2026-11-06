
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> records = new ArrayList<>();
    public static void main(String[] args) {
        Scanner inport = new Scanner(System.in);
        while (true) {
            System.out.println("1 记一笔 ");
            System.out.println("2 查看全部");
            System.out.println("3 删除");
            System.out.println("4 退出");

            String number = inport.nextLine();
            switch (number) {
                case "1":
                    System.out.println("1 记一笔");
                    System.out.println("请输入类型：");
                    String type = inport.nextLine();
                    System.out.println("请输入金额：");
                    String amount = inport.nextLine();
                    System.out.println("请输入备注：");
                    String note = inport.nextLine();
                    records.add(type+"|"+amount+"|"+note);
                    System.out.println(records);
                    System.out.println("记账成功");

                    break;
                case "2":
                    System.out.println("2 查看全部");
                    break;
                case "3":
                    System.out.println("3 删除");
                    break;
                case "4":
                    System.out.println("4 退出");
                    return;
                default:
                    System.out.println("输入无效");

            }

        }

    }
}
