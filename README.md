# Hướng dẫn chạy chương trình: Hệ thống quản lý cửa hàng bán điện thoại.
Ta cần cài đặt các phần mềm để phát triển hệ thống ![tại đây](https://drive.google.com/drive/folders/0B_ZobqmN2htVeXRRUjJfOWJmOEk?usp=sharing)
Để chạy được chương trình ta cần cài cặt chương trình như sau:
1. Tạo database.
Chúng tôi sử dụng Postgresql cho hệ thống này.
Tạo một cơ sở dữ liệu (Tạo bảng và quan hệ giữa các bảng) với tên là MobileDB, tất cả các bảng được lưu trong Schemas > public:
![Image of Thuan](https://github.com/dinhthuan182/PTHTTTDN/blob/master/db.png)

2. Kết nối đến jBoss.
- Sau khi đã có Jboss ta mở giao diện web của jboss admin và tải file postgresql-42.1.4.jar lên.
- Kết nối jboss đến database; trên màn hình jboss admin chọn Configuration > Subsystems > Datasources > Non-XA > Add để tạo kết nối đến database.
3. Kết nối đến cơ sở dữ liệu trong NetBeans
Sau khi khởi động, chọn Service > (click phải chuột)Databases > New Connection.
4. Cấu hình chương trình.
Từ màn hình netBeans, chọn Projects.
- Tại project MobileStoreSystemManagement > Configuration > jboss.xml ta chỉnh sửa lại:
'''xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml version="1.0" encoding="UTF-8"?>
<datasources>
 <local-tx-datasource>
 <jndi-name>PostgresDS</jndi-name>
 <connection-url>jdbc:postgresql://localhost:5432/MobileDB</connection-url>
 <driver-class>org.postgresql.driver</driver-class>
 <user-name><-username-></user-name>
 <password><-password-></password>
 <min-pool-size>5</min-pool-size>
 <max-pool-size>20</max-pool-size>
 <idle-timeout-minutes>5</idle-timeout-minutes>
 </local-tx-datasource>
</datasources>
'''
chỉnh sửa lại username và password để truy cập vào postgresql.
Sau khi hoàn tất, ta tải project lên jboss admin.
# Chạy chương trình.
Ta chạy project MobileStoreManagementApp. Khi chạy chương trình ta cần đăng nhập vào hệ thống.
![giao diện chính](https://github.com/dinhthuan182/PTHTTTDN/blob/master/main-interface.png)
Sau khi đăng nhập ta có thể thao tác với hệ thống:
1. Quản lý cửa hàng (Thêm, sửa, xóa, hiển thị cửa hàng)
2. Quản lý nhân viên (Thêm, sửa, xóa, hiển thị, phân quyền nhân viên)
3. Quản lý sản phẩm (Thêm, sửa, xóa, hiển thị sản phẩm)
4. Quản lý nhà cung cấp (Thêm, sửa, xóa, hiển thị nhà cung cấp)
5. Quản lý khách hàng (Thêm, sửa, xóa, hiển thị khách hàng)
6. Quản lý đơn bán hàng (Thêm, sửa, xóa, hiển thị đơn đặt hàng)
7. Quản lý xuất nhập kho (Thêm, sửa, xóa, hiển thị, thống kê hàng tòn kho)



