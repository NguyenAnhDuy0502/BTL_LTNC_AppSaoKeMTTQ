package BTL_LTNC.Controller;

import BTL_LTNC.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private List<Transaction> transList;

    @GetMapping("/all")
    public List<Transaction> getAll() {
        return transList;
    }

    @GetMapping("/search")
    public List<Transaction> search(
            @RequestParam(value = "Date", required = false) String date,
            @RequestParam(value = "TransactionNo", required = false) Integer transactionNo,
            @RequestParam(value = "Credit", required = false) Double credit,
            @RequestParam(value = " Debit", required = false) Double debit,
            @RequestParam(value = "Detail", required = false) String detail
    ) {
        List<Transaction> filledTransList = new ArrayList<>();
        for (Transaction transaction : transList) {

            //Kiểm tra chỉ cần 1 keyword trong bất kì trường nào không khớp nhảy sang vòng lặp tiếp
            if (!date.isEmpty() && !transaction.getDateTime().toLowerCase().contains(date.trim().toLowerCase())) continue;
            if (transactionNo != null && transaction.getTransNo() != transactionNo) continue;
            if (credit != null && transaction.getCredit() != credit) continue;
            if (debit != null && transaction.getDebit() != debit) continue;
            if (!detail.isEmpty() && !transaction.getDetail().toLowerCase().contains(detail.trim().toLowerCase())) continue;

            filledTransList.add(transaction);
        }
        return filledTransList;
    }

    @GetMapping("/searchCredit")
    public List<Transaction> searchCredit(
            @RequestParam(value = "MinCredit", required = false) Integer minCredit,
            @RequestParam(value = "MaxCredit", required = false) Integer maxCredit
    ) {
        List<Transaction> filledTransList = new ArrayList<>();
        for (Transaction transaction : transList) {

            if (minCredit == null && maxCredit == null) return transList;
            else if (minCredit != null && maxCredit != null &&
                    (transaction.getCredit() < minCredit || transaction.getCredit() > maxCredit)) continue;
            else if (minCredit != null && transaction.getCredit() < minCredit) continue;
            else if (maxCredit != null && transaction.getCredit() > maxCredit) continue;

            filledTransList.add(transaction);
        }
        return filledTransList;
    }
}





