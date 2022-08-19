package com.logisticcompany.team4;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.CustomerRepository;
import com.logisticcompany.team4.repository.RoleRepository;
import com.logisticcompany.team4.repository.UserRepository;
import com.logisticcompany.team4.services.CustomerServices;
import com.logisticcompany.team4.services.UserServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServices_Test {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private RoleRepository roleRepository;


    private User FirstUser;
    private User SecondUser;

    @BeforeEach
    public void init(){
        FirstUser = userRepository.findById(1).get();
        SecondUser = userRepository.findById(2).get();
    }

    @Test
    public void getUsersTest(){
        List<User> expectedListOfUsers=new ArrayList<>();
        expectedListOfUsers.add(FirstUser);
        expectedListOfUsers.add(SecondUser);
        Assertions.assertEquals(expectedListOfUsers.size()+8,userServices.findAll().size());

    }

    @Test
    public void addUserTest(){
        User toAddUser=new User(0, "Angel", "Gerdjikov", "d.g@gmail.com", "gg_user","password", true, roleRepository.getById(1), null, null);
        userServices.addUser(toAddUser);
        Assertions.assertEquals(userServices.findAll().size(),10);
    }

    @Test
    public void getUserTest(){
        User user=FirstUser;
        User user1=userServices.findUserById(FirstUser.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
        Assertions.assertEquals(user.getFirstName(),user1.getFirstName());
    }
    @Test
    public void UpdateUserTest() throws Exception{
        User toUpdateUser=new User(2, "Angel", "Dobrev", "d.g@gmail.com", "gg_user","password", true, roleRepository.getById(2), null, null);
        userServices.updateUser(toUpdateUser);
        Assertions.assertEquals("Dobrev",userServices.findUserById(SecondUser.getId()).getLastName());
    }
            @Test
        public void deleteUserTest() throws Exception{
            User user=userRepository.save(new User(0, "Pesho", "Goshov", "h.g@gmail.com", "yy_user","password", true, roleRepository.getById(2), null, null));
            userServices.deleteUser(user.getId());
            Assertions.assertFalse(userServices.findAll().contains(user));
        }

}
