package scratch;

public class Account {
        private int balance;
        private String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

}
