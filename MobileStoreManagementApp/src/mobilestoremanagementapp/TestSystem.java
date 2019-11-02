/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilestoremanagementapp;

import edu.tdt.entity.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author thuan
 */
public class TestSystem {
    private Properties props;
    private InitialContext ctx;

    public TestSystem()
    {
        readJNDI();
    }

    /**
    * Read the JNDI properties file
    */
    private void readJNDI()
    {
        props = new Properties();

        try
        {
            props.load(new FileInputStream("jndi.properties"));
        } catch(IOException e)
        {
            e.getMessage();
        }

        try
        {
            ctx = new InitialContext(props);
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    /**
    * Construct and return the JNDI address of called class
    * @return String
    */
    private String getJNDI()
    {
        String appName = "";
        String moduleName = "MobileStoreSystemManagement";
        String distinctName = "";
        String sessionBeanName = MobileStoreSystemBean.class.getSimpleName();
        String viewClassName = MobileStoreSystemBeanRemote.class.getName() + "?stateful";

        return "ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+sessionBeanName+"!"+viewClassName;
    }

    /**
    * Show the GUI in console window
    */
    
    private void showMainMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT System Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. Store Management \n2. User Management \n3. Product Management \n4. Supplier Management \n5. Customer Management \n6. Order Management \n7. IO Warehouse Management \n8. Exit \nEnter Choice: ");
    }
    
    private void showStoreMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT Store Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Store \n2. Add Store \n3. Edit Store \n4. Remove Store \n5. Exit \nEnter Choice: ");
    }
    
    private void showUserMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT User Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List User \n2. Add User \n3. Edit User \n4. Remove User \n5. List Role \n6. Add Role \n7. Remove Role \n8. Add User Role \n9. List User of Role \n10. List Role of User \n11. Delete User Role \n12. Exit \nEnter Choice: ");
    }
    
    private void showProductMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT Product Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Product \n2. Add Product \n3. Edit Product \n4. Remove Product \n5. Exit \nEnter Choice: ");
    }
    
    private void showSupplierMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT Supplier Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Supplier \n2. Add Supplier \n3. Edit Supplier \n4. Remove Supplier \n5. Exit \nEnter Choice: ");
    }
    
    private void showCustomerMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT Customer Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Customer \n2. Add Customer \n3. Edit Customer \n4. Remove Customer \n5. Exit \nEnter Choice: ");
    }
    
    private void showOrderMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT Order Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Order \n2. Add Order \n3. Edit Order \n4. Remove Order \n5. Exit \nEnter Choice: ");
    }
    
    private void showIOWarehouseMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("TDT IO Warehouse Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List IO Warehouse \n2. Add IO Warehouse \n3. Edit IO Warehouse \n4. Remove IO Warehouse \n5. Exit \nEnter Choice: ");
    }
    
    private void getAllStores()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Store> storesList = libBean2.getAllStores();

        // Print all books
        if(storesList.isEmpty())
        {
            System.out.println("There is no store in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("------------List Store------------");
        for (int i = 0; i < storesList.size(); i++)
        {
            System.out.println(storesList.get(i).getId()+"\t"+ storesList.get(i).getName());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllUsers()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<User> usersList = libBean2.getAllUsers();

        // Print all books
        if(usersList.isEmpty())
        {
            System.out.println("There is no user in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("------------List User-------------");
        for (int i = 0; i < usersList.size(); i++)
        {
            System.out.println(usersList.get(i).getId()+"\t"+ usersList.get(i).getUserName()+"\t"+usersList.get(i).getPassword());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllRoles()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Role> rolesList = libBean2.getAllRoles();

        // Print all books
        if(rolesList.isEmpty())
        {
            System.out.println("There is no role in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("------------List Role-------------");
        for (int i = 0; i < rolesList.size(); i++)
        {
            System.out.println(i+"\t"+rolesList.get(i).getRoleName());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getUsersByRole(String role) {
        try
        {
            // We can define another bean to access the LibrarySessionBeanRemote
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            List<User> usersList = libBean2.getUsersByRole(role);

            if(usersList.isEmpty())
            {
                System.out.println("There is no user in the role!\n");
                return;
            }

            System.out.println("==================================");
            System.out.println("------------List "+ role +" Role-----------");
            for (int i = 0; i < usersList.size(); i++)
            {
                System.out.println(usersList.get(i).getId() +"\t"+ usersList.get(i).getUserName());
            }
            System.out.println("----------------------------------");

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getRolesByUser(Long id) {
        try
        {
            // We can define another bean to access the LibrarySessionBeanRemote
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            List<Role> rolesList = libBean2.getRolesByUser(id);

            // Print all books
            if(rolesList.isEmpty())
            {
                System.out.println("There is no role in the system!\n");
                return;
            }

            System.out.println("==================================");
            System.out.println("------------List Role of "+ id +"-------------");
            for (int i = 0; i < rolesList.size(); i++)
            {
                System.out.println((i+1)+"\t"+rolesList.get(i).getRoleName());
            }
            System.out.println();
            System.out.println("----------------------------------");

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllProducts()
    {
        try
        {
            // We can define another bean to access the LibrarySessionBeanRemote
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            List<Product> productsList = libBean2.getAllProducts();

            // Print all books
            if(productsList.isEmpty())
            {
                System.out.println("There is no product in the system!\n");
                return;
            }

            System.out.println("==================================");
            System.out.println("-----------List Product------------");
            for (int i = 0; i < productsList.size(); i++)
            {
                System.out.println(productsList.get(i).getId()+"\t"+ productsList.get(i).getName()+"\t"+productsList.get(i).getSupplierId().getName());
            }
            System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllSuppliers()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Supplier> suppliersList = libBean2.getAllSuppliers();

        // Print all books
        if(suppliersList.isEmpty())
        {
            System.out.println("There is no supplier in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("-----------List Supplier------------");
        for (int i = 0; i < suppliersList.size(); i++)
        {
            System.out.println(suppliersList.get(i).getId()+"\t"+ suppliersList.get(i).getName()+"\t"+suppliersList.get(i).getEmail());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllCustomers()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Customer> customersList = libBean2.getAllCustomers();

        // Print all books
        if(customersList.isEmpty())
        {
            System.out.println("There is no customer in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("-----------List Customer------------");
        for (int i = 0; i < customersList.size(); i++)
        {
            System.out.println(customersList.get(i).getId()+"\t"+ customersList.get(i).getFullname()+"\t"+customersList.get(i).getOrder1Collection().size());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllOrders()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Order1> ordersList = libBean2.getAllOrders();

        // Print all books
        if(ordersList.isEmpty())
        {
            System.out.println("There is no order in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("-----------List Order------------");
        for (int i = 0; i < ordersList.size(); i++)
        {
            System.out.println(ordersList.get(i).getId()+"\t"+ ordersList.get(i).getCustomerId().getFullname() +"\t"+ ordersList.get(i).getStoreId().getName() +"\t"+ ordersList.get(i).getTotal());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllIOWarehouses()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<IoWarehouse> warehousesList = libBean2.getAllIoWarehouses();

        // Print all books
        if(warehousesList.isEmpty())
        {
            System.out.println("There is no IO warehouse in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("-----------List IO Warehouse------------");
        for (int i = 0; i < warehousesList.size(); i++)
        {
            System.out.println(warehousesList.get(i).getId()+"\t"+ warehousesList.get(i).getStoreId().getName()+"\t"+warehousesList.get(i).getImport1());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    /**
    * Test the Stateless EJB
    */
    public void testEntityEJB()
    {
        User current_user = null;
        try
        {
            Scanner sc = new Scanner(System.in);

            // Lookup the SystemManagementRemote
            MobileStoreSystemBeanRemote session = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            // Log-in
            String _username, _password;
            System.out.println("Log-in to TDT System Management");
            System.out.print("Username: ");
            _username = sc.nextLine();
            System.out.print("Password: ");
            _password = sc.nextLine();
            current_user = session.Login(_username, _password);
            if(current_user == null)
            {
                System.err.println("Wrong username/password!");
                return;
            }
            
            //Collection<Role> userRole = current_user.getRoleCollection();
            System.out.println("==================================");
            System.out.println("current user : " + current_user.getUserName() +" - "+ current_user.getRoleCollection());
            
            System.out.println("----------------------------------");

            
            // Show GUI
            int choice;
            
            do
            {
                showMainMenuGUI();
                choice = Integer.parseInt(sc.nextLine());
                switch(choice)
                {
                    case 1: //Store Management
                        Long store_id;
                        String store_name, store_address, store_phone, store_email;
                        Boolean store_status;
                        int storeChoice;
                        do{
                            showStoreMenuGUI();
                            storeChoice = Integer.parseInt(sc.nextLine());
                            switch(storeChoice)
                            {
                                case 1: // List store
                                    getAllStores();
                                    break;

                                case 2: // Add store
                                    System.out.print("Enter store name : ");
                                    store_name = sc.nextLine();
                                    System.out.print("Enter store address : ");
                                    store_address = sc.nextLine();
                                    System.out.print("Enter store phone : ");
                                    store_phone = sc.nextLine();
                                    System.out.print("Enter store email : ");
                                    store_email = sc.nextLine();
                                    Store store_created = session.insertStore(store_name, store_address, store_phone, store_email);
                                    if(store_created != null) { 
                                        System.out.print("Create Successful!");
                                    }else
                                    {
                                        System.out.print("Create Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;

                                case 3: // Update Store
                                    System.out.print("Enter store id : ");
                                    store_id = Long.parseLong(sc.nextLine());
                                    Store finded = session.findStoreById(store_id);
                                    if(finded == null)
                                    {
                                        System.out.print("The store id is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter store new name : ");
                                    store_name = sc.nextLine();
                                    System.out.print("Enter store new address : ");
                                    store_address = sc.nextLine();
                                    System.out.print("Enter store new phone : ");
                                    store_phone = sc.nextLine();
                                    System.out.print("Enter store new email : ");
                                    store_email = sc.nextLine();
                                    System.out.print("Enter store new status : ");
                                    store_status = Boolean.parseBoolean(sc.nextLine());
                                    boolean res = session.updateStore(store_id, store_name, store_address, store_phone, store_email, store_status);
                                    if(res == true)
                                    {
                                        System.out.print("Update Successful!");
                                    }else
                                    {
                                        System.out.print("Update Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;

                                case 4: // Delete store
                                    System.out.print("Enter id of store : ");
                                    store_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteStore(store_id))
                                    {
                                        System.out.print("Delete Successful!");
                                    }else
                                    {
                                        System.out.print("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                   
                                default:
                                    break;
                            }
                        } while(storeChoice != 5);
                        break;             
                                    
                    case 2: //User Management
                        Long store_id_new, user_id;
                        String user_name, user_address, user_phone, user_email, user_password, full_name, user_role;
                        Boolean user_status;
                        int userChoice;
                        
                        do{
                            showUserMenuGUI();
                            userChoice = Integer.parseInt(sc.nextLine());
                            switch(userChoice) {
                                case 1: //List User
                                    getAllUsers();
                                    break;
                                    
                                case 2: //Add User
                                    System.out.print("Enter username : ");
                                    user_name = sc.nextLine();
                                    System.out.print("Enter password : ");
                                    user_password = sc.nextLine();
                                    System.out.print("Enter full name : ");
                                    full_name = sc.nextLine();
                                    System.out.print("Enter user address : ");
                                    user_address = sc.nextLine();
                                    System.out.print("Enter user phone : ");
                                    user_phone = sc.nextLine();
                                    System.out.print("Enter store id : ");
                                    store_id_new = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter user email : ");
                                    user_email = sc.nextLine();
                                    System.out.print("Enter role name : ");
                                    user_role = sc.nextLine();

                                    User createdUser = session.insertUser(user_name, user_password, full_name, user_address, user_phone, store_id_new, user_email);
                                    session.insertUserRole(createdUser.getId(), user_role);
                                    if(createdUser != null)
                                    {
                                        System.out.println("Create Successful!");
                                    }else
                                    {
                                        System.out.println("Create Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit User
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    User user_finded = session.findUserById(user_id);
                                    if(user_finded == null)
                                    {
                                        System.out.print("The ID is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter new password : ");
                                    user_password = sc.nextLine();
                                    System.out.print("Enter new full name : ");
                                    full_name = sc.nextLine();
                                    System.out.print("Enter new address : ");
                                    user_address = sc.nextLine();
                                    System.out.print("Enter new phone : ");
                                    user_phone = sc.nextLine();
                                    System.out.print("Enter new store id : ");
                                    store_id_new = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter new email : ");
                                    user_email = sc.nextLine();
                                    System.out.print("Enter new status : ");
                                    user_status = Boolean.parseBoolean(sc.nextLine());
                                    boolean res = session.updateUser(user_id, user_password, full_name, user_address, user_phone, store_id_new, user_email, user_status);
                                    if(res == true)
                                    {
                                        System.out.println("Update Successful!");
                                    }else
                                    {
                                        System.out.println("Update Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove User
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteUser(user_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                   
                                case 5: // List Role
                                    getAllRoles();
                                    break;
                                    
                                case 6: //Add Role
                                    System.out.print("Enter role: ");
                                    user_role = sc.nextLine();
                                    session.insertRole(user_role);
                                    System.out.println("----------------------------------");
                                    break;
                                case 7: //Remove Role
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 8: //Add User Role
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter role for user : ");
                                    user_role = sc.nextLine();
                                    session.insertUserRole(user_id, user_role);
                                    System.out.println("----------------------------------");
                                    break;
                                 
                                case 9: //List User of Role
                                    System.out.print("Enter role name : ");
                                    user_role = sc.nextLine();
                                    getUsersByRole(user_role);
                                    
                                    break;
                                    
                                case 10: //list Role of User
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    getRolesByUser(user_id);
                                    break;
                                    
                                case 11: //Delete User Role
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter role for user : ");
                                    user_role = sc.nextLine();
                                    
                                    if(session.deleteUserRole(user_id, user_role))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                default:
                                    break;
                            }
                        } while(userChoice != 12);
                        
                        break;
                        
                    case 3: // Product Management
                        Long product_id, product_supplier;
                        String product_name, product_color, product_operator, product_screen, product_pin, product_description;
                        int product_camera1,product_camera2, product_memory, product_price, product_warranty;
                        Boolean product_status;
                        
                        
                        int productChoice;
                        do{
                            showProductMenuGUI();
                            productChoice = Integer.parseInt(sc.nextLine());
                            switch(productChoice)
                            {
                                case 1: //List product
                                    getAllProducts();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add product
                                    System.out.print("Enter phone name : ");
                                    product_name = sc.nextLine();
                                    System.out.print("Enter supplier id :");
                                    product_supplier = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter after camera : ");
                                    product_camera1 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter before camera : ");
                                    product_camera2 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter color : ");
                                    product_color = sc.nextLine();
                                    System.out.print("Enter memory : ");
                                    product_memory= Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter operator : ");
                                    product_operator = sc.nextLine();
                                    System.out.print("Enter warranty : ");
                                    product_warranty = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter screen : ");
                                    product_screen = sc.nextLine();
                                    System.out.print("Enter pin : ");
                                    product_pin = sc.nextLine();
                                    System.out.print("Enter price : ");
                                    product_price = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter Description : ");
                                    product_description = sc.nextLine();
                                    
                                    Product product_created = session.insertProduct(product_name, product_supplier, product_camera1, product_camera2, product_color, product_description, product_memory, product_operator, product_price, product_warranty, product_screen, product_pin);
                                    if(product_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit product
                                    System.out.print("Enter product id : ");
                                    product_id = Long.parseLong(sc.nextLine());
                                    if(session.findProductById(product_id) == null)
                                    {
                                        System.out.print("The ID is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter new phone name : ");
                                    product_name = sc.nextLine();
                                    System.out.print("Enter new supplier id :");
                                    product_supplier = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter new after camera : ");
                                    product_camera1 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new before camera : ");
                                    product_camera2 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter color : ");
                                    product_color = sc.nextLine();
                                    System.out.print("Enter new memory : ");
                                    product_memory= Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new operator : ");
                                    product_operator = sc.nextLine();
                                    System.out.print("Enter new warranty : ");
                                    product_warranty = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new screen : ");
                                    product_screen = sc.nextLine();
                                    System.out.print("Enter new pin : ");
                                    product_pin = sc.nextLine();
                                    System.out.print("Enter new price : ");
                                    product_price = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new description : ");
                                    product_description = sc.nextLine();
                                    System.out.print("Enter new status : ");
                                    product_status = Boolean.parseBoolean(sc.nextLine());
                                    
                                    boolean product_res = session.updateProduct(product_id, product_name, product_supplier, product_camera1, product_camera2, product_color, product_description, product_memory, product_operator, product_price, product_warranty, product_screen, product_pin, product_status);
                                    if(product_res) {
                                        System.out.println("Update successfull");
                                    } else {
                                        System.out.println("Update faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove product
                                    System.out.print("Enter product id : ");
                                    product_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteProduct(product_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(productChoice != 5);
                        
                        break;
                        
                    case 4: //Supplier Management
                        Long supplier_id;
                        String supplier_name, supplier_address, supplier_phone, supplier_email;
                        Boolean supplier_status, supplier_res;
                        
                        int supplierChoice;
                        do{
                            showSupplierMenuGUI();
                            supplierChoice = Integer.parseInt(sc.nextLine());
                            switch(supplierChoice)
                            {
                                case 1: //List Supplier
                                    getAllSuppliers();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add Supplier
                                    System.out.print("Enter supplier name : ");
                                    supplier_name = sc.nextLine();
                                    System.out.print("Enter supplier address : ");
                                    supplier_address = sc.nextLine();
                                    System.out.print("Enter supplier phone : ");
                                    supplier_phone = sc.nextLine();
                                    System.out.print("Enter supplier email : ");
                                    supplier_email = sc.nextLine();
                                    Supplier supplier_created = session.insertSupplier(supplier_name, supplier_address, supplier_phone, supplier_email);
                                    if(supplier_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit Supplier
                                    System.out.print("Enter supplier id : ");
                                    supplier_id = Long.parseLong(sc.nextLine());
                                    if(session.findSupplierById(supplier_id) == null)
                                    {
                                        System.out.print("The ID is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter supplier name : ");
                                    supplier_name = sc.nextLine();
                                    System.out.print("Enter supplier address : ");
                                    supplier_address = sc.nextLine();
                                    System.out.print("Enter supplier phone : ");
                                    supplier_phone = sc.nextLine();
                                    System.out.print("Enter supplier email : ");
                                    supplier_email = sc.nextLine();
                                    System.out.print("Enter supplier status : ");
                                    supplier_status = Boolean.parseBoolean(sc.nextLine());
                                    supplier_res = session.updateSupplier(supplier_id, supplier_name, supplier_address, supplier_phone, supplier_email, supplier_status);
                                    if(supplier_res) {
                                        System.out.println("Update successfull");
                                    } else {
                                        System.out.println("Update faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove Supplier
                                    System.out.print("Enter supplier id : ");
                                    supplier_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteSupplier(supplier_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(supplierChoice != 5);
                        
                        break;
                        
                    case 5: //Customer Management
                        Long customer_id;
                        String customer_fullname, customer_address, customer_phone, customer_email;
                        Boolean customer_res;
                        int customerChoice;
                        do{
                            showCustomerMenuGUI();
                            customerChoice = Integer.parseInt(sc.nextLine());
                            switch(customerChoice)
                            {
                                case 1: //List Customer
                                    getAllCustomers();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add Customer
                                    System.out.print("Enter customer name : ");
                                    customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer address : ");
                                    customer_address = sc.nextLine();
                                    System.out.print("Enter customer phone : ");
                                    customer_phone = sc.nextLine();
                                    System.out.print("Enter customer email : ");
                                    customer_email = sc.nextLine();
                                    Customer customer_created = session.insertCustomer(customer_fullname, customer_address, customer_phone, customer_email);
                                    if(customer_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit Customer
                                    System.out.print("Enter customer id : ");
                                    customer_id = Long.parseLong(sc.nextLine());
                                    if(session.findCustomerById(customer_id) == null)
                                    {
                                        System.out.print("The ID is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter customer name : ");
                                    customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer address : ");
                                    customer_address = sc.nextLine();
                                    System.out.print("Enter customer phone : ");
                                    customer_phone = sc.nextLine();
                                    System.out.print("Enter customer email : ");
                                    customer_email = sc.nextLine();
                                    customer_res = session.updateCustomer(customer_id, customer_fullname, customer_address, customer_phone, customer_email);
                                    if(customer_res) {
                                        System.out.println("Update successfull");
                                    } else {
                                        System.out.println("Update faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove Customer
                                    System.out.print("Enter customer id : ");
                                    customer_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteCustomer(customer_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(customerChoice != 5);
                        break;
                        
                    case 6: //Order Management
                        Long order_id, order_customer_id, order_staff_id, order_store_id, order_product_id;
                        BigInteger order_total;
                        String order_customer_fullname, order_customer_address, order_customer_phone, order_customer_email, order_note;
                        int orderChoice, order_next, order_quantity;
                        Store store_finded;
                        Order1 order_finded;
                        List<OrderDetail> productlist = new ArrayList();
                        do{
                            showOrderMenuGUI();
                            orderChoice = Integer.parseInt(sc.nextLine());
                            switch(orderChoice)
                            {
                                case 1: //List Order
                                    getAllOrders();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add Order
                                    System.out.print("Enter customer name : ");
                                    order_customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer address :");
                                    order_customer_address = sc.nextLine();
                                    System.out.print("Enter customer phone : ");
                                    order_customer_phone = sc.nextLine();
                                    System.out.print("Enter customer email : ");                                   
                                    order_customer_email = sc.nextLine();
                                    System.out.print("Enter store id : ");
                                    order_store_id  = Long.parseLong(sc.nextLine());
                                    store_finded = session.findStoreById(order_store_id);
                                    if(store_finded == null) {
                                        System.out.print("The store is not exist!");
                                        break;
                                    }
                                    do {
                                        System.out.print("1. Add product.\n2. Delete product.\n3. Finish.\nEnter choise : ");
                                        order_next = Integer.parseInt(sc.nextLine());
                                        if(order_next == 1) {
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            Product p = session.findProductById(order_product_id);
                                            if(p == null) {
                                                System.out.print("The product is not exist!");
                                                break;
                                            }
                                            System.out.print("Enter quantity of "+p.getName()+": ");
                                            order_quantity = Integer.parseInt(sc.nextLine());
                                            
                                            OrderDetail order = new OrderDetail();
                                            order.setProduct(p);
                                            order.setQuantity(order_quantity);
                                            order.setPrice(p.getPrice());
                                            productlist.add(order);
                                     
                                        } else if(order_next == 2) {
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            productlist.remove(new Product(order_product_id));
                                            
                                        } else {
                                            break;
                                        }
                                    }while(order_next != 3);
                                    for(int i =0; i<productlist.size(); i++) {
                                        System.out.println(i +". "+ productlist.get(i).getProduct().getName() +" "+ productlist.get(i).getPrice() +" "+ productlist.get(i).getQuantity());
                                    }
                                    System.out.print("Note : ");
                                    order_note = sc.nextLine();
                                    
                                    Order1 order_created = session.insertOrder(order_customer_fullname, order_customer_address, order_customer_phone, order_customer_email, store_finded, current_user, productlist, order_note);
                                    if(order_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit Order
                                    System.out.print("Enter order id : ");
                                    order_id = Long.parseLong(sc.nextLine());
                                    order_finded = session.findOrderById(order_id);
                                    if(order_finded == null) {
                                        System.out.println("The id is not exist!");
                                        break;
                                    }else {
                                        productlist.addAll(order_finded.getOrderDetailCollection());
                                    }
                                    System.out.print("Enter customer name : ");
                                    order_customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer address :");
                                    order_customer_address = sc.nextLine();
                                    System.out.print("Enter customer phone : ");
                                    order_customer_phone = sc.nextLine();
                                    System.out.print("Enter customer email : ");                                   
                                    order_customer_email = sc.nextLine();
                                    System.out.print("Enter store id : ");
                                    order_store_id  = Long.parseLong(sc.nextLine());
                                    store_finded = session.findStoreById(order_store_id);
                                    if(store_finded == null) {
                                        System.out.print("The store is not exist!");
                                        break;
                                    }
                                    do {
                                        System.out.print("1. Add product.\n2. Delete product.\n3. Finish.\nEnter choise : ");
                                        order_next = Integer.parseInt(sc.nextLine());
                                        if(order_next == 1) {
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            Product p = session.findProductById(order_product_id);
                                            if(p == null) {
                                                System.out.print("The product is not exist!");
                                                break;
                                            }
                                            System.out.print("Enter quantity of "+p.getName()+": ");
                                            order_quantity = Integer.parseInt(sc.nextLine());
                                            
                                            OrderDetail order = new OrderDetail();
                                            order.setProduct(p);
                                            order.setQuantity(order_quantity);
                                            order.setPrice(p.getPrice());
                                            productlist.add(order);
                                     
                                        } else if(order_next == 2) {
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            productlist.remove(new Product(order_product_id));
                                            
                                        } else {
                                            break;
                                        }
                                    }while(order_next != 3);
                                    for(int i =0; i<productlist.size(); i++) {
                                        System.out.println(i +". "+ productlist.get(i).getProduct().getName() +" "+ productlist.get(i).getPrice() +" "+ productlist.get(i).getQuantity());
                                    }
                                    System.out.print("Note : ");
                                    order_note = sc.nextLine();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove Order
                                    System.out.print("Enter order id : ");
                                    order_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteOrder(order_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(orderChoice != 5);
                        break;
                        
                    case 7: //IO Warehouse Management
                        int warehouseChoice;
                        do{
                            showIOWarehouseMenuGUI();
                            warehouseChoice = Integer.parseInt(sc.nextLine());
                            switch(warehouseChoice)
                            {
                                case 1: //List IO Warehouse
                                    getAllIOWarehouses();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add IO Warehouse
                                    
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit IO Warehouse
                                    
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove IO Warehouse
                                    
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(warehouseChoice != 5);
                        break;
                        
                    default:
                        break;
                        
                }
            } while(choice != 8);

        } catch (NamingException ex)
        {
            Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
