# 📒 控制台记账本 — 项目需求说明书

> **这份文档是怎么来的**：由 ZCode 根据你的现状定制（5 个月前学过 JavaSE 到 IO 流，大部分已遗忘）。
> **怎么用它**：从阶段 0 开始，一个阶段一个阶段做。每个阶段末尾有【验收标准】，全部打勾才能进入下一阶段。
> **卡住了怎么办**：先自己想 10~15 分钟，想不出来就把问题（报错截图/代码/你想干什么）发给 ZCode 问。
> **预计总耗时**：阶段 0~4 合计约 **3~6 周**（按每天 2 小时算）。

---

## 进度总览（做完一项勾一项）

- [ ] 阶段 0：环境准备 + 第一次上传 GitHub（约 1 天）
- [ ] 阶段 1：能用就行版——内存记账（约 3~5 天）
- [ ] 阶段 2：面向对象重构（约 4~7 天）⭐ 本项目重点
- [ ] 阶段 3：统计功能（约 4~7 天）
- [ ] 阶段 4：文件持久化——数据不丢（约 5~10 天）⭐ 复习你的 IO 流
- [ ] 阶段 5（可选）：进阶加分项

---

## 学习约定（先读完这个再动手）

1. **每天固定写代码，建议不少于 2 小时**。连续性比单次时长重要，宁可每天 1 小时，不要一周憋一次 7 小时。
2. **先自己想 10~15 分钟再问**。问的时候要说清楚三件事：想做什么、已经试了什么、完整报错是什么。
3. **可以让 AI（ZCode）讲知识点、帮你调 bug，但不要让 AI 直接生成整个功能**。AI 写的代码你抄一遍，等于没学——面试和工作中会原形毕露。
4. **每完成一个小功能就 `git commit` 一次，每天收工前 `git push` 一次**。你的 GitHub 提交记录就是你学习过程的证明，以后找工作能拿出来看。
5. **忘了是正常的**。这个项目会把 JavaSE 的核心知识全部强迫你用 3 遍以上，用着用着就想起来了。千万不要回头把视频课从头再看一遍——那是最大的时间黑洞。

---

# 阶段 0：环境准备 + 第一次上传 GitHub（约 1 天）

你电脑上已经有：**JDK 17 ✅、IDEA ✅**。还差：**Git**（重装系统时丢了）。

### 0.1 安装 Git

1. 打开 https://git-scm.com/download/win ，下载 64-bit Git for Windows Setup。
2. 安装时**一路点 Next 用默认选项**即可（默认会自动配置好环境变量）。
3. 装完后**重新打开一个命令行窗口**（旧的窗口认不到新装的东西），输入：
   ```
   git --version
   ```
   显示版本号（如 `git version 2.x.x`）就说明装好了。

### 0.2 注册 GitHub 账号

1. 打开 https://github.com ，点 Sign up 注册。
2. 用户名认真取：全小写字母+数字，好记好念——**以后简历上要写这个主页**。

### 0.3 告诉 Git 你是谁（装完 Git 必做一次）

在命令行执行（换成你自己的信息，邮箱用注册 GitHub 的那个）：
```
git config --global user.name "你的名字"
git config --global user.email "你的GitHub邮箱"
```

### 0.4 在 GitHub 上创建远程仓库

1. 登录 GitHub，点右上角 **+** → **New repository**。
2. Repository name 填：`expense-book`
3. 选 **Public**（公开，别人才能看到你的学习成果）。
4. **下面的 Add a README 等 checkbox 一个都不要勾**。
5. 点 **Create repository**。

### 0.5 用 IDEA 打开项目文件夹并跑通 Hello World

1. IDEA → File → Open → 选择 `expense-book` 这个文件夹（本文档所在目录）。
2. 在 `src` 下新建 `Main.java`，写一个最简单的程序：
   ```java
   public class Main {
       public static void main(String[] args) {
           System.out.println("记账本启动成功！");
       }
   }
   ```
3. 点运行，控制台打印出「记账本启动成功！」即通过。

### 0.6 第一次提交并推送到 GitHub

在 IDEA 下方的 **Terminal** 面板（就是命令行）里，依次执行：
```
git init
git add .
git commit -m "init: 项目需求文档 + Hello World"
git branch -M main
git remote add origin https://github.com/你的用户名/expense-book.git
git push -u origin main
```
> 第一次 push 会弹出 GitHub 登录窗口，用浏览器登录你的 GitHub 账号即可。

**✅ 阶段 0 验收标准：**
- [ ] 命令行 `git --version` 有输出
- [ ] 程序能打印「记账本启动成功！」
- [ ] 刷新 GitHub 仓库网页，能看到 `PROJECT.md` 和 `Main.java` 两个文件

**以后每天的固定动作（背下来）：**
```
git add .
git commit -m "阶段X: 干了什么事"
git push
```

---

# 阶段 1：能用就行版——内存记账（约 3~5 天）

> **目标**：不追求代码漂亮，先把程序跑起来。允许把所有代码都写在 `Main.java` 里，
> 但每个功能建议抽成一个独立的方法（`addRecord()`、`showAll()`……）。

### 功能需求

| 编号 | 功能 | 说明 |
|---|---|---|
| F1 | 菜单循环 | 启动后显示菜单：`1记一笔 2查看全部 3删除 4退出`，处理完一个操作后继续显示，直到选退出 |
| F2 | 记一笔 | 依次输入：类型（餐饮/交通/购物/娱乐/其他）、金额、备注，存入内存 |
| F3 | 查看全部 | 像表格一样打印所有账单，最后显示总支出 |
| F4 | 删除 | 输入序号，删除对应那一条 |
| F5 | 输入容错 | 金额输成字母等错误输入时，**程序不能崩溃**，提示后让用户重输 |

### 模块对应的核心思想（本阶段知识点）

| 模块 | 核心思想 |
|---|---|
| 菜单循环 | `while(true)` + `switch` 是所有控制台程序的心脏 |
| 键盘输入 | `Scanner` 的 `nextInt()` / `nextLine()`，以及两者混用的「吃回车」陷阱 |
| 数据存储 | 用 `ArrayList<String>` 存账单，每条拼成一个字符串如 `"餐饮|35.0|午饭"`——为什么比数组方便？ |
| 输入容错 | `hasNextDouble()` 先判断再读取；**异常的最外层兜底** `try-catch` |
| 代码组织 | 一个方法只做一件事，方法名说清楚它干什么 |

### 关键代码提示（只给骨架，血肉自己填）

菜单循环骨架：
```java
Scanner sc = new Scanner(System.in);
while (true) {
    printMenu();
    String choice = sc.nextLine();
    switch (choice) {
        case "1": addRecord(); break;
        case "2": showAll();   break;
        case "3": delete();    break;
        case "4": System.out.println("再见！"); return;
        default:  System.out.println("输入无效，请重新输入");
    }
}
```

金额容错骨架：
```java
double amount;
while (true) {
    System.out.print("请输入金额：");
    if (sc.hasNextDouble()) {
        amount = sc.nextDouble();
        sc.nextLine(); // 吃掉留在缓冲区的回车，否则下一个 nextLine 会读到空
        break;
    } else {
        System.out.println("金额必须是数字！");
        sc.next(); // 把错误的输入丢掉
    }
}
```

**思考题（做的时候问自己）**：
- 用 `double` 存金额有什么隐患？（提示：`0.1 + 0.2 == 0.3` 是 true 还是 false？跑一下试试）
- 删除某一条之后，其余数据的序号要怎么处理才不乱？

**✅ 阶段 1 验收标准：**
- [ ] 连续记 3 笔 → 查看 → 删 1 笔 → 再查看，数据都正确
- [ ] 金额输入 `abc`，程序提示重新输入而不是崩溃退出
- [ ] `git log` 里至少有 5 个 commit（说明你是分步做的，不是一口气糊完）

---

# 阶段 2：面向对象重构（约 4~7 天）⭐ 本项目重点

> **目标：不加任何新功能**，把阶段 1 的代码改造成「像样的 Java 程序」。
> 功能和阶段 1 完全一样，但代码结构完全重写。这就是真实工作中的「重构」。

### 模块设计（在 src 下建三个包）

```
src
├── model / Record.java        ← 一条账单（数据）
├── service / RecordService.java ← 增删查的业务逻辑
└── Main.java                  ← 只负责菜单显示和键盘输入输出
```

### 模块对应的核心思想（本阶段知识点）

| 模块 | 核心思想 |
|---|---|
| Record 类 | 把「一条账单」抽象成类：字段私有 + getter/setter = **封装** |
| 构造方法 | `new Record(...)` 时完成初始化；`this` 的用法 |
| toString | 重写 `toString()`，让打印一行账单变优雅 |
| RecordService | 业务逻辑从 Main 中剥离；**为什么 `ArrayList` 要放在 Service 里而不是 `static`？** |
| static | `static int nextId` 做 id 自增；static 与实例成员的区别 |
| 包 package | 分层思想：显示（ui）/ 业务（service）/ 数据（model）各管各的 |

### 关键代码提示

Record 类骨架（留了 TODO 给你）：
```java
public class Record {
    private static int nextId = 1;  // 所有对象共享的自增序号
    private final int id;
    private String type;      // 餐饮/交通/购物/娱乐/其他
    private int amountFen;    // 金额，单位：分（解决阶段1的double思考题）
    private String note;
    private String date;      // 先用字符串存 "2026-10-27"，阶段3换 LocalDate

    public Record(String type, int amountFen, String note) {
        this.id = nextId++;
        // TODO: 补全其余字段赋值，日期用工具方法生成今天的日期字符串
    }
    // TODO: 为每个字段生成 getter；重写 toString() 打印成表格友好的一行
}
```

**✅ 阶段 2 验收标准：**
- [ ] `Main.java` 里只有菜单交互，**没有任何业务逻辑**（没有 ArrayList、没有统计计算）
- [ ] 删除改为「按 id 删除」，删错 id 有提示
- [ ] 对外功能与阶段 1 完全一致（重构 = 结构变、功能不变）
- [ ] 金额以「分」存储、以「元」显示（3550 分显示为 35.50 元）
- [ ] 每个类/每个改动都有独立 commit

---

# 阶段 3：统计功能（约 4~7 天）

### 功能需求

| 编号 | 功能 | 说明 |
|---|---|---|
| F6 | 分类统计 | 每种类型的支出总额和占比，按金额降序排列 |
| F7 | 月度账单 | 输入年月（如 2026-10），显示该月账单明细和总额 |
| F8 | 极值与均值 | 最贵的一笔、平均每笔多少 |
| F9 | 范围筛选 | 输入最小/最大金额，列出区间的账单 |

### 模块对应的核心思想（本阶段知识点）

| 模块 | 核心思想 |
|---|---|
| 分类统计 | **HashMap 分组累加**——统计类需求的万能套路 |
| 排序 | `Comparator`：匿名内部类 → Lambda → `Comparator.comparing()` 三种写法都试一遍 |
| 日期 | `LocalDate` / `DateTimeFormatter`（Java 8 日期类，比老 `Date` 好用，现在企业都用它） |
| 筛选 | `if` 条件组合；进阶可试 Stream API 的 `filter` |

### 关键代码提示

分组累加套路（背下来，工作还用）：
```java
Map<String, Integer> sumByType = new HashMap<>();
for (Record r : records) {
    int old = sumByType.getOrDefault(r.getType(), 0); // 没有就当0
    sumByType.put(r.getType(), old + r.getAmountFen());
}
```

排序：
```java
records.sort(Comparator.comparing(Record::getAmountFen).reversed());
```

今天日期字符串：
```java
LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
```

**✅ 阶段 3 验收标准：**
- [ ] 手工造 10 笔测试数据，统计结果和手算完全一致（拿计算器核对）
- [ ] 分类统计按金额从大到小排列
- [ ] 阶段 2 的 `date` 字符串全部换成 `LocalDate`，程序其他功能不受影响
- [ ] 金额按「分」参与计算、按「元」显示，全程无精度错误

---

# 阶段 4：文件持久化——数据不丢（约 5~10 天）⭐ 复习你的 IO 流

> **目标**：程序关掉再打开，数据还在。启动时读文件恢复数据，数据变化后写回文件。
> 这是你之前学到一半的地方，这个阶段把它彻底拿下。

### 功能需求

| 编号 | 功能 | 说明 |
|---|---|---|
| F10 | 保存 | 数据变化时（或退出时）把全部账单写入 `data/records.txt` |
| F11 | 加载 | 启动时读取文件，恢复上次的账单 |
| F12 | 容灾 | 文件不存在时自动创建；文件被改坏时给友好提示并重建空账本，**绝不崩溃** |

### 技术方案（按顺序做两版，都要做）

**v1 文本版**：每行一笔，用 `|` 分隔字段：
```
餐饮|3500|午饭|2026-10-27
交通|800|地铁|2026-10-27
```
用 `BufferedReader` 逐行读、`split("\\|")` 拆开还原成 Record。

**v2 对象版**：`ObjectOutputStream` 把整个 `List<Record>` 一次性写入文件，`ObjectInputStream` 一次性读回。`Record` 需要 `implements Serializable`。

**做完思考：两种方案各有什么优缺点？文件被文本编辑器打开时分别长什么样？人肉可读重要吗？**（这是面试经典题）

### 模块对应的核心思想（本阶段知识点）

| 模块 | 核心思想 |
|---|---|
| 流的选择 | 字节流 vs 字符流：什么时候用哪个？（文本→字符流，其他→字节流） |
| 缓冲流 | 为什么 `BufferedReader` 要套在 `FileReader` 外面？（装饰器模式） |
| 资源关闭 | **try-with-resources** 让流自动关闭，彻底告别手写 `finally` 关流 |
| 序列化 | `Serializable` 接口 / `serialVersionUID` 是干嘛的 |
| 异常处理 | catch 到异常后该干什么：给用户友好提示？打日志？继续还是中止？ |
| 字符集 | 写文件指定 UTF-8，用记事本打开验证不乱码 |

### 关键代码提示

文本写入（注意 try-with-resources 写法）：
```java
try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(FILE), StandardCharsets.UTF_8))) {
    for (Record r : records) {
        bw.write(r.toFileLine());  // 自己在 Record 里实现这个方法
        bw.newLine();
    }
} catch (IOException e) {
    System.out.println("保存失败：" + e.getMessage());
}
```

对象版读写：
```java
// 写：List<Record> 整体进文件
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
    oos.writeObject(records);
}
// 读：注意返回 Object 要强转，文件为空/损坏会抛异常，要 catch 住
```

**✅ 阶段 4 验收标准：**
- [ ] 记 3 笔 → 退出 → 重新启动 → 数据完好，id 编号继续自增不重复
- [ ] 手动把 `records.txt` 改坏（删掉半行），程序提示数据损坏并重建空账本，不崩溃
- [ ] v1 和 v2 两版都完成过并各有 commit（可以最终只保留一版）
- [ ] 能口头说出：字节流和字符流的区别、try-with-resources 好在哪

---

# 阶段 5（可选加分项）

按兴趣和能力选做，每一个都是简历上的亮点：

- [ ] 导出月度报表到 `report-2026-10.txt`（综合：统计 + IO）
- [ ] 简单登录功能：密码存文件，MD5 加密后比对（字符串处理 + 加密常识）
- [ ] 用 JUnit 给 RecordService 写单元测试（企业开发标配）
- [ ] 用 Maven 重构项目结构（为接下来学 Spring 打基础）
- [ ] 写一份漂亮的 `README.md`：项目介绍、功能列表、运行截图、技术栈——**求职时面试官真的会看**

---

# 附录 A：提问模板（照这个格式问，效率最高）

```
【我在做】阶段2，给 Record 类重写 toString
【我预期】打印出：1 | 餐饮 | 35.00 | 午饭
【实际发生】报错（完整复制贴上）：
    java.lang.NullPointerException ...
【相关代码】（只贴出事的那个方法，不要贴整个文件）
```

# 附录 B：常见问题速查

| 现象 | 原因与解法 |
|---|---|
| 控制台中文乱码 | IDEA → Settings → Editor → File Encodings，三处全设为 UTF-8 |
| `nextInt()` 后 `nextLine()` 读到空字符串 | 中间补一句 `sc.nextLine()` 吃掉回车 |
| 找不到 main 方法 | 检查类名和文件名是否一致、main 签名是否拼对 |
| git push 要登录 | 弹出的浏览器窗口登录 GitHub 即可（一次即可记住） |
| push 被拒绝 (rejected) | 先 `git pull` 再 `git push` |

# 附录 C：时间估算汇总

| 阶段 | 每天2小时估算 |
|---|---|
| 0 环境 | 1 天 |
| 1 内存版 | 3~5 天 |
| 2 重构 | 4~7 天 |
| 3 统计 | 4~7 天 |
| 4 持久化 | 5~10 天 |
| **合计** | **约 17~30 天**（含卡壳和复习，实际按 3~6 周安排最稳） |

> 把这份文档发给你的大佬朋友，他就能根据难度判断你的节奏是否合理。
