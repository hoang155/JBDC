package org.example;

import entity.Invoice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InvoiceManager manager = new InvoiceManager();
        Scanner scanner = new Scanner(System.in);
        int autoId = 1; // Biến tự tăng cấp ID hệ thống khi thêm mới

        while (true) {
            System.out.println("\n******************** MENU QUẢN LÝ HÓA ĐƠN ********************");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Sửa hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Hiển thị danh sách hóa đơn");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // 1. Nhập và validate mã hóa đơn
                    String code;
                    while (true) {
                        System.out.println("Nhập mã hóa đơn: ");
                        code = scanner.nextLine().trim();
                        if (code.isEmpty()) {
                            System.out.println("❌ Lỗi: Mã hóa đơn không được để trống!");
                            continue;
                        }
                        break;
                    }

                    // 2. Nhập và validate số tiền >= 0
                    double amount = 0;
                    while (true) {
                        System.out.println("Nhập số tiền: ");
                        try {
                            amount = Double.parseDouble(scanner.nextLine().trim());
                            if (amount < 0) {
                                System.out.println("Vui lòng nhập số thực >= 0 !");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Lỗi: Số tiền phải là định dạng số thực hợp lệ!");
                        }
                    }

                    Invoice newInvoice = new Invoice(autoId++, code, amount);
                    manager.add(newInvoice);
                    break;

                case "2":
                    if (manager.isEmpty()) {
                        System.out.println("⚠️ Danh sách trống, không có hóa đơn nào để sửa.");
                        break;
                    }
                    manager.display();

                    System.out.println("Nhập id hóa đơn cần sửa: ");
                    int updateId = 0;
                    try {
                        updateId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Lỗi: ID phải là số nguyên!");
                        break;
                    }

                    int updateIndex = manager.findIndexById(updateId);
                    if (updateIndex == -1) {
                        System.out.println("❌ Không tìm thấy hóa đơn nào có id = " + updateId);
                    } else {
                        // Nhập mã mới kèm validate chống trống
                        String newCode;
                        while (true) {
                            System.out.println("Nhập mã hóa đơn mới: ");
                            newCode = scanner.nextLine().trim();
                            if (newCode.isEmpty()) {
                                System.out.println("Vui lòng ko để trống !");
                                continue;
                            }
                            break;
                        }

                        // Nhập số tiền mới kèm validate >= 0
                        double newAmount = 0;
                        while (true) {
                            System.out.println("Nhập số tiền mới: ");
                            try {
                                newAmount = Double.parseDouble(scanner.nextLine().trim());
                                if (newAmount < 0) {
                                    System.out.println("Vui lòng nhập số thực >= 0 !");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Lỗi: Định dạng tiền không hợp lệ!");
                            }
                        }

                        Invoice updatedInvoice = new Invoice(updateId, newCode, newAmount);
                        manager.update(updateIndex, updatedInvoice);
                    }
                    break;

                case "3":
                    if (manager.isEmpty()) {
                        System.out.println("⚠️ Danh sách trống, không có hóa đơn nào để xóa.");
                        break;
                    }
                    manager.display();

                    System.out.println("Nhập id hóa đơn cần xóa: ");
                    int deleteId = 0;
                    try {
                        deleteId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Lỗi: ID phải là số nguyên!");
                        break;
                    }

                    int deleteIndex = manager.findIndexById(deleteId);
                    if (deleteIndex == -1) {
                        System.out.println("Không tìm thấy hóa đơn nào có id = " + deleteId);
                    } else {
                        manager.delete(deleteIndex);
                    }
                    break;

                case "4":
                    manager.display();
                    break;

                case "5":
                    System.out.println("👋 Đang đóng hệ thống quản lý hóa đơn. Tạm biệt!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng chọn lại từ 1 đến 5.");
            }
        }
    }
}