package pet_store.service.storeservice;

import io.restassured.response.Response;
import pet_store.service.CommonService;
import pet_store.service.uritemplate.UriTemplate;

public class StoreOrderService extends CommonService {
    public StoreOrderService(){}
    private static StoreOrderService instance;
    public static StoreOrderService getInstance() {
        if (instance == null) {
            instance = new StoreOrderService();
        }
        return instance;
    }
    public Response getRequest(UriTemplate uri) {
        return super.getRequest(uri.getUri());
    }

    public Response getRequestById(UriTemplate uri, int id) {
        return super.getRequest(uri.getUri(id));
    }

    public Response getRequestByUsername(UriTemplate uri, String username) {
        return super.getRequest(uri.getUri(username));
    }

    public Response getRequestAfterDelete(UriTemplate uri, int id) {
        return super.getRequestAfterDelete(uri.getUri(id));
    }

    public Response getRequestAfterDelete(UriTemplate uri, String username) {
        return super.getRequestAfterDelete(uri.getUri(username));
    }

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public void deleteRequestById(UriTemplate uri, int id) {
        super.deleteRequest(uri.getUri(id));
    }

    public void deleteRequestByUsername(UriTemplate uri,String username){
        super.deleteRequest(uri.getUri(username));
    }
}
