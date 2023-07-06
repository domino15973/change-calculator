import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ChangeCalculator {
    private static List<Money> inventory;

    public static void main(String[] args) {
        initializeInventory();
        calculateChange();
    }

    public static void initializeInventory() {
        inventory = new ArrayList<>();
        inventory.add(new Money(500, 1));    // 5 zł = 500 gr
        inventory.add(new Money(200, 3));    // 2 zł = 200 gr
        inventory.add(new Money(100, 5));    // 1 zł = 100 gr
        inventory.add(new Money(50, 10));    // 50 gr
        inventory.add(new Money(20, 20));    // 20 gr
        inventory.add(new Money(10, 200));   // 10 gr
        inventory.add(new Money(5, 100));    // 5 gr
        inventory.add(new Money(2, 100));    // 2 gr
        inventory.add(new Money(1, 10000));  // 1 gr
        inventory.sort(Comparator.reverseOrder());
    }

    public static void calculateChange() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter the change amount (in zł): ");
            BigDecimal amount;
            try {
                amount = scanner.nextBigDecimal();
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Invalid amount. Exiting the program.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid amount format. Please enter a valid amount.");
                scanner.nextLine(); // Clear input buffer
                continue;
            }

            int remainingAmount = amount.multiply(BigDecimal.valueOf(100)).intValue();

            System.out.println("For the change of " + amount + " zł:");

            boolean insufficientChange = false;

            for (Money money : inventory) {
                int denomination = money.getDenomination();
                int numCoins = remainingAmount / denomination;

                if (numCoins > money.getQuantity()) {
                    numCoins = money.getQuantity();
                }

                if (numCoins > 0) {
                    System.out.println("Dispense " + numCoins + " coin(s) of " + denomination / 100.0 + " zł");
                    remainingAmount -= numCoins * denomination;
                    money.setQuantity(money.getQuantity() - numCoins);
                }

                if (remainingAmount == 0) {
                    break;
                }

                if (remainingAmount < denomination && money == inventory.get(inventory.size() - 1)) {
                    insufficientChange = true;
                    break;
                }
            }

            if (insufficientChange) {
                System.out.println("Insufficient change available. Exiting the program.");
                break;
            }

        } while (true);

        scanner.close();
    }
}

class Money implements Comparable<Money> {
    private int denomination;
    private int quantity;

    public Money(int denomination, int quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Money other) {
        return Integer.compare(this.denomination, other.denomination);
    }
}