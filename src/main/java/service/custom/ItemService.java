package service.custom;

import javafx.collections.ObservableList;
import model.Item;
import model.Supplier;
import service.SuperService;

public interface ItemService extends SuperService {
    boolean add(Item item);
    boolean edit(Item item);
    boolean delete(String id);
    Item search(String id);
    ObservableList<Item> getAll();

    String getID();
    ObservableList<String> getIDs();
}
