package pet_store.steps.storesteps;

import io.restassured.response.Response;
import pet_store.entities.StoreOrder;
import pet_store.service.storeservice.StoreOrderService;

import static pet_store.service.uritemplate.storeuritemplate.StoreOrderServiceUri.*;

public class StoreOrderServiceSteps {
    private static final StoreOrderService STORE_ORDER_SERVICE = StoreOrderService.getInstance();

    public static Response placeOrder(StoreOrder order) {
        return STORE_ORDER_SERVICE.postRequest(STORE_ORDER, order);
    }

    public static Response placeOrderById(int id) {
        return STORE_ORDER_SERVICE.getRequestById(STORE_ORDER_BY_ID, id);
    }

    public static Response getOrderByIdAfterDelete(int id){
        return STORE_ORDER_SERVICE.getRequestAfterDelete(STORE_ORDER_BY_ID, id);
    }

    public static void deleteOrderById(int id){
        STORE_ORDER_SERVICE.deleteRequestById(STORE_ORDER_BY_ID, id);
    }

    public static Response getStoreOrderInventory(){
        return STORE_ORDER_SERVICE.getRequest(STORE_ORDER_INVENTORY);
    }
}
