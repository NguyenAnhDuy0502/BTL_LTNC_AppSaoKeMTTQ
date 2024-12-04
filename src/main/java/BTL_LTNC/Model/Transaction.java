package BTL_LTNC.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
        String dateTime;
        int transNo;
        double credit;
        double debit;
        String detail;
        boolean isDeleted;

        public Transaction(String dateTime, int transNo, double credit, double debit, String detail) {
            this.dateTime = dateTime;
            this.transNo = transNo;
            this.credit = credit;
            this.debit = debit;
            this.detail = detail;
            this.isDeleted = false;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "dateTime='" + dateTime + '\'' +
                    ", transNo='" + transNo + '\'' +
                    ", credit=" + credit +
                    ", debit=" + debit +
                    ", detail='" + detail + '\'' +
                    '}';
        }
    }

