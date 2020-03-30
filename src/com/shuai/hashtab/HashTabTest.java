package com.shuai.hashtab;

import java.util.Scanner;

public class HashTabTest {
    public static void main(String[] args) {

        HashTab hashTab = new HashTab(10);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n|--输入指令：add(添加) find(查找) delete(删除) update(更新) list(查看) exit(退出)--|");
            String next = scanner.next();
            switch (next) {
                //添加雇员
                case "add":
                    System.out.println("输入雇员编号：");
                    String id1 = scanner.next();
                    if (!isPositiveInt(id1)) {
                        System.out.println("编号必须是正整数");
                        break;
                    }
                    System.out.println("输入雇员姓名：");
                    String name1 = scanner.next();
                    Emp emp1 = new Emp(Integer.parseInt(id1), name1);
                    hashTab.addByIdOrder(emp1);
                    break;

                //查找雇员
                case "find":
                    System.out.println("输入雇员编号：");
                    String findId = scanner.next();
                    if (!isPositiveInt(findId)) {
                        System.out.println("编号必须是正整数");
                        break;
                    }
                    Emp empById = hashTab.findEmpById(Integer.parseInt(findId));
                    if (empById != null) {
                        System.out.println("要查找的雇员信息为：" + empById);
                    }
                    break;

                //删除雇员
                case "delete":
                    System.out.println("输入雇员编号：");
                    String deleteId = scanner.next();
                    if (!isPositiveInt(deleteId)) {
                        System.out.println("编号必须是正整数");
                        break;
                    }
                    hashTab.delete(Integer.parseInt(deleteId));
                    break;

                //更新雇员信息
                case "update":
                    System.out.println("输入雇员编号：");
                    String updateId = scanner.next();
                    if (!isPositiveInt(updateId)) {
                        System.out.println("编号必须是正整数");
                        break;
                    }
                    System.out.println("输入雇员姓名：");
                    String updateName = scanner.next();
                    Emp updateEmp = new Emp(Integer.parseInt(updateId), updateName);
                    hashTab.update(updateEmp);
                    break;

                //查看所有雇员信息
                case "list":
                    hashTab.list();
                    break;

                //退出系统
                case "exit":
                    loop = false;
                    scanner.close();
                    break;

                default:
                    System.out.println("指令错误");
                    break;
            }
        }

    }

    /**
     * 是否是正整数
     *
     * @param str
     * @return
     */
    public static boolean isPositiveInt(String str) {
        try {
            int i = Integer.parseInt(str);
            if (i >= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}


/**
 * 雇员哈希表
 */
class HashTab {

    private EmpLinkedList[] linkedLists;
    int maxSize;

    public HashTab(int maxSize) {
        if (maxSize <= 0) {
            throw new RuntimeException("HashTab长度不合法！");
        }
        this.maxSize = maxSize;
        linkedLists = new EmpLinkedList[maxSize];
        for (int i = 0; i < maxSize; i++) {
            linkedLists[i] = new EmpLinkedList();
        }
    }

    public void addByIdOrder(Emp emp) {
        try {
            linkedLists[getLinkedIndex(emp)].addByIdOrder(emp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Emp emp) {
        linkedLists[getLinkedIndex(emp)].update(emp);
    }

    public void delete(int id) {
        linkedLists[getLinkedIndex(id)].delete(id);
    }

    public void list() {
        for (int i = 0; i < linkedLists.length; i++) {
            linkedLists[i].list(i);
        }
    }

    public Emp findEmpById(int id) {
        EmpLinkedList list = linkedLists[getLinkedIndex(id)];
        return list.findEmpById(id);
    }

    private int getLinkedIndex(Emp emp) {
        if (emp != null) {
            return emp.id % maxSize;
        }
        return -1;
    }

    private int getLinkedIndex(int id) {
        return id % maxSize;
    }

}

/**
 * 雇员
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id=" + id + ",name=" + name + ",next.id=" + (next == null ? null : next.id) + "]";
    }
}

/**
 * 存放雇员的链表
 */
class EmpLinkedList {

    private Emp head;

    /**
     * 遍历链表
     */
    public void list(int i) {
        if (head == null) {
            System.out.println("链表 " + i + " 为空");
            return;
        }
        Emp temp = head;
        StringBuilder buffer = new StringBuilder();
        buffer.append("链表 " + i + " ==>");
        while (temp != null) {
            buffer.append(temp).append("  ");
            temp = temp.next;
        }
        System.out.println(buffer.toString());
    }


    /**
     * 添加雇员(按照id顺序排列)
     *
     * @param emp
     */
    public void addByIdOrder(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        //针对头节点，单独处理。
        if (head.id > emp.id) {
            Emp t = head;
            head = emp;
            emp.next = t;
            return;
        }
        if (head.id == emp.id) {
            throw new RuntimeException("存在id为" + emp.id + "的雇员，添加失败");
        }

        Emp temp = head;
        boolean hasSame = false;
        while (true) {
            if (temp.next == null) {//temp就是最后一个了。
                break;
            }
            if (temp.next.id == emp.id) {
                hasSame = true;
                break;
            }
            if (temp.next.id > emp.id) {
                break;
            }
            temp = temp.next;
        }
        if (hasSame) {
            throw new RuntimeException("存在id为" + emp.id + "的雇员，添加失败");
        } else {
            emp.next = temp.next;
            temp.next = emp;
        }
    }

    /**
     * 更新雇员信息
     *
     * @param emp
     */
    public void update(Emp emp) {
        if (head == null) {
            System.out.println("update失败，未找id为" + emp.id + "的雇员，此雇员所在链表为空");
            return;
        }
        Emp temp = head;
        boolean hasFind = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.id == emp.id) {
                hasFind = true;
                break;
            }
            temp = temp.next;
        }
        if (hasFind) {//找到temp
            temp.name = emp.name;
        } else {
            System.out.println("update失败，未找id为" + emp.id + "的雇员");
        }
    }

    /**
     * 删除雇员
     *
     * @param id
     */
    public void delete(int id) {
        if (head == null) {
            System.out.println("delete失败，未找id为" + id + "的雇员，此雇员所在链表为空");
            return;
        }

        //针对头节点单独处理
        //如果头结点就是要查找的雇员
        if (head.id == id) {
            head = head.next;
            return;
        }

        Emp temp = head;
        boolean hasFind = false;

        while (true) {
            if (temp.next == null) {//temp就是最后一个了。
                break;
            }
            if (temp.next.id == id) {
                hasFind = true;
                break;
            }
            temp = temp.next;
        }
        if (hasFind) {//找到，就是next
            temp.next = temp.next.next;
        } else {
            System.out.println("delete失败，未找id为" + id + "的雇员");
        }
    }

    /**
     * 查找雇员
     *
     * @param id
     * @return
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("未找id为" + id + "的雇员，此雇员所在链表为空");
            return null;
        }
        Emp temp = head;
        boolean hasFind = false;
        while (temp != null) {
            if (temp.id == id) {
                hasFind = true;
                break;
            }
            temp = temp.next;
        }
        if (hasFind) {
            return temp;
        } else {
            System.out.println("未找id为" + id + "的雇员");
            return null;
        }
    }
}