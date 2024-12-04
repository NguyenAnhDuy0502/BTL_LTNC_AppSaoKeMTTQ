package BTL_LTNC.Model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ChanningHashMap {
    List<List<Transaction>> hashMap = new ArrayList<>(10000);

    public ChanningHashMap() {
        for (int i = 0; i < 10000; i++)
            hashMap.add(new ArrayList<>());
    }

    public void add(Transaction transaction) {
        hashMap.get(hash(transaction.transNo)).add(transaction);
    }

    public int hash(int value) {
        return value % 10000;
    }

    public List<Transaction> getTransactionsByTransactionNo(int value) {
        List<Transaction> findedTransaction = new ArrayList<>();

        for (List<Transaction> transactions : getHashMap()) {
            for (Transaction transaction : transactions) {
                if(!transaction.isDeleted && transaction.transNo == value)
                    findedTransaction.add(transaction);
            }
        }
        return findedTransaction;
    }

    public void deleteTransactionByTransactionNo(int value) {
        List<Transaction> findedList = getTransactionsByTransactionNo(value);
        if(!findedList.isEmpty())
            findedList.getFirst().isDeleted = true;
    }

    public List<Transaction> convertToOneWayList() {
        return hashMap.stream()
                .flatMap(List::stream)
                .filter(transaction -> !transaction.isDeleted())
                .collect(Collectors.toList());
    }
}
