/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thuan
 */
@Stateful
public class MobileStoreSystemBean implements MobileStoreSystemBeanRemote {

    @PersistenceContext(unitName = "MobileStoreSystemManagementPU")
    private EntityManager em;

    public MobileStoreSystemBean()
    {

    }
    
    @Override
    public User insertUser(String userName, String userPassword, String fullName, String address, String phone, Long storeId, String email) {
        User u = new User();
        u.setUserName(userName);
        u.setPassword(userPassword);
        u.setFullName(fullName);
        u.setAddress(address);
        u.setPhone(phone);
        u.setStoreId(this.findStoreById(storeId));
        u.setEmail(email);
        u.setStatus(Boolean.TRUE);
        em.persist(u);
        
        return (User) em.createNamedQuery("User.findByUserName").setParameter("userName", u.getUserName()).getSingleResult();
    }

    @Override
    public void insertRole(String roleName) {
        Role role = new Role(roleName);
        em.persist(role);
    }

    @Override
    public void insertUserRole(Long user_id, String roleName) {
        User user = em.find(User.class, user_id);
        Role role = em.find(Role.class, roleName);

        user.getRoleCollection().add(role);
        role.getUserCollection().add(user);
    }

    @Override
    public ArrayList<String> searchRole(String rolename) {
        Role role = em.find(Role.class, rolename);

        if(role != null)
        {
            ArrayList<String> arrOutput = new ArrayList<String>();
            for(User user : role.getUserCollection())
            {
                arrOutput.add(user.getUserName());
            }
            return arrOutput;
        }
        return null;
    }


    @Override
    public User Login(String username, String password) {
        try {
            Query loginQuery = em.createQuery("SELECT u FROM User u WHERE u.userName = :username AND u.password = :password");
            loginQuery.setParameter("username", username);
            loginQuery.setParameter("password", password);
            User user = (User) loginQuery.getSingleResult();

            return user;
        } catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public Store insertStore(String name, String address, String phone, String email) {
        try {
            Store store = new Store();
            store.setName(name);
            store.setAddress(address);
            store.setPhone(phone);
            store.setEmail(email);
            em.persist(store);
            return (Store) em.createNamedQuery("Store.findByName").setParameter("name", name).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateStore(Long id, String name, String address, String phone, String email, Boolean status) {
        Store store = em.find(Store.class, id);
        if(store == null)
        {
            return false;
        }
        try
        {
            Query updateQuery = em.createQuery("UPDATE Store AS s SET s.name = :name, s.address = :address, s.phone = :phone, s.email = :email, s.status = :status WHERE s.id = :id");
            updateQuery.setParameter("name", name);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("status", status);
            updateQuery.setParameter("id", store.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteStore(Long id) {
        try
        {
            Query deleteQuery = em.createQuery("DELETE FROM Store s WHERE s.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Store> getAllStores() {
        return em.createNamedQuery("Store.findAll").getResultList();
    }

    @Override
    public Store findStoreById(Long id) {
        return (Store) em.createNamedQuery("Store.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public boolean updateUser(Long id, String userPassword, String fullName, String address, String phone, Long storeId, String email, Boolean status) {
        try
        {
            Query updateQuery = em.createQuery("UPDATE User AS u SET u.password = :password, u.fullName = :fullname, u.address = :address, u.phone = :phone, u.storeId = :storeid, u.email = :email, u.status = :status WHERE u.id = :id");
            updateQuery.setParameter("password", userPassword);
            updateQuery.setParameter("fullname", fullName);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("storeid", findStoreById(storeId));
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("status", status);
            updateQuery.setParameter("id", id);
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        try
        {
            Query deleteQuery = em.createQuery("DELETE FROM User u WHERE u.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public User findUserById(Long id) {
        return (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createNamedQuery("Role.findAll").getResultList();
    }

    @Override
    public List<User> getUsersByRole(String roleName) {
        //Role role = em.find(Role.class, roleName.toUpperCase());
        Role role = (Role) em.createNamedQuery("Role.findByRoleName").setParameter("roleName", roleName).getSingleResult();
        if(role != null)
        {
            ArrayList<User> arrOutput = new ArrayList<User>();
            for(User user : role.getUserCollection())
            {
                arrOutput.add(user);
            }
            return arrOutput;
        }
        return null;
    }

    @Override
    public List<Role> getRolesByUser(Long userId) {
        User user = em.find(User.class, userId);
        
        if(user != null)
        {
            ArrayList<Role> arrResult = new ArrayList<Role>();
            for(Role role : user.getRoleCollection())
            {
                arrResult.add(role);
            }
            return arrResult;
        }
        return null;
    }
    private Role findRole(String roleName) {
        return (Role) em.createNamedQuery("Role.findByRoleName").setParameter("roleName", roleName).getSingleResult();
    }

    @Override
    public boolean deleteUserRole(Long user_id, String roleName) {
        try
        {
            User findedUser = findUserById(user_id);
            findedUser.getRoleCollection().remove(findRole(roleName));
            em.persist(findedUser);
            return true;
        }catch(Exception ex)
        {
            return false;
        }
    }

    @Override
    public Product insertProduct(String name, Long supplierId, Integer afterCamera, Integer beforeCamera, String color, String description, Integer memory, String operator, Integer price, Integer warranty, String screen, String pin) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setSupplierId(findSupplierById(supplierId));
            product.setAfterCamera(afterCamera);
            product.setBeforeCamera(beforeCamera);
            product.setColor(color);
            product.setDescription(description);
            product.setMemory(memory);
            product.setOperator(operator);
            product.setPrice(price);
            product.setWarranty(warranty);
            product.setScreen(screen);
            product.setPin(pin);
            product.setStatus(Boolean.TRUE);
            em.persist(product);
            return (Product) em.createNamedQuery("Product.findByName").setParameter("name", name).getSingleResult();
        } catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean updateProduct(Long id, String name, Long supplierID, Integer afterCamera, Integer beforeCamera, String color, String description, Integer memory, String operator, Integer price, Integer warranty, String screen, String pin, Boolean status) {
        Product product = em.find(Product.class, id);
        try
        {
            Query updateQuery = em.createQuery("UPDATE Product AS p SET p.name = :name, p.supplierId = :supplier, p.afterCamera = :afterCamera, p.beforeCamera = :beforeCamera, p.color = :color, p.description = :description ,p.memory = :memory ,p.operator = :operator ,p.price = :price , p.warranty = :warranty, p.screen = :screen , p.pin = :pin, p.status = :status WHERE p.id = :id");
            if(name.equals(null) || name.equals("")) {
                updateQuery.setParameter("name", product.getName());
            } else {
                updateQuery.setParameter("name", name);
            }
            
            if(supplierID.equals(null) || supplierID.equals("")) {
                updateQuery.setParameter("supplier", product.getSupplierId());
            } else {
                updateQuery.setParameter("supplier", findSupplierById(supplierID));
            }
            
            if(afterCamera.equals(null) || afterCamera.equals("")) {
                updateQuery.setParameter("afterCamera", product.getAfterCamera());
            } else {
                updateQuery.setParameter("afterCamera", afterCamera);
            }
            
            if(beforeCamera.equals(null) || beforeCamera.equals("")) {
                updateQuery.setParameter("beforeCamera", product.getBeforeCamera());
            } else {
                updateQuery.setParameter("beforeCamera", beforeCamera);
            }
            
            if(color.equals(null) || color.equals("")) {
                updateQuery.setParameter("color", product.getColor());
            } else {
                updateQuery.setParameter("color", color);
            }
            
            if(description.equals(null) || description.equals("")) {
                updateQuery.setParameter("description", product.getDescription());
            } else {
                updateQuery.setParameter("description", description);
            }
            
            if(memory.equals(null) || memory.equals("")) {
                updateQuery.setParameter("memory", product.getMemory());
            } else {
                updateQuery.setParameter("memory", memory);
            }
            
            if(operator.equals(null) || operator.equals("")) {
                updateQuery.setParameter("operator", product.getOperator());
            } else {
                updateQuery.setParameter("operator", operator);
            }
            
            if(price.equals(null) || price.equals("")) {
                updateQuery.setParameter("price", product.getPrice());
            } else {
                updateQuery.setParameter("price", price);
            }
            
            if(warranty.equals(null) || warranty.equals("")) {
                updateQuery.setParameter("warranty", product.getWarranty());
            } else {
                updateQuery.setParameter("warranty", warranty);
            }
            
            if(screen.equals(null) || screen.equals("")) {
                updateQuery.setParameter("screen", product.getScreen());
            } else {
                updateQuery.setParameter("screen", screen);
            }
            
            if(pin.equals(null) || pin.equals("")) {
                updateQuery.setParameter("pin", product.getPin());
            } else {
                updateQuery.setParameter("pin", pin);
            }
            
            if(status.equals(null) || status.equals("")) {
                updateQuery.setParameter("status", product.getStatus());
            } else {
                updateQuery.setParameter("status", status);
            }
            
            updateQuery.setParameter("id", product.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        try
        {
            Query deleteQuery = em.createQuery("DELETE FROM Product p WHERE p.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            e.getMessage();
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return em.createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public Product findProductById(Long id) {
        return (Product) em.createNamedQuery("Product.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public Supplier insertSupplier(String name, String address, String phone, String email) {
        try {
            Supplier supplier = new Supplier();
            supplier.setName(name);
            supplier.setAddress(address);
            supplier.setPhone(phone);
            supplier.setEmail(email);
            supplier.setStatus(Boolean.TRUE);
            em.persist(supplier);
            return (Supplier) em.createNamedQuery("Supplier.findByName").setParameter("name", name).getSingleResult();
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateSupplier(Long id, String fullname, String address, String phone, String email, Boolean status) {
        Supplier supplier = em.find(Supplier.class, id);
        try
        {
            Query updateQuery = em.createQuery("UPDATE Supplier AS s SET s.name = :name, s.address = :address, s.phone = :phone, s.email = :email ,s.status = :status  WHERE s.id = :id");
            updateQuery.setParameter("name", fullname);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("status",status);
            updateQuery.setParameter("id", supplier.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(Long id) {
        try
        {
            Query deleteQuery = em.createQuery("DELETE FROM Supplier s WHERE s.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        } 
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return em.createNamedQuery("Supplier.findAll").getResultList();
    }

    @Override
    public Supplier findSupplierById(Long id) {
        return (Supplier) em.createNamedQuery("Supplier.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public Customer insertCustomer(String fullname, String address, String phone, String email) {
        try {
            Customer customer = new Customer();
            customer.setFullname(fullname);
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setEmail(email);
            em.persist(customer);
            return (Customer) em.createNamedQuery("Customer.findByPhone").setParameter("phone", phone).getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(Long id, String fullname, String address, String phone, String email) {
        Customer customer = em.find(Customer.class, id);
        try
        {
            Query updateQuery = em.createQuery("UPDATE Customer AS c SET c.fullname = :fullname, c.address = :address, c.phone = :phone, c.email = :email  WHERE c.id = :id");
            updateQuery.setParameter("fullname", fullname);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("id", customer.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(Long id) {
       try
        {
            Query deleteQuery = em.createQuery("DELETE FROM Customer c WHERE c.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }  
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return (Customer) em.createNamedQuery("Customer.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public Order1 insertOrder(String customerName, String customerAddress, String customerPhone, String customerEmail, Store store, User staff, List<OrderDetail> productList, String note) {
        int total = 0;
        Customer customer = findCustomerByPhone(customerPhone);
        if(customer == null) {
            customer = this.insertCustomer(customerName, customerAddress, customerPhone, customerEmail);
        }
        //total = price * quantity
        for(OrderDetail detail :productList ) {
            total +=  detail.getQuantity()*detail.getPrice();
        }
        //Create order
        Order1 order = new Order1();
        order.setCustomerId(customer);
        order.setStoreId(store);
        order.setStaffId(staff);
        order.setCreatedAt(new Date());
        order.setTotal(total);
        order.setNote(note);
        em.persist(order);
        
        for(OrderDetail detail :productList ) {
            this.insertOrderDetail(order, detail.getProduct(), detail.getPrice(), detail.getQuantity());
        }
        
        return order;
    }

    @Override
    public boolean updateOrder(Long id, BigInteger total, Character note) {
        Order1 order = em.find(Order1.class, id);
        try
        {
            Query updateQuery = em.createQuery("UPDATE Order1 AS o SET o.createdAt = :createdAt, o.total = :total, o.note = : note  WHERE o.id = :id");
            //updateQuery.setParameter("createdAt", createdAt);
            updateQuery.setParameter("total", total);
            updateQuery.setParameter("note", note);
            updateQuery.setParameter("id", order.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Long id) {
        try
        {
            Order1 order = (Order1) em.createNamedQuery("Order1.findById").setParameter("id", id).getSingleResult();
            deleteOrderDetail(order);
            Query deleteQuery = em.createQuery("DELETE FROM Order1 o WHERE o.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }  
    }
    
    public void deleteOrderDetail(Order1 order) {
        Query deleteDetailQuery = em.createQuery("DELETE FROM OrderDetail od WHERE od.order1 = :order");
        deleteDetailQuery.setParameter("order", order).executeUpdate();
    }

    @Override
    public List<Order1> getAllOrders() {
        return em.createNamedQuery("Order1.findAll").getResultList();
    }

    @Override
    public Order1 findOrderById(Long id) {
        return (Order1) em.createNamedQuery("Order1.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public IoWarehouse insertIoWarehouse(BigInteger price, BigInteger total, Boolean import1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateIoWarehouse(Long id, BigInteger price, BigInteger total, Boolean import1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteIoWarehouse(Long id) {
        try
        {
            Query deleteQuery = em.createQuery("DELETE FROM IoWarehouse i WHERE i.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        } 
    }

    @Override
    public List<IoWarehouse> getAllIoWarehouses() {
        return em.createNamedQuery("IoWarehouse.findAll").getResultList();
    }

    @Override
    public IoWarehouse findIoWarehouselById(Long id) {
        return (IoWarehouse) em.createNamedQuery("IoWarehouse.findById").setParameter("id", id).getSingleResult();
    }

    public void insertOrderDetail(Order1 order, Product product, int price, int quantity) {
        OrderDetail oDetail = new OrderDetail();
        oDetail.setOrder1(order);
        oDetail.setProduct(product);
        oDetail.setOrderDetailPK(new OrderDetailPK(order.getId(), product.getId()));
        oDetail.setPrice(price);
        oDetail.setQuantity(quantity);
        em.persist(oDetail);
    }

    @Override
    public Customer findCustomerByPhone(String phone) {
        try {
            return (Customer) em.createNamedQuery("Customer.findByPhone").setParameter("phone", phone).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }
}
