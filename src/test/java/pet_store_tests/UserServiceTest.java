package pet_store_tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import pet_store.entities.User;
import pet_store.steps.usersteps.UserServiceSteps;

import java.util.ArrayList;

public class UserServiceTest {
    private static Response response;
    private static User user;

    //Create list of users with given input array
    @Test
    public void createUserArrayListTest() {
        ArrayList<User> users = createUserListBody();
        response = UserServiceSteps.createUserList(users);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    //Get user by username
    @Test
    public void getUsernameTest() {
        response = UserServiceSteps.getUsername();
        Assert.assertEquals(response.as(User.class).getUsername(), "TestAPI");
    }

    //Logs user into the system
    @Test
    public void getLoginTest() {
        response = UserServiceSteps.getUserLogin();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    //Logs out current logged-in user session
    @Test
    public void getUserLogoutTest() {
        response = UserServiceSteps.getUserLogout();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    //Create user
    @Test
    public void createUserTest() {
        user = createUserBody();
        response = UserServiceSteps.createUser(user);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    //Delete user
    @Test(dependsOnMethods = "createUserTest")
    public void deleteUserByUsernameTest() {
        user = createUserBody();

        UserServiceSteps.deleteUserByUsername(user.getUsername());
        response = UserServiceSteps.getUserByUsernameAfterDelete(user.getUsername());

        Assert.assertEquals(response.getStatusCode(), 404, "This is not the expected status code !");
    }

    //Update user
    @Test
    public void updateUserTest(){
        user = createUserBody();
        response = UserServiceSteps.updateUser(user);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    private User createUserBody() {
        return new User()
                .setId(700)
                .setUsername("apitest")
                .setFirstname("api")
                .setLastname("test")
                .setEmail("api.test@gmail.com")
                .setPassword("qweTY09")
                .setPhone("0767890123")
                .setUserStatus(200);
    }

    private ArrayList<User> createUserListBody() {
        ArrayList<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(new User().setId(100)
                .setUsername("TestAPI")
                .setFirstname("Test")
                .setLastname("API")
                .setEmail("Test_API@gmail.com")
                .setPassword("Asdfghj123")
                .setPhone("0782700926")
                .setUserStatus(200));
        return listOfUsers;
    }
}
