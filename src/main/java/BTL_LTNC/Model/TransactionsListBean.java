package BTL_LTNC.Model;

import com.opencsv.CSVReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TransactionsArrayBean {

    @Bean
    public List<Transaction> createArrayTransactions()
    {
        String csvFile = "src/main/resources/chuyen_khoan.csv"; // Đường dẫn đến tệp CSV
        List<Transaction> transactions = new ArrayList<>(); // Mảng chứa các đối tượng Transaction

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                // Giả sử các cột trong CSV theo thứ tự: dateTime, transNo, credit, debit, detail
                String dateTime = nextLine[0];
                String transNo = nextLine[1];
                double credit = Double.parseDouble(nextLine[2]);
                double debit = Double.parseDouble(nextLine[3]);
                String detail = nextLine[4];

                // Tạo đối tượng Transaction từ dòng CSV
                Transaction transaction = new Transaction(dateTime, transNo, credit, debit, detail);

                // Thêm đối tượng Transaction vào mảng
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
