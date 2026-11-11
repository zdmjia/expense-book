
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
                    String amount = "";
                    while (true) {
                        System.out.println("请输入金额：");
                        amount = inport.nextLine();
                        try {
                            double value = Double.parseDouble(amount);   // 这回接住返回值
                            if (value < 0) {                             // ← 业务关：负数拦截
                                System.out.println("金额不能是负数！");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("金额必须是数字！");
                            continue;
                        }
                    }
                    System.out.println("请输入备注：");
                    String note = inport.nextLine();
                    records.add(type + "|" + amount + "|" + note);
                    System.out.println(records);
                    System.out.println("记账成功");

                    break;
                case "2":
                    System.out.println("2 查看全部");
                    if (records.size() == 0) {
                        System.out.println("还没有账单");
                        break;
                    }
                    double total = 0;

                    for (int i = 0; i < records.size(); ++i) {
                        System.out.print(i + 1 + "  ");
                        System.out.println(records.get(i));
                        String line = records.get(i);
                        String[] parts = line.split("\\|");
                        String amountText = parts[1];
                        total += Double.parseDouble(amountText);
                    }
                    System.out.println("总金额：" + total);
                    break;
                case "3":
                    System.out.println("3 删除");
                    for (int i = 0; i < records.size(); ++i) {
                        System.out.print(i + 1 + "  ");
                        System.out.println(records.get(i));
                    }
                    while (true) {
                        if (records.size() == 0) {
                            System.out.println("没有数据，返回主界面");
                            break;
                        }
                        System.out.println("请输入要删除的序号：");
                        String indexText = inport.nextLine();

                        try {
                            int index = Integer.parseInt(indexText);

                            if (index >= records.size() + 1 || index <= 0) {
                                System.out.println("没有这一条");
                                continue;
                            } else {
                                records.remove(index - 1);
                                System.out.println("删除成功");
                                if (records.size() == 0) System.out.println("数据已空");
                                else {
                                    for (int i = 0; i < records.size(); ++i) {
                                        System.out.print(i + 1 + "  ");
                                        System.out.println(records.get(i));
                                    }
                                }
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("只能输入数字，请重新输入");
                            continue;
                        }
                    }


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
