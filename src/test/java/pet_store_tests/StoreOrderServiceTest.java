package pet_store_tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import pet_store.entities.StoreOrder;
import pet_store.steps.storesteps.StoreOrderServiceSteps;

public class StoreOrderServiceTest {
    private static Response response;
    private static StoreOrder order;

    //Place an order for a pet
    @Test
    public void placeOrderTest() {
        order = createStoreOrderBody();
        response = StoreOrderServiceSteps.placeOrder(order);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    //Returns pet inventory by status
    @Test (dependsOnMethods = "placeOrderTest")
    public void getStoreOrderByInventory(){
        response = StoreOrderServiceSteps.getStoreOrderInventory();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    //Find purchase order by id
    @Test(dependsOnMethods = "placeOrderTest")
    public void findOrderByIdTest() {
        order = createStoreOrderBody();
        response = StoreOrderServiceSteps.placeOrderById(order.getId());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "This is not the expected status code !");
    }

    //Delete purchase order by id
    @Test(dependsOnMethods = "findOrderByIdTest")
    public void deleteOrderByIdTest() {
        order = createStoreOrderBody();
        response = StoreOrderServiceSteps.placeOrder(order);

        StoreOrderServiceSteps.deleteOrderById(order.getId());
        response = StoreOrderServiceSteps.getOrderByIdAfterDelete(order.getId());

        Assert.assertEquals(response.getStatusCode(), 404, "This is not the expected status code !");
    }

    private StoreOrder createStoreOrderBody() {
        return new StoreOrder()
                .setId(10)
                .setPetId(100)
                .setQuantity(1)
                .setShipDate("2022-10-31T07:13:05.633Z")
                .setStatus("placed")
                .setComplete(true);
    }
}
